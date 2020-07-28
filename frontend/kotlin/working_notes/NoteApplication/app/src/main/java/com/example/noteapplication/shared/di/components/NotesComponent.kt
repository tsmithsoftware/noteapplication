package com.example.noteapplication.shared.di.components

import android.content.Context
import com.example.noteapplication.features.notes.app.ui.fragments.LoginPasswordFragment
import com.example.noteapplication.features.notes.app.ui.fragments.LoginUsernameFragment
import com.example.noteapplication.features.notes.app.ui.activities.MainActivity
import com.example.noteapplication.shared.di.scopes.ActivityScope
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
