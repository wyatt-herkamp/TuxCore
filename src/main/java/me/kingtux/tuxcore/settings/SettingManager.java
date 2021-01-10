package me.kingtux.tuxcore.settings;

import me.kingtux.tuxcore.TuxCore;
import me.kingtux.tuxorm.Dao;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class SettingManager {
    private TuxCore tuxCore;
    private Dao<Setting, Long> settingDao;

    public SettingManager(TuxCore tuxCore) {
        this.tuxCore = tuxCore;
        tuxCore.getCommonConnection().registerClass(Setting.class);
        settingDao = tuxCore.getCommonConnection().createDao(Setting.class);
    }

    public @NotNull Optional<Setting> getSetting(Settings settings) {
        return settingDao.fetchFirst("key", settings);
    }

    public void setSetting(Setting setting) {
        settingDao.updateOrCreate(setting);
    }

}
