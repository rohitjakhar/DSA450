package com.rohitjakhar.dsa450.ui.questionlist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import com.rohitjakhar.dsa450.component.QuestionList
import com.rohitjakhar.dsa450.navigation.MainAction
import com.rohitjakhar.dsa450.ui.viewmodel.QuestionListViewModel

@Composable
fun QuestionListScreen(
    id: Int? = 0,
    questionListViewModel: QuestionListViewModel,
    action: MainAction
) {
    val context = LocalContext.current
    if (id != null) {
        questionListViewModel.getQuestionList(context, id)
    }
    val questionList = questionListViewModel.questionList.collectAsState().value

    QuestionList(questionList = questionList, action = action, context = context)
}
