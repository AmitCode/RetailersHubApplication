package com.intoThe.utils;

import com.intoThe.entities.OtpEntity;
import com.intoThe.repository.OtpRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;

public class OtpUtils {
    private static final SecureRandom secureRandom = new SecureRandom();

    public static String generateOtp(){
//        int otp = secureRandom.nextInt();
        int otp = 100000 + secureRandom.nextInt(900000);
        /*secureRandom.nextInt(); will  generates 0 to 899999 so adding the 100000 will ensure 100000 to 899999*/
        return String.valueOf(otp);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public static void incrementRetry(OtpEntity otp, OtpRepository otpRepository) {
        otp.setOptRetry(otp.getOptRetry() + 1);
        otpRepository.save(otp);
    }

}
