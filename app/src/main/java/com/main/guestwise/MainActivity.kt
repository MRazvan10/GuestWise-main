package com.main.guestwise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
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

    LoginScreen(language = language, onUpdateLanguage = { languageViewModel.updateLanguage(it) })

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GuestWiseTheme {

    }
}