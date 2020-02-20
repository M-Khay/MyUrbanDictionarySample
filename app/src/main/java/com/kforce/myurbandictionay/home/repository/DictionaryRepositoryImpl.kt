package com.kforce.myurbandictionay.home.repository

import android.util.Log
import android.widget.Toast
import com.kforce.myurbandictionay.home.repository.model.api.model.DefinationModel
import com.kforce.myurbandictionay.home.repository.model.api.model.DefinationResponseModel
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DictionaryRepositoryImpl(private val service: DictionaryApi) : DictionaryRepository {

    val TAG: String = DictionaryRepositoryImpl::class.java.name


    override fun getMeaningListFor(
        word: String,
        successHandler: (DefinationResponseModel?) -> Unit,
        failureHandler: (Throwable?) -> Unit
    ) {
        service.getMeaningOf(word).enqueue(object : Callback<DefinationResponseModel> {
            override fun onResponse(
                call: Call<DefinationResponseModel>,
                response: Response<DefinationResponseModel>
            ) {
                Log.d(TAG, response.toString())
                response.body()?.let {
                    successHandler(it)
                }
            }

            override fun onFailure(call: Call<DefinationResponseModel>, t: Throwable) {
                failureHandler(t)
                Log.d(TAG, t.toString())
            }

        })
    }
}


