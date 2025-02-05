package bank_manager.back_end.utils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtils {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    public static String hashPassword(String password){
        return encoder.encode(password);
    }

    public static boolean checkMatchingPassword(String password, String hashedPassword){
        return encoder.matches(password, hashedPassword);
    }
}
