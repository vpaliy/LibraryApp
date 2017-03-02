package com.vpaliy.data.source.local;

import android.content.ContentValues;
import android.content.Context;
import com.vpaliy.common.Preconditions;
import com.vpaliy.data.entity.UserEntity;
import com.vpaliy.data.source.DataSource;
import com.vpaliy.data.specification.SQLSpecification;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.LinkedList;
import java.util.List;
import android.support.annotation.NonNull;

import static com.vpaliy.data.source.local.PersistenceContract.UserEntry;


@SuppressWarnings("WeakerAccess")
public class LocalUserDataSource implements DataSource<UserEntity, SQLSpecification> {


    private static LocalUserDataSource INSTANCE;

    private UserSQLHelper dbHelper;

    private LocalUserDataSource(@NonNull Context context) {
        this.dbHelper=new UserSQLHelper(context);
    }

    @Override
    public void add(UserEntity item) {
        Preconditions.checkNotNull(item);
        SQLiteDatabase db=dbHelper.getReadableDatabase();

        ContentValues values=new ContentValues();
        values.put(UserEntry.COLUMN_FIRST_NAME,item.getFirstName());
        values.put(UserEntry.COLUMN_LAST_NAME,item.getLastName());
        values.put(UserEntry.COLUMN_NAME_ENTRY_ID,item.getID());
        values.put(UserEntry.COLUMN_AGE,item.getAge());
        values.put(UserEntry.COLUMN_EMAIL_ADDRESS,item.getEmailAddress());

        db.insert(UserEntry.TABLE_NAME,null,values);
        db.close();
    }

    @Override
    public void add(Iterable<UserEntity> items) {
        for(UserEntity entity:items) {
            add(entity);
        }
    }

    @Override
    public void update(UserEntity item) {
        SQLiteDatabase db=dbHelper.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(UserEntry.COLUMN_FIRST_NAME,item.getFirstName());
        values.put(UserEntry.COLUMN_LAST_NAME,item.getLastName());
        values.put(UserEntry.COLUMN_AGE,item.getAge());
        values.put(UserEntry.COLUMN_EMAIL_ADDRESS,item.getEmailAddress());

        String selection = UserEntry.COLUMN_NAME_ENTRY_ID + " LIKE ?";
        String[] selectionArgs = {Integer.toString(item.getID())};

        db.update(UserEntry.TABLE_NAME, values, selection, selectionArgs);
        db.close();

    }

    @Override
    public void update(@NonNull UserEntity item, @NonNull SQLSpecification specification) {
        String[] projections=specification.getProjection();

        SQLiteDatabase db=dbHelper.getReadableDatabase();

        ContentValues values = new ContentValues();
        for(String projection:projections) {
            switch (projection) {
                case UserEntry.COLUMN_AGE:
                    values.put(UserEntry.COLUMN_AGE,item.getAge());
                    break;
                case UserEntry.COLUMN_EMAIL_ADDRESS:
                    values.put(UserEntry.COLUMN_EMAIL_ADDRESS,item.getEmailAddress());
                    break;
                case UserEntry.COLUMN_FIRST_NAME:
                    values.put(UserEntry.COLUMN_FIRST_NAME,item.getFirstName());
                    break;
                case UserEntry.COLUMN_LAST_NAME:
                    values.put(UserEntry.COLUMN_LAST_NAME,item.getFirstName());
                    break;
            }
        }

        String selection = UserEntry.COLUMN_NAME_ENTRY_ID + " LIKE ?";
        String[] selectionArgs = {Integer.toString(item.getID())};

        db.update(UserEntry.TABLE_NAME, values, selection, selectionArgs);
        db.close();
    }

    @Override
    public List<UserEntity> query(@NonNull SQLSpecification specification) {
        String[] projections=specification.getProjection();
        Preconditions.checkIfContains(projections, UserEntry.COLUMN_LAST_NAME,
                UserEntry.COLUMN_LAST_NAME, UserEntry.COLUMN_NAME_ENTRY_ID);
        List<UserEntity> result=new LinkedList<>();
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        String selection=specification.getSelection();
        String[] selectionArgs=specification.getSelectionArgs();
        String order=specification.getOrder();
        Cursor cursor=db.query(UserEntry.TABLE_NAME,
                projections,selection,selectionArgs,null,null,order);
        if(cursor!=null) {
            if(cursor.getCount()>0) {
                while(cursor.moveToNext()) {
                    //main entries
                    String firstName=cursor.getString(cursor.getColumnIndex(UserEntry.COLUMN_FIRST_NAME));
                    String lastName=cursor.getString(cursor.getColumnIndex(UserEntry.COLUMN_LAST_NAME));
                    int ID=cursor.getInt(cursor.getColumnIndex(UserEntry.COLUMN_NAME_ENTRY_ID));
                    //create an instance
                    UserEntity userEntity=new UserEntity(firstName,lastName,ID);
                    //fill up with details
                    for(String projection:projections) {
                        switch (projection) {
                            case UserEntry.COLUMN_AGE:
                                userEntity.setAge(cursor.getInt(cursor.getColumnIndex(projection)));
                                break;
                            case UserEntry.COLUMN_EMAIL_ADDRESS:
                                userEntity.setEmailAddress(cursor.getString(cursor.getColumnIndex(projection)));
                                break;
                        }
                    }
                    result.add(userEntity);
                }
            }
            cursor.close();
        }
        db.close();
        return result;
    }

    @Override
    public UserEntity get(@NonNull SQLSpecification specification) {
        String[] projections=specification.getProjection();
        Preconditions.checkIfContains(projections, UserEntry.COLUMN_LAST_NAME,
                UserEntry.COLUMN_LAST_NAME, UserEntry.COLUMN_NAME_ENTRY_ID);
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        String selection=specification.getSelection();
        String[] selectionArgs=specification.getSelectionArgs();
        String order=specification.getOrder();
        Cursor cursor=db.query(UserEntry.TABLE_NAME,
                projections,selection,selectionArgs,null,null,order);
        UserEntity userEntity=null;
        if(cursor!=null) {
            if(cursor.getCount()>0) {
                while(cursor.moveToNext()) {
                    //main entries
                    String firstName=cursor.getString(cursor.getColumnIndex(UserEntry.COLUMN_FIRST_NAME));
                    String lastName=cursor.getString(cursor.getColumnIndex(UserEntry.COLUMN_LAST_NAME));
                    int ID=cursor.getInt(cursor.getColumnIndex(UserEntry.COLUMN_NAME_ENTRY_ID));
                    //create an instance
                    userEntity=new UserEntity(firstName,lastName,ID);
                    //fill up with details
                    for(String projection:projections) {
                        switch (projection) {
                            case UserEntry.COLUMN_AGE:
                                userEntity.setAge(cursor.getInt(cursor.getColumnIndex(projection)));
                                break;
                            case UserEntry.COLUMN_EMAIL_ADDRESS:
                                userEntity.setEmailAddress(cursor.getString(cursor.getColumnIndex(projection)));
                                break;
                        }
                    }
                }
            }
            cursor.close();
        }
        db.close();
        return userEntity;
    }

    public static LocalUserDataSource getInstance(@NonNull Context context) {
        synchronized (LocalUserDataSource.class) {
            if (INSTANCE == null) {
                INSTANCE = new LocalUserDataSource(context);
            }
        }
        return INSTANCE;
    }
}
