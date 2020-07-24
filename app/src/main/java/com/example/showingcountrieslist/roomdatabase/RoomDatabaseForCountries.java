package com.example.showingcountrieslist.roomdatabase;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.showingcountrieslist.model.CountriesEntity;

@Database(entities = {CountriesEntity.class}, version = 4)
public abstract class RoomDatabaseForCountries extends RoomDatabase {

    public abstract DaoInterface getPromptReminderDaoInstance();

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }
}
