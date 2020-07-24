package com.example.showingcountrieslist.DI;

import com.example.showingcountrieslist.ui.activities.MainActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class ActivityBuilderModule {

    @PerActivity
    @ContributesAndroidInjector(modules = {MainActivityViewModelModule.class})
    public abstract MainActivity injectMainActivity();

}
