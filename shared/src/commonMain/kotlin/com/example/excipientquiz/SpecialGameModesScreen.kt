package com.example.excipientquiz

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpecialGameModesScreen(onModeSelected: (String) -> Unit, onBack: () -> Unit) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Special Game Modes") }
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Button(onClick = onBack) {
                    Text("Back")
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(specialGameModes) { mode ->
                    val isLocked = !ProgressionManager.isSpecialModeUnlocked(mode.id)
                    SpecialGameModeCard(mode = mode, isLocked = isLocked, onClick = { onModeSelected(mode.id) })
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "To play \"Emulsion emulator\" and \"Lanette Lingering\" you need to unlock the \"All\" tier for \"Creams & Emulsions\".\nTo play \"Cellulose Connoisseur\" you need to unlock the All tier for \"Solid dosage forms\".\nTo play \"Stunning Stability\" you need to unlock the All tier for \"Preservatives & antioxidants\".",
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun SpecialGameModeCard(mode: SpecialGameMode, isLocked: Boolean, onClick: () -> Unit) {
    val textColor = if (isLocked) Color.Gray else Color.Unspecified
    val cardColors = if (isLocked) CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.5f)) else CardDefaults.cardColors()

    Card(
        colors = cardColors,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = !isLocked, onClick = onClick),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(mode.name, fontWeight = FontWeight.Bold, color = textColor)
            Text(mode.description, color = textColor)
        }
    }
}
