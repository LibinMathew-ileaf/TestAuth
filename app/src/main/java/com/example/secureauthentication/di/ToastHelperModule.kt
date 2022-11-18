package com.example.secureauthentication.di
import android.content.Context
import com.example.secureauthentication.utils.CryptoManger
import com.example.secureauthentication.utils.ToastHelper

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ToastHelperModule {
    @Provides
    fun provideCToastHelper(
        @ApplicationContext context: Context
    ): ToastHelper {
        return ToastHelper(context);
    }
}