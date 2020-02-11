package com.kforce.myurbandictionay.home.ui.dictionary.rv

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kforce.myurbandictionay.R

class DefinationListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val author by lazy { view.findViewById(R.id.author) as TextView }
    val defination by lazy { view.findViewById(R.id.definition) as TextView }
    val thumbsUp by lazy { view.findViewById(R.id.up) as TextView }
    val thumbsDown by lazy { view.findViewById(R.id.down) as TextView }
}