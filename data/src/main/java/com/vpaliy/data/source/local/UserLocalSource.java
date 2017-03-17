package com.vpaliy.data.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;
import com.vpaliy.common.scheduler.SchedulerProvider;
import com.vpaliy.data.source.DataSource;
import com.vpaliy.data.entity.UserEntity;

import java.util.Collection;
import java.util.List;
import rx.Observable;
import rx.functions.Func1;

import static com.vpaliy.common.Preconditions.*;
import static com.vpaliy.data.source.local.PersistenceContract.UserEntry;

public class UserLocalSource extends DataSource<UserEntity> {

    @NonNull
    private BriteDatabase database;

    @NonNull
    private Func1<Cursor,UserEntity> mapper;

    private UserLocalSource(@NonNull Context context,
                            @NonNull SchedulerProvider schedulerProvider) {
        checkNotNull(context);
        checkNotNull(schedulerProvider);
        UserSQLHelper userSQLHelper=new UserSQLHelper(context);
        SqlBrite sqlBrite=new SqlBrite.Builder().build();
        mapper=this::getUser;
        database=sqlBrite.wrapDatabaseHelper(userSQLHelper,schedulerProvider.io());

    }

    @NonNull
    private UserEntity getUser(@NonNull Cursor cursor) {
        String firstName=cursor.getString(cursor.getColumnIndex(UserEntry.COLUMN_FIRST_NAME));
        String lastName=cursor.getString(cursor.getColumnIndex(UserEntry.COLUMN_LAST_NAME));
        String ID=cursor.getString(cursor.getColumnIndex(UserEntry.COLUMN_NAME_ENTRY_ID));
        String emailAddress=cursor.getString(cursor.getColumnIndex(UserEntry.COLUMN_EMAIL_ADDRESS));
        int age=cursor.getInt(cursor.getColumnIndex(UserEntry.COLUMN_AGE));

        UserEntity userEntity=new UserEntity(firstName,lastName,ID);
        userEntity.setAge(age);
        userEntity.setEmailAddress(emailAddress);
        return userEntity;
    }

    @Override
    public void deleteById(String ID) {
        String selection=UserEntry.COLUMN_NAME_ENTRY_ID+" LIKE ?";
        String[] selectionArgs={ID};

        database.delete(UserEntry.TABLE_NAME,selection,selectionArgs);
    }

    @Override
    public void delete(UserEntity item) {
        String selection=UserEntry.COLUMN_NAME_ENTRY_ID+" LIKE ?";
        String[] selectionArgs={item.getID()};

        database.delete(UserEntry.TABLE_NAME,selection,selectionArgs);
    }

    @Override
    public void update(UserEntity item) {
        checkNotNull(item);

        ContentValues values=new ContentValues();
        values.put(UserEntry.COLUMN_NAME_ENTRY_ID,item.getID());
        values.put(UserEntry.COLUMN_FIRST_NAME,item.getFirstName());
        values.put(UserEntry.COLUMN_LAST_NAME,item.getLastName());
        values.put(UserEntry.COLUMN_AGE,item.getAge());
        values.put(UserEntry.COLUMN_EMAIL_ADDRESS,item.getEmailAddress());

        String selection=UserEntry.COLUMN_NAME_ENTRY_ID+" LIKE ?";
        String[] selectionArgs={item.getID()};

        database.update(UserEntry.TABLE_NAME,values,selection,selectionArgs);
    }

    @Override
    public Observable<List<UserEntity>> getList() {
        String[] projection= {
                UserEntry.COLUMN_NAME_ENTRY_ID,
                UserEntry.COLUMN_FIRST_NAME,
                UserEntry.COLUMN_LAST_NAME,
                UserEntry.COLUMN_AGE,
                UserEntry.COLUMN_EMAIL_ADDRESS
        };

        String SQL=String.format("SELECT %s FROM %s", TextUtils.join(",",projection),UserEntry.TABLE_NAME);
        return database.createQuery(UserEntry.TABLE_NAME,SQL)
            .mapToList(mapper);
    }

    @Override
    public Observable<UserEntity> findById(String ID) {
        String[] projection= {
                UserEntry.COLUMN_NAME_ENTRY_ID,
                UserEntry.COLUMN_FIRST_NAME,
                UserEntry.COLUMN_LAST_NAME,
                UserEntry.COLUMN_AGE,
                UserEntry.COLUMN_EMAIL_ADDRESS
        };

        String SQL=String.format("SELECT %s FROM %s WHERE %s LIKE ?",
                TextUtils.join(",", projection), UserEntry.TABLE_NAME,UserEntry.COLUMN_NAME_ENTRY_ID);

        return database.createQuery(UserEntry.TABLE_NAME,SQL,ID)
            .mapToOne(mapper);
    }

    @Override
    public void add(UserEntity item) {
        checkNotNull(item);

        ContentValues values=new ContentValues();
        values.put(UserEntry.COLUMN_NAME_ENTRY_ID,item.getID());
        values.put(UserEntry.COLUMN_FIRST_NAME,item.getFirstName());
        values.put(UserEntry.COLUMN_LAST_NAME,item.getLastName());
        values.put(UserEntry.COLUMN_AGE,item.getAge());
        values.put(UserEntry.COLUMN_EMAIL_ADDRESS,item.getEmailAddress());

        database.insert(UserEntry.TABLE_NAME,values);
    }

    @Override
    public void add(Collection<UserEntity> collection) {
        checkNotNull(collection);
        for(UserEntity entity:collection) {
            add(entity);
        }
    }

    @Override
    public void clear() {
        database.delete(UserEntry.TABLE_NAME,null);
    }
}
