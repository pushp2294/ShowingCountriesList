package com.example.showingcountrieslist.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.showingcountrieslist.util.DataConverter;

import java.util.List;

@Entity(tableName = "CountriesTable")
public class CountriesEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public String capital;
    public Integer population;
    public String flag;

    @TypeConverters(DataConverter.class)
    public List<Double> latlng = null;

    @TypeConverters(DataConverter.class)
    public List<Language> languages = null;

    @TypeConverters(DataConverter.class)
    public List<Currency> currencies = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public List<Double> getLatlng() {
        return latlng;
    }

    public void setLatlng(List<Double> latlng) {
        this.latlng = latlng;
    }
}
