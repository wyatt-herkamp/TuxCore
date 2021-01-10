package me.kingtux.tuxcore;

import me.kingtux.enumconfig.BukkitYamlHandler;
import me.kingtux.enumconfig.EnumConfig;
import me.kingtux.enumconfig.EnumConfigLoader;
import me.kingtux.enumconfig.annotations.ConfigEntry;
import me.kingtux.enumconfig.annotations.ConfigValue;
import org.apache.commons.text.StringSubstitutor;
import org.bukkit.ChatColor;

import java.io.File;
import java.util.Map;

//https://commons.apache.org/proper/commons-text/javadocs/api-release/org/apache/commons/text/StringSubstitutor.html
//Variables ${var}
public enum MCLocale implements EnumConfig {
    /**
     * Valid Params: permission
     */
    @ConfigEntry
    MISSING_PERMISSION("You lack the permission to do that"),
    @ConfigEntry
    MUST_BE_PLAYER("You must be a player"),
    @ConfigEntry
    RELOADING_PLUGIN("Reloading the plugin"),

    @ConfigEntry
    INVALID_COMMAND("Invalid Command"),
    /**
     * Valid Params: hint
     */
    @ConfigEntry
    INVALID_COMMAND_WITH_HINT("Invalid Command Try: ${hint}");

    @ConfigValue
    private String value;

    MCLocale(String value) {
        this.value = value;
    }

    public static void loadLang(TuxCore tuxCore) {
        BukkitYamlHandler yamlHandler = new BukkitYamlHandler(new File(tuxCore.getDataFolder(), "mc.locale.yml"));
        EnumConfigLoader.loadLang(yamlHandler, MCLocale.class, true);
    }

    public String getValue() {
        return value;
    }

    public String color() {
        return ChatColor.translateAlternateColorCodes('&', getValue());
    }

    public String colorAndSubstitute(Map<String, String> values) {
        return ChatColor.translateAlternateColorCodes('&', getValueAndSubstitute(values));
    }

    private String getValueAndSubstitute(Map<String, String> values) {
        return StringSubstitutor.replace(getValue(), values);
    }

    public void setValue(String value) {
        this.value = value;
    }
}