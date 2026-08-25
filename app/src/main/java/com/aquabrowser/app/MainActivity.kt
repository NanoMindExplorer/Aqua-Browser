package com.aquabrowser.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.aquabrowser.app.components.BrowserView
import com.aquabrowser.app.theme.DynamicAquaTheme
import com.aquabrowser.app.ui.screens.*
import com.aquabrowser.app.viewmodel.BrowserViewModel

class MainActivity : ComponentActivity() {
    private val browserViewModel: BrowserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            DynamicAquaTheme {
                MainScreenApp(browserViewModel)
            }
        }
    }
}

@Composable
fun MainScreenApp(viewModel: BrowserViewModel) {
    val currentScreen = viewModel.currentScreen.value

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize().systemBarsPadding(),
        bottomBar = {
            NavigationBar(containerColor = MaterialTheme.colorScheme.surface) {
                NavigationBarItem(
                    icon = { Text("H", color = MaterialTheme.colorScheme.onSurface) },
                    label = { Text("Home", color = MaterialTheme.colorScheme.secondary) },
                    selected = currentScreen == 0 || currentScreen == 1,
                    onClick = { viewModel.currentScreen.value = if (viewModel.tabs[viewModel.activeTabIndex.value].url.isEmpty()) 0 else 1 }
                )
                NavigationBarItem(
                    icon = { Text(viewModel.tabs.size.toString(), color = MaterialTheme.colorScheme.primary) },
                    label = { Text("Tabs", color = MaterialTheme.colorScheme.secondary) },
                    selected = currentScreen == 2,
                    onClick = { viewModel.currentScreen.value = 2 }
                )
                NavigationBarItem(
                    icon = { Text("E", color = MaterialTheme.colorScheme.onSurface) },
                    label = { Text("Ext", color = MaterialTheme.colorScheme.secondary) },
                    selected = currentScreen == 3,
                    onClick = { viewModel.currentScreen.value = 3 }
                )
                NavigationBarItem(
                    icon = { Text("S", color = MaterialTheme.colorScheme.onSurface) },
                    label = { Text("Set", color = MaterialTheme.colorScheme.secondary) },
                    selected = currentScreen == 4,
                    onClick = { viewModel.currentScreen.value = 4 }
                )
            }
        }
    ) { innerPadding ->
        Modifier.padding(innerPadding).let {
            Column(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
                // If in browser view, show Address Bar
                if (currentScreen == 1) {
                    val activeTab = viewModel.tabs[viewModel.activeTabIndex.value]
                    Row(
                        modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.surface).padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(activeTab.title, color = MaterialTheme.colorScheme.onSurface, maxLines = 1, overflow = TextOverflow.Ellipsis, modifier = Modifier.weight(1f).padding(start = 8.dp))
                    }
                    // Render the WebView
                    BrowserView(tab = activeTab) { newTitle ->
                        // Force recomposition triggers if needed
                    }
                } else {
                    // Render UI Screens
                    when (currentScreen) {
                        0 -> HomeScreen(viewModel)
                        2 -> TabSwitcherScreen(viewModel)
                        3 -> ExtensionsScreen()
                        4 -> SettingsScreen()
                    }
                }
            }
        }
    }
}
