package com.aquabrowser.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.aquabrowser.app.ui.screens.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                MainScreenApp()
            }
        }
    }
}

@Composable
fun MainScreenApp() {
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("Home", "Tabs", "Extensions", "Bookmarks", "Settings")

    Scaffold(
        containerColor = Color(0xFF0F172A),
        bottomBar = {
            NavigationBar(containerColor = Color(0xFF1E293B)) {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = { /* You can add icons here later */ Text(item[0].toString(), color = Color.White) },
                        label = { Text(item, color = Color.LightGray) },
                        selected = selectedItem == index,
                        onClick = { selectedItem = index }
                    )
                }
            }
        }
    ) { innerPadding ->
        Modifier.padding(innerPadding).let {
            when (selectedItem) {
                0 -> HomeScreen()
                1 -> TabSwitcherScreen()
                2 -> ExtensionsScreen()
                3 -> BookmarksScreen()
                4 -> SettingsScreen()
            }
        }
    }
}
