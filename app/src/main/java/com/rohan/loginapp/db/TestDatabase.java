package com.rohan.loginapp.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class},version = 1)
public abstract class TestDatabase extends RoomDatabase {
    public abstract UserDao getUserDao();
}
