package com.rohitjakhar.dsa450

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.rohitjakhar.dsa450.navigation.Screen
import com.rohitjakhar.dsa450.ui.home.Home
import com.rohitjakhar.dsa450.ui.home.HomeViewModel
import com.rohitjakhar.dsa450.ui.questionlist.QuestionList
import com.rohitjakhar.dsa450.ui.questionlist.QuestionListViewModel
import com.rohitjakhar.dsa450.ui.theme.DSA450Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DSA450Theme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                val homeViewModel by viewModels<HomeViewModel>()
                val questionListViewModel by viewModels<QuestionListViewModel>()
                NavHost(
                    navController,
                    startDestination = Screen.HomeScreen.route
                ) {
                    composable(Screen.HomeScreen.route) {
                        Home(
                            homeViewModel = homeViewModel,
                            navController = navController
                        )
                    }
                    composable(
                        route = "${Screen.QuestionListScreen.route}/{category_id}",
                        arguments = listOf(navArgument("category_id") { type = NavType.IntType })
                    ) { navBackStack ->
                        QuestionList(
                            id = navBackStack.arguments?.getInt("category_id"),
                            questionListViewModel = questionListViewModel,
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}
