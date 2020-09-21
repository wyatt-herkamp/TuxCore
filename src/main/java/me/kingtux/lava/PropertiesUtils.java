package me.kingtux.lava;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtils {
    /**
     * Loads a Properties from a File.
     *
     * @param file the file you want to load
     * @return Properties loaded
     * @throws IOException if an error occurred.
     */
    public static Properties loadPropertiesFromFile(File file) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(file));
        return properties;
    }
}
