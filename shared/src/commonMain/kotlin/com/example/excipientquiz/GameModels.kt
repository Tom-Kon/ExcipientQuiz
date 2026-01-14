package com.example.excipientquiz

import com.russhwolf.settings.get
import com.russhwolf.settings.set
import io.github.oshai.kotlinlogging.KotlinLogging

private val appSettings = createSettings()
private val logger = KotlinLogging.logger("QuizLogic")

enum class GameMode {
    EXCIPIENT_SPEEDRUN,
    SURVIVAL,
    STUDY
}

data class Question(
    val questionText: String,
    val options: List<String>,    val correctAnswer: String,
    val questionProperty: PropertyType,
    val answerProperty: PropertyType,
    val correctExcipient: Excipient // Keep track of the correct excipient for feedback
)

fun generateQuestion(
    questionType: PropertyType,
    answerType: PropertyType,
    selectedQuizModes: Set<String>,
    forceCorrectExcipient: Excipient? = null
): Question {

    // 1. Make a selected excipient pool based on the quiz content selection
    val availableExcipients = if (selectedQuizModes.contains("All Excipients")) {
        excipients
    } else {
        selectedQuizModes.flatMap { mode -> com.example.excipientquiz.quizModes[mode] ?: emptyList() }.distinct()
    }

    if (availableExcipients.isEmpty()) {
        logger.warn { "No questions available for quizModes: $selectedQuizModes. Returning fallback question." }
        return Question("No questions available for this mode!", listOf("OK"), "OK", questionType, answerType, excipients.first())
    }

    // 2. Pick the correct excipient. Use the forced one if provided, otherwise pick random.
    val correctExcipient = forceCorrectExcipient ?: availableExcipients.random()
    
    val questionText = getProperty(correctExcipient, questionType)
    val correctAnswerText = getProperty(correctExcipient, answerType)

    val distractorTexts: List<String>

    if (answerType == PropertyType.MOLECULE_TYPE) {
        // Special case for Molecule Type: Always use the fixed set of options.
        val allMoleculeTypes = listOf("Polymer", "Small organic molecule", "Inorganic molecule")
        distractorTexts = allMoleculeTypes.filter { it != correctAnswerText }
    } else {
        // 3. Find an appropriate distractor pool: filter the excipients selected in 1 so they do not have the same question tag as mentioned in step 2.
        // "Question tag" here refers to the property value being asked (e.g., if Question is Function, tag is "Preservative").
        val distractorPool = availableExcipients.filter { excipient ->
            getProperty(excipient, questionType) != questionText
        }

        // Pick 3 random distractors from the filtered pool.
        // We also ensure the answer text is unique and not equal to the correct answer to avoid duplicate buttons.
        // We also explicitly filter out "none" or blank answers so they don't appear as distractors.
        distractorTexts = distractorPool
            .map { getProperty(it, answerType) }
            .filter { it.isNotBlank() && !it.equals("none", ignoreCase = true) }
            .distinct()
            .filter { it != correctAnswerText }
            .shuffled()
            .take(3)
    }

    // Combine and shuffle options
    val options = (distractorTexts + correctAnswerText).shuffled()

    // --- DEBUGGING WITH THE LOGGER ---
    logger.info { "--- NEW QUESTION ---" }
    logger.info { "Question: '$questionText' | Correct Answer: '$correctAnswerText'" }
    logger.info { "Selected Modes: $selectedQuizModes | Pool Size: ${availableExcipients.size}" }
    logger.info { "Final Options: $options" }
    logger.info { "--------------------" }
    // --- END DEBUGGING ---

    return Question(
        questionText = questionText,
        options = options,
        correctAnswer = correctAnswerText,
        questionProperty = questionType,
        answerProperty = answerType,
        correctExcipient = correctExcipient
    )
}


