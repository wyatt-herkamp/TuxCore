package me.kingtux.tuxcore.discord;

import me.kingtux.tuxcore.TuxCore;
import me.kingtux.tuxcore.settings.Setting;
import me.kingtux.tuxcore.settings.Settings;
import net.dv8tion.jda.api.entities.GuildChannel;
import org.bukkit.Bukkit;

import java.util.Optional;

public class ServerCountUpdater implements Runnable {
    private TuxCore tuxCore;

    public ServerCountUpdater(TuxCore tuxCore) {
        this.tuxCore = tuxCore;
    }

    @Override
    public void run() {
        Optional<Setting> settingOptional = tuxCore.getSettingManager().getSetting(Settings.DISCORD_SERVER_COUNT_CHANNEL);
        if (settingOptional.isPresent()) {
            Setting setting = settingOptional.get();
            GuildChannel channel = tuxCore.getTuxCoreDiscord().getJda().getGuildChannelById(setting.getSettingValue());
            if (channel != null) {
                channel.getManager().setName(createChannelName()).queue();
            } else {
                tuxCore.getLogger().warning("Channel: " + setting.getSettingValue() + " Is missing.");
            }
        } else {
            tuxCore.getLogger().warning("Missing Discord Server Count Channel");
        }
    }


    private String createChannelName() {
        return "Vanilla Count: " + Bukkit.getOnlinePlayers().size();
    }
}
