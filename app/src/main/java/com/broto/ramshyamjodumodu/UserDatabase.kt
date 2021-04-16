package com.broto.ramshyamjodumodu

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class, Student::class], version = 1)
abstract class UserDatabase: RoomDatabase() {
    abstract fun getUserDAO(): UserDAO
    abstract fun getStudentDAO(): StudentDAO
}