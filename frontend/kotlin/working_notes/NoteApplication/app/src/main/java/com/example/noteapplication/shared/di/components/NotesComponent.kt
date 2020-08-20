package com.example.noteapplication.shared.di.components

import android.content.Context
import com.example.noteapplication.features.login.app.ui.LoginFragment
import com.example.noteapplication.features.notes.app.ui.fragments.EditNoteFragment
import com.example.noteapplication.features.notes.app.ui.fragments.MainNoteFragment
import com.example.noteapplication.features.notes.app.ui.fragments.SecondaryFragment
import com.example.noteapplication.features.notes.app.ui.fragments.SubmitNoteFragment
import com.example.noteapplication.shared.app.activities.MainActivity
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
    fun inject(usernameFragment: MainNoteFragment)
    fun inject(passwordFragment: SecondaryFragment)
    fun inject(submitNoteFragment: SubmitNoteFragment)
    fun inject(editNoteFragment: EditNoteFragment)
    fun inject(loginFragment: LoginFragment)
}
