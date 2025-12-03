package com.example.excipientquiz

enum class GameMode {
    EXCIPIENT_SPEEDRUN,
    SURVIVAL
}

enum class PropertyType {
    NAME,
    STRUCTURE,
    FUNCTION,
    ALTERNATIVE_NAME,
    MOLECULE_TYPE
}

// Represents the tiers of progression for a given quiz mode
enum class ProgressionTier {
    LOCKED, // Only Name -> Structure is available in Survival Mode
    ALTERNATIVE_NAMES, // Alt names and Molecule Type are unlocked in Survival, Base pair in Excipient Speedrun
    FULLY_UNLOCKED   // All pairs available in Survival, Tier 2 pairs in Excipient Speedrun
}
