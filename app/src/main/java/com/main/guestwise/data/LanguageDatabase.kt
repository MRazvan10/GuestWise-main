package com.main.guestwise.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.main.guestwise.model.Language

@Database(entities = [Language::class], version = 1, exportSchema = false)
abstract class LanguageDatabase : RoomDatabase() {
    abstract fun languageDao(): LanguageDatabaseDao
}