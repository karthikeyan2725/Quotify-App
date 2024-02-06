package com.example.quotesapp.di

import androidx.lifecycle.SavedStateHandle
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ViewModule {
    @Provides
    @Tag
    @ViewModelScoped
    fun provideTag(savedStateHandle:SavedStateHandle): String =
        savedStateHandle.get<String>("tag")?:""
}