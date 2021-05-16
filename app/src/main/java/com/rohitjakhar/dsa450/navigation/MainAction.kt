package com.rohitjakhar.dsa450.navigation

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.navigation.NavController
import androidx.navigation.compose.navigate

class MainAction(navController: NavController) {
    val upPress: () -> Unit = {
        navController.navigateUp()
    }

    val gotoQuestionList: (Int) -> Unit = { categoryId ->
        navController.navigate("${Screen.QuestionListScreen.route}/$categoryId")
    }

    val openCustomTab: (Context, Uri) -> Unit = { context, openUrlLink ->
        val customIntent = CustomTabsIntent.Builder()
            .build()
        customIntent.launchUrl(context, openUrlLink)
    }
}
