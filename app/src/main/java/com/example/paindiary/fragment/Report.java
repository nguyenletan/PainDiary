package com.example.paindiary.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.paindiary.databinding.ReportBinding;
import com.example.paindiary.entity.Pain;
import com.example.paindiary.entity.PainStat;
import com.example.paindiary.viewmodel.PainStatViewModel;
import com.example.paindiary.viewmodel.PainViewModel;
import com.example.paindiary.viewmodel.SharedViewModel;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Report extends Fragment {
    private ReportBinding addBinding;
    private PainStatViewModel painStatViewModel;
    private PainViewModel painViewModel;
    // private List<PainStat> painStat;
    private List<Pain> pains;

    public Report() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the View for this fragment
        addBinding = ReportBinding.inflate(inflater, container, false);
        View view = addBinding.getRoot();

        painStatViewModel = new ViewModelProvider(requireActivity()).get(PainStatViewModel.class);
        painStatViewModel.getPainStatistic().observe(getViewLifecycleOwner(), new
                Observer<List<PainStat>>() {
                    @Override
                    public void onChanged(@Nullable final List<PainStat> painStats) {
                        //adapter.addPains(pains);
                        List<PieEntry> pieEntries = new ArrayList<>();
                        for (PainStat painStat : painStats) {
                            pieEntries.add(new PieEntry(painStat.step, painStat.painLocation));
                        }
                        Legend legend = addBinding.pieChart.getLegend();
                        legend.setTextSize(12);
                        legend.setWordWrapEnabled(true);
                        legend.setEnabled(false);
                        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
                        legend.setDirection(Legend.LegendDirection.LEFT_TO_RIGHT);
                        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
                        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
                        legend.setDrawInside(false);
                        PieDataSet pieDataSet = new PieDataSet(pieEntries, "Steps");
                        pieDataSet.setColors(ColorTemplate.PASTEL_COLORS);
                        pieDataSet.setValueTextSize(16);
                        pieDataSet.setValueTextColor(Color.rgb(255,255,255));
                        PieData pieData = new PieData(pieDataSet);
                        addBinding.pieChart.setData(pieData);

                        addBinding.pieChart.setVisibility(View.VISIBLE);
                        addBinding.pieChart.animateY(800);

                        //addBinding.pieChart.setEntryLabelColor(Color.rgb(255, 255, 255));
                        addBinding.pieChart.setEntryLabelTextSize(14);
                        addBinding.pieChart.setDrawEntryLabels(true);
                        //addBinding.pieChart.(false);

                        //addBinding.pieChart.needsHighlight(0);
                        addBinding.pieChart.setCenterText("Daily Steps");
                        addBinding.pieChart.setCenterTextSize(20);

                        Description description = new Description();
                        description.setText("Daily Steps Chart");
                        addBinding.pieChart.setDescription(description);

                        //addBinding.pieChart.invalidate();
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