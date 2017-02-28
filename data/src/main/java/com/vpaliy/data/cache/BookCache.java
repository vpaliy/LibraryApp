package com.vpaliy.data.cache;

import android.content.Context;

import java.io.File;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import android.support.annotation.NonNull;

import com.vpaliy.data.entity.BookEntity;

public class BookCache extends Cache<BookEntity> {

    private final static String DEFAULT_NAME="book:";

    private final Serializer<BookEntity> serializer;
    private final File cacheDir;
    private final Executor executor= Executors.newSingleThreadExecutor();

    public BookCache(@NonNull Context context) {
        this.serializer=new Serializer<>();
        this.cacheDir=context.getCacheDir();
    }

    @Override
    public void add(BookEntity item) {
        if(item!=null) {
            final File userFile=buildFile(item.getID());
            if(!isCached(item.getID())) {
                final String jsonString=serializer.serialize(item,BookEntity.class);
                executeAsynchronously(new CacheWriter(userFile,jsonString));
            }
        }
    }

    @Override
    public BookEntity get(int ID) {
        File file=buildFile(ID);
        String fileContent=FileManager.readFileContent(file);
        return serializer.deserialize(fileContent,BookEntity.class);
    }

    @Override
    public boolean isCached(int ID) {
        return FileManager.exists(buildFile(ID));
    }

    @Override
    public void clearCache() {
        executeAsynchronously(new ClearTask(cacheDir));
    }

    private File buildFile(int ID) {
        final StringBuilder fileNameBuilder = new StringBuilder();
        fileNameBuilder.append(this.cacheDir.getPath());
        fileNameBuilder.append(File.separator);
        fileNameBuilder.append(DEFAULT_NAME);
        fileNameBuilder.append(ID);

        return new File(fileNameBuilder.toString());

    }

    private void executeAsynchronously(Runnable runnable) {
        executor.execute(runnable);
    }

    private static class CacheWriter implements Runnable {
        private final File fileToWrite;
        private final String fileContent;

        CacheWriter(File fileToWrite, String fileContent) {
            this.fileToWrite = fileToWrite;
            this.fileContent = fileContent;
        }

        @Override
        public void run() {
            FileManager.writeToFile(fileToWrite, fileContent);
        }
    }


    private static class ClearTask implements Runnable {

        private final File cacheDir;

        ClearTask(File cacheDir) {
            this.cacheDir = cacheDir;
        }

        @Override
        public void run() {
            FileManager.clearDirectory(this.cacheDir);
        }
    }

}