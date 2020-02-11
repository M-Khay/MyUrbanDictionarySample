package com.kforce.myurbandictionay.home

import android.app.Activity
import android.app.Application
import com.kforce.myurbandictionay.home.di.ComponentInjector
import com.kforce.myurbandictionay.home.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class MyApplication : Application()  {

    override fun onCreate() {
        super.onCreate()

        ComponentInjector.init()


    }
}