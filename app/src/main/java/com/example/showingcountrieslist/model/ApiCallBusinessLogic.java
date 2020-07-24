package com.example.showingcountrieslist.model;
import android.annotation.SuppressLint;

import androidx.lifecycle.MutableLiveData;

import com.example.showingcountrieslist.roomdatabase.DaoInterface;
import com.example.showingcountrieslist.roomdatabase.RoomDatabaseForCountries;
import com.example.showingcountrieslist.util.ApiServices;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class ApiCallBusinessLogic {
    @Inject
    ApiServices apiServices;
    @Inject
    RoomDatabaseForCountries roomDatabaseForCountries;
    @Inject
    DaoInterface daoInterface;
    MutableLiveData<String> countriesMutableList;

    @Inject
    public ApiCallBusinessLogic() {
    }

    public MutableLiveData<String> getCountryDetails() {
        countriesMutableList = new MutableLiveData<>();

        apiServices.fetchCurrentCountryDataList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<CountriesEntity>>() {
                    @Override
                    public void onSuccess(List<CountriesEntity> countriesEntityList) {
                        deletAndUpdateData(countriesEntityList);
                    }
                    @Override
                    public void onError(Throwable e) {
                        countriesMutableList.setValue("error");
                    }
                });
        return countriesMutableList;

    }

    public void insertData(final List<CountriesEntity> countriesEntityList) {
                Single<List<Long>> insertObservable = daoInterface.insertanewData(countriesEntityList);
        insertObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Long>>() {
                    @SuppressLint("CheckResult")
                    @Override
                    public void onSuccess(List<Long> data) {
                        countriesMutableList.setValue("success");
                    }
                    @Override
                    public void onError(Throwable e) {
                        countriesMutableList.setValue("error");
                    }
                });
    }

    public void deletAndUpdateData(final List<CountriesEntity> countriesEntityList) {
        Single<Integer> insertObservable = daoInterface.deleteAll();
        insertObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Integer>() {
                    @Override
                    public void onSuccess(Integer id) {
                        insertData(countriesEntityList);
                    }
                    @Override
                    public void onError(Throwable e) {
                        countriesMutableList.setValue("error");
                    }
                });
    }
}
