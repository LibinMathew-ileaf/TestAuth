package com.example.secureauthentication.di
import com.example.secureauthentication.utils.Decryptor
import com.example.secureauthentication.utils.Encryptor

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object CryptoMangerModule {

    @Provides
    fun provideDeCryptor(): Decryptor {
        return Decryptor()
    }

    @Provides
    fun provideEnCryptor(): Encryptor {
        return Encryptor()
    }
}