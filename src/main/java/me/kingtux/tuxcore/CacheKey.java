package me.kingtux.tuxcore;

import net.dv8tion.jda.api.entities.User;

import java.util.Objects;
import java.util.UUID;

public class CacheKey {

    private final UUID uuid;
    private final User id;

    public CacheKey(UUID uuid, User id) {
        this.uuid = uuid;
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public User getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CacheKey cacheKey = (CacheKey) o;
        return Objects.equals(id, cacheKey.getId()) ||
                Objects.equals(uuid, cacheKey.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, id);
    }
}
