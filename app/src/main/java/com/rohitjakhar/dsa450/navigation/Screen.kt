package com.rohitjakhar.dsa450.navigation

sealed class Screen(val route: String, val resourceId: String) {
    object HomeScreen : Screen("nav_home", "Home")
    object QuestionListScreen : Screen("nav_question_list", "Question List")
}
