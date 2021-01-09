package me.kingtux.tuxcore;

import java.util.Random;

public class Utils {
    private final static String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    public static String generateRandomString(int i) {
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < i) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }

}
