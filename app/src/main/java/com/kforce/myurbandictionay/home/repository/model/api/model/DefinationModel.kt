package com.kforce.myurbandictionay.home.repository.model.api.model

import com.google.gson.annotations.SerializedName


data class DefinationModel(
    @SerializedName("author")
    var author: String,
    @SerializedName("defid")
    var defid: Int,
    @SerializedName("definition")
    var definition: String,
    @SerializedName("thumbs_down")
    var thumbsDown: Int,
    @SerializedName("thumbs_up")
    var thumbsUp: Int
)
