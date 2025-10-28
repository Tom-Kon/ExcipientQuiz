package com.example.excipientquiz

import android.content.Context
import android.content.SharedPreferences

enum class ProgressionTier {
    LOCKED,          // Only Name/Structure is available in Survival
    ALTERNATIVE_NAMES, // Alt names and Molecule Type are unlocked in Survival, Base pair in Time Attack
    FULLY_UNLOCKED   // All pairs available in Survival, Tier 2 pairs in Time Attack
}

object ProgressionManager {

    private const val PREFS_NAME = "ExcipientQuizProgression"

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    private fun getTierKey(quizMode: String) = "tier_$quizMode"

    fun getProgressionTier(context: Context, quizMode: String): ProgressionTier {
        val tierName = getPreferences(context).getString(getTierKey(quizMode), ProgressionTier.LOCKED.name)
        return ProgressionTier.valueOf(tierName ?: ProgressionTier.LOCKED.name)
    }

    fun setProgressionTier(context: Context, quizMode: String, tier: ProgressionTier) {
        getPreferences(context).edit().putString(getTierKey(quizMode), tier.name).apply()
    }

    private fun getEffectiveTier(context: Context, quizModes: Set<String>): ProgressionTier {
        val relevantCategories = if (quizModes.contains("All Excipients")) {
            com.example.excipientquiz.quizModes.keys.filter { it != "All Excipients" && it != "Other" }
        } else {
            quizModes.filter { it != "Other" }
        }

        if (relevantCategories.isEmpty()) return ProgressionTier.FULLY_UNLOCKED

        return relevantCategories
            .map { getProgressionTier(context, it) }
            .minOrNull() ?: ProgressionTier.LOCKED
    }

    private fun getRequiredTier(isMultiOrAll: Boolean, gameMode: GameMode, qType: PropertyType, aType: PropertyType): ProgressionTier {
        val props = setOf(qType, aType)
        val isBasePair = props == setOf(PropertyType.NAME, PropertyType.STRUCTURE)
        
        val tier2Props = setOf(PropertyType.NAME, PropertyType.STRUCTURE, PropertyType.ALTERNATIVE_NAME, PropertyType.MOLECULE_TYPE)
        val isTier2Pair = tier2Props.containsAll(props)

        val survivalRequirement = when {
            isBasePair -> ProgressionTier.LOCKED
            isTier2Pair -> ProgressionTier.ALTERNATIVE_NAMES
            else -> ProgressionTier.FULLY_UNLOCKED
        }
        
        val baseRequirement = if (isMultiOrAll) {
            when (survivalRequirement) {
                ProgressionTier.LOCKED -> ProgressionTier.ALTERNATIVE_NAMES
                ProgressionTier.ALTERNATIVE_NAMES -> ProgressionTier.FULLY_UNLOCKED
                ProgressionTier.FULLY_UNLOCKED -> ProgressionTier.FULLY_UNLOCKED
            }
        } else {
            survivalRequirement
        }

        if (gameMode == GameMode.TIME_ATTACK) {
            return when (baseRequirement) {
                ProgressionTier.LOCKED -> ProgressionTier.ALTERNATIVE_NAMES
                ProgressionTier.ALTERNATIVE_NAMES -> ProgressionTier.FULLY_UNLOCKED
                ProgressionTier.FULLY_UNLOCKED -> ProgressionTier.FULLY_UNLOCKED
            }
        } else { // SURVIVAL
            return baseRequirement
        }
    }

    fun isPlayable(context: Context, quizModes: Set<String>, qType: PropertyType, aType: PropertyType, gameMode: GameMode): Boolean {
        if (qType == aType) return false // Cannot play a game against itself
        
        val effectiveTier = getEffectiveTier(context, quizModes)
        val isMultiOrAll = quizModes.size > 1 || quizModes.contains("All Excipients")
        val requiredTier = getRequiredTier(isMultiOrAll, gameMode, qType, aType)

        return effectiveTier.ordinal >= requiredTier.ordinal
    }
}
