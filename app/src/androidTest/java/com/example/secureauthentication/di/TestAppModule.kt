package com.example.secureauthentication.di

import android.content.Context
import androidx.room.Room
import com.example.secureauthentication.database.AuthDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext



import javax.inject.Named

@Module
@InstallIn(ActivityComponent::class)
object TestAppModule {

    @Provides
    @Named("test_db")
    fun provideInMemoryDb(@ApplicationContext context: Context) =
        Room.inMemoryDatabaseBuilder(context, AuthDatabase::class.java)
            .allowMainThreadQueries()
            .build()

}