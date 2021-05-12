package com.example.paindiary.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.paindiary.dao.PainStatDAO;
import com.example.paindiary.database.PainDatabase;
import com.example.paindiary.entity.PainStat;

import java.util.List;

public class PainStatRepository {
    private PainStatDAO painStatDao;
    private LiveData<List<PainStat>> painStat;
    public PainStatRepository(Application application){
        PainDatabase db = PainDatabase.getInstance(application);
        painStatDao = db.painStatDao();
        painStat = painStatDao.getPainStatistic();
    }
    // Room executes this query on a separate thread
    public LiveData<List<PainStat>> getPainStatistic() {
        return painStat;
    }
}
