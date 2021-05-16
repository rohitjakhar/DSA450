package com.rohitjakhar.dsa450.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.* // ktlint-disable no-wildcard-imports
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rohitjakhar.dsa450.model.Category
import com.rohitjakhar.dsa450.navigation.MainAction

@Composable
fun CategoryCard(
    action: MainAction,
    category: Category
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                action.gotoQuestionList(category.categoryId)
            }
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
            Spacer(modifier = Modifier.padding(0.dp, 8.dp))
            Text(
                text = "Total Question ${category.totalQuestion}",
                fontSize = 24.sp,
                fontStyle = FontStyle.Normal
            )
        }
    }
}
