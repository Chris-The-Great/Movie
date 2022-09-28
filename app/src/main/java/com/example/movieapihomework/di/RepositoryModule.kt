package com.example.movieapihomework.di

import com.example.movieapihomework.api.PopularREImpl
import com.example.movieapihomework.api.PopularRe
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun providesRespository(
        popularREImpl: PopularREImpl
    ) :PopularRe
}