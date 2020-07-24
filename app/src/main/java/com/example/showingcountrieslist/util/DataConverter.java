package com.example.showingcountrieslist.util;

import androidx.room.TypeConverter;

import com.example.showingcountrieslist.model.Currency;
import com.example.showingcountrieslist.model.Language;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class DataConverter {

    @TypeConverter
    public String fromCountryLangList(List<Language> countryLang) {
        if (countryLang == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Language>>() {}.getType();
        String json = gson.toJson(countryLang, type);
        return json;
    }

    @TypeConverter
    public List<Language> toCountryLangList(String countryLangString) {
        if (countryLangString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Language>>() {}.getType();
        List<Language> countryLangList = gson.fromJson(countryLangString, type);
        return countryLangList;
    }


    @TypeConverter
    public String fromCountryCurrencyList(List<Currency> countryCurrency) {
        if (countryCurrency == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Currency>>() {}.getType();
        String json = gson.toJson(countryCurrency, type);
        return json;
    }

    @TypeConverter
    public List<Currency> toCountryCurrencyList(String countryCurrencyString) {
        if (countryCurrencyString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Currency>>() {}.getType();
        List<Currency> countryCurrencyList = gson.fromJson(countryCurrencyString, type);
        return countryCurrencyList;
    }

    @TypeConverter
    public String fromCountryLatLongList(List<Double> countryLatlang) {
        if (countryLatlang == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Double>>() {}.getType();
        String json = gson.toJson(countryLatlang, type);
        return json;
    }

    @TypeConverter
    public List<Double> toCountryLatLongList(String countryLatlang) {
        if (countryLatlang == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Double>>() {}.getType();
        List<Double> countryLatlangList = gson.fromJson(countryLatlang, type);
        return countryLatlangList;
    }
}
