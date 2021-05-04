package com.rohitjakhar.dsa450.ui.questionlist

import androidx.lifecycle.ViewModel
import com.rohitjakhar.dsa450.data.LocalRepository
import com.rohitjakhar.dsa450.model.Question
import kotlinx.coroutines.flow.MutableStateFlow

class QuestionListViewModel : ViewModel() {
    private val localRepository = LocalRepository()
    private var _questionList = MutableStateFlow<List<Question>>(emptyList())
    val questionList: MutableStateFlow<List<Question>> get() = _questionList

    fun getQuestionList(categoryId: Int) =
        localRepository.getQuestionList(categoryId)
}
