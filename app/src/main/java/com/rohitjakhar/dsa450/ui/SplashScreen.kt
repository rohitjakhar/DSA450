package com.rohitjakhar.dsa450.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.FirebaseAuth
import com.rohitjakhar.dsa450.MainActivity

class SplashScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("FirebaseCheck", "Splash Screen")
        lifecycleScope.launchWhenCreated {
            if (FirebaseAuth.getInstance().currentUser != null) {
                Log.d("FirebaseCheck", "Is login")
                val loginIntent = Intent(this@SplashScreen, MainActivity::class.java)
                startActivity(loginIntent)
                finish()
            } else {
                Log.d("FirebaseCheck", "Is Not login")
                val authIntent = Intent(this@SplashScreen, AuthActivity::class.java)
                startActivity(authIntent)
                finish()
            }
        }
    }
}
