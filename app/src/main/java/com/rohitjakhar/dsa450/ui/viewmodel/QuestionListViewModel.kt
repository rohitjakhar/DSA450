package com.rohitjakhar.dsa450.ui.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rohitjakhar.dsa450.model.Question
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.json.JSONArray

class QuestionListViewModel() : ViewModel() {
    private var _questionList = MutableStateFlow<List<Question>>(emptyList())
    val questionList: StateFlow<List<Question>> get() = _questionList

    fun getQuestionList(application: Context, categoryId: Int) =
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val moshi = com.squareup.moshi.Moshi.Builder()
                    .add(com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory())
                    .build()

                val listType = com.squareup.moshi.Types.newParameterizedType(
                    List::class.java,
                    Question::class.java
                )
                val adapter: com.squareup.moshi.JsonAdapter<List<Question>> =
                    moshi.adapter(listType)

                val myJson = application.assets.open("dsa450.json").bufferedReader().use {
                    it.readText()
                }

                val arr = JSONArray(myJson)
                val arrTopic = arr.getJSONObject(categoryId)
                val questionArray = arrTopic.getJSONArray("questions")
                val ql = adapter.fromJson(questionArray.toString())

                Log.d("TestJson", questionArray.length().toString())

                _questionList.value = ql!!
            } catch (e: Exception) {
                Log.d("TestJson", "error " + e.localizedMessage?.toString())
            }
        }
}
