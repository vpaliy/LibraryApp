package com.vpaliy.data.source.local;

import android.content.Context;
import com.vpaliy.data.source.Repository;
import com.vpaliy.data.entity.UserEntity;
import com.vpaliy.data.specification.SQLSpecification;

import android.support.annotation.NonNull;
import java.util.List;


@SuppressWarnings("WeakerAccess")
public class LocalUserRepository
    implements Repository<UserEntity, SQLSpecification> {

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
    public void update(@NonNull SQLSpecification specification) {

    }

    @Override
    public List<UserEntity> query(@NonNull SQLSpecification specification) {
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
