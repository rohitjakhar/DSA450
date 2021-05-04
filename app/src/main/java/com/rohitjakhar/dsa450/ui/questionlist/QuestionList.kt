package com.rohitjakhar.dsa450.ui.questionlist

import android.content.Intent
import android.net.Uri
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun QuestionList(
    id: Int?,
    questionListViewModel: QuestionListViewModel
) {
    val context = LocalContext.current
    val questionList = id?.let { questionListViewModel.getQuestionList(it) }
    LazyColumn(
        content = {
            if (questionList != null) {
                items(questionList.size) { index: Int ->
                    val question = questionList[index]
                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable {
                                val intent = Intent(Intent.ACTION_VIEW)
                                intent.data = Uri.parse(question.questionLink)
                                context.startActivity(intent)
                            }
                            .border(1.dp, Color.Cyan, RoundedCornerShape(CornerSize(8.dp)))
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = question.questionStatement)
                        }
                    }
                }
            }
        }
    )
}
