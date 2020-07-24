package com.example.showingcountrieslist.DI;

import android.app.Application;

import com.example.showingcountrieslist.BaseApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

@Singleton
@Component(modules = {AndroidInjectionModule.class,ViewModelFactoryModule.class,ActivityBuilderModule.class,ApiCallModule.class,LocalDataBaseModule.class})
public interface AppComponent extends AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder
    {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }
}
