package com.main.guestwise.repository

import com.main.guestwise.data.LanguageDatabaseDao
import com.main.guestwise.models.Language
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LanguageRepository @Inject constructor(private val languageDatabaseDao: LanguageDatabaseDao) {
    suspend fun updateLanguage(language: Language) = languageDatabaseDao.updateLanguage(language)
    fun getLanguage(): Flow<Language?> = languageDatabaseDao.getLanguage().flowOn(Dispatchers.IO).conflate()
}