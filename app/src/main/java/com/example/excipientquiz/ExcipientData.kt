package com.example.excipientquiz
import androidx.annotation.DrawableRes
import java.util.LinkedHashMap

data class Excipient(
    val name: String,
    @DrawableRes val imageRes: Int,
    val function: String,
    val moleculetype: String,
    val alternativename: String,
    val usage: String,
    val charge: String,
    val aqsol: String
)

val excipients = listOf(
    Excipient("Aspartame", R.drawable.aspartame, "Sweetener", "Small organic molecule",
        "none", "Solid dosage forms, Solutions", "Zwitterionic (pKas of ≈ 3 and ≈ 8)", "none"),
    Excipient("Benzyl alcohol", R.drawable.benzylalcohol, "Preservative", "Small organic molecule",
        "none", "Injectables", "Neutral", "none"),
    Excipient("Calcium stearate", R.drawable.castearate, "Emulsifier (W/O), Lubricant, Glidant", "Small organic molecule",
        "none", "Solid dosage forms", "Neutral in acidic environment, negative in neutral-basic environment (pKa ≈ 4.5)", "none"),
    Excipient("Cellulose acetophtalate", R.drawable.cap, "Enteric coatings", "Polymer",
        "none", "Solid dosage forms", "Neutral in acidic environment, negative in neutral-basic environment (pKa ≈ 5)", "none"),
    Excipient("Colloidal silicium dioxide", R.drawable.sio2, "Lubricant, Glidant", "Anorganic molecule",
        "Colloidal silica, diatomaceous earth", "Solid dosage forms", "Neutral", "none"),
    Excipient("Dibutyl sebacate", R.drawable.dbs, "Plasticizer", "Small organic molecule",
        "DBS", "Solid dosage forms", "none", "none"),
    Excipient("Ethyl cellulose", R.drawable.ethylcellulose, "Sustained release", "Polymer",
        "none", "Solid dosage forms", "Neutral", "none"),
    Excipient("Eudragit L100", R.drawable.eudragitl100, "Enteric coatings", "Polymer",
        "Copolymer of methacrylic acid and methyl methacrylate", "Solid dosage forms", "Neutral in acidic environment, negative in neutral-basic environment (pKa ≈ 6)", "none"),
    Excipient("Eudragit RL/RS", R.drawable.eudragitrlrs, "Sustained release", "Polymer",
        "Copolymer of methacrylic acid and methacrylic acid esters", "Solid dosage forms", "Positive", "none"),
    Excipient("Gelatin", R.drawable.gelatin, "Binder, Capsule manufacture", "Polymer",
        "Hydrolyzed/denatured collagen", "Solid dosage forms", "Depends on type (can be negative or positive)", "none"),
    Excipient("Glycerol", R.drawable.glycerol, "Plasticizer, Solvent, Thickener, Sweetener", "Small organic molecule",
        "Glycerin(e)", "Solutions, Creams/ointments, Solid dosage forms", "Neutral", "none"),
    Excipient("Hydroxypropyl cellulose", R.drawable.hydroxypropylcellulose, "Pore former, Binder, Thickener", "Polymer",
        "Hyprolose/Klucel", "Solid dosage forms, Lotions and shampoos", "Neutral", "none"),
    Excipient("Hydroxypropyl methylcellulose", R.drawable.hydroxypropylmethylcellulose, "Pore former, Thickener", "Polymer",
        "Hypromellose", "Solid dosage forms, Emulsions, Suspensions", "Neutral", "none"),
    Excipient("Hydroxypropyl methylcellulose acetate succinate", R.drawable.hpmcas, "Enteric coatings, ASD stabilisation", "Polymer",
        "Hypromellose acetate succinate", "Solid dosage forms", "Neutral in acidic environment, negative in neutral-basic environment (pKa ≈ 5)", "none"),
    Excipient("Hydroxypropyl methylcellulose phtalate", R.drawable.hpmcp, "Enteric coatings", "Polymer",
        "Hypromellose phthalate", "Solid dosage forms", "Neutral in acidic environment, negative in neutral-basic environment (pKa ≈ 5)", "none"),
    Excipient("Lactose", R.drawable.lactose, "Filler, Diluent", "Small organic molecule",
        "Milk sugar", "Solid dosage forms", "Neutral", "none"),
    Excipient("Lecithin", R.drawable.lecithin, "Emulsifier (O/W)", "Small organic molecule",
        "none", "Emulsions", "Zwitterionic; depends on pH", "none"),
    Excipient("Magnesium Stearate", R.drawable.magnesiumstearate, "Lubricant", "Small organic molecule",
        "none", "Solid dosage forms", "Neutral in acidic environment, negative in neutral-basic environment (pKa ≈ 5)", "none"),
    Excipient("Mannitol", R.drawable.mannitol, "Filler, Sweetener", "Small organic molecule",
        "none", "Solid dosage forms", "Neutral", "none"),
    Excipient("Menthol", R.drawable.menthol, "Counterirritant and sensory modifier", "Small organic molecule",
        "none", "Creams/ointments", "Neutral", "none"),
    Excipient("Methyl paraben", R.drawable.methylparaben, "Preservative", "Small organic molecule",
        "Nipagin", "Injectibles, Creams/ointments", "Neutral", "none"),
    Excipient("Microcrystalline Cellulose", R.drawable.cellulose, "Binder, Disintegrant", "Polymer",
        "Avicel", "Solid dosage forms", "Neutral", "none"),
    Excipient("Polyethylene glycol", R.drawable.peg, "Pore former, Plasticizer, Solvent, Base", "Polymer",
        "Polyethylene oxide, macrogol", "Solutions, Creams/ointments, Suppositories, Solid dosage forms", "Neutral", "none"),
    Excipient("Polysorbate 80", R.drawable.polysorbate80, "Emulsifier (O/W), Solubilizer",
        "Polymer", "Tween 80, Polyoxyethylene sorbitan monooleate polyethylene glycol sorbitan monooleate", "Emulsions, Creams/ointments, Shampoos/lotions", "Neutral", "none"),
    Excipient("Polyvinyl alcohol", R.drawable.polyvinylalcohol, "Non-functional tablet coating, Binder", "Polymer",
        "none", "Solid dosage forms", "Neutral", "none"),
    Excipient("Polyvinylpyrrolidone", R.drawable.povidone, "Binder, Film former", "Polymer",
        "povidone, Kollidon", "Solid dosage forms", "Neutral", "none"),
    Excipient("Polyvinylpyrollidone-co-vinyl acetate", R.drawable.pvpva, "Binder, Film former", "Polymer",
        "none", "Solid dosage forms", "Neutral", "none"),
    Excipient("Propyl paraben", R.drawable.propylparaben, "Preservative", "Small organic molecule",
        "Nipasol", "Injectibles, Solutions, Creams/ointments, Suspensions", "Neutral", "none"),
    Excipient("Sodium benzoate", R.drawable.sodiumbenzoate, "Preservative", "Small organic molecule",
        "none", "Solutions", "none", "none"),
    Excipient("Sodium dodecyl sulfate", R.drawable.sodiumdodecylsulfate, "Emulsifier (O/W), Lubricant, Solubilizer, Wetting agent", "Small organic molecule",
        "Sodium lauryl sulfate, SLS, SDS", "Solid dosage forms, Emulsions, Suspensions, Creams/ointments, Shampoos/lotions", "Neutral in acidic environment, negative in neutral-basic environment (pKa ≈ 3.3)", "none"),
    Excipient("Sorbitan monooleate", R.drawable.span80, "Emulsifier (W/O), Wetting agent",
        "Small organic molecule", "Span 80", "Emulsions, Creams/ointments, Suspensions", "Neutral", "none"),
    Excipient("Starch", R.drawable.starch, "Filler, Binder, Disintegrant, Glidant", "Polymer",
        "none", "Solid dosage forms", "Neutral", "none"),
    Excipient("Stearic acid", R.drawable.stearicacid, "Lubricant, Glidant, Emulsifier (W/O), Solubilizer", "Small organic molecule",
        "none", "Emulsions, Creams/ointments, Shampoos/lotions, Solid dosage forms", "Neutral in acidic environment, negative in neutral-basic environment (pKa ≈ 5)", "none"),
    Excipient("Talc", R.drawable.talc, "Lubricant, Glidant", "Anorganic molecule",
        "Magnesium silicate", "Solid dosage forms", "Neutral", "none"),
    Excipient("Triethyl citrate", R.drawable.tec, "Plasticizer", "Small organic molecule",
        "TEC", "Solid dosage forms", "Neutral", "none")
)

