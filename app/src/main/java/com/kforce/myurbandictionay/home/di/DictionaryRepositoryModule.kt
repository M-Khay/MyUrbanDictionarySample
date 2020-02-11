package com.kforce.myurbandictionay.home.di

import android.content.Context
import com.kforce.myurbandictionay.home.repository.DictionaryApi
import com.kforce.myurbandictionay.home.repository.DictionaryRepository
import com.kforce.myurbandictionay.home.repository.DictionaryRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DictionaryRepositoryModule {
    @Provides @Singleton
    fun provideDictionaryRepository(dictionaryApi: DictionaryApi):DictionaryRepository = DictionaryRepositoryImpl(dictionaryApi)
}