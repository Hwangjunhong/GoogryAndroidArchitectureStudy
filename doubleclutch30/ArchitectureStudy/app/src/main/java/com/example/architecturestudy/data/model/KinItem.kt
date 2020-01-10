package com.example.architecturestudy.data.model

import com.google.gson.annotations.SerializedName

data class KinItem(

    @SerializedName("title")
    val title : String,
    @SerializedName("description")
    val description : String,
    @SerializedName("link")
    val link : String
)