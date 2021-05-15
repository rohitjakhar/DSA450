package com.rohitjakhar.dsa450.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.* // ktlint-disable no-wildcard-imports
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.rohitjakhar.dsa450.AuthViewModel
import com.rohitjakhar.dsa450.MainActivity
import com.rohitjakhar.dsa450.R
import com.rohitjakhar.dsa450.ui.theme.DSA450Theme
import kotlinx.coroutines.flow.collect

class AuthActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DSA450Theme {
                val context = LocalContext.current
                val authViewModel by viewModels<AuthViewModel>()
                val gso =
                    rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { it ->
                        if (it.resultCode == RESULT_OK) {
                            val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
                            Log.d("authde", "in try")
                            val account = task.getResult(ApiException::class.java)
                            val credential =
                                GoogleAuthProvider.getCredential(account!!.idToken, null)
                            authViewModel.signing(credential = credential)
                            lifecycleScope.launchWhenStarted {
                                authViewModel.loginState.collect { state ->
                                    when (state) {
                                        is AuthViewModel.Companion.Events.Success -> {
                                            // Login
                                            Toast.makeText(
                                                context,
                                                "Suceess ${state.successMessage}",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            startActivity(Intent(context, MainActivity::class.java))
                                            finish()
                                        }
                                        is AuthViewModel.Companion.Events.Failure -> {
                                            // Failed
                                            Toast.makeText(
                                                context,
                                                "Error ${state.failureMessage}",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                        is AuthViewModel.Companion.Events.Loading -> {
                                            // still login show progress bar
                                        }
                                    }
                                }
                            }
                        } else {
                            Toast.makeText(context, "Error: ${it.data}", Toast.LENGTH_SHORT).show()
                            return@rememberLauncherForActivityResult
                        }
                    }

                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painterResource(id = R.drawable.ic_login_logo),
                            contentDescription = "Login", contentScale = ContentScale.Inside
                        )

                        Spacer(modifier = Modifier.padding(0.dp, 16.dp))
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Button(
                                onClick = {
                                    val gsp =
                                        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                                            .requestIdToken(
                                                "882260534441-9v9kkiikofnamvqt625iuo677v9c55l5.apps.googleusercontent.com"
                                            )
                                            .requestEmail()
                                            .build()
                                    val googleSignInClient = GoogleSignIn.getClient(context, gsp)
                                    val signingINtent = googleSignInClient.signInIntent
                                    gso.launch(signingINtent)
                                },
                            ) {
                                Text("Login")
                            }
                        }
                        Spacer(modifier = Modifier.padding(0.dp, 16.dp))
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Button(
                                onClick = {
                                    startActivity(Intent(context, MainActivity::class.java))
                                    finish()
                                },
                            ) {
                                Text("Anonymously")
                            }
                        }
                    }
                }
            }
        }
    }
}
