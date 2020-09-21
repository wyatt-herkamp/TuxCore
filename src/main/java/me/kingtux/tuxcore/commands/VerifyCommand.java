package me.kingtux.tuxcore.commands;

import dev.nitrocommand.core.annotations.BaseCommand;
import dev.nitrocommand.core.annotations.CommandArgument;
import dev.nitrocommand.core.annotations.NitroCommand;
import dev.nitrocommand.core.annotations.SubCommand;
import me.kingtux.tuxcore.TuxCore;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

@NitroCommand(command = "verify", description = "Finishes the verify process", format = "/verify {verify-key}")
public class VerifyCommand {
    private TuxCore tuxCore;

    public VerifyCommand(TuxCore tuxCore) {
        this.tuxCore = tuxCore;
    }

    @BaseCommand
    public void baseCommand(Player player) {
        if (tuxCore.getVerifyManager().isVerified(player.getUniqueId())) {
            //Print Verified Info
            player.sendMessage("You are already verified");
            return;
        }
        //TODO print steps for getting verified
    }

    @SubCommand(format = "{key}")
    public void verifyKey(Player player, @CommandArgument("key") String key) {
        UUID uuid = player.getUniqueId();
        if (tuxCore.getVerifyManager().isVerified(uuid)) {
            player.sendMessage("You are already verified");
            return;
        }
        if (tuxCore.getVerifyManager().doesKeyExist(key)) {
            player.sendMessage("You are now verified");
            tuxCore.getVerifyManager().finishVerifcation(key, player.getUniqueId());

        }
    }
}
