package com.vpaliy.datasource.cache;


import com.vpaliy.data.cache.FileManager;
import com.vpaliy.datasource.ApplicationStub;
import com.vpaliy.datasource.BuildConfig;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, application = ApplicationStub.class)
public class FileManagerTest {

    private File cacheDir;

    @Before
    public void setUp() {
        cacheDir=RuntimeEnvironment.application.getCacheDir();
    }

    @After
    public void tearDown() {
        if(cacheDir!=null) {
            FileManager.clearDirectory(cacheDir);
        }
    }

    @Test
    public void testShouldWriteIntoFile()throws IOException {
        File fileToWrite = createDummyFile();
        String fileContent = "content";
        //write into file
        FileManager.writeToFile(fileToWrite, fileContent);
        assertThat(fileToWrite.exists(), is(true));
    }

    @Test
    public void testFileContent() {
        File fileToWrite = createDummyFile();
        String fileContent = "content\n";

        FileManager.writeToFile(fileToWrite, fileContent);
        String expectedContent = FileManager.readFileContent(fileToWrite);

        assertThat(expectedContent, is(equalTo(fileContent)));
    }

    private File createDummyFile() {
        String dummyFilePath = cacheDir.getPath() + File.separator + "dummyFile";
        return new File(dummyFilePath);
    }

}
