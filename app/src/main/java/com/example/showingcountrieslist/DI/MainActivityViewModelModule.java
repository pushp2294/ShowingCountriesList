package com.example.showingcountrieslist.DI;

import androidx.lifecycle.ViewModel;

import com.example.showingcountrieslist.viewModels.MainActivityViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract  class MainActivityViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel.class)
    public  abstract ViewModel bindMainActivityViewModel(MainActivityViewModel mainActivityViewModel);

}
