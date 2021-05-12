package com.example.paindiary.viewmodel;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.paindiary.entity.Pain;
import com.example.paindiary.entity.PainStat;
import com.example.paindiary.repository.PainRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class PainViewModel extends AndroidViewModel {
    private PainRepository cRepository;
    private LiveData<List<Pain>> allPains;

    public PainViewModel(Application application) {
        super(application);
        cRepository = new PainRepository(application);
        allPains = cRepository.getAllPains();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public CompletableFuture<Pain> findByIDFuture(final int painId) {
        return cRepository.findByIDFuture(painId);
    }

    public LiveData<List<Pain>> getAllPains() {
        return allPains;
    }

    public void insert(Pain pain) {
        cRepository.insert(pain);
    }

    public void deleteAll() {
        cRepository.deleteAll();
    }

    public void update(Pain pain) {
        cRepository.updatePain(pain);
    }
}
