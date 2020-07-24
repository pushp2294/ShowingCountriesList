package com.example.showingcountrieslist.DI;

import android.app.Application;

import androidx.room.Room;

import com.example.showingcountrieslist.roomdatabase.DaoInterface;
import com.example.showingcountrieslist.roomdatabase.RoomDatabaseForCountries;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public  class LocalDataBaseModule {

    @Singleton
    @Provides
    static RoomDatabaseForCountries providesRoomDatabase(Application mApplication) {
        return Room.databaseBuilder(mApplication, RoomDatabaseForCountries.class, "CNGNEW").build();
    }

    @Singleton
    @Provides
    public  DaoInterface getdaoInterfaceObject(RoomDatabaseForCountries roomDatabaseForCountries)
    {
        return roomDatabaseForCountries.getPromptReminderDaoInstance();
    }
}
