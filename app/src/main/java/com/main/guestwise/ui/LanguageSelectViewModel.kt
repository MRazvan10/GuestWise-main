package com.main.guestwise.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.main.guestwise.models.LanguageModel
import com.main.guestwise.modules.repository.database.DatabaseService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LanguageSelectViewModel @Inject constructor(
    private val databaseService: DatabaseService
) : ViewModel() {
    val languageLiveData: LiveData<List<LanguageModel>> by lazy { _languageLiveData }
    private val _languageLiveData: MutableLiveData<List<LanguageModel>> by lazy {
        MutableLiveData<List<LanguageModel>>()
    }

    init {
        getLanguage()
    }

    fun getLanguage() {
        viewModelScope.launch(Dispatchers.IO) {
            val language = databaseService.getLanguage()
            _languageLiveData.postValue(listOf(language))
        }
    }

    suspend fun changeLanguage(language: LanguageModel) {
        databaseService.addLanguage(language)

    }
}