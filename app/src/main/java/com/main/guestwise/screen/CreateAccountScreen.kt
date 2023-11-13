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
import com.main.guestwise.ui.components.CustomInputText
import com.main.guestwise.ui.components.EmailInputText
import com.main.guestwise.ui.components.PasswordInputText
import com.main.guestwise.ui.components.PhoneInputText

@ExperimentalComposeUiApi
@Composable
fun CreateAccountScreen(
    languageViewModel: LanguageViewModel, language: Language, auth: FirebaseAuth, navController: NavController
) {
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var repeatPassword by remember {
        mutableStateOf("")
    }
    var name by remember {
        mutableStateOf("")
    }
    var phone by remember {
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

                PasswordInputText(modifier = Modifier.padding(
                    top = 8.dp, bottom = 8.dp
                ), text = repeatPassword, label = when (language.code) {
                    "en" -> stringResource(R.string.repeat_password_en)
                    "ro" -> stringResource(R.string.repeat_password_ro)
                    "fr" -> stringResource(R.string.repeat_password_fr)
                    "es" -> stringResource(R.string.repeat_password_es)
                    "pt" -> stringResource(R.string.repeat_password_pt)
                    "de" -> stringResource(R.string.repeat_password_de)
                    else -> ""
                }, onTextChange = {
                    repeatPassword = it
                })

                CustomInputText(modifier = Modifier.padding(
                    top = 8.dp, bottom = 8.dp
                ), text = name, label = when (language.code) {
                    "en" -> stringResource(R.string.name_en)
                    "ro" -> stringResource(R.string.name_ro)
                    "fr" -> stringResource(R.string.name_fr)
                    "es" -> stringResource(R.string.name_es)
                    "pt" -> stringResource(R.string.name_pt)
                    "de" -> stringResource(R.string.name_de)
                    else -> ""
                }, onTextChange = {
                    name = it
                })

                PhoneInputText(modifier = Modifier.padding(
                    top = 8.dp, bottom = 8.dp
                ), text = phone, label = when (language.code) {
                    "en" -> stringResource(R.string.phone_en)
                    "ro" -> stringResource(R.string.phone_ro)
                    "fr" -> stringResource(R.string.phone_fr)
                    "es" -> stringResource(R.string.phone_es)
                    "pt" -> stringResource(R.string.phone_pt)
                    "de" -> stringResource(R.string.phone_de)
                    else -> ""
                }, onTextChange = {
                    phone = it
                })

                CustomButton(text = when (language.code) {
                    "en" -> stringResource(R.string.register_en)
                    "ro" -> stringResource(R.string.register_ro)
                    "fr" -> stringResource(R.string.register_fr)
                    "es" -> stringResource(R.string.register_es)
                    "pt" -> stringResource(R.string.register_pt)
                    "de" -> stringResource(R.string.register_de)
                    else -> ""
                }, modifier = Modifier.padding(
                    top = 8.dp, bottom = 8.dp
                ), onClick = {
                    auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                        navController.navigate("homePage")
                    }.addOnFailureListener {
                        //
                    }
                })

            }
            Divider(modifier = Modifier.padding(10.dp))
        }

    }
}