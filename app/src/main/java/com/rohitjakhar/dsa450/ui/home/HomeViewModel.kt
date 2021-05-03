package com.rohitjakhar.dsa450.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rohitjakhar.dsa450.data.LocalRepository
import com.rohitjakhar.dsa450.model.Category
import com.rohitjakhar.dsa450.model.Question
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val localRepo = LocalRepository()
    private var _categoryList: MutableStateFlow<List<Category>> = MutableStateFlow(emptyList())
    val categoryList: StateFlow<List<Category>> get() = _categoryList

    init {
        viewModelScope.launch {
            _categoryList.value = localRepo.listOfCategory
        }
    }
}
