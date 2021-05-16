package com.rohitjakhar.dsa450.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.rohitjakhar.dsa450.R

sealed class Screen(val route: String, @StringRes val resourceId: Int, val icons: ImageVector) {
    object HomeScreen : Screen("nav_home", R.string.home, Icons.Filled.Favorite)
    object QuestionListScreen : Screen(
        "nav_question_list",
        R.string.questionList,
        Icons.Filled.Favorite
    )

    object Profile : Screen("nav_profile", R.string.profile, Icons.Filled.Person)
    object About : Screen("nav_about", R.string.about, Icons.Filled.Info)
}
