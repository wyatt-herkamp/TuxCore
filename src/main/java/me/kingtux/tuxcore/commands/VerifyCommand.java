package me.kingtux.tuxcore.commands;

import dev.nitrocommand.core.annotations.BaseCommand;
import dev.nitrocommand.core.annotations.CommandArgument;
import dev.nitrocommand.core.annotations.NitroCommand;
import dev.nitrocommand.core.annotations.SubCommand;
import me.kingtux.tuxcore.TuxCore;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@NitroCommand(command = "verify", description = "Finishes the verify process", format = "/verify {verify-key}")
public class VerifyCommand {
    private TuxCore tuxCore;

    public VerifyCommand(TuxCore tuxCore) {
        this.tuxCore = tuxCore;
    }

    @BaseCommand
    public void baseCommand(CommandSender commandSender) {

    }

    @SubCommand(format = "{key}")
    public void verifyKey(Player player, @CommandArgument("key") String key) {

    }
}
