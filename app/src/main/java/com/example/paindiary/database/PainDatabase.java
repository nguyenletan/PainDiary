package com.example.paindiary.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.paindiary.Converters;
import com.example.paindiary.dao.PainDAO;
import com.example.paindiary.dao.PainStatDAO;
import com.example.paindiary.entity.Pain;
import com.example.paindiary.entity.PainStat;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Pain.class}, version = 3, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class PainDatabase extends RoomDatabase {
    public abstract PainDAO painDao();
    public abstract PainStatDAO painStatDao();
    private static PainDatabase INSTANCE;
    //we create an ExecutorService with a fixed thread pool so we can later run database operations asynchronously on a background thread.
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    //A synchronized method in a multi threaded environment means that two threads are not allowed to access data at the same time
    public static synchronized PainDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    PainDatabase.class, "PainDatabase")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}
