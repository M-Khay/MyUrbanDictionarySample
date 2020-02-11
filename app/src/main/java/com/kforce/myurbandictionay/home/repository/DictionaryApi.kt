package com.kforce.myurbandictionay.home.repository

import com.kforce.myurbandictionay.home.repository.model.api.model.DefinationModel
import com.kforce.myurbandictionay.home.repository.model.api.model.DefinationResponseModel
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DictionaryApi {
    @GET("define")
    fun getMeaningOf(@Query("term") term: String): Call<DefinationResponseModel>

}