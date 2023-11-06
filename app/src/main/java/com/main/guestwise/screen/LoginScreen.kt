package com.main.guestwise.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.main.guestwise.R
import com.main.guestwise.components.CustomButton
import com.main.guestwise.components.CustomInputText
import com.main.guestwise.components.PasswordInputText
import com.main.guestwise.model.Language

@ExperimentalComposeUiApi
@Composable
fun LoginScreen(
    language: Language, onUpdateLanguage: (Language) -> Unit
) {
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }
    val languages = mapOf(
        Language(code = "en") to R.drawable.en,
        Language(code = "ro") to R.drawable.ro,
        Language(code = "fr") to R.drawable.fr,
        Language(code = "es") to R.drawable.es,
        Language(code = "pt") to R.drawable.pt,
        Language(code = "de") to R.drawable.de
    )
    Column(modifier = Modifier.padding(6.dp)) {
        TopAppBar(title = {
            Text(text = stringResource(id = R.string.app_name))
        }, actions = {
            Box(Modifier.padding(10.dp)) {
                languages[language]?.let { painterResource(id = it) }?.let {
                    Image(
                        painter = it, contentDescription = null, modifier = Modifier.clickable(onClick = { expanded = true })
                    )
                }
                DropdownMenu(
                    expanded = expanded, onDismissRequest = { expanded = false }, modifier = Modifier
                        .width(50.dp)
                        .wrapContentHeight()
                ) {
                    languages.forEach { (changedLanguage, _) ->
                        DropdownMenuItem(onClick = {
                            expanded = false
                            onUpdateLanguage(changedLanguage)
                        }) {
                            Text(text = changedLanguage.code)
                        }
                    }
                }
            }
        }, backgroundColor = MaterialTheme.colors.primary
        )

        // Content
        Column(
            modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomInputText(modifier = Modifier.padding(
                top = 9.dp, bottom = 8.dp
            ), text = email, label = stringResource(R.string.e_mail), onTextChange = {
                if (it.all { char ->
                        char.isLetter() || char.isWhitespace()
                    }) email = it
            })

            PasswordInputText(modifier = Modifier.padding(
                top = 9.dp, bottom = 8.dp
            ), text = password, label = when (language.code) {
                "en" -> stringResource(R.string.password_en)
                "ro" -> stringResource(R.string.password_ro)
                "fr" -> stringResource(R.string.password_fr)
                "es" -> stringResource(R.string.password_es)
                "pt" -> stringResource(R.string.password_pt)
                "de" -> stringResource(R.string.password_de)
                else -> ""
            }, onTextChange = {
                if (it.all { char ->
                        char.isLetter() || char.isWhitespace()
                    }) password = it
            })

            CustomButton(text = when (language.code) {
                "en" -> stringResource(R.string.login_en)
                "ro" -> stringResource(R.string.login_ro)
                "fr" -> stringResource(R.string.login_fr)
                "es" -> stringResource(R.string.login_es)
                "pt" -> stringResource(R.string.login_pt)
                "de" -> stringResource(R.string.login_de)
                else -> ""
            }, onClick = {
                if (email.isNotEmpty() && password.isNotEmpty()) {
//                    onUpdateLanguage(
//                        Language(
//                            title = email, description = password
//                        )
//                    )
                    email = ""
                    password = ""
                }
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