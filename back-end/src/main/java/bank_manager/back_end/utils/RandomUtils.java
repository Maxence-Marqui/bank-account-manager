package bank_manager.back_end.utils;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Random;

@Component
public class RandomUtils {
    public static String getRandomCharacterString(int length) {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < length) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return salt.toString();
    }

    public static String generateUniqueAccountNumber() {
        Random rnd = new Random();
        long timestamp = Instant.now().toEpochMilli() % 1_000_000_000; // Prend les 9 derniers chiffres
        int randomPart = rnd.nextInt(900) + 100; // Génère un nombre entre 100 et 999
        return String.valueOf(timestamp) + randomPart;
    }
}