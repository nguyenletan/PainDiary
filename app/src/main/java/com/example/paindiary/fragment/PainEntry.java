package com.example.paindiary.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.paindiary.R;
import com.example.paindiary.databinding.PainEntryBinding;
import com.example.paindiary.entity.Pain;
import com.example.paindiary.viewmodel.PainViewModel;
import com.example.paindiary.viewmodel.SharedViewModel;

public class PainEntry extends Fragment {
    String pain_level;
    String pain_location;
    String mood_level;
    private PainEntryBinding addBinding;
    private PainViewModel painViewModel;

    public PainEntry() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the View for this fragment
        addBinding = PainEntryBinding.inflate(inflater, container, false);
        View view = addBinding.getRoot();

        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.pain_level, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        addBinding.painLevelSpinner.setAdapter(spinnerAdapter);

        addBinding.painLevelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String temp1 = parent.getItemAtPosition(position).toString();
                pain_level = temp1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        addBinding.painLocationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String temp2 = parent.getItemAtPosition(position).toString();
                pain_location = temp2;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        addBinding.moodLevelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String temp3 = parent.getItemAtPosition(position).toString();
                mood_level = temp3;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        painViewModel = new ViewModelProvider(requireActivity()).get(PainViewModel.class);

        addBinding.saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String physical_step;
                physical_step = addBinding.editPhysicalStep.getText().toString();
                String step_goal_control = addBinding.editStepGoal.getText().toString();

                if ((!pain_level.isEmpty() && pain_level != null)) {
                    int physical_steps = Integer.parseInt(physical_step);
                    int step_goal = Integer.parseInt(step_goal_control);
                    Pain pain = new Pain(pain_level, pain_location, mood_level, physical_steps, step_goal);
                    painViewModel.insert(pain);
                }
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
