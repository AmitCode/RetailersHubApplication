package com.intoThe.service.impl;

import com.intoThe.dto.UserDTO;
import com.intoThe.dto.request.EmailRequest;
import com.intoThe.dto.request.PasswordResetRequest;
import com.intoThe.dto.request.UserRegistrationRequest;
import com.intoThe.dto.request.UserUpdateRequest;
import com.intoThe.dto.response.AuthenticationServiceResponse;
import com.intoThe.dto.response.EmailServiceResponse;
import com.intoThe.entities.EntityVerificationToken;
import com.intoThe.entities.OtpEntity;
import com.intoThe.entities.Users;
import com.intoThe.enums.OtpTypes;
import com.intoThe.enums.TokenExpirationUnit;
import com.intoThe.exceptions.SuppliersOprException.*;
import com.intoThe.mapper.UserDataModelMapper;
import com.intoThe.mapper.VerificationTokenModelMapper;
import com.intoThe.repository.EntityVerificationTokenRepository;
import com.intoThe.repository.OtpRepository;
import com.intoThe.repository.UserRepository;
import com.intoThe.service.UserService;
import com.intoThe.service.WebClientServices;
import com.intoThe.utils.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final EntityVerificationTokenRepository tokenRepository;
    private final OtpRepository otpRepository;
    private final WebClient notificationServiceWebClient;
    private final WebClient userServiceWebClient;
    private final EntityManager entityManager;
    //private final RestTemplate restTemplate;
    private final UserDataModelMapper userModelMapper = new UserDataModelMapper();
    AuthenticationServiceResponse response;

    @Value("${verification.token.expiry.time.unit}")
    private TokenExpirationUnit verificationExpiryTimeUnit;
    @Value("${verification.token.expiry.time}")
    private int tokenValidDuration;

    //@Autowired ----> // @Autowired needed — ONLY ONE constructor
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
                           @Qualifier("notificationServiceWebClient") WebClient webClient,
                           @Qualifier("userServiceWebClient") WebClient userServiceWebClient,
                           EntityVerificationTokenRepository tokenRepository,
                           EntityManager entityManager,
                           OtpRepository otpRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.notificationServiceWebClient = webClient;
        this.tokenRepository = tokenRepository;
        this.userServiceWebClient = userServiceWebClient;
        this.entityManager = entityManager;
        this.otpRepository = otpRepository;
        //this.restTemplate = restTemplate;
    }

    /**
     * This method is used to add a new user to the database.
     *
     * @param userDTO The {@link UserDTO} object containing the details of the user to be added.
     * @return A string indicating that the data has been saved successfully.
     */
    /*@Transactional:-
    * This annotation only works inside ONE database transaction.
    * This will only control the transaction within the auth service.
    * This will not roll back if the user service or notification service api call fails.\
    * for this we will have to use Kafka or RabbitMQ for distributed transaction management.*/
//    @Override
//    @Transactional
//    public ResponseEntity<?> addUser(UserDTO userDTO) {
//        userDTO.setUserPassword(passwordEncoder.encode(userDTO.getUserPassword()));
////            synchronized (this){
////
////            }
//
////            if(UserUtils.isUserNameAlreadyExist(userDTO.getUserName(), userRepository)){
////                System.out.println("Username: " + userDTO.getUserName());
////                throw  new UserNameAlreadyExist("User with this user name already exist!...");
////            }else{
////
////                Users newUser = userRepository.save(userModelMapper.mapToUser(userDTO));
////                //AuthenticationServiceResponse response1 = restTemplate.post
////                try{
////                    response = webClient.post()
////                            .uri("/usersOpr/v1/addNewUser")
////                            .bodyValue(userDTO)
////                            .retrieve()
////                            .bodyToMono(AuthenticationServiceResponse.class)
////                            .block();
////
////                    response.setResponseMsg("User created successfully!...")
////                            .setIsOprSuccess(true)
////                            .setStatusCode(HttpStatus.CREATED.toString());
////                }catch (WebClientResponseException){
////                    return ResponseEntity
////                            .status(webClientResponseException.getStatusCode())
////                            .contentType(MediaType.APPLICATION_JSON)
////                            .body(webClientResponseException.getResponseBodyAsString());
////                }
////            }
//            /*
//            Commented the above code for handling the race conditions issue.so i had 3 3 options to handle this.
//            Option1:- synchronized(this)
//            i haven't used this because :-> it only works per JVM instance, not across:
//                1.multiple app instances (microservices, scaling).
//                2.multiple nodes
//            Option2:- synchronized(ClassName.class)
//            we will not use this because ->
//                1.Global lock across all threads.
//                2.Kills throughput under load.
//                3.Still doesn’t work in distributed systems
//            Option3:- Correct Approach (Production Standard)
//                1. Enforce uniqueness at DB level(this is mandatory)
//                  ---> @Column(unique = true)
//                        private String userName;
//                2. Remove manual check -> UserUtils.isUserNameAlreadyExist(...)
//                3. Handle exception instead as i did below
//                   This is:-
//                    1.atomic.
//                    2.thread-safe.
//                    3.works across distributed systems.
//            */
//        if(UserUtils.isUserNameAlreadyExist(userDTO.getUserName(), userRepository)){
//            throw new UserNameAlreadyExist("User already exists with this username!...");
//        }else if (UserUtils.isUserExistWithEmail(userDTO.getUserEmail(), userRepository)){
//            throw new EmailIdAlreadyExist("User already exists with the Email!...");
//        }else {
//            try {
//
//                Users newUser = userRepository.save(userModelMapper.mapToUser(userDTO));
//                //Calling userService(microservice) for creating the new user.
//                ResponseEntity<AuthenticationServiceResponse> responseEntity = WebClientServices.callUserService(userDTO, webClient);
//                if(responseEntity.getStatusCode().equals(HttpStatus.CREATED)){
//                    response = new AuthenticationServiceResponse();
//                    response.setResponseMsg("User created successfully!...")
//                            .setIsOprSuccess(true)
//                            .setStatusCode(HttpStatus.CREATED.toString());
//                }else{
//                    return responseEntity;
//                }
//            } catch (DataIntegrityViolationException ex) {
//                throw new UserNameAlreadyExist("User with this user name already exist!...");
//            } catch (WebClientResponseException webClientResponseException) {
//                return ResponseEntity
//                        .status(webClientResponseException.getStatusCode())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .body(webClientResponseException.getResponseBodyAsString());
//            }
//            return new ResponseEntity<>(response, HttpStatus.CREATED);
//        }
//    }

