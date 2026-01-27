package booking.reservationapi.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordTest {
    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        boolean matches = passwordEncoder.matches(
            "testpass",
            "$2a$10$ZUXCmBwa/N8z2M3BgY/5hOGDcndLUFafPS4ZQCT6AlDV8yGLFuggy"
        );

        System.out.println("Password matches? " + matches);
    }
}
