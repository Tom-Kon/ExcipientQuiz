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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

data class Achievement(
    val id: String,
    val name: String,
    val description: String,
    val imageRes: Int
)

val staticAchievements = listOf(
    Achievement(
        id = AchievementManager.TIME_ATTACK_ACE,
        name = "Time Attack Ace",
        description = "Achieve Timing Expert status in all quiz categories.",
        imageRes = R.drawable.badge_time_attack_ace
    ),
    Achievement(
        id = AchievementManager.SURVIVALIST,
        name = "Survivalist",
        description = "Achieve Survival Expert status in all quiz categories.",
        imageRes = R.drawable.badge_survivalist
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AchievementsScreen(onBack: () -> Unit) {
    val context = LocalContext.current

    val dynamicAchievements = quizModes.keys
        .filter { it != "All Excipients" && it != "Other" }
        .flatMap { category ->
            listOf(
                Achievement(
                    id = AchievementManager.getSurvivalExpertId(category),
                    name = "$category Survival Expert",
                    description = "Complete all Q&A pairs for $category in Survival mode.",
                    imageRes = R.drawable.ic_survival
                ),
                Achievement(
                    id = AchievementManager.getTimeAttackExpertId(category),
                    name = "$category Timing Expert",
                    description = "Complete all Q&A pairs for $category in Time Attack mode.",
                    imageRes = R.drawable.ic_time
                )
            )
        }

    val allAchievements = (staticAchievements + dynamicAchievements).sortedBy { it.name }

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
                AchievementCard(achievement = achievement, isUnlocked = AchievementManager.isAchievementUnlocked(context, achievement.id))
            }
        }
    }
}

@Composable
fun AchievementCard(achievement: Achievement, isUnlocked: Boolean) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .alpha(if (isUnlocked) 1f else 0.5f)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = achievement.imageRes),
                contentDescription = achievement.name,
                modifier = Modifier.size(96.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = achievement.name, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = achievement.description, style = MaterialTheme.typography.bodySmall, textAlign = TextAlign.Center)
        }
    }
}
