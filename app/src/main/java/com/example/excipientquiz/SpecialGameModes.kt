package com.example.excipientquiz

data class SpecialGameMode(
    val id: String,
    val name: String,
    val description: String
)

val specialGameModes = listOf(
    SpecialGameMode(
        id = "emulsion_types",
        name = "Emulsion emulator?",
        description = "Guess the emulsion type (O/W, W/O, etc.) for each emulsifier."
    ),
    SpecialGameMode(
        id = "cellulose_connoisseur",
        name = "Cellulose Connoisseur",
        description = "Match the name to the structure for all cellulose-based excipients."
    ),
    SpecialGameMode(
        id = "lanette_lingering",
        name = "Lanette Lingering",
        description = "What is the composition of the different Lanette waxes?"
    ),
    SpecialGameMode(
        id = "stunning_stability",
        name = "Stunning Stability",
        description = "Classify preservatives and antioxidants by their solubility."
    )
)
