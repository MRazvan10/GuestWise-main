package com.main.guestwise.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.main.guestwise.R
import com.main.guestwise.models.Language
import com.main.guestwise.ui.components.CustomButton
import com.main.guestwise.ui.components.EmailInputText
import com.main.guestwise.ui.components.PasswordInputText

@ExperimentalComposeUiApi
@Composable
fun LoginScreen(
    languageViewModel: LanguageViewModel, language: Language, auth: FirebaseAuth, navController: NavController
) {
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    Column {
        AppBar(language = language, onUpdateLanguage = { languageViewModel.updateLanguage(it) })
        Column(modifier = Modifier.padding(6.dp)) {
            Column(
                modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                EmailInputText(modifier = Modifier.padding(
                    top = 8.dp, bottom = 8.dp
                ), text = email, label = stringResource(R.string.e_mail), onTextChange = {
                    email = it
                })

                PasswordInputText(modifier = Modifier.padding(
                    top = 8.dp, bottom = 8.dp
                ), text = password, label = when (language.code) {
                    "en" -> stringResource(R.string.password_en)
                    "ro" -> stringResource(R.string.password_ro)
                    "fr" -> stringResource(R.string.password_fr)
                    "es" -> stringResource(R.string.password_es)
                    "pt" -> stringResource(R.string.password_pt)
                    "de" -> stringResource(R.string.password_de)
                    else -> ""
                }, onTextChange = {
                    password = it
                })

                CustomButton(text = when (language.code) {
                    "en" -> stringResource(R.string.login_en)
                    "ro" -> stringResource(R.string.login_ro)
                    "fr" -> stringResource(R.string.login_fr)
                    "es" -> stringResource(R.string.login_es)
                    "pt" -> stringResource(R.string.login_pt)
                    "de" -> stringResource(R.string.login_de)
                    else -> ""
                }, modifier = Modifier.padding(
                    top = 8.dp, bottom = 8.dp
                ), onClick = {
                    if (email.isNotEmpty() && password.isNotEmpty()) {
                        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener { navController.navigate("homePage") }
                            .addOnFailureListener {
                                //
                            }
                    }
                })

                CustomButton(text = when (language.code) {
                    "en" -> stringResource(R.string.register_en)
                    "ro" -> stringResource(R.string.register_ro)
                    "fr" -> stringResource(R.string.register_fr)
                    "es" -> stringResource(R.string.register_es)
                    "pt" -> stringResource(R.string.register_pt)
                    "de" -> stringResource(R.string.register_de)
                    else -> ""
                }, modifier = Modifier.padding(bottom = 8.dp), onClick = { navController.navigate("createAccount") })

            }
            Divider(modifier = Modifier.padding(10.dp))
        }

    }
}