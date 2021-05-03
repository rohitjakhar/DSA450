package com.rohitjakhar.dsa450.ui.home

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.rohitjakhar.dsa450.navigation.Screen

@Composable
fun Home(
    homeViewModel: HomeViewModel,
    navController: NavController
) {
    val categoryList = homeViewModel.categoryList
    Log.d("debug450", "Load Home")
    LazyColumn(
        content = {
            items(categoryList.value.size) { index: Int ->
                val category = categoryList.value[index]
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { navController.navigate("${Screen.QuestionListScreen.route}/${category.categoryId}") }
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(text = category.categoryName)
                    }
                }
            }
        }
    )
}
