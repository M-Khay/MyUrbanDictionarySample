package com.kforce.myurbandictionay.home.ui.dictionary.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kforce.myurbandictionay.R
import com.kforce.myurbandictionay.home.ui.dictionary.DefinationListViewModel

class DefinationListAdapter(var meaningListViewModel: DefinationListViewModel) :
    RecyclerView.Adapter<DefinationListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefinationListViewHolder {
        return DefinationListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.dictionary_rv_row_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return meaningListViewModel.getCount()//To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: DefinationListViewHolder, position: Int) {


        meaningListViewModel.getItemAt(position).let {
            holder.apply {
                defination.text = it?.definition
                author.text = it?.author
                thumbsDown.text = it?.thumbsDown.toString()
                thumbsUp.text = it?.thumbsUp.toString()
            }
        }

    }

}