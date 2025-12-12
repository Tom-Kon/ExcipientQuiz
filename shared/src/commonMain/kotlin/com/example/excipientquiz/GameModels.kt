package com.example.excipientquiz

import com.russhwolf.settings.get
import com.russhwolf.settings.set

private val appSettings = createSettings()

enum class GameMode {
    EXCIPIENT_SPEEDRUN,
    SURVIVAL
}

enum class MusicType {
    MENU,
    EXCIPIENT_SPEEDRUN,
    SURVIVAL
}

enum class SoundEffect {
    SUCCESS,
    FAIL,
    WHOOSH,
    GAME_OVER,
    SUCCESS_END,
    ACHIEVEMENT
}

enum class PropertyType {
    NAME,
    STRUCTURE,
    ALTERNATIVE_NAME,
    MOLECULE_TYPE,
    FUNCTION
}

enum class ProgressionTier(val unlockedExcipients: List<String>) {
    LOCKED(emptyList()),
    ALTERNATIVE_NAMES(listOf("Alternative Name", "Molecule Type")),
    FULLY_UNLOCKED(listOf("Function"))
}

data class Progression(val tier: ProgressionTier)

enum class Achievement(
    val title: String,
    val description: String,
    val imageRes: String,
    private val checkLogic: (score: Int, time: Long, wasSuccessful: Boolean, questionCount: Int, gameMode: GameMode, qType: PropertyType, aType: PropertyType, selectedQuizModes: Set<String>) -> Boolean
) {
    QUICK_THINKER(
        title = "Quick Thinker",
        description = "Complete a Time Attack game in under 60 seconds",
        imageRes = "allachievementtime",
        checkLogic = { score, time, wasSuccessful, questionCount, gameMode, qType, aType, selectedQuizModes ->
            gameMode == GameMode.EXCIPIENT_SPEEDRUN && wasSuccessful && time < 60
        }
    ),
    SURVIVAL_EXPERT(
        title = "Survival Expert",
        description = "Survive for 10 rounds in Survival mode",
        imageRes = "allachievementsurvival",
        checkLogic = { score, time, wasSuccessful, questionCount, gameMode, qType, aType, selectedQuizModes ->
            gameMode == GameMode.SURVIVAL && score >= 10
        }
    ),
    CREAMS_TIME(
        title = "Creams Connoisseur (Time)",
        description = "Complete a time attack game in the creams category.",
        imageRes = "creamsachievementtime",
        checkLogic = { _, _, wasSuccessful, _, gameMode, _, _, selectedQuizModes ->
            gameMode == GameMode.EXCIPIENT_SPEEDRUN && wasSuccessful && selectedQuizModes == setOf("Creams & Emulsions")
        }
    ),
    CREAMS_SURVIVAL(
        title = "Creams Connoisseur (Survival)",
        description = "Survive 10 rounds in a survival game in the creams category.",
        imageRes = "creamsachievementsurvival",
        checkLogic = { score, _, _, _, gameMode, _, _, selectedQuizModes ->
            gameMode == GameMode.SURVIVAL && score >= 10 && selectedQuizModes == setOf("Creams & Emulsions")
        }
    ),
    LIQUIDS_TIME(
        title = "Liquids Luminary (Time)",
        description = "Complete a time attack game in the liquids category.",
        imageRes = "liquidsachievementtime",
        checkLogic = { _, _, wasSuccessful, _, gameMode, _, _, selectedQuizModes ->
            gameMode == GameMode.EXCIPIENT_SPEEDRUN && wasSuccessful && selectedQuizModes == setOf("Parenterals & Liquids")
        }
    ),
    LIQUIDS_SURVIVAL(
        title = "Liquids Luminary (Survival)",
        description = "Survive 10 rounds in a survival game in the liquids category.",
        imageRes = "liquidsachievementsurvival",
        checkLogic = { score, _, _, _, gameMode, _, _, selectedQuizModes ->
            gameMode == GameMode.SURVIVAL && score >= 10 && selectedQuizModes == setOf("Parenterals & Liquids")
        }
    ),
    SOLID_DOSAGE_TIME(
        title = "Solid Strategist (Time)",
        description = "Complete a time attack game in the solid dosage category.",
        imageRes = "soliddosageachievementtime",
        checkLogic = { _, _, wasSuccessful, _, gameMode, _, _, selectedQuizModes ->
            gameMode == GameMode.EXCIPIENT_SPEEDRUN && wasSuccessful && selectedQuizModes == setOf("Solid dosage forms")
        }
    ),
    SOLID_DOSAGE_SURVIVAL(
        title = "Solid Strategist (Survival)",
        description = "Survive 10 rounds in a survival game in the solid dosage category.",
        imageRes = "soliddosageachievementsurvival",
        checkLogic = { score, _, _, _, gameMode, _, _, selectedQuizModes ->
            gameMode == GameMode.SURVIVAL && score >= 10 && selectedQuizModes == setOf("Solid dosage forms")
        }
    ),
    SUSPENSIONS_TIME(
        title = "Suspensions Specialist (Time)",
        description = "Complete a time attack game in the suspensions category.",
        imageRes = "suspensionsachievementtime",
        checkLogic = { _, _, wasSuccessful, _, gameMode, _, _, selectedQuizModes ->
            gameMode == GameMode.EXCIPIENT_SPEEDRUN && wasSuccessful && selectedQuizModes == setOf("Gels & Suspensions")
        }
    ),
    SUSPENSIONS_SURVIVAL(
        title = "Suspensions Specialist (Survival)",
        description = "Survive 10 rounds in a survival game in the suspensions category.",
        imageRes = "suspensionsachievementsurvival",
        checkLogic = { score, _, _, _, gameMode, _, _, selectedQuizModes ->
            gameMode == GameMode.SURVIVAL && score >= 10 && selectedQuizModes == setOf("Gels & Suspensions")
        }
    ),
    PRESERVATIVES_TIME(
        title = "Preservatives Pro (Time)",
        description = "Complete a time attack game in the preservatives category.",
        imageRes = "preservativesachievementtime",
        checkLogic = { _, _, wasSuccessful, _, gameMode, _, _, selectedQuizModes ->
            gameMode == GameMode.EXCIPIENT_SPEEDRUN && wasSuccessful && selectedQuizModes == setOf("Preservatives & antioxidants")
        }
    ),
    PRESERVATIVES_SURVIVAL(
        title = "Preservatives Pro (Survival)",
        description = "Survive 10 rounds in a survival game in the preservatives category.",
        imageRes = "preservativesachievementsurvival",
        checkLogic = { score, _, _, _, gameMode, _, _, selectedQuizModes ->
            gameMode == GameMode.SURVIVAL && score >= 10 && selectedQuizModes == setOf("Preservatives & antioxidants")
        }
    );

    fun isUnlocked(): Boolean = appSettings.get(name, false)

    fun unlock() {
        appSettings[name] = true
    }

    fun check(score: Int, time: Long, wasSuccessful: Boolean, questionCount: Int, gameMode: GameMode, qType: PropertyType, aType: PropertyType, selectedQuizModes: Set<String>): Boolean {
        return checkLogic(score, time, wasSuccessful, questionCount, gameMode, qType, aType, selectedQuizModes)
    }
}
