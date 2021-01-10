package me.kingtux.tuxcore.discord.commands;

import dev.nitrocommand.core.annotations.BaseCommand;
import dev.nitrocommand.core.annotations.NitroCommand;
import dev.nitrocommand.core.annotations.SubCommand;
import dev.nitrocommand.jda4.annotations.JDAPermission;
import me.kingtux.tuxcore.TuxCore;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.TextChannel;

@NitroCommand(command = "ta", description = "An admin tool for TuxCore", format = "/tuxadmin {sub-task}")
@JDAPermission(Permission.ADMINISTRATOR)
public class TuxAdmin {
    private TuxCore tuxCore;

    public TuxAdmin(TuxCore tuxCore) {
        this.tuxCore = tuxCore;
    }

    @BaseCommand
    public void init() {

    }

    @SubCommand(format = "update-count")
    public void updateCount(TextChannel channel) {
        channel.sendMessage("Updating Count").queue();
        tuxCore.getTuxCoreDiscord().getRunnable().run();
    }
}
