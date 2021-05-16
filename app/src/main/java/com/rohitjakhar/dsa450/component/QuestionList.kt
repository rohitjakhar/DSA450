package com.rohitjakhar.dsa450.component

import android.content.Context
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.rohitjakhar.dsa450.model.Question
import com.rohitjakhar.dsa450.navigation.MainAction

@Composable
fun QuestionList(
    questionList: List<Question>,
    action: MainAction,
    context: Context
) {

    LazyColumn(
        content = {
            items(questionList.size) { index: Int ->
                val question = questionList[index]
                QuestionCard(
                    question = question,
                    index = index,
                    action = action,
                    context = context
                )
            }
        }
    )
}
