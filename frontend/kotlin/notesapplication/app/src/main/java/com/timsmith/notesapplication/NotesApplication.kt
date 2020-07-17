package com.timsmith.notesapplication

import android.content.Context
import com.timsmith.notesapplication.app.di.AppComponent
import com.timsmith.notesapplication.app.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class NotesApplication : DaggerApplication() {

    private lateinit var appComponent: AppComponent

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        //MultiDex.install(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
       appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
        return appComponent
    }
}