package me.kingtux.tuxcore.discord.commands;

import dev.nitrocommand.core.annotations.BaseCommand;
import dev.nitrocommand.core.annotations.CommandArgument;
import dev.nitrocommand.core.annotations.NitroCommand;
import dev.nitrocommand.core.annotations.SubCommand;
import dev.nitrocommand.jda4.annotations.JDAPermission;
import me.kingtux.tuxcore.TuxCore;
import me.kingtux.tuxcore.discord.DiscordSettingChecker;
import me.kingtux.tuxcore.settings.Setting;
import me.kingtux.tuxcore.settings.Settings;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

import java.util.Optional;

@NitroCommand(command = "setting", description = "", format = "")
public class SettingCommand {
    private TuxCore tuxCore;

    public SettingCommand(TuxCore tuxCore) {
        this.tuxCore = tuxCore;
    }

    @BaseCommand
    public void init(User user, TextChannel channel) {

    }

    @JDAPermission(Permission.ADMINISTRATOR)
    @SubCommand(format = "{SETTING} {VALUE}")
    public void subCommand(User user, TextChannel textChannel, @CommandArgument("setting") String setting, @CommandArgument("value") String value) {
        Settings settings = Settings.valueOf(setting.toUpperCase());
        if (!settings.getSettingChecker().isValid(value)) {
            if (settings.getSettingChecker() instanceof DiscordSettingChecker) {
                textChannel.sendMessage("The value must be in the format of a mention").queue();
            } else {
                textChannel.sendMessage("Please use this format: " + settings.getSettingChecker().getFormat()).queue();
            }
            return;
        }
        Setting settingObject;
        Optional<Setting> setting1 = tuxCore.getSettingManager().getSetting(settings);
        settingObject = setting1.orElse(new Setting(settings, value));
        settingObject.setSettingValue(value);
        tuxCore.getSettingManager().setSetting(settingObject);
        textChannel.sendMessage("Setting has been updated").queue();

    }
}
