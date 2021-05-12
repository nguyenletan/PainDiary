package com.example.paindiary.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity
public class PainStat {
    @ColumnInfo(name = "painLocation")
    @PrimaryKey
    @NonNull
    public String painLocation;

    @NonNull
    @ColumnInfo(name = "step")
    public int step;

    public PainStat(String painLocation, int step) {
        this.step = step;
        this.painLocation = painLocation;
    }
}

