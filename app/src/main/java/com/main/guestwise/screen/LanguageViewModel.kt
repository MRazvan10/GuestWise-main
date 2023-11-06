package com.main.guestwise.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.main.guestwise.model.Language
import com.main.guestwise.repository.LanguageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LanguageViewModel @Inject constructor(private val repository: LanguageRepository) : ViewModel() {

    private val m_language = MutableStateFlow(Language(code = "en"))
    val language = m_language.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getLanguage().distinctUntilChanged().collect { language ->
                if (language == null) {
                    m_language.value = Language(code = "en")
                } else {
                    m_language.value = language
                }
            }
        }
    }

    fun updateLanguage(language: Language) = viewModelScope.launch { repository.updateLanguage(language) }

}