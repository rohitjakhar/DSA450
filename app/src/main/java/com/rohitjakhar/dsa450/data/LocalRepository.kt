package com.rohitjakhar.dsa450.data

import com.rohitjakhar.dsa450.data.questiondata.ArrayQuestion

class LocalRepository {

    fun getCategory() = listOfCategory

    fun getQuestionList(categoryId: Int) = when (categoryId) {
        1 -> { arrayQuestion }
        else -> null
    }

    private val listOfCategory = listOf(
        CategoryData.ArrayQuestion,
        CategoryData.StringQuestion,
        CategoryData.MatrixQuestion,
        CategoryData.SearchingQuestion,
        CategoryData.LinkedListQuestion,
        CategoryData.BinaryTreesQuestion,
        CategoryData.BSTQuestion,
        CategoryData.GreedyQuestion,
        CategoryData.BacktrackingQuestion,
        CategoryData.StackQueuesQuestion,
        CategoryData.HeapQuestion,
        CategoryData.GraphQuestion,
        CategoryData.TrieQuestion,
        CategoryData.DynamicPQuestion,
        CategoryData.BitManipulationQuestion,
    )

    val arrayQuestion = listOf(
        ArrayQuestion.arr1,
        ArrayQuestion.arr2,
        ArrayQuestion.arr3,
        ArrayQuestion.arr4,
        ArrayQuestion.arr5,
        ArrayQuestion.arr6,
        ArrayQuestion.arr7,
        ArrayQuestion.arr9,
        ArrayQuestion.arr10,
        ArrayQuestion.arr11,
        ArrayQuestion.arr12,
        ArrayQuestion.arr13,
        ArrayQuestion.arr14,
        ArrayQuestion.arr15,
        ArrayQuestion.arr16,
        ArrayQuestion.arr17,
        ArrayQuestion.arr18,
        ArrayQuestion.arr19,
        ArrayQuestion.arr20,
        ArrayQuestion.arr22,
        ArrayQuestion.arr23,
        ArrayQuestion.arr24,
        ArrayQuestion.arr25,
        ArrayQuestion.arr26,
        ArrayQuestion.arr27,
        ArrayQuestion.arr28,
        ArrayQuestion.arr29,
        ArrayQuestion.arr30,
        ArrayQuestion.arr31,
        ArrayQuestion.arr32,
        ArrayQuestion.arr33,
        ArrayQuestion.arr34,
        ArrayQuestion.arr35,
        ArrayQuestion.arr36,
    )
}
