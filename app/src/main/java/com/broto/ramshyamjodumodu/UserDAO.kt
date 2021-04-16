package com.broto.ramshyamjodumodu

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDAO {

    @Query("Select * from user_table where password like :password AND username like :username")
    fun verifyPassword(username: String, password: String): User?

    @Insert
    fun insertUser(vararg user: User)

}