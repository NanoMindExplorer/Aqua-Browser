package com.aquabrowser.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.aquabrowser.app.theme.DynamicAquaTheme
import com.aquabrowser.app.ui.screens.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Memaksa mode Fullscreen (Edge-to-Edge) untuk menutupi notch dan bezel
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            DynamicAquaTheme {
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
        containerColor = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize().systemBarsPadding(), // Mencegah UI tertutup kamera/notch
        bottomBar = {
            NavigationBar(containerColor = MaterialTheme.colorScheme.surface) {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = { Text(item[0].toString(), color = MaterialTheme.colorScheme.onSurface) },
                        label = { Text(item, color = MaterialTheme.colorScheme.secondary) },
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
