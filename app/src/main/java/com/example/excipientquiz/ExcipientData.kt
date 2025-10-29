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
    val aqsol: String,
    val note: String
)

val excipients = listOf(
    Excipient("Aspartame", R.drawable.aspartame, "Sweetener", "Small organic molecule",
        "none", "Solid dosage forms, Solutions", "Zwitterionic (pKas of ≈ 3 and ≈ 8)", "none"),
    Excipient("Benzyl alcohol", R.drawable.benzylalcohol, "Preservative", "Small organic molecule",
        "none", "Injectables", "Neutral", "Water soluble (1 g/30 mL)", "Concentration: 1-2%. Can be active substance (local anesthetic). Is volatile. Safe for internal use but has a bad taste."),
    Excipient("Benzalkonium Chloride", R.drawable.benzalkoniumchloride, "Preservative", "Small organic molecule",
        "none", "Injectables, eye drops, creams/ointments, lotions/shampoos", "Positive", "Water soluble (1 g/1 mL)", "Concentration: 0,004-0,02%. Precipitates with a whole series of negative ions such as iodides, nitrates, citrates, tartrates, SDS, etc. Has emulsifying properties and can be used in combination with better emulsifiers."),
    Excipient("Benzoic acid", R.drawable.benzoicacid, "Preservative", "Small organic molecule",
        "none", "Solutions, Injectables, eye drops, creams/ointments, lotions/shampoos", "Neutral in acidic environment (pKa 4.2), otherwise negative.", "Water soluble (1 g/300 mL)", "Concentration: 0,1-0,3%. Sodium and potassium salts are also used."),
    Excipient("Calcium stearate", R.drawable.castearate, "Emulsifier (W/O), Lubricant, Glidant", "Small organic molecule",
        "none", "Solid dosage forms", "Neutral in acidic environment, negative in neutral-basic environment (pKa ≈ 4.5)", "none"),
    Excipient("Cellulose acetophtalate", R.drawable.cap, "Enteric coatings", "Polymer",
        "none", "Solid dosage forms", "Neutral in acidic environment, negative in neutral-basic environment (pKa ≈ 5)", "none"),
    Excipient("Chlorhexidine", R.drawable.chlorhexidine, "Preservative", "Small organic molecule", "none",
        "Creams/ointments, Gels, eye drops", "Positively charged (+4) at very low pH (pKa of 2.2), positively charged (+2) between pH 4 to 8 (pKa of 10.3), Neutral at high pH.", "Water soluble (dihydrochloride salt: 0.6 mg/mL, diacetate salt: 19 mg/mL, digluconate salt: > 500 mg/mL)", "Concentration: max 0.3% in EU, often below 0.1%. Chlorhexidin digluconate is only available as a 20% m/V aqueous solution. Chlorhexidine is thermally unstable (degrades above 70 °C). The positive charges are due to the 4 guanidine functional groups."),
    Excipient("Chlorobutanol", R.drawable.chlorobutanol, "Preservative", "Small organic molecule",
        "1,1,1-trichloro-2-methyl-2-propanol", "Injectables, creams/ointments, lotions/shampoos", "Neutral", "Water soluble (8 mg/mL).", "Concentration: 0.5%. Only use for pH lower than 5.5."),
    Excipient("Chlorocresol", R.drawable.chlorocresol, "Preservative", "Small organic molecule",
        "none", "Injectables, creams/ointments, lotions/shampoos", "Neutral except for alkaline environments (negative)", "Water soluble (3.8-4 mg/mL). Also lipophilic.", "Concentration: 0.1%. Use at pH lower than 9."),
    Excipient("Cresol", R.drawable.cresol, "Preservative", "Small organic molecule",
        "none", "Injectables, creams/ointments, lotions/shampoos", "Neutral except for alkaline environments (negative)", "Water soluble (exact concentration depends on isomer but in order of 2 g/100mL and above). Also lipophilic.", "Concentration: 0.3%. Actually consists of the ortho, meta, and para isomers."),
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
        "Nipagin", "Injectibles, Creams/ointments", "Neutral", "Water soluble (1 g/400 mL)", "Concentration: 0.05-0.25%.Do not use in micellar solutions and O/W emulsions (will migrate to internal phase and loose activity.)"),
    Excipient("Microcrystalline Cellulose", R.drawable.cellulose, "Binder, Disintegrant", "Polymer",
        "Avicel", "Solid dosage forms", "Neutral", "none"),
    Excipient("Phenol", R.drawable.phenol, "Preservative", "Small organic molecule",
        "none", "Injectables, creams/ointments, lotions/shampoos", "Neutral except for alkaline environments (negative)", "Water soluble (1 g/15 mL). Also lipophilic.", "Concentration: 0.5%. Sensitive to oxidation. Most stable between a pH of 5 to 7."),
    Excipient("Phenyl mercuric borate", R.drawable.phenylmercuricborate, "Preservative", "Small organic molecule",
        "none", "Eye drops", "Dissociates into positively charged phenyl mercury and negatively charged deprotonated boric acid", "Water soluble (54 mg/mL).", "Concentration: 0.001%. Only use for a pH above 6. Best not to use due to presence of mercury."),
    Excipient("Phenyl mercuric nitrate", R.drawable.phenylmercuricnitrate, "Preservative", "Small organic molecule",
        "none", "Eye drops", "Dissociates into positively charged phenyl mercury and negatively charged nitrate ions.", "Water soluble (<1 mg/mL).", "Concentration: 0.001-0.005%. Adsorbs to polyethylene surfaces. Interactions with halides, anionic molecules, metals, ammonia and ammonia salts, sodium EDTA, sodium thiosulfate. Best not to use due to presence of mercury."),
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
        "Nipasol", "Injectibles, Solutions, Creams/ointments, Suspensions", "Neutral", "Water soluble (1 g in 2500 mL).", "Concentration: 0.02-0.04%.Do not use in micellar solutions and O/W emulsions (will migrate to internal phase and loose activity)?"),
    Excipient("Sodium benzoate", R.drawable.sodiumbenzoate, "Preservative", "Small organic molecule",
        "none", "Solutions", "in acidic environment, negative in neutral-basic environment (pKa ≈ 4)", "Water soluble (1 g/20 mL).", "Concentration: 0,1%-0,3%. The potassium salt is also used."),
    Excipient("Sodium dodecyl sulfate", R.drawable.sodiumdodecylsulfate, "Emulsifier (O/W), Lubricant, Solubilizer, Wetting agent", "Small organic molecule",
        "Sodium lauryl sulfate, SLS, SDS", "Solid dosage forms, Emulsions, Suspensions, Creams/ointments, Shampoos/lotions", "Neutral in acidic environment, negative in neutral-basic environment (pKa ≈ 3.3)", "none"),
    Excipient("Sorbic acid", R.drawable.sorbicacid, "Preservative", "Small organic molecule", "none",
        "Eye drops, Solutions, Gels, Creams/ointments, Shampoos/lotions, Emulsions, Injectables", "Neutral in acidic pH (pKa 4.8), negative otherwise", "Water soluble (1,6 mg/mL).", "Concentration: 0.05-2%. Only works at a pH < 5.5, and the same goes for the potassium salt."),
    Excipient("Sorbitan monooleate", R.drawable.span80, "Emulsifier (W/O), Wetting agent",
        "Small organic molecule", "Span 80", "Emulsions, Creams/ointments, Suspensions", "Neutral", "none"),
    Excipient("Starch", R.drawable.starch, "Filler, Binder, Disintegrant, Glidant", "Polymer",
        "none", "Solid dosage forms", "Neutral", "none"),
    Excipient("Stearic acid", R.drawable.stearicacid, "Lubricant, Glidant, Emulsifier (W/O), Solubilizer", "Small organic molecule",
        "none", "Emulsions, Creams/ointments, Shampoos/lotions, Solid dosage forms", "Neutral in acidic environment, negative in neutral-basic environment (pKa ≈ 5)", "none"),
    Excipient("Talc", R.drawable.talc, "Lubricant, Glidant", "Anorganic molecule",
        "Magnesium silicate", "Solid dosage forms", "Neutral", "none"),
    Excipient("Thiomersal", R.drawable.thiomersal, "Preservative", "Small organic molecule",
        "thimerosal, sodium ethylmercurithiosalicylate", "Eye drops", "Neutral in acidic environment (pKa of 3.5), negative otherwise.", "Water soluble (2.5 g/mL).", "Concentration: 0.002%-0.01%. Only use between pH 7 to 8. Precipitates in acidic environment. Best not to use due to presence of mercury."),
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
