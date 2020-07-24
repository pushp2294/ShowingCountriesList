package com.example.showingcountrieslist.viewModels;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.showingcountrieslist.model.ApiCallBusinessLogic;

import javax.inject.Inject;

public class MainActivityViewModel extends ViewModel {
    @Inject
    ApiCallBusinessLogic apiCallBusinessLogic;

    @Inject
    public MainActivityViewModel() {
    }
    public MutableLiveData<String> reFreshTheNewCountrylist() {
        return apiCallBusinessLogic.getCountryDetails();

    }


}

