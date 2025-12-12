package com.example.excipientquiz

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun OptionsScreen(
    availableModes: Map<String, List<Excipient>>,
    initialSelection: Set<String>,
    onSave: (Set<String>) -> Unit,
    onBack: () -> Unit,
    onShowSpecialModes: () -> Unit
) {
    val selectedModes = remember { mutableStateListOf(*initialSelection.toTypedArray()) }

    Scaffold(
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = onBack) { Text("Back") }
                Button(onClick = { onSave(selectedModes.toSet()) }) { Text("Save") }
            }
        }
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding).padding(horizontal = 16.dp)) {
            item {
                Text(text = "Select Quiz Content", style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(16.dp))
            }
            
            items(availableModes.keys.toList()) { mode ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = selectedModes.contains(mode),
                        onCheckedChange = {
                            if (it) selectedModes.add(mode) else selectedModes.remove(mode)
                        }
                    )
                    Text(mode)
                }
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
                Button(onClick = onShowSpecialModes, modifier = Modifier.fillMaxWidth()) {
                    Text("Special Game Modes")
                }
            }
        }
    }
}
