package booking.reservationapi.security;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class HashGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12); // cost factor 12
        String password = "testpass";
        String newHash = encoder.encode(password);

        System.out.println("Lozinka: " + password);
        System.out.println("BCrypt hash: " + newHash);
        System.out.println("Poklapa se? " + encoder.matches(password, newHash));
    }
}
