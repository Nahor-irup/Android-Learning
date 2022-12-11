package com.rohan.loginapp.db;

import androidx.annotation.NonNull;
import androidx.annotation.Size;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "email_data")
    @NonNull
    public String email;

    @ColumnInfo(name = "pass_data")
    @NonNull
    public String password;
}
