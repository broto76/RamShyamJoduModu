package com.broto.ramshyamjodumodu

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student_table")
data class Student(
    @PrimaryKey(autoGenerate = true) var rollNumber: Int,
    @ColumnInfo var name: String,
    @ColumnInfo var address: String,
    @ColumnInfo var studentClass: Int
)