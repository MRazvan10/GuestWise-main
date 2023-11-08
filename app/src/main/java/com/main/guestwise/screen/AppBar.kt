package com.main.guestwise.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.main.guestwise.R
import com.main.guestwise.models.Language

@Composable
fun AppBar(
    language: Language, onUpdateLanguage: (Language) -> Unit
) {

    var expanded by remember { mutableStateOf(false) }
    val languages = mapOf(
        Language(code = "en") to R.drawable.en,
        Language(code = "ro") to R.drawable.ro,
        Language(code = "fr") to R.drawable.fr,
        Language(code = "es") to R.drawable.es,
        Language(code = "pt") to R.drawable.pt,
        Language(code = "de") to R.drawable.de
    )
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
}