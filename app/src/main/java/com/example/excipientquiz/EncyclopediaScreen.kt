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
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EncyclopediaScreen(
    listState: LazyListState,
    onExcipientSelected: (Excipient) -> Unit, 
    onBack: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val functions = encyclopediaFilters.keys.toList()
    var selectedFunction by remember { mutableStateOf(functions[0]) }

    val filteredExcipients = remember(selectedFunction) {
        if (selectedFunction == "All Functions") {
            excipients.sortedBy { it.name }
        } else {
            encyclopediaFilters[selectedFunction] ?: emptyList()
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Excipient Encyclopedia") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent
                )
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
            modifier = Modifier.padding(innerPadding).padding(horizontal = 16.dp)
        ) {
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
                modifier = Modifier.fillMaxWidth()
            ) {
                TextField(
                    value = selectedFunction,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Filter by Function") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier.menuAnchor().fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    functions.forEach { function ->
                        DropdownMenuItem(
                            text = { Text(function) },
                            onClick = {
                                selectedFunction = function
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                state = listState, // Use the hoisted state here
                modifier = Modifier.fillMaxSize()
            ) {
                items(filteredExcipients) { excipient ->
                    ExcipientSummaryCard(excipient = excipient, onClick = { onExcipientSelected(excipient) })
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
fun ExcipientSummaryCard(excipient: Excipient, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(excipient.name, fontWeight = FontWeight.Bold)
            Text("Function: ${excipient.function}")
            Text("Usage: ${excipient.usage}")
        }
    }
}