@Override
@Transactional
public ResponseEntity<?> addUser(UserRegistrationRequest registrationRequest) {

    if(UserUtils.isUserNameAlreadyExist(registrationRequest.getUserName(), userRepository)){
        throw new UserNameAlreadyExist("User already exists with this username!...");

    }else if (UserUtils.isUserExistWithEmail(registrationRequest.getUserEmail(), userRepository)){
        throw new EmailIdAlreadyExist("User already exists with the Email!...");

    }else {
        try {

            registrationRequest.setUserPassword(passwordEncoder.encode(registrationRequest.getUserPassword()));

            Users mappedUser = UserDataModelMapper.mapToUser(registrationRequest);
            mappedUser.setCreatedBy(registrationRequest.getUserName());
            Users newUser = userRepository.save(mappedUser);

            String token = VerificationTokenUtils.generateVerificationToken();
            String hashToken = HashUtils.getSHA256Hash(token);
            EntityVerificationToken verificationToken = VerificationTokenModelMapper.getVerificationToken(
                    hashToken, "SHA-256", newUser.getUserId(), newUser.getUserName(),
                    tokenValidDuration, verificationExpiryTimeUnit
            );

            tokenRepository.save(verificationToken);
            EmailRequest emailRequest = AuthServiceUtils.prepareEmailRequest(newUser, hashToken, "REG",
                    "Verify Your User Account");
            ResponseEntity<EmailServiceResponse> responseEntity = WebClientServices
                    .callEmailNotificationService("email/sendMail", emailRequest, notificationServiceWebClient);

            response = AuthenticationServiceResponse.createResponse()
                    .setResponseMsg("User created successfully!...")
                    .setIsOprSuccess(true)
                    .setStatusCode(HttpStatus.CREATED.toString());

        } catch (DataIntegrityViolationException ex) {
            throw new UserNameAlreadyExist("User with this user name already exist!...");
        } catch (WebClientResponseException webClientResponseException) {
            return ResponseEntity
                    .status(webClientResponseException.getStatusCode())
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(webClientResponseException.getResponseBodyAsString());
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}

    /**
     * This method is used to update an existing user in the database.
     *
     * @param userDTO The {@link UserDTO} object containing the details of the user to be updated.
     * @return The updated {@link UserDTO} object.
     */
    @Transactional
    @Override
    public String updateUser(UserUpdateRequest updateRequest){

        String userName = updateRequest.getUserName();
        Users users = UserUtils.isUserExist(userName,userRepository);
        users.setUserName(updateRequest.getUserName());
        users.setUpdatedBy(userName);
        users = userRepository.save(users);

        return "User update operation completed successfully.";
    }

    /**
     * This method is used to delete an existing user from the database.
     *
     * @param userName The ID of the user to be deleted.
     * @return A string indicating that the user has been successfully deleted.
     */
    @Transactional
    @Override
    public ResponseEntity<AuthenticationServiceResponse> deleteUser(String userName) {
        Users users = UserUtils.isUserExist(userName,userRepository);

        ResponseEntity<AuthenticationServiceResponse> authenticationServiceResponse = WebClientServices
                .callUserServiceDeleteUser("/deleteUser", userName, userServiceWebClient);

        if(authenticationServiceResponse.getStatusCode().is2xxSuccessful()){
            userRepository.deleteByUserName(userName);
            response = AuthenticationServiceResponse.createResponse()
                    .setStatusCode(HttpStatus.ACCEPTED.toString())
                    .setResponseMsg("User has been deleted successfully!...")
                    .setIsOprSuccess(true);
        }else{
            response = AuthenticationServiceResponse.createResponse()
                    .setStatusCode(authenticationServiceResponse.getBody().getStatusCode())
                    .setResponseMsg(authenticationServiceResponse.getBody().getResponseMsg())
                    .setIsOprSuccess(false);
        }

        return new ResponseEntity<> (response, HttpStatus.ACCEPTED);
    }

    /**
     * This method is used to activate or deactivate a user in the database.
     *
     * @param userName The ID of the user to be activated or deactivated.
     * @param isActive A string that is case-insensitive and can only be "Y" for activation or "N" for deactivation.
     * @return A string indicating that the user has been successfully activated or deactivated.
     * @throws IllegalArgumentException if the isActive parameter is neither "Y" nor "N".
     */
    @Transactional
    @Override
    public ResponseEntity<AuthenticationServiceResponse> activateOrDeactivate(String userName, Boolean isActive) {
        Users users = UserUtils.isUserExist(userName,userRepository);
        users.setIsUserActive(isActive);
        users.setUpdatedBy(userName);

        ResponseEntity<AuthenticationServiceResponse> authenticationServiceResponse = WebClientServices
                .callUserServiceActiveAndDeActivate("/activateOrDeactivateUser", userName, isActive, userServiceWebClient);

        if(authenticationServiceResponse.getStatusCode().is2xxSuccessful()){
            userRepository.deleteByUserName(userName);
            response = AuthenticationServiceResponse.createResponse()
                    .setStatusCode(HttpStatus.ACCEPTED.toString())
                    .setResponseMsg((isActive) ? "User has been deleted successfully!..."
                            : "User has been Deactivated!...")
                    .setIsOprSuccess(true);
        }else{
            response = AuthenticationServiceResponse.createResponse()
                    .setStatusCode(authenticationServiceResponse.getBody().getStatusCode())
                    .setResponseMsg(authenticationServiceResponse.getBody().getResponseMsg())
                    .setIsOprSuccess(false);
        }

        return new ResponseEntity<> (response, HttpStatus.ACCEPTED);
    }

    /**
     * This method is used to get the user information for a given userId.
     *
     * @param userName The userId of the user to get the information for.
    /**
     *

     * @return A list of {@link UserDTO} objects representing all users in the database.

     * @return A {@link UserDTO} object containing the information of the user.
     */
    @Transactional
    @Override
    public UserDTO getUserInfo(String userName) {
        return UserDataModelMapper.mapToUserDTO(UserUtils.isUserExist(userName,userRepository));
    }

    /**
     * This method is used to get the user information for all users in the database.
     *
     * @return A list of {@link UserDTO} objects representing all users in the database.
     */
    @Transactional
    @Override
    public List<UserDTO> getAllUsers() {
        List<Users> usersList = userRepository.findAll();
        return userModelMapper.mapToListOfUserDTO(usersList);
    }

    @Override
    public ResponseEntity<AuthenticationServiceResponse> passwordResetRequest(String userEmail){
        try{
            String token = VerificationTokenUtils.generateVerificationToken();
            String hashToken = HashUtils.getSHA256Hash(token);

            Optional<Users> usersOptional = userRepository.findByUserEmail(userEmail);
            if(usersOptional.isPresent()){
                Users users = usersOptional.get();
                EntityVerificationToken verificationToken = VerificationTokenModelMapper.getVerificationToken(
                        hashToken, "SHA-256", users.getUserId(), users.getUserName(),
                        tokenValidDuration, verificationExpiryTimeUnit
                );
                tokenRepository.save(verificationToken);

                EmailRequest emailRequest = AuthServiceUtils.prepareEmailRequest(users, hashToken,
                        "PASS-RESET", "Reset your password for your candidate account");
                ResponseEntity<EmailServiceResponse> responseEntity = WebClientServices
                        .callEmailNotificationService("email/sendMail", emailRequest, notificationServiceWebClient);
            }

        }catch (Exception exception) {
            throw new InternalServerErrorException("[Auth Service]-{passwordReset} Ex- "
                    +exception.getMessage());
        }finally {
            response = AuthenticationServiceResponse.createResponse()
                    .setIsOprSuccess(true)
                    .setResponseMsg("")
                    .setStatusCode(HttpStatus.ACCEPTED.toString());
        }
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    public ResponseEntity<AuthenticationServiceResponse> passwordReset(PasswordResetRequest passwordResetRequest) {
        try{
            Optional<EntityVerificationToken> tokenOptional = tokenRepository.findByVerificationToken(
                    passwordResetRequest.getToken()
            );
            if(tokenOptional.isEmpty())
                throw new InvalidToken("Invalid verification token!...");

            EntityVerificationToken verificationTokenInfo = tokenOptional.get();
            Optional<Users> usersOptional = userRepository.findByUserId(verificationTokenInfo.getUserId());
            if(usersOptional.isEmpty())
                throw new ResourceNotFoundException("User not found with userId associated with token!...");

            Users users = usersOptional.get();
            boolean isPasswordMatched = passwordEncoder.matches(users.getPassword(),
                    passwordResetRequest.getOldPassword());
            if(isPasswordMatched)
                throw new InvalidCredentials("Old password is wrong!...");

            String sqlScript = "UPDATE INTO_USER_DATA SET PASSWORD = ?, UPDATED_BY = "
            + users.getUserName() + "WHERE USER_ID = ?";
            Query query = entityManager.createNativeQuery(sqlScript);
            entityManager.setProperty(passwordEncoder.encode(passwordResetRequest.getNewPassword()),
                    users.getUserId());
            query.executeUpdate();

            response = AuthenticationServiceResponse.createResponse()
                    .setResponseMsg("Password has changed successfully!...")
                    .setStatusCode(HttpStatus.ACCEPTED.toString())
                    .setIsOprSuccess(true);
        }catch (Exception exception){
            throw new InternalServerErrorException("[Password Change] Exception occurred Ex-"
                    +exception.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @Transactional
    @Override
    public ResponseEntity<AuthenticationServiceResponse> forgotPasswordRequest(String userEmailId) {
        try {
            Optional<Users> usersOptional = userRepository.findByUserEmail(userEmailId);

            if (usersOptional.isPresent()) {

                Users users = usersOptional.get();
                String otp = OtpUtils.generateOtp();
                OtpEntity otpEntity = OtpEntity.getOtpEntity()
                        .setUserId(users.getUserId())
                        .setOtpTypes(OtpTypes.FORGET_PASSWORD_OTP_VERIFICATION)
                        .setOpt(otp)
                        .setUserEmail(userEmailId)
                        .setUserName(users.getUserName());

                otpRepository.save(otpEntity);
                EmailRequest emailRequest = AuthServiceUtils.prepareSendOtpEmailRequest(otp,
                        "OTP_VERIFICATION", "Forgot Password Email Verification",
                        userEmailId);
                WebClientServices.callEmailNotificationService(
                        "email/sendMail", emailRequest, notificationServiceWebClient
                );
            }

        } catch (Exception exception) {
            throw new InternalServerErrorException("[Forgot Password Request] Exception occurred Ex-"
                    + exception.getMessage());
        } finally {
            response = AuthenticationServiceResponse.createResponse()
                    .setResponseMsg("If the email exists, OTP has been sent.")
                    .setIsOprSuccess(true)
                    .setStatusCode(HttpStatus.OK.toString());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Transactional
    @Override
    public ResponseEntity<AuthenticationServiceResponse> forgetPassword(String token, String newPassword){
        try{
            Optional<EntityVerificationToken> verificationToken = tokenRepository.findByVerificationToken(token);
            if(verificationToken.isEmpty())
                throw new VerificationTokenException("Invalid verification token!...");

            EntityVerificationToken storedToken = verificationToken.get();
            if(VerificationTokenUtils.isTokenExpired(storedToken.getTokenGeneratedAt(), String.valueOf(storedToken.getTokenValidDurationUnit())))
                throw new VerificationTokenExpired("Verification token has expired!...");

            String sqlScript = "UPDATE INTO_USER_DATA SET PASSWORD = ?  WHERE USER_ID = ?";
            Query query = entityManager.createNativeQuery(sqlScript);
            entityManager.setProperty(passwordEncoder.encode(newPassword),
                    storedToken.getUserId());
            query.executeUpdate();

            return new ResponseEntity<>(AuthenticationServiceResponse.createResponse()
                    .setResponseMsg("Password has changed successfully!...")
                    .setStatusCode(HttpStatus.ACCEPTED.toString())
                    .setIsOprSuccess(true), HttpStatus.OK);

        }catch (Exception exception){
            throw new InternalServerErrorException("[Exception occurred while password change]- " +
                    "{Ex}:- " + exception.getMessage());
        }
    }
}
