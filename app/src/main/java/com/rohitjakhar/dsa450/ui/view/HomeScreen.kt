package com.rohitjakhar.dsa450.ui.home

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.rohitjakhar.dsa450.component.CategoryList
import com.rohitjakhar.dsa450.navigation.MainAction
import com.rohitjakhar.dsa450.ui.viewmodel.HomeViewModel

@Composable
fun Home(
    homeViewModel: HomeViewModel,
    navController: NavController
) {
    val categoryList = homeViewModel.categoryList.collectAsState()
    Scaffold {
        CategoryList(
            categoryList = categoryList,
            action = MainAction(navController = navController)
        )
    }
}
