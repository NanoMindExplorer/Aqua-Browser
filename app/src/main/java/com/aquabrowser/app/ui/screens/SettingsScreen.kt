package com.aquabrowser.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.aquabrowser.app.theme.ThemeManager

@Composable
fun SettingsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        Text("Settings", color = MaterialTheme.colorScheme.onBackground, style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(20.dp))
        
        SettingsItem("Search Engine", "Google", onClick = {})
        
        // Theme Engine Demo
        Text("Chrome Web Store Themes (Demo)", color = MaterialTheme.colorScheme.primary, modifier = Modifier.padding(top = 16.dp, bottom = 8.dp))
        
        SettingsItem("Apply 'Cyberpunk Pink' Theme", "Simulates parsing a Chrome Theme JSON") {
            val mockChromeJson = """
                {
                  "theme": {
                    "colors": {
                      "frame": [45, 0, 30],
                      "toolbar": [255, 0, 128],
                      "tab_text": [255, 255, 255],
                      "button_background": [0, 255, 255]
                    }
                  }
                }
            """.trimIndent()
            ThemeManager.instance.applyChromeThemeFromJson(mockChromeJson)
        }
        
        SettingsItem("Reset Theme", "Back to Default Aqua") {
            ThemeManager.instance.resetToDefault()
        }
        
        Spacer(modifier = Modifier.height(20.dp))
        SettingsItem("Privacy and Security", "Clear browsing data, block tracking", onClick = {})
        SettingsItem("Downloads", "Ask where to save files", onClick = {})
    }
}

@Composable
fun SettingsItem(title: String, subtitle: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp).clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(16.dp).fillMaxWidth()) {
            Text(title, color = MaterialTheme.colorScheme.onSurface, style = MaterialTheme.typography.titleMedium)
            Text(subtitle, color = MaterialTheme.colorScheme.secondary, style = MaterialTheme.typography.bodySmall)
        }
    }
}
