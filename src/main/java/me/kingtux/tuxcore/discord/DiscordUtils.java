package me.kingtux.tuxcore.discord;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DiscordUtils {
    private static final Pattern LONG_PATTERN = Pattern.compile("(<#)(\\d+)(>)");

    public static boolean isValidLong(String string) {
        Matcher matcher = LONG_PATTERN.matcher(string);
        return matcher.matches();
    }
}
