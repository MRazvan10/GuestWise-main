package com.main.guestwise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.main.guestwise.screen.CreateAccountScreen
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
                    val language = languageViewModel.language.collectAsState().value
                    val auth: FirebaseAuth = Firebase.auth
                    val navController = rememberNavController()
                    val startDestination = if (auth.currentUser == null) {
                        "login"
                    } else {
                        "homePage"
                    }
                    NavHost(
                        navController = navController, startDestination = startDestination
                    ) {
                        composable("login") {
                            LoginScreen(languageViewModel, language, auth, navController)
                        }
                        composable("createAccount") {
                            CreateAccountScreen(languageViewModel, language, auth, navController)
                        }
                        composable("homePage") {
                            Text(text = auth.currentUser?.email.toString())
                        }
                    }
                }
            }
        }
    }
}