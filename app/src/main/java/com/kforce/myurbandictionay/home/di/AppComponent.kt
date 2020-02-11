package com.kforce.myurbandictionay.home.di

import android.app.Application
import com.kforce.myurbandictionay.home.MyApplication
import com.kforce.myurbandictionay.home.ui.dictionary.DefinationListViewModel
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = arrayOf(
        AndroidSupportInjectionModule::class, AppModule::class,
        NetworkModule::class, DictionaryRepositoryModule::class
    )
)

interface AppComponent {
    fun inject(definationListViewModel: DefinationListViewModel)
}


