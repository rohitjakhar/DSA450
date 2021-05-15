package com.rohitjakhar.dsa450.ui.questionlist

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun QuestionList(
    id: Int? = 0,
    questionListViewModel: QuestionListViewModel
) {
    val context = LocalContext.current
    if (id != null) {
        questionListViewModel.getQuestionList(context, id)
    }
    val questionList = questionListViewModel.questionList.collectAsState().value

    LazyColumn(
        content = {
            items(questionList.size) { index: Int ->
                val question = questionList[index]
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable {
                            openChromeTab(context, Uri.parse(question.questionLink))
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
        }
    )
}

fun openChromeTab(context: Context, questionLink: Uri) {
    val customIntent = CustomTabsIntent.Builder()
        .build()
    customIntent.launchUrl(context, questionLink)
}
