package me.kingtux.tuxcore;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import me.kingtux.tuxorm.Dao;
import net.dv8tion.jda.api.entities.User;

import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class VerifyManager {
    private final TuxCore tuxCore;
    private final Dao<VerifyKey, Long> verifyKeys;
    private final Dao<MCDUser, Long> users;
    public final LoadingCache<CacheKey, MCDUser> userCache;

    public VerifyManager(TuxCore tuxCore) {
        this.tuxCore = tuxCore;
        verifyKeys = tuxCore.getCommonConnection().createDao(VerifyKey.class);
        users = tuxCore.getCommonConnection().createDao(MCDUser.class);
        userCache = CacheBuilder.newBuilder().build(new CacheLoader<CacheKey, MCDUser>() {
            @Override
            public MCDUser load(CacheKey key) throws Exception {
                if (key.getId() != 0) {
                    return users.fetchFirst("user", key.getId()).get();
                } else if (key.getUuid() != null) {
                    return users.fetchFirst("mc_user", key.getUuid()).get();
                } else {
                    throw new IllegalArgumentException("WHY THO");
                }
            }
        });
    }

    public MCDUser getUser(UUID uuid) {
        try {
            MCDUser mcdUser = userCache.get(new CacheKey(uuid, 0));
            return mcdUser;
        } catch (ExecutionException e) {
            if (e.getCause() instanceof NoSuchElementException) {
                return null;
            }
        }
        return null;
    }

    public MCDUser getUser(long id) {
        try {
            MCDUser mcdUser = userCache.get(new CacheKey(null, id));
            if (mcdUser.getMcUserID() == null) {
                userCache.invalidate(new CacheKey(null, id));
            }
            return mcdUser;
        } catch (ExecutionException e) {
            if (e.getCause() instanceof NoSuchElementException) {
                return null;
            }
        }
        return null;
    }

    public void finishVerifcation(String key, UUID uuid) {
        if (!doesKeyExist(key)) {
            throw new IllegalArgumentException("BAD BOI");
        }
        VerifyKey verifyKey = verifyKeys.fetchFirst("key", key).get();
        MCDUser mcdUser = verifyKey.getMcdUser();
        mcdUser.setMcUserID(uuid);
        verifyKeys.delete(verifyKey);
        users.update(mcdUser);

    }

    public boolean doesKeyExist(String key) {
        return verifyKeys.fetchFirst("key", key).isPresent();
    }

    public boolean isVerified(User user) {
        return getUser(user.getIdLong()).getMcUserID() != null;
    }

    public boolean isUserVerifying(User user) {
        return getVerifyingKey(user) != null;
    }

    public VerifyKey getVerifyingKey(User user) {
        MCDUser user1 = getUser(user.getIdLong());
        if (user1 == null) return null;
        return verifyKeys.fetchFirst("user", user1).orElse(null);
    }

    public MCDUser createUser(User user) {
        MCDUser mcdUser = new MCDUser(user);
        mcdUser = users.create(mcdUser);
        return mcdUser;
    }

    public VerifyKey createVerifyKey(MCDUser mcdUser) {
        VerifyKey verifyKey = new VerifyKey(mcdUser, Utils.generateRandomString(6));
        verifyKey = verifyKeys.create(verifyKey);
        return verifyKey;
    }

    public boolean isVerified(UUID uniqueId) {
        return getUser(uniqueId) != null;
    }

}
