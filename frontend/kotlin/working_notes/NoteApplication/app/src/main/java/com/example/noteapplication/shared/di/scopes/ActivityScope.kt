package com.example.noteapplication.shared.di.scopes

import javax.inject.Scope

// Definition of a custom scope called ActivityScope
@Scope
@Retention(value = AnnotationRetention.RUNTIME)
annotation class ActivityScope
