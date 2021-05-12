package com.example.paindiary.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.paindiary.adapter.RecyclerViewAdapter;
import com.example.paindiary.databinding.DailyRecordBinding;
import com.example.paindiary.entity.Pain;
import com.example.paindiary.viewmodel.PainViewModel;

import java.util.ArrayList;
import java.util.List;

public class DailyRecord extends Fragment {
    private RecyclerView.LayoutManager layoutManager;
    private List<Pain> pains;
    private RecyclerViewAdapter adapter;
    private PainViewModel painViewModel;
    private DailyRecordBinding addBinding;

    public DailyRecord(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the View for this fragment
        addBinding = DailyRecordBinding.inflate(inflater, container, false);
        View view = addBinding.getRoot();

        pains = new ArrayList<Pain>();
        pains = Pain.createPainsList();

        adapter = new RecyclerViewAdapter(pains);
        //this just creates a line divider between rows
        addBinding.recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        addBinding.recyclerView.setAdapter(adapter);
        addBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        painViewModel = new ViewModelProvider(requireActivity()).get(PainViewModel.class);
        painViewModel.getAllPains().observe(getViewLifecycleOwner(), new
                Observer<List<Pain>>() {
                    @Override
                    public void onChanged(@Nullable final List<Pain> pains) {
                        adapter.addPains(pains);
                    }
                });
        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        addBinding = null;
    }
}
