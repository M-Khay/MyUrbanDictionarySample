package com.kforce.myurbandictionay.home.ui.dictionary

import android.util.Log
import android.util.Log.d
import android.util.Log.e
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kforce.myurbandictionay.home.repository.DictionaryRepository
import com.kforce.myurbandictionay.home.repository.model.api.model.DefinationModel
import com.kforce.myurbandictionay.home.repository.model.api.model.DefinationResponseModel
import com.kforce.myurbandictionay.home.utils.DefinationComparator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.util.HalfSerializer.onError
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DefinationListViewModel : ViewModel() {

    @Inject
    lateinit var repository: DictionaryRepository

    var stateLiveData = MutableLiveData<DictionaryState>()

    init {
        stateLiveData.value = DefaultState(emptyList(), false)
    }

    fun getDefinationListOf(word: String) {
        stateLiveData.value = LoadingState(emptyList(), false)
        repository.getMeaningListFor(
            word,
            successHandler = {
                stateLiveData.value = DefaultState(it?.list?: emptyList(), true)
            }, failureHandler = {
                stateLiveData.value = DefaultState(emptyList(), true)
            })
    }

    fun showFilteredByMinThumbsUp() {
        stateLiveData.value = DefaultState(
            stateLiveData.value?.data?.sortedWith(DefinationComparator.ThumbsUpComparator)?: emptyList(), true
        )
    }


    fun showFilteredByMaxThumbsUp() {
        stateLiveData.value = DefaultState(
            stateLiveData.value?.data?.sortedWith(DefinationComparator.ThumbsUpComparator)?.reversed()?: emptyList(),
            true
        )
    }

    fun showFilteredByMinThumbsDown() {
        stateLiveData.value = DefaultState(
            stateLiveData.value?.data?.sortedWith(DefinationComparator.ThumbsDownComparator)?: emptyList(), true
        )
    }

    fun showFilteredByMaxThumbsDown() {
        stateLiveData.value = DefaultState(
            stateLiveData.value?.data?.sortedWith(DefinationComparator.ThumbsDownComparator)?.reversed()?: emptyList(),
            true
        )
    }

    fun getItemAt(position: Int): DefinationModel? {
        if (position < getCount()) {
            stateLiveData.value?.let {
                return stateLiveData.value!!.data?.get(position)
            }
        }
        return null
    }

    fun getCount(): Int {
        stateLiveData.value?.let {
            return it.data?.size
        }
        return 0
    }
}