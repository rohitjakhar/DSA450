package com.rohitjakhar.dsa450.model

import com.squareup.moshi.Json

data class Question(

    @Json(name = "Topic")
    val questionTopic: String,
    @Json(name = "Problem")
    val questionStatement: String,
    @Json(name = "URL")
    val questionLink: String,
)
