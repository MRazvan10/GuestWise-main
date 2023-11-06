package com.main.guestwise.di

import android.content.Context
import androidx.room.Room
import com.main.guestwise.data.LanguageDatabase
import com.main.guestwise.data.LanguageDatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideLanguageDao(languageDatabase: LanguageDatabase): LanguageDatabaseDao = languageDatabase.languageDao()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): LanguageDatabase = Room.databaseBuilder(
        context, LanguageDatabase::class.java, "room"
    ).fallbackToDestructiveMigration().build()
}