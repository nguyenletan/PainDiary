package com.example.paindiary.viewmodel;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.paindiary.entity.Pain;
import com.example.paindiary.entity.PainStat;
import com.example.paindiary.repository.PainRepository;
import com.example.paindiary.repository.PainStatRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class PainStatViewModel extends AndroidViewModel {
    private PainStatRepository cRepository;
    private LiveData<List<PainStat>> painStat;

    public PainStatViewModel(Application application) {
        super(application);
        cRepository = new PainStatRepository(application);
        painStat = cRepository.getPainStatistic();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public LiveData<List<PainStat>> getPainStatistic() {
        return painStat;
    }

}
