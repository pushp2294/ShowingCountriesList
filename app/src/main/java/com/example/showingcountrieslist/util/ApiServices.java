package com.example.showingcountrieslist.util;

import com.example.showingcountrieslist.model.CountriesEntity;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServices {

    @GET("rest/v2/all")
    Single<List<CountriesEntity>> fetchCurrentCountryDataList();
}
