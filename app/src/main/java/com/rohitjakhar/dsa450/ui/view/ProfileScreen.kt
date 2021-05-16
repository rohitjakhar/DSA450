package com.rohitjakhar.dsa450.ui.profile

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.* // ktlint-disable no-wildcard-imports
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.transform.CircleCropTransformation
import com.google.accompanist.coil.rememberCoilPainter
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.rohitjakhar.dsa450.utils.Constant.DEFAULT_USER_IMAGE
import com.rohitjakhar.dsa450.ui.SplashScreen

@Composable
fun Profile(
    navController: NavController,
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        val userinfo = Firebase.auth.currentUser

        Image(
            painter = rememberCoilPainter(
                request = userinfo?.photoUrl ?: DEFAULT_USER_IMAGE,
                requestBuilder = {
                    transformations(CircleCropTransformation())
                }
            ),
            contentDescription = "Image"
        )

        Spacer(modifier = Modifier.padding(0.dp, 16.dp))
        Box(
            Modifier
                .border(2.dp, Color.Cyan)
                .padding(26.dp)
        ) {
            Text(text = userinfo?.displayName ?: "null")
        }

        Spacer(modifier = Modifier.padding(0.dp, 16.dp))
        Box(
            Modifier
                .border(2.dp, Color.Cyan)
                .padding(26.dp)
        ) {
            Text(text = userinfo?.email ?: "null")
        }
        Spacer(modifier = Modifier.padding(0.dp, 16.dp))

        Column(
            verticalArrangement = Arrangement.Bottom
        ) {
            Button(
                onClick = {
                    Firebase.auth.signOut()
                    context.startActivity(Intent(context.applicationContext, SplashScreen::class.java))
                },
            ) {
                Text("Logout")
            }
        }
    }
}
