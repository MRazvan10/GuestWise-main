package com.main.guestwise.ui.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation


@ExperimentalComposeUiApi
@Composable
fun CustomInputText(
    modifier: Modifier = Modifier,
    text: String,
    label: String,
    maxLine: Int = 1,
    onTextChange: (String) -> Unit,
    onImeAction: () -> Unit = {}
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = text, onValueChange = onTextChange, colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent
        ), maxLines = maxLine, label = { Text(text = label) }, keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ), keyboardActions = KeyboardActions(onDone = {
            onImeAction()
            keyboardController?.hide()

        }), modifier = modifier
    )

}

@ExperimentalComposeUiApi
@Composable
fun EmailInputText(
    modifier: Modifier = Modifier,
    text: String,
    label: String,
    maxLine: Int = 1,
    onTextChange: (String) -> Unit,
    onImeAction: () -> Unit = {}
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = text,
        onValueChange = onTextChange,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent
        ),
        maxLines = maxLine,
        label = { Text(text = label) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        keyboardActions = KeyboardActions(onDone = {
            onImeAction()
            keyboardController?.hide()

        }),
        modifier = modifier
    )

}

@ExperimentalComposeUiApi
@Composable
fun PhoneInputText(
    modifier: Modifier = Modifier,
    text: String,
    label: String,
    maxLine: Int = 1,
    onTextChange: (String) -> Unit,
    onImeAction: () -> Unit = {}
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = text,
        onValueChange = onTextChange,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent
        ),
        maxLines = maxLine,
        label = { Text(text = label) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        keyboardActions = KeyboardActions(onDone = {
            onImeAction()
            keyboardController?.hide()

        }),
        modifier = modifier
    )

}

@ExperimentalComposeUiApi
@Composable
fun PasswordInputText(
    modifier: Modifier = Modifier,
    text: String,
    label: String,
    maxLine: Int = 1,
    onTextChange: (String) -> Unit,
    onImeAction: () -> Unit = {}
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    TextField(value = text,
        onValueChange = onTextChange,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent
        ),
        maxLines = maxLine,
        label = { Text(text = label) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        keyboardActions = KeyboardActions(onDone = {
            onImeAction()
            keyboardController?.hide()

        }),
        modifier = modifier,
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val image = if (passwordVisible) Icons.Filled.Visibility
            else Icons.Filled.VisibilityOff

            val description = if (passwordVisible) "Hide password" else "Show password"

            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(imageVector = image, description)
            }
        })

}

@Composable
fun CustomButton(
    modifier: Modifier = Modifier, text: String, onClick: () -> Unit, enabled: Boolean = true
) {
    Button(
        onClick = onClick, shape = CircleShape, enabled = enabled, modifier = modifier
    ) {
        Text(text)

    }

}
