package com.vpaliy.datasource.cache;


import android.content.Context;
import android.content.SharedPreferences;
import java.io.File;
import java.io.IOException;
import org.junit.rules.TemporaryFolder;
import org.mockito.runners.MockitoJUnitRunner;
import com.vpaliy.data.cache.FileManager;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;


@RunWith(MockitoJUnitRunner.class)
public class FileManagerTest {

    private static final String CONTENT="some:data";
    private static final String PREFERENCE_NAME="preference";
    private static final String KEY="key";

    @Mock
    private Context context;

    @Mock
    private SharedPreferences preferences;

    @Mock
    private SharedPreferences.Editor editor;

    @Rule
    public TemporaryFolder folder=new TemporaryFolder();


    @Before
    @SuppressWarnings("ALL")
    public void setUp() {
        when(context.getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE)).thenReturn(preferences);
        when(preferences.edit()).thenReturn(editor);
    }

    @Test
    public void testFileReadingMethod()throws IOException {
        File dummyFile=folder.newFile();
        FileManager.writeToFile(dummyFile,CONTENT);

        String result=FileManager.readFileContent(dummyFile);
        assertThat(result,is(CONTENT));

    }


    @Test
    public void testIfFileExists()throws IOException {
        File dummyFile=folder.newFile();

        assertThat(dummyFile.exists(),is(true));
        assertThat(FileManager.exists(dummyFile),is(true));
        assertThat(dummyFile.exists(),is(FileManager.exists(dummyFile)));

    }

    @Test
    public void testWritingToFile()throws IOException {
        File dummyFile=folder.newFile();
        String result;

        FileManager.writeToFile(dummyFile,CONTENT);
        result=FileManager.readFileContent(dummyFile);
        assertThat(result, is(CONTENT));

        FileManager.writeToFile(dummyFile,CONTENT);
        result+=FileManager.readFileContent(dummyFile);
        assertThat(result.length(),is(CONTENT.length()*2));

        FileManager.writeToFile(dummyFile,CONTENT);
        result+=FileManager.readFileContent(dummyFile);
        assertThat(result.length(),is(CONTENT.length()*3));
        assertThat(result,containsString(CONTENT));

    }


    @Test
    public void testClearDirectoryMethod()throws IOException {
        File dummyFolder=folder.newFolder();
        assertThat(FileManager.clearDirectory(dummyFolder),is(false));
    }


    @Test
    public void testWriteAndReadToPreferencesInt(){
        int value=10;
        FileManager.writeToPreferencesInt(context,PREFERENCE_NAME,KEY,value);

        when(editor.putInt(KEY,value)).thenReturn(editor);
        when(preferences.getInt(KEY,0)).thenReturn(value);

        assertThat(FileManager.getFromPreferencesInt(context,PREFERENCE_NAME,KEY),is(value));

        verify(editor).putInt(KEY,value);
        verify(preferences).getInt(KEY,0);
    }


    @Test
    public void testWriteAndReadToPreferencesLong(){
        long value=10;
        FileManager.writeToPreferencesLong(context,PREFERENCE_NAME,KEY,value);

        when(editor.putLong(KEY,value)).thenReturn(editor);
        when(preferences.getLong(KEY,0)).thenReturn(value);

        assertThat(FileManager.getFromPreferencesLong(context,PREFERENCE_NAME,KEY),is(value));

        verify(editor).putLong(KEY,value);
        verify(preferences).getLong(KEY,0);
    }


    @Test
    public void testWriteAndReadToPreferencesBoolean(){
        FileManager.writeToPreferencesBoolean(context,PREFERENCE_NAME,KEY,true);

        when(editor.putBoolean(KEY,true)).thenReturn(editor);
        when(preferences.getBoolean(KEY,false)).thenReturn(true);

        assertThat(FileManager.getFromPreferencesBoolean(context,PREFERENCE_NAME,KEY),is(true));

        verify(editor).putBoolean(KEY,true);
        verify(preferences).getBoolean(KEY,false);
    }

}
