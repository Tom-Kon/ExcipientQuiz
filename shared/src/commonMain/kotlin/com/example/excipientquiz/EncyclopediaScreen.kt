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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
    selectedFunction: String,
    onSelectedFunctionChange: (String) -> Unit,
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    onExcipientSelected: (Excipient) -> Unit, 
    onBack: () -> Unit
) {
    var showFilter by remember { mutableStateOf(false) }
    var isFilterMenuExpanded by remember { mutableStateOf(false) }
    var showSearch by remember { mutableStateOf(false) }
    val functions = encyclopediaFilters.keys.toList()

    val filteredExcipients = remember(selectedFunction, searchText) {
        val functionFiltered = if (selectedFunction == "All Functions") {
            excipients
        } else {
            encyclopediaFilters[selectedFunction] ?: emptyList()
        }

        if (searchText.isBlank()) {
            functionFiltered.sortedBy { it.name }
        } else {
            functionFiltered.filter { excipient ->
                excipient.name.contains(searchText, ignoreCase = true) ||
                excipient.alternativename.contains(searchText, ignoreCase = true) ||
                excipient.function.contains(searchText, ignoreCase = true) ||
                excipient.usage.contains(searchText, ignoreCase = true) ||
                excipient.note.contains(searchText, ignoreCase = true)
            }.sortedBy { it.name }
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Excipient Encyclopedia") },
                navigationIcon = {
                    IconButton(onClick = { 
                        showFilter = !showFilter
                        if (showFilter) showSearch = false // Hide search when showing filter
                    }) {
                        Icon(Icons.Default.List, contentDescription = "Filter")
                    }
                },
                actions = {
                    IconButton(onClick = { 
                        showSearch = !showSearch
                        if (showSearch) showFilter = false // Hide filter when showing search
                     }) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                },
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
            if (showFilter) {
                ExposedDropdownMenuBox(
                    expanded = isFilterMenuExpanded,
                    onExpandedChange = { isFilterMenuExpanded = it },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextField(
                        value = selectedFunction,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Filter by Function") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isFilterMenuExpanded) },
                        colors = ExposedDropdownMenuDefaults.textFieldColors(),
                        modifier = Modifier.menuAnchor().fillMaxWidth()
                    )
                    ExposedDropdownMenu(
                        expanded = isFilterMenuExpanded,
                        onDismissRequest = { isFilterMenuExpanded = false }
                    ) {
                        functions.forEach { function ->
                            DropdownMenuItem(
                                text = { Text(function) },
                                onClick = {
                                    onSelectedFunctionChange(function)
                                    isFilterMenuExpanded = false
                                }
                            )
                        }
                    }
                }
            }

            if(showSearch) {
                TextField(
                    value = searchText,
                    onValueChange = onSearchTextChange,
                    label = { Text("Search...") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                state = listState,
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