fun getProperty(excipient: Excipient, propertyType: PropertyType): String {
    return when (propertyType) {
        PropertyType.NAME -> excipient.name
        PropertyType.STRUCTURE -> excipient.imageRes
        PropertyType.FUNCTION -> excipient.function
        PropertyType.MOLECULE_TYPE -> excipient.moleculetype
        PropertyType.ALTERNATIVE_NAME -> excipient.alternativename
    }
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
        description = "Reach 90% correct answers in the all excipients category with < 3.5s per answer.",
        imageRes = "allachievementtime",
        checkLogic = { _, _, wasSuccessful, _, gameMode, _, _, selectedQuizModes ->
            gameMode == GameMode.EXCIPIENT_SPEEDRUN && wasSuccessful && selectedQuizModes.contains("All Excipients") && ProgressionManager.hasCompletedSpeedrunAchievement("All Excipients")
        }
    ),
    SURVIVAL_EXPERT(
        title = "Survival Expert",
        description = "Survive all possible quizzes in the all excipients category.",
        imageRes = "allachievementsurvival",
        checkLogic = { _, _, wasSuccessful, _, gameMode, _, _, selectedQuizModes ->
            gameMode == GameMode.SURVIVAL && wasSuccessful && selectedQuizModes.contains("All Excipients") && ProgressionManager.hasCompletedAllSurvivalQuizzes("All Excipients")
        }
    ),
    CREAMS_TIME(
        title = "Creams Connoisseur (Time)",
        description = "Reach 90% correct answers in the creams category with < 3.5s per answer.",
        imageRes = "creamsachievementtime",
        checkLogic = { _, _, wasSuccessful, _, gameMode, _, _, selectedQuizModes ->
            gameMode == GameMode.EXCIPIENT_SPEEDRUN && wasSuccessful && ProgressionManager.hasCompletedSpeedrunAchievement("Creams & Emulsions")
        }
    ),
    CREAMS_SURVIVAL(
        title = "Creams Connoisseur (Survival)",
        description = "Survive all possible quizzes in the creams category.",
        imageRes = "creamsachievementsurvival",
        checkLogic = { score, _, wasSuccessful, _, gameMode, qType, aType, selectedQuizModes ->
            gameMode == GameMode.SURVIVAL && wasSuccessful && ProgressionManager.hasCompletedAllSurvivalQuizzes("Creams & Emulsions")
        }
    ),
    LIQUIDS_TIME(
        title = "Liquids Luminary (Time)",
        description = "Reach 90% correct answers in the liquids category with < 3.5s per answer.",
        imageRes = "liquidsachievementtime",
        checkLogic = { _, _, wasSuccessful, _, gameMode, _, _, selectedQuizModes ->
            gameMode == GameMode.EXCIPIENT_SPEEDRUN && wasSuccessful && ProgressionManager.hasCompletedSpeedrunAchievement("Parenterals & Liquids")
        }
    ),
    LIQUIDS_SURVIVAL(
        title = "Liquids Luminary (Survival)",
        description = "Survive all possible quizzes in the liquids category.",
        imageRes = "liquidsachievementsurvival",
        checkLogic = { score, _, wasSuccessful, _, gameMode, qType, aType, selectedQuizModes ->
            gameMode == GameMode.SURVIVAL && wasSuccessful && ProgressionManager.hasCompletedAllSurvivalQuizzes("Parenterals & Liquids")
        }
    ),
    SOLID_DOSAGE_TIME(
        title = "Solid Strategist (Time)",
        description = "Reach 90% correct answers in the solid dosage category with < 3.5s per answer.",
        imageRes = "soliddosageachievementtime",
        checkLogic = { _, _, wasSuccessful, _, gameMode, _, _, selectedQuizModes ->
            gameMode == GameMode.EXCIPIENT_SPEEDRUN && wasSuccessful && ProgressionManager.hasCompletedSpeedrunAchievement("Solid dosage forms")
        }
    ),
    SOLID_DOSAGE_SURVIVAL(
        title = "Solid Strategist (Survival)",
        description = "Survive all possible quizzes in the solid dosage category.",
        imageRes = "soliddosageachievementsurvival",
        checkLogic = { score, _, wasSuccessful, _, gameMode, qType, aType, selectedQuizModes ->
            gameMode == GameMode.SURVIVAL && wasSuccessful && ProgressionManager.hasCompletedAllSurvivalQuizzes("Solid dosage forms")
        }
    ),
    SUSPENSIONS_TIME(
        title = "Suspensions Specialist (Time)",
        description = "Reach 90% correct answers in the suspensions category with < 3.5s per answer.",
        imageRes = "suspensionsachievementtime",
        checkLogic = { _, _, wasSuccessful, _, gameMode, _, _, selectedQuizModes ->
            gameMode == GameMode.EXCIPIENT_SPEEDRUN && wasSuccessful && ProgressionManager.hasCompletedSpeedrunAchievement("Gels & Suspensions")
        }
    ),
    SUSPENSIONS_SURVIVAL(
        title = "Suspensions Specialist (Survival)",
        description = "Survive all possible quizzes in the suspensions category.",
        imageRes = "suspensionsachievementsurvival",
        checkLogic = { score, _, wasSuccessful, _, gameMode, qType, aType, selectedQuizModes ->
            gameMode == GameMode.SURVIVAL && wasSuccessful && ProgressionManager.hasCompletedAllSurvivalQuizzes("Gels & Suspensions")
        }
    ),
    PRESERVATIVES_TIME(
        title = "Preservatives Pro (Time)",
        description = "Reach 90% correct answers in the preservatives category with < 3.5s per answer.",
        imageRes = "preservativesachievementtime",
        checkLogic = { _, _, wasSuccessful, _, gameMode, _, _, selectedQuizModes ->
            gameMode == GameMode.EXCIPIENT_SPEEDRUN && wasSuccessful && ProgressionManager.hasCompletedSpeedrunAchievement("Preservatives & antioxidants")
        }
    ),
    PRESERVATIVES_SURVIVAL(
        title = "Preservatives Pro (Survival)",
        description = "Survive all possible quizzes in the preservatives category.",
        imageRes = "preservativesachievementsurvival",
        checkLogic = { score, _, wasSuccessful, _, gameMode, qType, aType, selectedQuizModes ->
            gameMode == GameMode.SURVIVAL && wasSuccessful && ProgressionManager.hasCompletedAllSurvivalQuizzes("Preservatives & antioxidants")
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
