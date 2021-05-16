package com.rohitjakhar.dsa450.component

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import com.rohitjakhar.dsa450.model.Category
import com.rohitjakhar.dsa450.navigation.MainAction

@Composable
fun CategoryList(
    categoryList: State<List<Category>>,
    action: MainAction
) {
    LazyColumn(
        content = {
            items(categoryList.value.size) { index: Int ->
                val category = categoryList.value[index]
                CategoryCard(
                    action = action,
                    category = category
                )
            }
        }
    )
}
