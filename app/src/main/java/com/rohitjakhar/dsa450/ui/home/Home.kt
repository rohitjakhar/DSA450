package com.rohitjakhar.dsa450.ui.home

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
    val categoryList = homeViewModel.categoryList.collectAsState()
    Log.d("debug450", "Load Home")
    Log.d("debug450", categoryList.value.toString())
    Scaffold {

        LazyColumn(
            content = {
                items(categoryList.value.size) { index: Int ->
                    val category = categoryList.value[index]
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .clickable { navController.navigate("${Screen.QuestionListScreen.route}/${category.categoryId}") }
                            .border(2.dp, Color(0xFFEDC126), RoundedCornerShape(16.dp))

                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(10.dp)
                        ) {
                            Text(
                                text = category.categoryName,
                                fontSize = 24.sp,
                                fontStyle = FontStyle.Normal
                            )
                        }
                    }
                }
            }
        )
    }
}
