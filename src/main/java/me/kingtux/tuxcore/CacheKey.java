package me.kingtux.tuxcore;

import java.util.Objects;
import java.util.UUID;

public class CacheKey {

    private final UUID uuid;
    private final long id;

    public CacheKey(UUID uuid, long id) {
        this.uuid = uuid;
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CacheKey cacheKey = (CacheKey) o;
        return id == cacheKey.id ||
                Objects.equals(uuid, cacheKey.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, id);
    }
}
