package com.kforce.myurbandictionay.home.ui.dictionary

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager


import com.kforce.myurbandictionay.R
import com.kforce.myurbandictionay.home.di.ComponentInjector
import com.kforce.myurbandictionay.home.ui.dictionary.rv.DefinationListAdapter
import kotlinx.android.synthetic.main.fragment_dictionary_list.*

class DictionaryFragment : Fragment() {
    private lateinit var viewModel: DefinationListViewModel
    private lateinit var adapter: DefinationListAdapter


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
            searchNewTerm()
        }
        search_text.setOnEditorActionListener{v, actionId, _ ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH){
                v.clearFocus()
                hideKeyboard()
                searchNewTerm()
            }
            true
        }

        adapter = DefinationListAdapter(viewModel)
        rv_dictionary_list.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = this@DictionaryFragment.adapter
        }

        viewModel.stateLiveData.observe(this.viewLifecycleOwner, definitionListObserver)

        setupFilterMenu()
    }

    private fun searchNewTerm() {
        val searchText: String = search_text.text.toString()
        Log.d(TAG, "Searched Term : $searchText")
        filter_menu.close() // if filter_menu was open and a new term is searched, first close the menu.
        viewModel.getDefinationListOf(searchText)
    }

    private val definitionListObserver = Observer<DictionaryState> { state ->
        state?.let {
            when (state) {
                is DefaultState -> {
                    filter_menu.visibility = View.VISIBLE
                    progress_bar.visibility = View.GONE
                    adapter.notifyDataSetChanged()
                }
                is LoadingState -> {
                    Log.d(TAG, "loading state")
                    filter_menu.visibility = View.INVISIBLE
                    progress_bar.visibility = View.VISIBLE
                }
                is ErrorState -> {
                    Log.e(TAG, "error state")
                    filter_menu.visibility = View.INVISIBLE
                    progress_bar.visibility = View.GONE
                    adapter.notifyDataSetChanged()
                    Toast.makeText(activity, "Error from api side", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setupFilterMenu(){
        filter_menu.inflate(R.menu.menu)
        filter_menu.setOnActionSelectedListener { actionItem ->
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
        }
        filter_menu.visibility = View.INVISIBLE
    }

    fun hideKeyboard() {
            val keyboard =
                activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            keyboard.hideSoftInputFromWindow(view?.windowToken, 0)
    }
}