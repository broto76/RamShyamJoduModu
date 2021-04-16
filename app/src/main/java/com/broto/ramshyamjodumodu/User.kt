package com.broto.ramshyamjodumodu

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(@PrimaryKey(autoGenerate = true) var uid: Int,
                @ColumnInfo var username: String,
                @ColumnInfo var password: String)