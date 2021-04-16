package com.broto.ramshyamjodumodu

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    var currentUser: User? = null
    var mProcessedDefaultLogin = false

    val ioScope = CoroutineScope(Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        ll_login_form.visibility = View.GONE

        processDefaultLogin()

        btn_submit.setOnClickListener {

            if (!mProcessedDefaultLogin) {
                Log.d("Login","Default Login is still processing")
                Toast.makeText(this@LoginActivity, "Please Wait..", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val db = Room.databaseBuilder(
                applicationContext,
                UserDatabase::class.java,
                Constants.DB_NAME
            ).build()

            ioScope.launch {
                val userDao = db.getUserDAO()
                currentUser = userDao.verifyPassword(et_username.text.toString(),
                    et_password.text.toString())

                db.close()

                Log.d("Login", "defaultScope")

                runOnUiThread{
                    if (currentUser == null) {
                        Toast.makeText(this@LoginActivity, "Validation failed", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@LoginActivity, "Validation passed", Toast.LENGTH_SHORT).show()
                        storeLoginCreds(et_username.text.toString(),
                            et_password.text.toString())
                        val intent = Intent(this@LoginActivity, HomePageActivity::class.java)
                        intent.putExtra(Constants.KEY_USERNAME, currentUser?.username)
                        startActivity(intent)
                        finish()
                    }
                }
            }

        }
    }

    private fun storeLoginCreds(username: String, password: String) {
        var pref = getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE)
        val editor = pref.edit()

        editor.putString(Constants.PREF_USERNAME, username)
        editor.putString(Constants.PREF_PASSWORD, password)

        editor.apply()
    }

    private fun processDefaultLogin() {
        var pref = getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE)
        val username = pref.getString(Constants.PREF_USERNAME, "") ?: ""
        val password = pref.getString(Constants.PREF_PASSWORD, "") ?: ""

        if (username.isNotEmpty() && password.isNotEmpty()) {
            ioScope.launch {
                val db = Room.databaseBuilder(
                    applicationContext,
                    UserDatabase::class.java,
                    Constants.DB_NAME
                ).build()

                val userDao = db.getUserDAO()
                currentUser = userDao.verifyPassword(username, password)

                db.close()

                if (currentUser == null) {
                    Log.d("Login", "Unable to validate login with stored preferences")
                    mProcessedDefaultLogin = true
                    ll_login_form.visibility = View.VISIBLE
                } else {
                    Log.d("Login", "Logging in with shared preferences")
                    runOnUiThread {
                        val intent = Intent(this@LoginActivity, HomePageActivity::class.java)
                        intent.putExtra(Constants.KEY_USERNAME, currentUser?.username)
                        startActivity(intent)
                        finish()
                    }
                }
            }
        } else {
            Log.d("Login","No username/password stored in preferences")
            mProcessedDefaultLogin = true
            ll_login_form.visibility = View.VISIBLE
        }

    }
}
