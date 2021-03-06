package com.rohitjakhar.dsa450.component

import android.content.Context
import android.net.Uri
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.rohitjakhar.dsa450.model.Question
import com.rohitjakhar.dsa450.navigation.MainAction

@Composable
fun QuestionCard(
    action: MainAction,
    question: Question,
    index: Int,
    context: Context
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                action.openCustomTab(context, Uri.parse(question.questionLink))
            }
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, Color(0xFFEDC126), RoundedCornerShape(CornerSize(8.dp)))
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalArrangement = Arrangement.Start,
        ) {
            Text(
                text = (index + 1).toString(),
                Modifier.padding(0.dp, 0.dp, 4.dp, 0.dp)
            )
            Text(text = question.questionStatement)
        }
    }
}
