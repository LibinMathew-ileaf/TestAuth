package com.example.secureauthentication.di
import android.content.Context
import com.example.secureauthentication.utils.CryptoManger

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object CryptoMangerModule {

    @Provides
    fun provideCryptoManger(
        @ApplicationContext context: Context
    ): CryptoManger {
        return CryptoManger()
    }
}