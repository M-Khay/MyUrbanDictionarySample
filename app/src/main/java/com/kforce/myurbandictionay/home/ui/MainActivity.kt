package com.kforce.myurbandictionay.home.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.kforce.myurbandictionay.R
import com.kforce.myurbandictionay.home.ui.dictionary.DefinationListViewModel
import com.kforce.myurbandictionay.home.ui.dictionary.DictionaryFragment

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: DefinationListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        if (savedInstanceState == null)
            supportFragmentManager.beginTransaction().add(
                R.id.container,
                DictionaryFragment.newInstance(),
                DictionaryFragment.TAG
            )
                .commitNow()

        viewModel = ViewModelProvider(this).get(DefinationListViewModel::class.java)

    }


    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }
}
