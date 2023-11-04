package com.main.guestwise.ui.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController


@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Greetings()
}

@Composable
private fun Greetings(
    modifier: Modifier = Modifier, name: String = "Name", items: List<String> = List(20) { "$it" }
) {
    Column {
        Greeting(name = name)
        LazyColumn(
            modifier = modifier
                .padding(vertical = 4.dp)
                .fillMaxWidth()
        ) {
            items(items = items) { item ->
                Item(name = item)
            }
        }
    }
}

@Composable
private fun Greeting(name: String) {

    val expanded = remember { mutableStateOf(false) }

    val extraPadding = if (expanded.value) 48.dp else 0.dp

    Surface(
        color = MaterialTheme.colorScheme.background, modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Hello, ")
                Text(text = name)
            }
        }
    }
}

@Composable
private fun Item(name: String) {

    Surface(
        color = MaterialTheme.colorScheme.background, modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Button(
                onClick = { }, modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
            ) {
                Text(name)
            }
            Divider(color = MaterialTheme.colorScheme.primary, thickness = 1.dp)
        }
    }
}