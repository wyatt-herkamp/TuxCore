package me.kingtux.tuxcore.settings;

import dev.nitrocommand.jda4.parsers.ChannelMentionParser;
import me.kingtux.tuxcore.discord.DiscordSettingChecker;

public enum Settings {
    DISCORD_SERVER_COUNT_CHANNEL(DiscordSettingChecker.DISCORD_SETTING_CHECKER),
    DISCORD_MINECRAFT_CHAT_CHANNEL(DiscordSettingChecker.DISCORD_SETTING_CHECKER),
    ;


    private String defaultValue = "";
    private SettingChecker settingChecker;

    Settings() {

    }

    Settings(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    Settings(SettingChecker settingChecker) {
        this.settingChecker = settingChecker;
    }

    Settings(String defaultValue, SettingChecker settingChecker) {
        this.defaultValue = defaultValue;
        this.settingChecker = settingChecker;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public SettingChecker getSettingChecker() {
        return settingChecker;
    }
}
