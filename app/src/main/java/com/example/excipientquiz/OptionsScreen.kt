package com.example.excipientquiz

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun OptionsScreen(
    availableModes: Map<String, List<Excipient>>,
    initialSelection: Set<String>,
    onSave: (Set<String>) -> Unit,
    onBack: () -> Unit
) {
    val selectedModes = remember { mutableStateOf(initialSelection) }
    val allExcipientsMode = "All Excipients"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Select Quiz Content", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(availableModes.keys.toList()) { mode ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { 
                            val currentSelection = selectedModes.value.toMutableSet()

                            if (mode == allExcipientsMode) {
                                // If "All" is clicked, it becomes the only selection
                                selectedModes.value = setOf(allExcipientsMode)
                            } else {
                                // If another item is clicked, "All" is deselected
                                currentSelection.remove(allExcipientsMode)
                                
                                // Toggle the clicked item
                                if (currentSelection.contains(mode)) {
                                    currentSelection.remove(mode)
                                } else {
                                    currentSelection.add(mode)
                                }

                                // If nothing is left, default back to "All"
                                if (currentSelection.isEmpty()) {
                                    selectedModes.value = setOf(allExcipientsMode)
                                } else {
                                    selectedModes.value = currentSelection
                                }
                            }
                        }
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = selectedModes.value.contains(mode),
                        onCheckedChange = null // Handled by the Row's clickable
                    )
                    Text(text = mode, modifier = Modifier.padding(start = 16.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Button(onClick = onBack, modifier = Modifier.padding(end = 8.dp)) {
                Text("Back")
            }
            Button(onClick = { onSave(selectedModes.value) }) {
                Text("Save")
            }
        }
    }
}
