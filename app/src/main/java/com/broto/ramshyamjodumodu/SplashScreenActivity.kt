package com.broto.ramshyamjodumodu

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.*

class SplashScreenActivity : AppCompatActivity() {

    val activityScope = CoroutineScope(Dispatchers.Main)

    private lateinit var splashAnimation: AnimationDrawable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        activityScope.launch {
            Log.d("Splash", "Testing from co")
//            val db = Room.databaseBuilder(
//                applicationContext,
//                UserDatabase::class.java,
//                Constants.DB_NAME
//            ).build()
//
//            Thread {
//                val userDao = db.getUserDAO()
//                userDao.insertUser(User(1, "Ritam", "Manna"))
//            }.start()

            delay(3000)

            var intent = Intent(this@SplashScreenActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    override fun onResume() {
        iv_splash.apply {
            setBackgroundResource(R.drawable.animation_splash)
            splashAnimation = background as AnimationDrawable
        }
        splashAnimation.start()
        super.onResume()
    }

    override fun onPause() {
        splashAnimation.stop()
        super.onPause()
    }
}
