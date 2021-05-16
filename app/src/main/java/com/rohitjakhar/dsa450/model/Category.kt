package com.rohitjakhar.dsa450.model

import com.squareup.moshi.Json

data class Category(

    @Json(name = "position")
    val categoryId: Int,
    @Json(name = "TopicName")
    val categoryName: String,
    @Json(name = "TotalQuestion")
    val totalQuestion: Int
)
