package me.kingtux.tuxcore;

public class TuxCorePermission {
    private static final String format = "tuxcore.%1$s.%2$s";

    public enum PermLevel {
        ADMIN,
        MOD,
        USER
    }

    public static String createPermission(PermLevel permlevel, String permission) {
        return String.format(format, permlevel.name().toLowerCase(), permission);
    }
}
