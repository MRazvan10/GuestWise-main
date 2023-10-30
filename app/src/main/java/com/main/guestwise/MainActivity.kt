package com.main.guestwise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.main.guestwise.ui.theme.GuestWiseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GuestWiseTheme {
                MyApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier) {

    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

    Surface(modifier) {
        if (shouldShowOnboarding) {
            OnboardingScreen(onContinueClicked = { shouldShowOnboarding = false })
        } else {
            Greetings()
        }
    }
}

@Composable
fun OnboardingScreen(
    onContinueClicked: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to GuestWise!")
        Button(
            modifier = Modifier.padding(top = 12.dp),
            onClick = onContinueClicked
        ) {
            Text("Register")
        }
        Button(
            onClick = onContinueClicked
        ) {
            Text("Log in")
        }
    }
}

@Composable
private fun Greetings(
    modifier: Modifier = Modifier,
    name: String = "Name",
    items: List<String> = List(20) { "$it" }
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

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    GuestWiseTheme {
        OnboardingScreen(onContinueClicked = {})
    }
}

@Composable
private fun Greeting(name: String) {

    val expanded = remember { mutableStateOf(false) }

    val extraPadding = if (expanded.value) 48.dp else 0.dp

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding),
                horizontalAlignment = Alignment.CenterHorizontally
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
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Button(
                onClick = { },
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
            ) {
                Text(name)
            }
            Divider(color = MaterialTheme.colorScheme.primary, thickness = 1.dp)
        }
    }
}

@Preview
@Composable
fun MyAppPreview() {
    GuestWiseTheme {
        MyApp(Modifier.fillMaxSize())
    }
}