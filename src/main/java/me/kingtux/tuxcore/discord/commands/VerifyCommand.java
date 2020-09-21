package me.kingtux.tuxcore.discord.commands;

import dev.nitrocommand.core.annotations.BaseCommand;
import dev.nitrocommand.core.annotations.NitroCommand;
import me.kingtux.tuxcore.MCDUser;
import me.kingtux.tuxcore.TuxCore;
import me.kingtux.tuxcore.VerifyKey;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.User;

@NitroCommand(command = "verify", description = "Starts verifying your mc account", format = "/verify")
public class VerifyCommand {
    private TuxCore tuxCore;

    public VerifyCommand(TuxCore tuxCore) {
        this.tuxCore = tuxCore;
    }

    @BaseCommand
    public void baseCommand(User user, Message message) {
        if (tuxCore.getVerifyManager().isVerified(user)) {
            message.getChannel().sendMessage("You are already verified").queue();
            return;
        }
        VerifyKey verifyKey = tuxCore.getVerifyManager().getVerifyingKey(user);
        if (verifyKey == null) {
            MCDUser mcdUser = tuxCore.getVerifyManager().createUser(user);
            verifyKey = tuxCore.getVerifyManager().createVerifyKey(mcdUser);
        }
        sendVerifyKey(user, verifyKey);
    }

    public void sendVerifyKey(User user, VerifyKey verifyKey) {
        PrivateChannel complete = user.openPrivateChannel().complete();
        complete.sendMessage("Join the MC server and run the command `/verify " + verifyKey.getKey() + "`").queue();
    }
}
