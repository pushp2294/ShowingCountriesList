package com.example.showingcountrieslist.DI;

import androidx.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelFactoryModule {
    @Binds
   public abstract ViewModelProvider.Factory providerFactory(ViewModelProviderFactory providerFactory);
}
