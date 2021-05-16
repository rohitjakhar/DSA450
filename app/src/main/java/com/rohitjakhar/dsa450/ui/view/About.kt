package com.rohitjakhar.dsa450.ui.about

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.play.core.review.testing.FakeReviewManager
import com.rohitjakhar.dsa450.R
import com.rohitjakhar.dsa450.utils.Constant.GITHUB_REPO
import com.rohitjakhar.dsa450.utils.Constant.LOVE_BABBAR_YT
import com.rohitjakhar.dsa450.utils.Constant.ROHIT_GITHUB

@Composable
fun About(mainActivity: Activity) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("DSA 450", fontWeight = FontWeight.Bold, fontSize = 32.sp)
        Spacer(modifier = Modifier.padding(0.dp, 16.dp))
        Text(text = context.resources.getString(R.string.about_des))
        Spacer(modifier = Modifier.padding(0.dp, 16.dp))
        Row() {
            Text(text = "Credit: ")
            Text(
                text = "Love Babbar",
                Modifier
                    .clickable {
                        openLink(context = context, Uri.parse(LOVE_BABBAR_YT))
                    },
                color = Color.Red
            )
        }

        Spacer(modifier = Modifier.padding(0.dp, 16.dp))
        Row() {
            Text(text = "Project by ")
            Text(
                text = "Rohit Jakhar",
                Modifier
                    .clickable {
                        openLink(context, Uri.parse(ROHIT_GITHUB))
                    },
                color = Color.Red
            )
        }
        Spacer(modifier = Modifier.padding(0.dp, 16.dp))
        Row() {
            Text(text = "Github Repo Link: ")
            Text(
                text = "DSA 450 ",
                Modifier
                    .clickable {
                        openLink(context, Uri.parse(GITHUB_REPO))
                    },
                color = Color.Red
            )
        }
        Spacer(modifier = Modifier.padding(0.dp, 16.dp))
        Button(
            onClick = {
                val manager = FakeReviewManager(context)
                val request = manager.requestReviewFlow()
                request.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        manager.launchReviewFlow(
                            mainActivity,
                            task.result
                        )
                            .addOnSuccessListener {
                            }
                            .addOnFailureListener {
                            }
                    } else {
                    }
                }
            }
        ) {
            Text(text = "Give Review")
        }
    }
}

private fun openLink(
    context: Context,
    link: Uri
) {
    context.startActivity(Intent(Intent.ACTION_VIEW, link))
}
