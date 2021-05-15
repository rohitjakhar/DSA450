package com.rohitjakhar.dsa450.network

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlin.Exception

class AuthRepo {
    fun loginUser(
        credential: AuthCredential,
        successListener: () -> Unit,
        failureListener: (Exception) -> Unit
    ) {
        Firebase.auth.signInWithCredential(credential)
            .addOnSuccessListener {
                successListener()
            }
            .addOnFailureListener { task ->
                failureListener.invoke(task)
            }
    }
}
