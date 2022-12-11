package com.rohan.loginapp.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Query("Select * from user")
    List<User> getAll();

    @Insert
    void insertUser(User user);

    @Query("Select * from user where email_data=:email and pass_data=:password Limit 1")
    User getUser(String email, String password);

}
