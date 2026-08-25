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
            .background(Color(0xFF020617))
            .padding(16.dp)
    ) {
        Text("Extensions", color = Color.White, style = MaterialTheme.typography.headlineMedium)
        Text("Manage your installed desktop tools", color = Color.Gray, style = MaterialTheme.typography.bodyMedium)
        
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
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B))
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(title, color = Color.White)
                Text(desc, color = Color.Gray, style = MaterialTheme.typography.bodySmall)
            }
            Switch(checked = true, onCheckedChange = {})
        }
    }
}
