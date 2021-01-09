package me.kingtux.tuxcore.commands;

import dev.nitrocommand.bukkit.annotations.BukkitPermission;
import dev.nitrocommand.core.annotations.BaseCommand;
import dev.nitrocommand.core.annotations.NitroCommand;
import dev.nitrocommand.core.annotations.SubCommand;
import me.kingtux.tuxcore.TuxCore;
import me.kingtux.tuxcore.TuxCorePermission;
import org.bukkit.command.CommandSender;

import javax.security.auth.login.LoginException;

@NitroCommand(command = "ta", description = "An admin tool for TuxCore", format = "/tuxadmin {sub-task}")
@BukkitPermission("tuxcore.admin")
public class TuxAdmin {
    private TuxCore tuxCore;

    public TuxAdmin(TuxCore tuxCore) {
        this.tuxCore = tuxCore;
    }

    @BaseCommand
    public void init() {

    }

    @SubCommand(format = "reset-discord")
    public void resetDiscord(CommandSender sender) {
        if (!sender.hasPermission(TuxCorePermission.createPermission(TuxCorePermission.PermLevel.ADMIN, "discord.reset"))) {
            sender.sendMessage("You lack permission to do that");
            return;
        }
        sender.sendMessage("Resetting Discord");
        try {
            tuxCore.getTuxCoreDiscord().reset();
        } catch (LoginException e) {
            sender.sendMessage("Failed to reset Discord");
            e.printStackTrace();
            return;
        }
        sender.sendMessage("Discord Reset Completed");
    }
}
