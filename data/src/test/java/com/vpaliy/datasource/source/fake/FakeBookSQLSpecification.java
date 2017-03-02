package com.vpaliy.datasource.source.fake;


import com.vpaliy.data.source.local.PersistenceContract;
import com.vpaliy.data.specification.SQLSpecificationAdapter;

public class FakeBookSQLSpecification extends SQLSpecificationAdapter {

    @Override
    public String[] getProjection() {
        return new String[] {
            PersistenceContract.BookEntry.COLUMN_NAME_ENTRY_ID,
            PersistenceContract.BookEntry.COLUMN_NAME_TITLE,
            PersistenceContract.BookEntry.COLUMN_NAME_AUTHOR
            };
    }
}
