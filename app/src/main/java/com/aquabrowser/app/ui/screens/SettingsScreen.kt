package com.aquabrowser.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF020617))
            .padding(16.dp)
    ) {
        Text("Settings", color = Color.White, style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(20.dp))
        
        SettingsItem("Search Engine", "Google")
        SettingsItem("Theme", "System Default")
        SettingsItem("Privacy and Security", "Clear browsing data, block tracking")
        SettingsItem("Site Settings", "Permissions, cookies, pop-ups")
        SettingsItem("Downloads", "Ask where to save files")
    }
}

@Composable
fun SettingsItem(title: String, subtitle: String) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B))
    ) {
        Column(modifier = Modifier.padding(16.dp).fillMaxWidth()) {
            Text(title, color = Color.White, style = MaterialTheme.typography.titleMedium)
            Text(subtitle, color = Color.Gray, style = MaterialTheme.typography.bodySmall)
        }
    }
}
