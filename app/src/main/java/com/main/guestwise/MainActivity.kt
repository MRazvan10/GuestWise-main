package com.main.guestwise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.main.guestwise.screen.AppBar
import com.main.guestwise.screen.LanguageViewModel
import com.main.guestwise.screen.LoginScreen
import com.main.guestwise.ui.theme.GuestWiseTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalComposeUiApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GuestWiseTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val languageViewModel = viewModel<LanguageViewModel>()
                    MainApp(languageViewModel)
                }
            }
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun MainApp(languageViewModel: LanguageViewModel) {
    val language = languageViewModel.language.collectAsState().value
    val auth: FirebaseAuth = Firebase.auth
    Column {
        AppBar(language = language, onUpdateLanguage = { languageViewModel.updateLanguage(it) })
        if (auth.currentUser == null) {
            LoginScreen(language = language, auth = auth)
        } else {
            Text("User is logged in")
        }
    }
}