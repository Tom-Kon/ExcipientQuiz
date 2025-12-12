package com.example.excipientquiz

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProgressionScreen(onBack: () -> Unit) {
    val progressionCategories = remember {
        quizModes.keys.filter { it != "All Excipients" && it != "Other" }.sorted()
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Progression Status") }
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            items(progressionCategories) { category ->
                val progression = remember(category) { ProgressionManager.getProgression(category) }
                ProgressionItem(category = category, tier = progression.tier)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
private fun ProgressionItem(category: String, tier: ProgressionTier) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = category, modifier = Modifier.weight(1f), fontWeight = FontWeight.SemiBold)
            
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Tier 2", style = MaterialTheme.typography.bodySmall)
                Checkbox(
                    checked = tier >= ProgressionTier.ALTERNATIVE_NAMES,
                    onCheckedChange = null, // Read-only
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("All", style = MaterialTheme.typography.bodySmall)
                Checkbox(
                    checked = tier == ProgressionTier.FULLY_UNLOCKED,
                    onCheckedChange = null, // Read-only
                )
            }
        }
    }
}
