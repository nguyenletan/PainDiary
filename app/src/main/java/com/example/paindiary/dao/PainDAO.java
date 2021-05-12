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
public interface PainDAO {
    @Query("SELECT * FROM pain ORDER BY uid ASC")
    LiveData<List<Pain>> getAll();

    @Query("SELECT * FROM pain WHERE uid = :painId LIMIT 1")
    Pain findByID(int painId);

    @Query("SELECT painLocation, count(*) as step  FROM pain GROUP BY painLocation ORDER BY painLocation ASC")
    LiveData<List<PainStat>> getPainStatistic();

    @Insert
    void insert(Pain pain);

    @Delete
    void delete(Pain pain);

    @Update
    void updatePain(Pain pain);

    @Query("DELETE FROM pain")
    void deleteAll();
}
