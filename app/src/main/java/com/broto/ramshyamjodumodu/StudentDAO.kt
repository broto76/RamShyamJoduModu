package com.broto.ramshyamjodumodu

import androidx.room.*

@Dao
interface StudentDAO {

    @Insert
    fun insertStudent(vararg student: Student)

    @Query("Select * from student_table")
    fun getAllStudents(): List<Student>

    @Query("Select * from student_table where rollNumber like :rollNumber")
    fun getStudent(rollNumber: Int): Student?

    @Update
    fun updateStudent(vararg student: Student)

    @Delete
    fun deleteStudent(vararg student: Student)
}