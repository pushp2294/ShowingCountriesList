package com.example.showingcountrieslist.roomdatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.showingcountrieslist.model.CountriesEntity;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface DaoInterface {

    @Query("SELECT * from CountriesTable")
    LiveData<List<CountriesEntity>> getAllList();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Single<List<Long>> insertanewData(List<CountriesEntity> countriesEntityList);

    @Query("DELETE FROM CountriesTable")
    Single<Integer> deleteAll();



}
