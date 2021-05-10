package com.rohitjakhar.dsa450

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.rohitjakhar.dsa450.navigation.Screen
import com.rohitjakhar.dsa450.ui.about.About
import com.rohitjakhar.dsa450.ui.home.Home
import com.rohitjakhar.dsa450.ui.home.HomeViewModel
import com.rohitjakhar.dsa450.ui.profile.Profile
import com.rohitjakhar.dsa450.ui.questionlist.QuestionList
import com.rohitjakhar.dsa450.ui.questionlist.QuestionListViewModel
import com.rohitjakhar.dsa450.ui.theme.DSA450Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DSA450Theme {
                val navController = rememberNavController()
                val homeViewModel by viewModels<HomeViewModel>()
                val questionListViewModel by viewModels<QuestionListViewModel>()

                Scaffold(
                    bottomBar = {
                        val items = listOf(
                            Screen.HomeScreen,
                            Screen.Profile,
                            Screen.About
                        )
                        BottomNavigation(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .wrapContentWidth(Alignment.CenterHorizontally)
                                .clip(RoundedCornerShape(16.dp))
                        ) {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)
                            items.forEach { screen ->
                                BottomNavigationItem(
                                    icon = { Icon(screen.icons, screen.route) },
                                    label = { Text(stringResource(screen.resourceId)) },
                                    selected = currentRoute == screen.route,
                                    onClick = {
                                        navController.navigate(screen.route) {
                                            popUpTo = navController.graph.startDestination
                                            launchSingleTop = true
                                        }
                                    }
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .padding(innerPadding)
                    ) {

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
                                arguments = listOf(
                                    navArgument("category_id") {
                                        type = NavType.IntType
                                    }
                                )
                            ) { navBackStack ->
                                QuestionList(
                                    id = navBackStack.arguments?.getInt("category_id"),
                                    questionListViewModel = questionListViewModel
                                )
                            }
                            composable(
                                route = Screen.Profile.route
                            ) {
                                Profile(navController = navController)
                            }
                            composable(route = Screen.About.route) {
                                About(navController = navController)
                            }
                        }
                    }
                }
            }
        }
    }
}
