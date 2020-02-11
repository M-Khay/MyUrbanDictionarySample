package com.kforce.myurbandictionay.home.ui.dictionary

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager


import com.kforce.myurbandictionay.R
import com.kforce.myurbandictionay.home.di.ComponentInjector
import com.kforce.myurbandictionay.home.ui.dictionary.rv.DefinationListAdapter
import com.leinardi.android.speeddial.SpeedDialView
import kotlinx.android.synthetic.main.fragment_dictionary_list.*

class DictionaryFragment : Fragment() {
    private lateinit var viewModel: DefinationListViewModel
    private lateinit var adapter: DefinationListAdapter

    private var isLoading = false

    companion object {
        const val TAG = "DictionaryFragment"
        fun newInstance() = DictionaryFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dictionary_list, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DefinationListViewModel::class.java).also {
            ComponentInjector.component.inject(it)
        }
        search_go.setOnClickListener {
            val text: String = searchtext.text.toString()
            searchNewTerm(text)

        }
        adapter = DefinationListAdapter(viewModel)
        rv_dictionary_list.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = this@DictionaryFragment.adapter
        }

        viewModel.stateLiveData.observe(this.viewLifecycleOwner, definatioListObserver)

        filter_menu.inflate(R.menu.menu)
        filter_menu.setOnActionSelectedListener(SpeedDialView.OnActionSelectedListener { actionItem ->
            when (actionItem.id) {
                R.id.thumbs_up -> {
                    viewModel.showFilteredByMinThumbsUp()
                    filter_menu.close()
                }
                R.id.thumbs_up_max -> {
                    viewModel.showFilteredByMaxThumbsUp()
                    filter_menu.close()  
                }
                R.id.thumbs_down -> {
                    viewModel.showFilteredByMinThumbsDown()
                    filter_menu.close()  
                }
                R.id.thumbs_down_max -> {
                    viewModel.showFilteredByMaxThumbsDown()
                    filter_menu.close()  
                }

            }
            false
        })
    }

    private fun searchNewTerm(serachText: String) {
        Log.d(TAG, serachText)
        viewModel.getDefinationListOf(serachText)
    }

    private val definatioListObserver = Observer<DictionaryState> { state ->
        state?.let {
            when (state) {
                is DefaultState -> {
                    isLoading = false
                    progress_bar.visibility = View.GONE
                    adapter.notifyDataSetChanged()

                }
                is LoadingState -> {
                    progress_bar.visibility = View.VISIBLE
                    Log.d(TAG, "loading state")
                }
                is ErrorState -> {
                    Log.e(TAG, "error state")
                    Toast.makeText(activity, "Error from api side", Toast.LENGTH_SHORT).show()
                    progress_bar.visibility = View.GONE
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }


}