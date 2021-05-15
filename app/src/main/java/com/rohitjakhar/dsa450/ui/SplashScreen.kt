package com.rohitjakhar.dsa450.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.appupdate.AppUpdateOptions
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import com.google.firebase.auth.FirebaseAuth
import com.rohitjakhar.dsa450.MainActivity

const val UPDATE_REQUEST_TYPE = 3434

class SplashScreen : ComponentActivity() {
    private lateinit var appUpdateManger: AppUpdateManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appUpdateManger = AppUpdateManagerFactory.create(applicationContext)
        checkUpdate()
    }

    private fun checkUpdate() {
        Log.d("updatedebug", "IN Check Update")
        val appUpdateInfoTask = appUpdateManger.appUpdateInfo
        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE) {

                Log.d("updatedebug", "Update Available ")
                appUpdateManger.startUpdateFlowForResult(
                    appUpdateInfo,
                    this,
                    AppUpdateOptions.newBuilder(AppUpdateType.IMMEDIATE)
                        .setAllowAssetPackDeletion(true)
                        .build(),
                    UPDATE_REQUEST_TYPE
                )
            } else {

                Log.d("updatedebug", "Update not found")
                checkLogin()
            }
        }
            .addOnFailureListener {
                Log.d("updatedebug", "Failed: $it: ${it.localizedMessage}")
                checkLogin()
            }
    }

    private fun checkLogin() {
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

    override fun onResume() {
        super.onResume()
        appUpdateManger
            .appUpdateInfo
            .addOnSuccessListener {
                if (it.updateAvailability() == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS) {
                    appUpdateManger.startUpdateFlowForResult(
                        it,
                        AppUpdateType.IMMEDIATE,
                        this,
                        UPDATE_REQUEST_TYPE
                    )
                } else {
                    checkLogin()
                }
            }
            .addOnFailureListener {
                Log.d("updatedebug", "Failed: $it: ${it.localizedMessage}")
                checkLogin()
            }
    }
}
