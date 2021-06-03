package com.rohitjakhar.dsa450.ui.home

import android.util.Log
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
    Log.d("TestJson: CategoryList", categoryList.value.toString())
    Scaffold {
        CategoryList(
            categoryList = categoryList,
            action = MainAction(navController = navController)
        )
    }
}
