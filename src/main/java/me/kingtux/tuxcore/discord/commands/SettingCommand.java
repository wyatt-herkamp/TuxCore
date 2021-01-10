package me.kingtux.tuxcore.discord.commands;

import dev.nitrocommand.core.annotations.BaseCommand;
import dev.nitrocommand.core.annotations.CommandArgument;
import dev.nitrocommand.core.annotations.NitroCommand;
import dev.nitrocommand.core.annotations.SubCommand;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

@NitroCommand(command = "setting", description = "", format = "")
public class SettingCommand {
    @BaseCommand
    public void init(User user, TextChannel channel) {

    }

    @SubCommand(format = "{SETTING} {VALUE}")
    public void subCommand(User user, TextChannel textChannel,@CommandArgument("setting") String setting, @CommandArgument("value") String value){


    }
}
