package me.kingtux.tuxcore.discord;

import dev.tuxjsql.basic.sql.BasicDataTypes;
import dev.tuxjsql.core.builders.ColumnBuilder;
import me.kingtux.tuxorm.TOConnection;
import me.kingtux.tuxorm.serializers.SingleSecondarySerializer;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.User;

public class UserSerializer implements SingleSecondarySerializer<User, Long> {
    private JDA jda;
    private TOConnection toConnection;

    public UserSerializer(JDA jda, TOConnection toConnection) {
        this.jda = jda;
        this.toConnection = toConnection;
    }

    @Override
    public Long getSimplifiedValue(User o) {
        return o.getIdLong();
    }

    @Override
    public User buildFromSimplifiedValue(Long value) {
        return jda.getUserById(value);
    }

    @Override
    public ColumnBuilder createColumn(String name) {
        return toConnection.getBuilder().createColumn().setDataType(BasicDataTypes.INTEGER).name(name);
    }
}
