package com.aquabrowser.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aquabrowser.app.viewmodel.BrowserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: BrowserViewModel) {
    var inputText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        
        Text("Aqua Browser", color = MaterialTheme.colorScheme.primary, fontSize = 28.sp)
        
        Spacer(modifier = Modifier.height(40.dp))
        
        OutlinedTextField(
            value = inputText,
            onValueChange = { inputText = it },
            placeholder = { Text("Search or type URL", color = MaterialTheme.colorScheme.secondary) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth().height(60.dp),
            shape = RoundedCornerShape(25.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = MaterialTheme.colorScheme.surface,
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = MaterialTheme.colorScheme.primary
            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Go),
            keyboardActions = KeyboardActions(
                onGo = {
                    if (inputText.isNotBlank()) {
                        viewModel.loadUrlInActiveTab(inputText)
                    }
                }
            )
        )
    }
}
