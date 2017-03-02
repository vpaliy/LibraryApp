package com.vpaliy.datasource.cache;


import android.content.Context;

import com.vpaliy.data.cache.Cache;
import com.vpaliy.data.cache.Serializer;
import com.vpaliy.data.entity.BookEntity;
import com.vpaliy.data.entity.UserEntity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
public class CacheTest {

    private static final int ID=123;

    @Mock
    private Serializer<UserEntity> userSerializer;

    @Mock
    private Serializer<BookEntity> bookSerializer;

    @Mock
    private Context context;

    @Rule
    public TemporaryFolder folder=new TemporaryFolder();

    @Rule
    public ExpectedException expectedException=ExpectedException.none();

    @InjectMocks
    private Cache<UserEntity> userCache;

    @InjectMocks
    private Cache<BookEntity> bookCache;

    @Before
    public void setUp() throws IOException{
        when(context.getCacheDir()).thenReturn(folder.newFile());
    }



}
