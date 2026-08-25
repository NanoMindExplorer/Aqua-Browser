package com.aquabrowser.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ExtensionsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        Text("Extensions", color = MaterialTheme.colorScheme.onBackground, style = MaterialTheme.typography.headlineMedium)
        Text("Manage your installed desktop tools", color = MaterialTheme.colorScheme.secondary, style = MaterialTheme.typography.bodyMedium)
        
        Spacer(modifier = Modifier.height(20.dp))
        
        // Example Extension Item
        ExtensionItem("uBlock Origin", "Block ads and trackers")
        ExtensionItem("Dark Reader", "Dark mode for every website")
        ExtensionItem("Grammarly", "Grammar checker")
    }
}

@Composable
fun ExtensionItem(title: String, desc: String) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(title, color = MaterialTheme.colorScheme.onBackground)
                Text(desc, color = MaterialTheme.colorScheme.secondary, style = MaterialTheme.typography.bodySmall)
            }
            Switch(checked = true, onCheckedChange = {})
        }
    }
}
