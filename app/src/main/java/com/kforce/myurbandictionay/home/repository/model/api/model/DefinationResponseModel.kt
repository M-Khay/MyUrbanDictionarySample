package com.kforce.myurbandictionay.home.repository.model.api.model

import com.google.gson.annotations.SerializedName

data class DefinationResponseModel(
    @SerializedName("list")
    var list: List<DefinationModel>
)