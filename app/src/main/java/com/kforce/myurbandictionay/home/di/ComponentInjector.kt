package com.kforce.myurbandictionay.home.di

class ComponentInjector {

    companion object {
        lateinit var component: AppComponent
        fun init() {
            component = DaggerAppComponent.builder()
                .dictionaryRepositoryModule(DictionaryRepositoryModule())
                .build()

        }
    }
}