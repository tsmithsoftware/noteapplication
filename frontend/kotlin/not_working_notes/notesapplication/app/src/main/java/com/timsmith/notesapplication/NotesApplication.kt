package com.timsmith.notesapplication

import com.timsmith.notesapplication.app.di.components.AppComponent
import com.timsmith.notesapplication.app.di.components.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class NotesApplication : DaggerApplication() {

    private lateinit var appComponent: AppComponent

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
      appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
        return appComponent
    }
}