package com.vpaliy.data.cache;

import android.content.Context;
import com.vpaliy.data.dataSource.Specification;
import com.vpaliy.data.entity.BookEntity;
import java.io.File;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import android.support.annotation.NonNull;

public class BookCache extends Cache<BookEntity,Specification> {

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
            final File userFile=buildFile(item);
            if(!isCached(null)) {
                final String jsonString=serializer.serialize(item,BookEntity.class);
                executeAsynchronously(new CacheWriter(userFile,jsonString));
            }
        }
    }

    @Override
    public BookEntity get(Specification params) {
        return null;
    }

    @Override
    public boolean isCached(Specification specification) {
        return false;
    }

    @Override
    public void clearCache() {
        executeAsynchronously(new ClearTask(cacheDir));
    }

    private File buildFile(@NonNull BookEntity entity) {
        final StringBuilder fileNameBuilder = new StringBuilder();
        fileNameBuilder.append(this.cacheDir.getPath());
        fileNameBuilder.append(File.separator);
        fileNameBuilder.append(entity.getClass().getSimpleName());
        //fileNameBuilder.append(entity.getId());

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
