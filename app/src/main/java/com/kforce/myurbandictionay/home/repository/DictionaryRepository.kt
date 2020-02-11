package com.kforce.myurbandictionay.home.repository

import com.kforce.myurbandictionay.home.repository.model.api.model.DefinationResponseModel

interface DictionaryRepository{
    fun getMeaningListFor(word : String,successHandler: (DefinationResponseModel?) -> Unit, failureHandler: (Throwable?) -> Unit)
}