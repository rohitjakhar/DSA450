package com.rohitjakhar.dsa450.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.rohitjakhar.dsa450.model.Category
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private var _categoryList: MutableStateFlow<List<Category>> = MutableStateFlow(emptyList())
    val categoryList: StateFlow<List<Category>> get() = _categoryList

    private fun getAllCategory(application: Application) = viewModelScope.launch(Dispatchers.IO) {
        try {
            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

            val listType = Types.newParameterizedType(List::class.java, Category::class.java)
            val adapter: JsonAdapter<List<Category>> = moshi.adapter(listType)
            val myJson = application.assets.open("dsa450.json").bufferedReader().use {
                it.readText()
            }

            _categoryList.value = adapter.fromJson(myJson)!!
        } catch (e: Exception) {
            Log.d("Error", e.message.toString())
        }
    }

    init {
        getAllCategory(application = application)
    }
}
