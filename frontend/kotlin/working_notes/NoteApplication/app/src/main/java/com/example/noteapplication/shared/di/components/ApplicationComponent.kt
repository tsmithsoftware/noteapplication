package com.example.noteapplication.shared.di.components

import android.content.Context
import com.example.noteapplication.shared.di.modules.NetworkModule
import com.example.noteapplication.shared.di.modules.NoteModule
import com.example.noteapplication.shared.di.modules.RoomModule
import com.example.noteapplication.shared.di.modules.SubcomponentsModule
import dagger.Component
import javax.inject.Singleton

// The "modules" attribute in the @Component annotation tells Dagger what Modules
// to include when building the graph
@Singleton
@Component(modules = [NetworkModule::class, SubcomponentsModule::class, NoteModule::class, RoomModule::class])
interface ApplicationComponent {
    fun context(): Context
    fun notesComponent(): NotesComponent.Factory
}