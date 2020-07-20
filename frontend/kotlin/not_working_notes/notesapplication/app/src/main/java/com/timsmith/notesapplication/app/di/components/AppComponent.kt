package com.timsmith.notesapplication.app.di.components

import android.app.Application
import com.timsmith.notesapplication.NotesApplication
import com.timsmith.notesapplication.app.di.modules.AppModule
import com.timsmith.notesapplication.app.di.modules.NetworkModule
import com.timsmith.notesapplication.app.di.modules.NoteModule
import com.timsmith.notesapplication.app.di.modules.ActivityBindingModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        AppModule::class,
        NoteModule::class,
        AndroidSupportInjectionModule::class,
        AndroidInjectionModule::class,
        ActivityBindingModule::class]
)
interface AppComponent : AndroidInjector<NotesApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}