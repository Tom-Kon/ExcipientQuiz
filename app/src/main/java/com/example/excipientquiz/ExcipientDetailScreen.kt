package com.example.excipientquiz

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExcipientDetailScreen(excipient: Excipient, onBack: () -> Unit) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(excipient.name) },
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
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Image(
                painter = painterResource(id = excipient.imageRes),
                contentDescription = excipient.name,
                modifier = Modifier
                    .size(250.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(16.dp))
            DetailItem(label = "Function", value = excipient.function)
            DetailItem(label = "Molecule Type", value = excipient.moleculetype)
            DetailItem(label = "Alternative Name", value = excipient.alternativename)
            DetailItem(label = "Usage", value = excipient.usage)
            DetailItem(label = "Charge", value = excipient.charge)
            DetailItem(label = "Aqueous Solubility", value = excipient.aqsol)
            if (excipient.note != "none") {
                DetailItem(label = "Note", value = excipient.note)
            }
        }
    }
}

@Composable
private fun DetailItem(label: String, value: String) {
    if (value != "none") {
        Column(modifier = Modifier.padding(vertical = 8.dp).fillMaxWidth()) {
            Text(text = label, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text(text = value, fontSize = 16.sp)
        }
    }
}
