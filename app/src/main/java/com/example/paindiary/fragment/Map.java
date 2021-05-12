package com.example.paindiary.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.paindiary.databinding.MapBinding;
import com.example.paindiary.viewmodel.SharedViewModel;

public class Map extends Fragment {
    private SharedViewModel model;
    private MapBinding addBinding;

    public Map(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the View for this fragment
        addBinding = MapBinding.inflate(inflater, container, false);
        View view = addBinding.getRoot();
        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        addBinding = null;
    }
}