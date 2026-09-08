package com.notifications.template;

public class OtpVerificationEmailTemplate {
    public static String getOtpVerificationMailTemplate(String otp) {
        return String.format("""
            <html>
                <body>
                    <h2>Dear User,</h2>

                    <p>This email is for verification of your Email ID.</p>

                    <h3>Your OTP: <strong>%s</strong></h3>

                    <p>Please do not share this OTP with anyone.</p>

                    <br>

                    <p>Thank You</p>
                </body>
            </html>
            """, otp);
    }
}
