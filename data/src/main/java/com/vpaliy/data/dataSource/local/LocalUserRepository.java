package com.vpaliy.data.dataSource.local;

import android.content.Context;
import com.vpaliy.data.dataSource.Repository;
import com.vpaliy.data.dataSource.Specification;
import com.vpaliy.data.entity.UserEntity;

import android.support.annotation.NonNull;
import java.util.List;

/**
 * Created by vpaliyX on 2/26/17.
 *
 */

@SuppressWarnings("WeakerAccess")
public class LocalUserRepository implements Repository<UserEntity> {

    private static LocalUserRepository INSTANCE;

    private UserSQLHelper dbHelper;

    private LocalUserRepository(@NonNull Context context) {
        this.dbHelper=new UserSQLHelper(context);
    }

    @Override
    public void add(UserEntity item) {

    }

    @Override
    public void add(Iterable<UserEntity> items) {

    }

    @Override
    public void update(UserEntity item) {

    }

    @Override
    public void update(@NonNull Specification specification) {

    }

    @Override
    public List<UserEntity> query(@NonNull Specification specification) {
        return null;
    }

    public static LocalUserRepository getInstance(@NonNull Context context) {
        synchronized (LocalUserRepository.class) {
            if (INSTANCE == null) {
                INSTANCE = new LocalUserRepository(context);
            }
        }
        return INSTANCE;
    }
}
