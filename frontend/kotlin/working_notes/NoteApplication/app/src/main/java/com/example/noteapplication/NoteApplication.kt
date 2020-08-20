package com.example.noteapplication

import android.app.Application
import com.example.noteapplication.shared.di.components.ApplicationComponent
import com.example.noteapplication.shared.di.components.DaggerApplicationComponent
import com.example.noteapplication.shared.di.modules.NoteModule
import com.example.noteapplication.shared.di.modules.RoomModule

class NoteApplication: Application() {
    val appComponent: ApplicationComponent = DaggerApplicationComponent
        .builder()
        .noteModule(NoteModule(this))
        .roomModule(RoomModule())
        .build()
}
