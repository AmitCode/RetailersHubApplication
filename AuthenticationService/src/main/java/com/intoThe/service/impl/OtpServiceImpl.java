package com.intoThe.service.impl;

import com.intoThe.dto.response.OtpServiceResponse;
import com.intoThe.entities.EntityVerificationToken;
import com.intoThe.entities.OtpEntity;
import com.intoThe.entities.Users;
import com.intoThe.enums.OtpTypes;
import com.intoThe.enums.TokenExpirationUnit;
import com.intoThe.exceptions.SuppliersOprException.OtpValidationException;
import com.intoThe.exceptions.SuppliersOprException.ResourceNotFoundException;
import com.intoThe.mapper.VerificationTokenModelMapper;
import com.intoThe.repository.EntityVerificationTokenRepository;
import com.intoThe.repository.OtpRepository;
import com.intoThe.repository.UserRepository;
import com.intoThe.service.OtpService;
import com.intoThe.utils.HashUtils;
import com.intoThe.utils.OtpUtils;
import com.intoThe.utils.VerificationTokenUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OtpServiceImpl implements OtpService {

    private final OtpRepository otpRepository;
    private final UserRepository userRepository;
    private final EntityVerificationTokenRepository verificationTokenRepository;


    @Value("${otp.retry.count}")
    private int maxOtpRetryCount;
    @Value("${otp.expiry.time.duration.unit}")
    private String otpExpiryTimeUnit;
    @Value("${otp.expiry.time.duration}")
    private int otpValidDuration;
    @Value("${verification.token.expiry.time.unit}")
    private TokenExpirationUnit verificationExpiryTimeUnit;
    @Value("${verification.token.expiry.time}")
    private int tokenValidDuration;

    public OtpServiceImpl(OtpRepository otpRepository, UserRepository userRepository,
                          EntityVerificationTokenRepository verificationTokenRepository){
        this.otpRepository = otpRepository;
        this.userRepository = userRepository;
        this.verificationTokenRepository = verificationTokenRepository;
    }

    @Transactional(noRollbackFor = OtpValidationException.class)
    @Override
    public ResponseEntity<OtpServiceResponse> validateOtp(String email, String otp) {

        OtpEntity storedOtp = otpRepository.findByUserEmailAndIsValidAndIsVerified(email, true, false)
                .orElseThrow(()-> new OtpValidationException("OTP does not exist or expired!"));

        validateRetryLimit(storedOtp);
        validateOtpMatch(storedOtp, otp);
        validateVerified(storedOtp);
        validateExpiry(storedOtp);

        String token = VerificationTokenUtils.generateVerificationToken();
        String hashToken = HashUtils.getSHA256Hash(token);
        EntityVerificationToken verificationToken = VerificationTokenModelMapper.getVerificationToken(
                hashToken, "SHA-256", storedOtp.getUserId(), storedOtp.getUserEmail(),
                tokenValidDuration, verificationExpiryTimeUnit

        );

        storedOtp.setIsOtpVerified(true);
        storedOtp.setValid(false);
        otpRepository.save(storedOtp);
        verificationTokenRepository.save(verificationToken);
        OtpServiceResponse response = OtpServiceResponse.createOtpResponse()
                .setStatus(HttpStatus.OK.toString())
                .setMessageToken(hashToken);
        return new ResponseEntity<>(response, HttpStatus.OK);
        //return null;
    }

    @Transactional
    @Override
    public ResponseEntity<OtpServiceResponse> generateOtp(String email){

        OtpServiceResponse otpServiceResponse;
        String otp = "";
        Optional<OtpEntity> optionalOtp;

        optionalOtp = otpRepository.
                findByUserEmailAndIsValidAndIsVerified(email, true, false);
        if(optionalOtp.isPresent()){
            throw new OtpValidationException("Otp already present, Please check your SMS!");
        }

        optionalOtp = otpRepository.
                findByUserEmailAndIsValidAndIsVerified(email, false, true);

        if(optionalOtp.isPresent()){
            OtpEntity otpEntity = optionalOtp.get();
            otpEntity.setIsOtpVerified(false);
            otpEntity.setValid(true);
            otpEntity.setOptRetry(0);
            otpRepository.save(otpEntity);
            otp = otpEntity.getOtp();
        }else{
            Users users = userRepository.findByUserEmail(email)
                    .orElseThrow(()-> new ResourceNotFoundException("Resource not found!..."));
            otp = OtpUtils.generateOtp();
            OtpEntity otpEntity = OtpEntity.getOtpEntity()
                    .setUserName(users.getUserName())
                    .setOtpTypes(OtpTypes.EMAIL_OTP_VERIFICATION)
                    .setUserId(users.getUserId())
                    .setOpt(otp)
                    .setUserEmail(users.getUserEmail())
                    .setOtpValidDuration(otpValidDuration)
                    .setOtpValidDurationUnit(otpExpiryTimeUnit);

            otpRepository.save(otpEntity);
        }


        otpServiceResponse = OtpServiceResponse.createOtpResponse()
                .setOtp(otp)
                .setStatus(HttpStatus.OK.toString());

        return new ResponseEntity<>(otpServiceResponse, HttpStatus.OK);
    }

    @Transactional(noRollbackFor = OtpValidationException.class)
    @Override
    public String validateOtpAuth(String otp) {

        OtpEntity storedOtp = otpRepository.findByOtpAndIsValid(otp, true)
                .orElseThrow(()-> new OtpValidationException("OTP does not exist or expired!"));

        validateRetryLimit(storedOtp);
        validateOtpMatch(storedOtp, otp);
        validateVerified(storedOtp);
        validateExpiry(storedOtp);

        String token = VerificationTokenUtils.generateVerificationToken();
        String hashToken = HashUtils.getSHA256Hash(token);
        EntityVerificationToken verificationToken = VerificationTokenModelMapper.getVerificationToken(
                hashToken, "SHA-256", storedOtp.getUserId(), storedOtp.getUserEmail(),
                tokenValidDuration, verificationExpiryTimeUnit

        );

        storedOtp.setIsOtpVerified(true);
        storedOtp.setValid(false);
        otpRepository.save(storedOtp);
        return storedOtp.getUserName();
    }

//    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void incrementOtpRetry(OtpEntity otp) {
        otp.setOptRetry(otp.getOptRetry() + 1);
        otpRepository.save(otp);
    }

    private void validateRetryLimit(OtpEntity otpEntity){
        if(otpEntity.getOptRetry() >=  maxOtpRetryCount){

            otpEntity.setValid(false);
            otpRepository.save(otpEntity);

            throw new OtpValidationException("You have exceeded the Max Retry Count, " +
                    "Please re-generate otp!...");
        }
    }

    private void validateOtpMatch(OtpEntity otpEntity, String otp){
        if(!otpEntity.getOtp().equalsIgnoreCase(otp)){
            incrementOtpRetry(otpEntity);
            throw new OtpValidationException("You have entered wrong otp!...");
        }
    }

    private void validateVerified(OtpEntity otpEntity){
        if (otpEntity.isVerified())
            throw new OtpValidationException("Otp is already verified!...");
    }

    private void validateExpiry(OtpEntity storedOtp) {

        if (!VerificationTokenUtils.isTokenExpired(storedOtp.getOtpGeneratedAt(), storedOtp.getOtpValidDuration(),
                storedOtp.getOtpValidDurationUnit())) {

            storedOtp.setValid(false);
            otpRepository.save(storedOtp);

            throw new OtpValidationException("Oto has expired, Please re-generate otp!...");
        }
    }

}
