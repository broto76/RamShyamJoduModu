package com.broto.ramshyamjodumodu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_home_page.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomePageActivity : AppCompatActivity() {

    private val TAG = "HomePageActivity"

    var username: String? = null

    private val ioScope = CoroutineScope(Dispatchers.IO)

    private var adapter: StudentDataAdapter? = null
    private lateinit var students: List<Student>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        rv_data.layoutManager = LinearLayoutManager(applicationContext)

        username = intent.getStringExtra(Constants.KEY_USERNAME)
        tv_homepage_username.text = "Welcome\n $username"

        floatingActionButton.setOnClickListener {
            startActivity(Intent(this@HomePageActivity, UpdateDetailsActivity::class.java))
        }

        floatingActionButtonWeather.setOnClickListener {
            val intent = Intent(this@HomePageActivity, WeatherActivity::class.java)
            intent.putExtra(Constants.KEY_USERNAME, username)
            startActivity(intent)
        }

        //getweather()
    }

    override fun onResume() {
        super.onResume()
        ioScope.launch {
            val db = Room.databaseBuilder(
                applicationContext,
                UserDatabase::class.java,
                Constants.DB_NAME
            ).build()
            val studentdao = db.getStudentDAO()

            students = studentdao.getAllStudents()
//            for (student in students) {
//                Log.d("HomeActivity","Name: ${student.name}")
//            }

            db.close()

            runOnUiThread {
                adapter = StudentDataAdapter(students, this@HomePageActivity)
                rv_data.adapter = adapter
            }
        }
    }

    fun refreshPage(student: Student) {
        runOnUiThread {
            (students as ArrayList).remove(student)
            adapter?.notifyDataSetChanged()
        }
    }

//    private fun populate() {
//        ioScope.launch {
//
//            val db = Room.databaseBuilder(
//                applicationContext,
//                UserDatabase::class.java,
//                Constants.DB_NAME
//            ).build()
//            val studentdao = db.getStudentDAO()
//
//            for (i in 1 .. 20) {
//                studentdao.insertStudent(Student(i, i.toString(), i.toString(), i))
//            }
//
//            db.close()
//            finish()
//        }
//    }
}
