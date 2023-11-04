package com.main.guestwise.modules.repository.database

import com.main.guestwise.core.RepositoryDao
import com.main.guestwise.models.LanguageModel
import javax.inject.Inject

interface DatabaseService {
    suspend fun addLanguage(languageModel: LanguageModel)
    suspend fun getLanguage(): LanguageModel
    suspend fun emptyLanguageTable()
}

class DatabaseServiceImpl @Inject constructor(
    private val repositoryDao: RepositoryDao
) : DatabaseService {

    override suspend fun addLanguage(languageModel: LanguageModel) =
        repositoryDao.insertLanguage(languageModel)

    override suspend fun getLanguage(): LanguageModel {
        val languages = repositoryDao.getLanguage()
        return if (languages.isNotEmpty()) {
            languages.first()
        } else {
            LanguageModel(id = -1)
        }
    }

    override suspend fun emptyLanguageTable() {
        repositoryDao.emptyLanguageTable()
    }
}
