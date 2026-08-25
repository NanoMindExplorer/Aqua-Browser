package com.aquabrowser.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BookmarksScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        Text("Bookmarks & History", color = MaterialTheme.colorScheme.onBackground, style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(20.dp))
        
        BookmarkItem("GitHub", "https://github.com")
        BookmarkItem("Stack Overflow", "https://stackoverflow.com")
        BookmarkItem("Android Developers", "https://developer.android.com")
    }
}

@Composable
fun BookmarkItem(title: String, url: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp)) {
        Text(title, color = MaterialTheme.colorScheme.onBackground, style = MaterialTheme.typography.titleMedium)
        Text(url, color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.bodySmall)
        Divider(color = Color.DarkGray, modifier = Modifier.padding(top = 8.dp))
    }
}
