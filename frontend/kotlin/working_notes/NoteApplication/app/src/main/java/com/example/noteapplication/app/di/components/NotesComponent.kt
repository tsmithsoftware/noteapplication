package com.example.noteapplication.app.di.components

import android.content.Context
import com.example.noteapplication.app.ui.LoginPasswordFragment
import com.example.noteapplication.app.ui.LoginUsernameFragment
import com.example.noteapplication.app.ui.MainActivity
import com.example.noteapplication.app.di.scopes.ActivityScope
import com.example.noteapplication.domain.repositories.NoteRepository
import dagger.Subcomponent

// @Subcomponent annotation informs Dagger this interface is a Dagger Subcomponent
@ActivityScope
@Subcomponent
interface NotesComponent {
    // Factory that is used to create instances of this subcomponent
    @Subcomponent.Factory
    interface Factory {
        fun create(): NotesComponent
    }

    fun inject(context: Context)
    fun inject(activity: MainActivity)
    fun inject(usernameFragment: LoginUsernameFragment)
    fun inject(passwordFragment: LoginPasswordFragment)

}
