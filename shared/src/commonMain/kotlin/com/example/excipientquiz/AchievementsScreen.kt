package com.example.excipientquiz

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import excipientquiz.shared.generated.resources.*
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AchievementsScreen(onBack: () -> Unit) {
    val allAchievements = AchievementManager.getAllAchievements()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Achievements") },
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
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            items(allAchievements) { achievement ->
                AchievementCard(achievement = achievement, isUnlocked = achievement.isUnlocked())
            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun AchievementCard(achievement: Achievement, isUnlocked: Boolean) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .alpha(if (isUnlocked) 1f else 0.25f)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            getAchievementDrawableResourceByName(achievement.imageRes)?.let { 
                Image(painter = painterResource(it), contentDescription = achievement.title, modifier = Modifier.size(96.dp))
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = achievement.title, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = achievement.description,
                style = MaterialTheme.typography.bodySmall, 
                textAlign = TextAlign.Center
            )
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
private fun getAchievementDrawableResourceByName(name: String): DrawableResource? {
    return when (name) {
        "allachievementtime" -> Res.drawable.allachievementtime
        "allachievementsurvival" -> Res.drawable.allachievementsurvival
        "creamsachievementtime" -> Res.drawable.creamsachievementtime
        "creamsachievementsurvival" -> Res.drawable.creamsachievementsurvival
        "liquidsachievementtime" -> Res.drawable.liquidsachievementtime
        "liquidsachievementsurvival" -> Res.drawable.liquidsachievementsurvival
        "soliddosageachievementtime" -> Res.drawable.soliddosageachievementtime
        "soliddosageachievementsurvival" -> Res.drawable.soliddosageachievementsurvival
        "suspensionsachievementtime" -> Res.drawable.suspensionsachievementtime
        "suspensionsachievementsurvival" -> Res.drawable.suspensionsachievementsurvival
        "preservativesachievementtime" -> Res.drawable.preservativesachievementtime
        "preservativesachievementsurvival" -> Res.drawable.preservativesachievementsurvival
        else -> null
    }
}
