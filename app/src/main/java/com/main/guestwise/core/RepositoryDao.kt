package com.main.guestwise.core

import androidx.room.*
import com.main.guestwise.models.LanguageModel

@Dao
interface RepositoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLanguage(languageModel: LanguageModel)

    @Transaction
    @Query("SELECT * FROM language")
    suspend fun getLanguage(): List<LanguageModel>

    @Query("DELETE FROM language")
    suspend fun emptyLanguageTable()
}