private fun createGroupedMap(excipients: List<Excipient>, keySelector: (Excipient) -> String, allItemsName: String): Map<String, List<Excipient>> {
    val allKeys = excipients.flatMap { keySelector(it).split(",") }.map { it.trim() }.filter { it.isNotEmpty() }.toSet()
    val keyMap = allKeys.associateWith { key ->
        excipients.filter { excipient -> keySelector(excipient).split(",").map { k -> k.trim() }.contains(key) }
    }

    val finalMap = LinkedHashMap<String, List<Excipient>>()
    finalMap[allItemsName] = excipients

    val others = mutableListOf<Excipient>()
    val sortedModes = mutableMapOf<String, List<Excipient>>()

    keyMap.forEach { (key, excipientList) ->
        if (excipientList.size == 1) {
            others.addAll(excipientList)
        } else {
            sortedModes[key] = excipientList
        }
    }

    finalMap.putAll(sortedModes.toSortedMap(String.CASE_INSENSITIVE_ORDER))
    if (others.isNotEmpty()) {
        finalMap["Other"] = others.distinct().sortedBy { it.name }
    }
    return finalMap
}

val quizModes: Map<String, List<Excipient>> = createGroupedMap(excipients, { it.usage }, "All Excipients")
val encyclopediaFilters: Map<String, List<Excipient>> = createGroupedMap(excipients, { it.function }, "All Functions")
