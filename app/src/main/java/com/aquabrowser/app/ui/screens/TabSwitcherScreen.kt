package com.aquabrowser.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.aquabrowser.app.viewmodel.BrowserViewModel

@Composable
fun TabSwitcherScreen(viewModel: BrowserViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("${viewModel.tabs.size} Open Tabs", color = MaterialTheme.colorScheme.onBackground, style = MaterialTheme.typography.titleLarge)
            TextButton(onClick = { viewModel.createNewTab() }) {
                Text("+ New Tab", color = MaterialTheme.colorScheme.primary)
            }
        }
        
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(viewModel.tabs.size) { index ->
                val tab = viewModel.tabs[index]
                val isSelected = index == viewModel.activeTabIndex.value
                
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clickable { viewModel.switchTab(index) },
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    border = if (isSelected) androidx.compose.foundation.BorderStroke(2.dp, MaterialTheme.colorScheme.primary) else null
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                            Text(tab.title, color = MaterialTheme.colorScheme.onSurface, style = MaterialTheme.typography.labelLarge, maxLines = 1, overflow = TextOverflow.Ellipsis, modifier = Modifier.weight(1f))
                            IconButton(onClick = { viewModel.closeTab(index) }, modifier = Modifier.size(24.dp)) {
                                Icon(Icons.Default.Close, contentDescription = "Close Tab", tint = MaterialTheme.colorScheme.secondary)
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.White, shape = MaterialTheme.shapes.small)
                        ) {
                            // In a real app, you'd capture a Bitmap snapshot of the WebView here
                            Text(tab.url, color = Color.DarkGray, fontSize = 10.sp, modifier = Modifier.padding(8.dp))
                        }
                    }
                }
            }
        }
    }
}
