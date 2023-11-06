package com.main.guestwise.data

import androidx.room.*
import com.main.guestwise.model.Language
import kotlinx.coroutines.flow.Flow

@Dao
interface LanguageDatabaseDao {

    @Query("SELECT * from language")
    fun getLanguage(): Flow<Language?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateLanguage(language: Language)
}
