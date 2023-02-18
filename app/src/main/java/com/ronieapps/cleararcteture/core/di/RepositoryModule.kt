package com.ronieapps.cleararcteture.core.di

import com.ronieapps.cleararcteture.core.domain.repository.AuthRepository
import com.ronieapps.cleararcteture.data.repository.AuthRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {
    @Binds
    fun bindAuthRepository(
        authRepository: AuthRepositoryImpl
    ) : AuthRepository
}