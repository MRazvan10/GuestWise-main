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
import com.google.firebase.auth.FirebaseAuth
import com.main.guestwise.R
import com.main.guestwise.ui.components.CustomButton
import com.main.guestwise.ui.components.EmailInputText
import com.main.guestwise.ui.components.PasswordInputText
import com.main.guestwise.models.Language

@ExperimentalComposeUiApi
@Composable
fun LoginScreen(
    language: Language,
    auth: FirebaseAuth
) {
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    Column(modifier = Modifier.padding(6.dp)) {
        // Content
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
            },modifier = Modifier.padding(
                top = 8.dp, bottom = 8.dp),
                onClick = {
                if (email.isNotEmpty() && password.isNotEmpty()) {
                    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    }.addOnFailureListener {
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
            },modifier = Modifier.padding( bottom = 8.dp),
                onClick = {
            })

        }
        Divider(modifier = Modifier.padding(10.dp))
//        LazyColumn {
//            items(languages) { note ->
//                NoteRow(language = note, onNoteClicked = { onRemoveNote(it) })
//            }
//        }

    }

}
//
//@Composable
//fun NoteRow(
//    modifier: Modifier = Modifier, language: Language, onNoteClicked: (Language) -> Unit
//) {
//    Surface(
//        modifier
//            .padding(4.dp)
//            .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
//            .fillMaxWidth(),
//        color = Color(0xFFDFE6EB),
//        elevation = 6.dp
//    ) {
//        Column(
//            modifier
//                .clickable { onNoteClicked(language) }
//                .padding(horizontal = 14.dp, vertical = 6.dp),
//            horizontalAlignment = Alignment.Start) {
//            Text(
//                text = language.title, style = MaterialTheme.typography.subtitle2
//            )
//            Text(text = language.description, style = MaterialTheme.typography.subtitle1)
//            Text(
//                text = formatDate(language.entryDate.time), style = MaterialTheme.typography.caption
//            )
//
//
//        }
//
//
//    }
//
//}