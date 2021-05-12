package com.example.paindiary.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Pain {
    @PrimaryKey(autoGenerate = true)
    public int uid;
    @NonNull
    public String painLevel;
    @NonNull
    public String painLocation;
    @NonNull
    public String moodLevel;

    @ColumnInfo(name = "physicalStep")
    @NonNull
    public int physicalStep;

    @ColumnInfo(name = "stepGoal")
    @NonNull
    public int stepGoal;

    public Pain(@NonNull String painLevel, @NonNull String painLocation, @NonNull String moodLevel, int physicalStep, int stepGoal) {
        this.painLevel = painLevel;
        this.painLocation = painLocation;
        this.moodLevel = moodLevel;
        this.physicalStep = physicalStep;
        this.stepGoal = stepGoal;
    }

    public static List<Pain> createPainsList() {
        List<Pain> pains = new ArrayList<Pain>();
        pains.add(new Pain("55", "head", "average", 555, 999));
        pains.add(new Pain("555555", "head", "average", 555, 500));
        return pains;
    }

    public int getStepGoal() {
        return stepGoal;
    }

    public String getPainLevel() {
        return painLevel;
    }

    public String getPainLocation() {
        return painLocation;
    }

    public String getMoodLevel() {
        return moodLevel;
    }

    public int getPhysicalStep() {
        return physicalStep;
    }
}
