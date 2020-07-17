package com.timsmith.notesapplication.app.di

import android.app.Application
import android.content.Context
import com.timsmith.notesapplication.app.rx.SchedulersFacade
import com.timsmith.notesapplication.app.rx.SchedulersProvider
import dagger.Binds
import dagger.Module


@Module
abstract class AppModule {

    @Binds
    abstract fun bindContext(application: Application): Context

    @Binds
    abstract fun providerScheduler(schedulersFacade: SchedulersFacade): SchedulersProvider
}