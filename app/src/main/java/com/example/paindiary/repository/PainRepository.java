package com.example.paindiary.repository;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;

import com.example.paindiary.dao.PainDAO;
import com.example.paindiary.database.PainDatabase;
import com.example.paindiary.entity.Pain;
import com.example.paindiary.entity.PainStat;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class PainRepository {
    private PainDAO painDao;
    private LiveData<List<Pain>> allPains;


    public PainRepository(Application application) {
        PainDatabase db = PainDatabase.getInstance(application);
        painDao = db.painDao();
        allPains = painDao.getAll();
    }

    // Room executes this query on a separate thread
    public LiveData<List<Pain>> getAllPains() {
        return allPains;
    }

    public void insert(final Pain pain) {
        PainDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                painDao.insert(pain);
            }
        });
    }

    public void deleteAll() {
        PainDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                painDao.deleteAll();
            }
        });
    }

    public void delete(final Pain pain) {
        PainDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                painDao.delete(pain);
            }
        });
    }

    public void updatePain(final Pain pain) {
        PainDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                painDao.updatePain(pain);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public CompletableFuture<Pain> findByIDFuture(final int painId) {
        return CompletableFuture.supplyAsync(new Supplier<Pain>() {
            @Override
            public Pain get() {
                return painDao.findByID(painId);
            }
        }, PainDatabase.databaseWriteExecutor);
    }
}