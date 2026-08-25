package com.aquabrowser.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0F172A)) // Dark ocean background
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        
        Text("Aqua Browser", color = Color(0xFF38BDF8), fontSize = 28.sp)
        
        Spacer(modifier = Modifier.height(40.dp))
        
        // Search Bar Mockup
        Surface(
            modifier = Modifier.fillMaxWidth().height(50.dp),
            shape = RoundedCornerShape(25.dp),
            color = Color(0xFF1E293B).copy(alpha = 0.8f) // Glassmorphism feel
        ) {
            Box(contentAlignment = Alignment.CenterStart, modifier = Modifier.padding(start = 20.dp)) {
                Text("Search or type URL", color = Color.Gray)
            }
        }
        
        // Additional UI elements (Speed dials) would go here
    }
}
