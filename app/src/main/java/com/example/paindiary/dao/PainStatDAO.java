package com.example.paindiary.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.paindiary.entity.Pain;
import com.example.paindiary.entity.PainStat;

import java.util.List;

@Dao
public interface PainStatDAO {
    //@Query("SELECT painLocation || ' ' || CAST(count(*) as String) FROM pain GROUP BY painLocation ORDER BY painLocation ASC")
    @Query("SELECT painLocation, count(*) as step  FROM pain GROUP BY painLocation ORDER BY painLocation ASC")
    LiveData<List<PainStat>> getPainStatistic();
}
