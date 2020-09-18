package me.kingtux.tuxcore.discord.commands;

import dev.nitrocommand.core.annotations.BaseCommand;
import dev.nitrocommand.core.annotations.CommandArgument;
import dev.nitrocommand.core.annotations.NitroCommand;
import dev.nitrocommand.core.annotations.SubCommand;
import me.kingtux.tuxcore.TuxCore;
import net.dv8tion.jda.api.entities.User;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
@NitroCommand(command = "verify", description = "Starts verifying your mc account", format = "/verify")
public class VerifyCommand {
    private TuxCore tuxCore;

    public VerifyCommand(TuxCore tuxCore) {
        this.tuxCore = tuxCore;
    }

    @BaseCommand
    public void baseCommand(User user) {

    }
}
