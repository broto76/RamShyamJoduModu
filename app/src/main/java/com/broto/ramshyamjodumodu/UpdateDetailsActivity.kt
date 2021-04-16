package com.broto.ramshyamjodumodu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_update_details.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class UpdateDetailsActivity : AppCompatActivity() {

    private val TAG = "UpdateDetailsActivity"

    private val ioScope = CoroutineScope(Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_details)

        restoreData()

        btn_submit.setOnClickListener {
            val name = et_name.text.toString()
            val rollNumber = et_roll.text.toString()
            val className = et_class.text.toString()
            val address = et_address.text.toString()

            if (name.isEmpty() || rollNumber.isEmpty() ||
                className.isEmpty() || address.isEmpty()) {
                Log.d(TAG, "Input is empty")
                return@setOnClickListener
            }

            ioScope.launch {

                val db = Room.databaseBuilder(
                    applicationContext,
                    UserDatabase::class.java,
                    Constants.DB_NAME
                ).build()
                val studentdao = db.getStudentDAO()

                val student = studentdao.getStudent(rollNumber.toInt())
                if (student == null) {
                    // Student does not exist. Insert to table
                    studentdao.insertStudent(Student(rollNumber.toInt(), name, address, className.toInt()))
                } else {
                    // Student exists. Update the entry
                    studentdao.updateStudent(Student(rollNumber.toInt(), name, address, className.toInt()))
                }

                db.close()
                finish()
            }
        }

    }

    private fun restoreData() {
        val name = intent.getStringExtra(Constants.KEY_NAME)
        val rollNumber = intent.getStringExtra(Constants.KEY_ROLL_NO)
        val className = intent.getStringExtra(Constants.KEY_CLASS)
        val address = intent.getStringExtra(Constants.KEY_ADDRESS)

        if (name == null || rollNumber == null || className == null || address == null) {
            Log.d(TAG, "Intent extra details is/are null")
            return
        }

        et_name.setText(name.toString())
        et_roll.setText(rollNumber.toString())
        et_address.setText(address.toString())
        et_class.setText(className.toString())

        btn_submit.text = "Update Data"

    }
}
