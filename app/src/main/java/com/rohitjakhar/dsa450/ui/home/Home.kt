package com.rohitjakhar.dsa450.ui.home

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                        .padding(10.dp)
                        .clickable { navController.navigate("${Screen.QuestionListScreen.route}/${category.categoryId}") }
                        .border(1.dp, Color.Cyan, RoundedCornerShape(CornerSize(8.dp)))


                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp)
                    ) {
                        Text(text = category.categoryName, fontSize = 24.sp, fontStyle = FontStyle.Normal)
                        Text(text = "Total questions ${category.categoryTotalQuestion}")
                    }
                }
            }
        }
    )
}
