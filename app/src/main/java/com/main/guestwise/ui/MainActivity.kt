package com.main.guestwise.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.main.guestwise.R
import com.main.guestwise.ui.login.LoginScreen
import com.main.guestwise.ui.theme.GuestWiseTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainActivityScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GuestWiseTheme {
                Column {
                    Bar()
                    val navController = rememberNavController()
                    NavHost(navController, startDestination = "start") {
                        composable(route = "start") {
                            LoginScreen()
                        }
                    }
                }
            }
        }
    }


    @Composable
    fun Bar() {

        var expanded by remember { mutableStateOf(false) }
        val languages = listOf(
            Pair(-1, Pair(R.drawable.en, "en")),
            Pair(0, Pair(R.drawable.ro, "ro")),
            Pair(1, Pair(R.drawable.fr, "fr")),
            Pair(2, Pair(R.drawable.es, "es")),
            Pair(3, Pair(R.drawable.pt, "pt")),
            Pair(4, Pair(R.drawable.de, "de"))
        )
        var selectedIndex by remember { mutableStateOf(0) }
        Row {
            Box(Modifier.size(50.dp)) {
                Image(painter = painterResource(id = R.drawable.guestwise),
                    contentDescription = null,
                    modifier = Modifier.clickable { println("Button Clicked!") })
            }
            Spacer(Modifier.weight(1f))
            Box(Modifier.padding(10.dp)) {
                Image(
                    painter = painterResource(id = languages[selectedIndex].second.first),
                    contentDescription = null,
                    modifier = Modifier.clickable(onClick = { expanded = true })
                )
                DropdownMenu(
                    expanded = expanded, onDismissRequest = { expanded = false }, modifier = Modifier
                        .width(50.dp)
                        .wrapContentHeight()
                ) {
                    languages.forEachIndexed { index, s ->
                        DropdownMenuItem(onClick = {
                            selectedIndex = index
                            expanded = false
//                            mainActivityScope.launch {
//                                viewModel.changeLanguage(LanguageModel(id = s.first, lang = s.second.second))
//                            }
                        }, text = {
                            Text(text = s.second.second)
                        })
                    }
                }
            }
        }
    }
}