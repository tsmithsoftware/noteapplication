package com.example.noteapplication.app.di.modules

import com.example.noteapplication.app.di.components.NotesComponent
import dagger.Module

// The "subcomponents" attribute in the @Module annotation tells Dagger what
// Subcomponents are children of the Component this module is included in.
@Module(subcomponents = [NotesComponent::class])
class SubcomponentsModule {}
