package com.example.paindiary.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.Date;
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
    @ColumnInfo(name = "temperature")
    public double temperature;
    @ColumnInfo(name = "humidity")
    public double humidity;
    @ColumnInfo(name = "pressure")
    public double pressure;
    @ColumnInfo(name = "createdDate")
    public Date createdDate;

    public Pain(@NonNull String painLevel, @NonNull String painLocation, @NonNull String moodLevel, int physicalStep, int stepGoal, double temperature, double humidity, double pressure, Date createdDate) {
        this.painLevel = painLevel;
        this.painLocation = painLocation;
        this.moodLevel = moodLevel;
        this.physicalStep = physicalStep;
        this.stepGoal = stepGoal;
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.createdDate = createdDate;
    }

    public static List<Pain> createPainsList() {
        List<Pain> pains = new ArrayList<Pain>();
        pains.add(new Pain("55", "head", "average", 555, 999, 0, 0, 0, new Date()));
        pains.add(new Pain("555555", "head", "average", 555, 500, 0, 0, 0, new Date()));
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
