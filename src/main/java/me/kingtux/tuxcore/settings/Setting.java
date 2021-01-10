package me.kingtux.tuxcore.settings;

import me.kingtux.tuxorm.BasicLoggingObject;
import me.kingtux.tuxorm.annotations.DBTable;
import me.kingtux.tuxorm.annotations.TableColumn;

@DBTable
public class Setting extends BasicLoggingObject {
    @TableColumn(name = "key")
    private Settings settingKey;
    @TableColumn
    private String settingValue;

    public Setting(Settings settingKey, String settingValue) {
        this.settingKey = settingKey;
        this.settingValue = settingValue;
    }

    public void setSettingValue(String settingValue) {
        this.settingValue = settingValue;
    }

    public Settings getSettingKey() {
        return settingKey;
    }

    public String getSettingValue() {
        return settingValue;
    }
}
