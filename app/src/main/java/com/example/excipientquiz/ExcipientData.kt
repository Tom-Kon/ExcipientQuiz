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
        "none", "Solid dosage forms, Solutions", "Zwitterionic (pKas of ≈ 3 and ≈ 8)", "none", "none"),
    Excipient("Benzyl alcohol", R.drawable.benzylalcohol, "Preservative", "Small organic molecule",
        "none", "Injectables", "Neutral", "Water soluble (1 g/30 mL)", "Concentration: 1-2%. Can be active substance (local anesthetic). Is volatile. Safe for internal use but has a bad taste."),
    Excipient("Benzalkonium Chloride", R.drawable.benzalkoniumchloride, "Preservative", "Small organic molecule",
        "none", "Injectables, eye drops, creams/ointments, lotions/shampoos", "Positive", "Water soluble (1 g/1 mL)", "Concentration: 0,004-0,02%. Precipitates with a whole series of negative ions such as iodides, nitrates, citrates, tartrates, SDS, etc. Has emulsifying properties and can be used in combination with better emulsifiers."),
    Excipient("Benzoic acid", R.drawable.benzoicacid, "Preservative", "Small organic molecule",
        "none", "Solutions, Injectables, eye drops, creams/ointments, lotions/shampoos", "Neutral in acidic environment (pKa 4.2), otherwise negative.", "Water soluble (1 g/300 mL)", "Concentration: 0,1-0,3%. Sodium and potassium salts are also used."),
    Excipient("Betaines", R.drawable.betaines, "Emulsifier (O/W)", "Small organic molecule", "Tego betaine",
        "Emulsions, Creams/ointments, Shampoos/lotions", "Zwitterionic. Permanent positive charge. Negative charge appears at neutral to alkaline pH (pKa around 5)", "Very water soluble (exact value depends on what betaine is used", "Betaines are a group of surfactants and emulsifiers. They are used in shampoos to improve foam quality."),
    Excipient("Brij excipient class", R.drawable.brij, "O/W emulsifier", "Small organic molecule",
        "Brij + number", "Emulsions, Creams/ointments", "Neutral", "Generally water soluble but value depends on the amount of PEG units.", "Brij's are a family of surfactants that resemble the structure of cetomacrogol. They are generally O/W emulsifiers, but their HLB value heavily depends on the number of PEG units and the length of the carbon change, so properties can differ."),
    Excipient("Butylhydroxyanisol", R.drawable.butylhydroxyanisol, "Antioxidant", "Small organic molecule", "BHA",
        "Emulsions, creams/ointments", "Neutral (could be negative in highly alkaline aqueous solution but not relevant)", "Not water soluble. Soluble in fats and oils.", "Concentration: 0.005-0.1%."),
    Excipient("Butylhydroxytoluene", R.drawable.butylhydroxytoluene, "Antioxidant", "Small organic molecule", "BHT",
        "Emulsions, creams/ointments", "Neutral (could be negative in highly alkaline aqueous solution but not relevant)", "Not water soluble. Soluble in fats and oils.", "Concentration: 0.01%."),
    Excipient("Calcium stearate", R.drawable.castearate, "Emulsifier (W/O), Lubricant, Glidant", "Small organic molecule",
        "none", "Solid dosage forms", "Neutral in acidic environment, negative in neutral-basic environment (pKa ≈ 4.5)", "none", "none"),
    Excipient("Cellulose acetophtalate", R.drawable.cap, "Enteric coatings", "Polymer",
        "none", "Solid dosage forms", "Neutral in acidic environment, negative in neutral-basic environment (pKa ≈ 5)", "none", "none"),
    Excipient("Cetostearyl alcohol", R.drawable.cetostearylalcohol, "Emulsifier (W/O)", "Small organic molecule",
        "Lanette O", "Emulsions, Creams/ointments", "Neutral", "Not soluble in water.", "Can be added to increase consistency. Is a weak emulsifier, so it is best to add another stronger emulsifier as well."),
    Excipient("Cetrimide", R.drawable.cetrimide, "Emulsifier (O/W), Preservative", "Small organic molecule",
        "Mixture of Dodecyltrimethylammonium bromide, Tetradecyltrimethylammonium bromide, Hexadecyltrimethylammonium bromide.", "Creams/ointments", "Positive", "Soluble in water (200 mg/mL)", "Often used in antiseptic creams due to its emulsifying and antiseptic properties."),
    Excipient("Cetyl alcohol", R.drawable.cetylalcohol, "Emulsifier (W/O)", "Small organic molecule", "none",
        "Emulsions, Creams/ointments", "Neutral", "Not soluble in water.", "Quite a weak emulsifier; it is probably best to add other, stronger, emulsifiers."),
    Excipient("Chlorhexidine", R.drawable.chlorhexidine, "Preservative", "Small organic molecule", "none",
        "Creams/ointments, Gels, eye drops", "Positively charged (+4) at very low pH (pKa of 2.2), positively charged (+2) between pH 4 to 8 (pKa of 10.3), Neutral at high pH.", "Water soluble (dihydrochloride salt: 0.6 mg/mL, diacetate salt: 19 mg/mL, digluconate salt: > 500 mg/mL)", "Concentration: max 0.3% in EU, often below 0.1%. Chlorhexidin digluconate is only available as a 20% m/V aqueous solution. Chlorhexidine is thermally unstable (degrades above 70 °C). The positive charges are due to the 4 guanidine functional groups."),
    Excipient("Chlorobutanol", R.drawable.chlorobutanol, "Preservative", "Small organic molecule",
        "1,1,1-trichloro-2-methyl-2-propanol", "Injectables, creams/ointments, lotions/shampoos", "Neutral", "Water soluble (8 mg/mL).", "Concentration: 0.5%. Only use for pH lower than 5.5."),
    Excipient("Chlorocresol", R.drawable.chlorocresol, "Preservative", "Small organic molecule",
        "none", "Injectables, creams/ointments, lotions/shampoos", "Neutral except for alkaline environments (negative)", "Water soluble (3.8-4 mg/mL). Also lipophilic.", "Concentration: 0.1%. Use at pH lower than 9."),
    Excipient("Comperlan", R.drawable.comperlan, "Emulsifier (W/O)", "Small organic molecule", "Cocamide diethanolamine, cocamide monoethanolamine, cocamide triethanolamine",
        "Creams/ointments, shampoos/lotions, Emulsions", "Neutral (Comperlan TEA is positive)", "Depends on type but not very water soluble", "Comperlans are often used as secondary tensides in shampoos. They are also used as foam stabilisers. Different types exist, as indicated by the alternative name."),
    Excipient("Cresol", R.drawable.cresol, "Preservative", "Small organic molecule",
        "none", "Injectables, creams/ointments, lotions/shampoos", "Neutral except for alkaline environments (negative)", "Water soluble (exact concentration depends on isomer but in order of 2 g/100mL and above). Also lipophilic.", "Concentration: 0.3%. Actually consists of the ortho, meta, and para isomers."),
    Excipient("Colloidal silicium dioxide", R.drawable.sio2, "Lubricant, Glidant", "Anorganic molecule",
        "Colloidal silica, diatomaceous earth", "Solid dosage forms", "Neutral", "none", "none"),
    Excipient("Dibutyl sebacate", R.drawable.dbs, "Plasticizer", "Small organic molecule",
        "DBS", "Solid dosage forms", "none", "none", "none"),
    Excipient("Disodium ethylenediaminetetraacetic acid", R.drawable.disodiumedta, "Antioxidant", "Small organic molecule",
        "Disodium EDTA", "Injectables, eye drops, creams/ointments, solutions", "Negative unless highly acidic pH (pH < 2). Three negative charges at neutral pH.", "Water soluble (1080 mg/mL)", "The mechanism of action is not the same as vitamin C or the antioxidants with sulfur. EDTA only complexes metal ions that could catalyse oxidation, so it is best used in conjunction with another antioxidant (e.g. vitamin C.)"),
    Excipient("Ethyl cellulose", R.drawable.ethylcellulose, "Sustained release", "Polymer",
        "none", "Solid dosage forms", "Neutral", "none", "none"),
    Excipient("Eudragit L100", R.drawable.eudragitl100, "Enteric coatings", "Polymer",
        "Copolymer of methacrylic acid and methyl methacrylate", "Solid dosage forms", "Neutral in acidic environment, negative in neutral-basic environment (pKa ≈ 6)", "none", "none"),
    Excipient("Eudragit RL/RS", R.drawable.eudragitrlrs, "Sustained release", "Polymer",
        "Copolymer of methacrylic acid and methacrylic acid esters", "Solid dosage forms", "Positive", "none", "none"),
    Excipient("Gelatin", R.drawable.gelatin, "Binder, Capsule manufacture", "Polymer",
        "Hydrolyzed/denatured collagen", "Solid dosage forms", "Depends on type (can be negative or positive)", "Swells and dissolves in hot water.", "Can be used to make a hydrophilic base for suppositories with glycerin ."),
    Excipient("Glycerol", R.drawable.glycerol, "Plasticizer, Solvent, Thickener, Sweetener", "Small organic molecule",
        "Glycerin(e)", "Solutions, Creams/ointments, Solid dosage forms", "Neutral", "none", "none"),
    Excipient("Glycerol monostearate", R.drawable.glycerolmonostearate, "Emulsifier (W/O)", "Small organic molecule", "Glycerin monostearate, monostearin, GMS",
        "Emulsions, Creams/ointments", "Neutral", "Not soluble in water.", "Two isomers exist for this molecule, depending on how the ester is formed."),
    Excipient("Hydroxypropyl cellulose", R.drawable.hydroxypropylcellulose, "Pore former, Binder, Thickener", "Polymer",
        "Hyprolose/Klucel", "Solid dosage forms, Lotions/shampoos, Suspensions, Gels", "Neutral", "Water soluble (but precipitates above cloud point at 40-45 °C)", "Needs wetting/dispersing agent to avoid clumping when adding water. Best used between pH 6-8. Incompatible with parabens because they adsorb to it, reducing effectiveness. Good for formulating larger quantities of ethanol."),
    Excipient("Hydroxypropyl methylcellulose", R.drawable.hydroxypropylmethylcellulose, "Pore former, Thickener, Emulsifier (O/W)", "Polymer",
        "Hypromellose", "Solid dosage forms, Emulsions, Suspensions", "Neutral", "Water soluble.", "Best used in pH range 3-11 (solubility reasons). Can be sterilised using an autoclave. Needs wetting/dispersing agent."),
    Excipient("Hydroxypropyl methylcellulose acetate succinate", R.drawable.hpmcas, "Enteric coatings, ASD stabilisation", "Polymer",
        "Hypromellose acetate succinate", "Solid dosage forms", "Neutral in acidic environment, negative in neutral-basic environment (pKa ≈ 5)", "none", "none"),
    Excipient("Hydroxypropyl methylcellulose phtalate", R.drawable.hpmcp, "Enteric coatings", "Polymer",
        "Hypromellose phthalate", "Solid dosage forms", "Neutral in acidic environment, negative in neutral-basic environment (pKa ≈ 5)", "none", "none"),
    Excipient("Lactose", R.drawable.lactose, "Filler, Diluent", "Small organic molecule",
        "Milk sugar", "Solid dosage forms", "Neutral", "none", "none"),
    Excipient("Lecithin", R.drawable.lecithin, "Emulsifier (O/W)", "Small organic molecule",
        "none", "Emulsions", "Zwitterionic; depends on pH", "none", "none"),
    Excipient("Magnesium Stearate", R.drawable.magnesiumstearate, "Lubricant", "Small organic molecule",
        "none", "Solid dosage forms", "Neutral in acidic environment, negative in neutral-basic environment (pKa ≈ 5)", "none", "none"),
    Excipient("Mannitol", R.drawable.mannitol, "Filler, Sweetener", "Small organic molecule",
        "none", "Solid dosage forms", "Neutral", "none", "none"),
    Excipient("Menthol", R.drawable.menthol, "Counterirritant and sensory modifier", "Small organic molecule",
        "none", "Creams/ointments", "Neutral", "none", "none"),
    Excipient("Methyl paraben", R.drawable.methylparaben, "Preservative", "Small organic molecule",
        "Nipagin", "Injectables, Creams/ointments", "Neutral", "Water soluble (1 g/400 mL)", "Concentration: 0.05-0.25%.Do not use in micellar solutions and O/W emulsions (will migrate to internal phase and loose activity.)"),
    Excipient("Microcrystalline Cellulose", R.drawable.cellulose, "Binder, Disintegrant, Thickener", "Polymer",
        "Avicel", "Solid dosage forms", "Neutral", "Not soluble in water.", "Avicel RC is a combination of microcrystalline cellulose and NaCMC; the NaCMC fraction forms a colloidal dispersion. Avicel works as a thickener because the tiny crystalline MCC particles disperse in the water and forms a thixotropic gel; it is more of a rheology modifier than a thickener. Avicel RC is a true thickener thanks to the dissolving NaCMC. Avicel needs a dispersing/wetting agent."),
    Excipient("Phenol", R.drawable.phenol, "Preservative", "Small organic molecule",
        "none", "Injectables, creams/ointments, lotions/shampoos", "Neutral except for alkaline environments (negative)", "Water soluble (1 g/15 mL). Also lipophilic.", "Concentration: 0.5%. Sensitive to oxidation. Most stable between a pH of 5 to 7."),
    Excipient("Phenyl mercuric borate", R.drawable.phenylmercuricborate, "Preservative", "Small organic molecule",
        "none", "Eye drops", "Dissociates into positively charged phenyl mercury and negatively charged deprotonated boric acid", "Water soluble (54 mg/mL).", "Concentration: 0.001%. Only use for a pH above 6. Best not to use due to presence of mercury."),
    Excipient("Phenyl mercuric nitrate", R.drawable.phenylmercuricnitrate, "Preservative", "Small organic molecule",
        "none", "Eye drops", "Dissociates into positively charged phenyl mercury and negatively charged nitrate ions.", "Water soluble (<1 mg/mL).", "Concentration: 0.001-0.005%. Adsorbs to polyethylene surfaces. Interactions with halides, anionic molecules, metals, ammonia and ammonia salts, sodium EDTA, sodium thiosulfate. Best not to use due to presence of mercury."),
    Excipient("Polyethylene glycol", R.drawable.peg, "Pore former, Plasticizer, Solvent, Base", "Polymer",
        "Polyethylene oxide, macrogol", "Solutions, Creams/ointments, Suppositories, Solid dosage forms", "Neutral", "none", "none"),
    Excipient("Polyethylene glycol cetyl ether", R.drawable.pegcetylether, "Emulsifier (O/W)", "Polymer",
        "polyethylene glycol hexadecyl ether, cetomacrogol", "Creams/ointments, Emulsions", "Neutral", "Soluble in water (but forms micelles).", "Does not give clear solutions so best replaced with tween 80 in micellar solutions.."),
    Excipient("Propyl gallate", R.drawable.propylgallate, "Antioxidant", "Small organic molecule",
        "none", "Emulsions, Creams/ointments", "Neutral (could be negative in highly alkaline aqueous solutions but is irrelevant).", "Not soluble in water. Soluble in oils and fats.", "Concentration: 0.005-0.15%."),
    Excipient("Polysorbate 80", R.drawable.polysorbate80, "Emulsifier (O/W), Solubilizer",
        "Polymer", "Tween 80, Polyoxyethylene sorbitan monooleate polyethylene glycol sorbitan monooleate", "Emulsions, Creams/ointments, Lotions/shampoos", "Neutral", "Miscible with water.", "Best not used internally due to foaming."),
    Excipient("Polyvinyl alcohol", R.drawable.polyvinylalcohol, "Non-functional tablet coating, Binder", "Polymer",
        "none", "Solid dosage forms", "Neutral", "none", "none"),
    Excipient("Polyvinylpyrrolidone", R.drawable.povidone, "Binder, Film former", "Polymer",
        "povidone, Kollidon", "Solid dosage forms", "Neutral", "none", "none"),
    Excipient("Polyvinylpyrrolidone-co-vinyl acetate", R.drawable.pvpva, "Binder, Film former", "Polymer",
        "none", "Solid dosage forms", "Neutral", "none", "none"),
    Excipient("Propyl paraben", R.drawable.propylparaben, "Preservative", "Small organic molecule",
        "Nipasol", "Injectables, Solutions, Creams/ointments, Suspensions", "Neutral", "Water soluble (1 g in 2500 mL).", "Concentration: 0.02-0.04%.Do not use in micellar solutions and O/W emulsions (will migrate to internal phase and loose activity)?"),
    Excipient("Sodium benzoate", R.drawable.sodiumbenzoate, "Preservative", "Small organic molecule",
        "none", "Solutions", "Neutral in acidic environment, negative in neutral-basic environment (pKa ≈ 4)", "Water soluble (1 g/20 mL).", "Concentration: 0,1%-0,3%. The potassium salt is also used."),
    Excipient("Sodium cetostearyl sulfate", R.drawable.sodiumcetostearylsulfate, "Emulsifier (O/W)", "Small organic molecule", "Lanette E",
        "Emulsions, Creams/ointments", "Neutral in very acidic condiitons (pKa around 2), negative otherwise", "Very low water solubility (16 mg/L)", "Sodium cetostearyl sulfate is a combination of C18 (stearyl) and C16 (ceto) sulfate esters. Despite its negative charge, it is not a very strong O/W emulsifier (due to long carbon chain) and should be used in conjunction with other emulsifiers. This is also why it is not a solubilizer while SDS is."),
    Excipient("Sodium dodecyl sulfate", R.drawable.sodiumdodecylsulfate, "Emulsifier (O/W), Lubricant, Solubilizer, Wetting agent", "Small organic molecule",
        "Sodium lauryl sulfate, SLS, SDS", "Solid dosage forms, Emulsions, Suspensions, Creams/ointments, Lotions/shampoos", "Neutral in acidic environment, negative in neutral-basic environment (pKa ≈ 3.3)", "Water soluble (100 mg/mL).", "Irritating when used in shampoos; ammonium lauryl sulfate or sodium lauryl ether sulfate are better. It is not the best solubiliser because it forms unclear solutions (negative charge repulsion). It is best replaced by tween 80."),
    Excipient("Sodium bisulfite", R.drawable.sodiumbisulfite, "Antioxidant", "Inorganic molecule", "none",
        "Injectables, eye drops, solutions, Emulsions", "Negatively charged at neutral and alkaline pH. Neutral at very acidic pH (pKa of 1.53 and 7.2)", "Water soluble (>200 mg/mL)", "Concentration: 0.1%. It is the same molecule as sodium sufite at neutral pH. Best used at neutral to alkaline pH."),
    Excipient("Sodium lauryl ether sulfate", R.drawable.sodiumlaurylethersulfate, "Emulsifier (O/W), Solubilizer", "Small organic molecule",
        "Texapon, Sodium laureth sulfate (laureth can also be laureth-2 or laureth-3), Sodium dodecyl polyoxyethylene sulfate, Sodium lauryl polyoxyethylene sulfate, Sodium lauryl polyoxyethylene ether sulfate", "Emulsions, creams/ointments, Lotions/shampoos", "Neutral in acidic environment, negative in neutral-basic environment (pKa ≈ 1.5)", "Water soluble (1000 mg/mL).", "Less irritating than cetomacrogol."),
    Excipient("Sodium metabisulfate", R.drawable.sodiummetabisulfite, "Antioxidant", "Inorganic molecule", "none",
        "Injectables, eye drops, solutions, Emulsions", "Negatively charged at neutral and alkaline pH. Neutral at very acidic pH (pKa of 1.53 and 7.2)", "Water soluble (>200 mg/mL)", "Concentration: 0.1%. It transforms into the same molecule as sodium (bi)sufite at neutral pH. Best used at acidic to neutral pH."),
    Excipient("Sodium sulfite", R.drawable.sodiumsulfite, "Antioxidant", "Inorganic molecule", "none",
        "Injectables, eye drops, solutions, Emulsions", "Negatively charged at neutral and alkaline pH. Neutral at very acidic pH (pKa of 1.53 and 7.2)","Water soluble (>200 mg/mL)", "Concentration: 0.1%. It is the same molecule as sodium bisufite at neutral pH. Best used at neutral to alkaline pH."),
    Excipient("Sodium thiosulfite", R.drawable.sodiumthiosulfite, "Antioxidant", "Inorganic molecule", "none",
        "Injectables, eye drops, solutions, Emulsions", "Negatively charged (twice) at neutral and alkaline pH. Neutral at very acidic pH (pKa of 0.6 and 1.7)","Water soluble (>700 mg/mL)", "Concentration: 0.1%. Typically used with iodine and silver salts. Not as strong an antioxidant as the sulfites, so best used in conjunction."),
    Excipient("Sorbic acid", R.drawable.sorbicacid, "Preservative", "Small organic molecule", "none",
        "Eye drops, Solutions, Gels, Creams/ointments, Lotions/shampoos, Emulsions, Injectables", "Neutral in acidic pH (pKa 4.8), negative otherwise", "Water soluble (1,6 mg/mL).", "Concentration: 0.05-2%. Only works at a pH < 5.5, and the same goes for the potassium salt."),
    Excipient("Sorbitan esters", R.drawable.span80, "Emulsifier (W/O), Wetting agent",
        "Small organic molecule", "Span + number, Sorbitanmonooleate, Sorbitansesquioleate, Sorbitantrioleate, Arlacel + number", "Emulsions, Creams/ointments, Suspensions", "Neutral", "none", "The spans are a series of esters consisting of sorbitan and oleic acid. Monooleate = 1 C18 chain, sesquioleate = 1,5 C18 chain (stoichiometrically), trioleate = 3 C18 chains."),
    Excipient("Starch", R.drawable.starch, "Filler, Binder, Disintegrant, Glidant, Thickener", "Polymer",
        "none", "Solid dosage forms", "Neutral", "Does not dissolve in water. Only swells in boiling water, not cold water.", "Not very thermally stable; the chain degrades if heated for too long."),
    Excipient("Stearic acid", R.drawable.stearicacid, "Lubricant, Glidant, Emulsifier (W/O), Solubilizer", "Small organic molecule",
        "none", "Emulsions, Creams/ointments, Lotions/shampoos, Solid dosage forms", "Neutral in acidic environment, negative in neutral-basic environment (pKa ≈ 5)", "Practically water insoluble (0.55 mg/L).", "Stearic acid is rather apolar due to the long carbon chain. This is why it tends to be more of a W/O emulsifier, especially around neutral and acid pH, and even more so if little water is present. It has a high melting temperature (around 70 °C), so the water phase needs to be heated to sufficiently high temperatures (> 80 °C) to form good emulsions. It has a firm consistency, allowing it to be used in O/W emulsions where oil content is less than 30%, which is exceptional."),
    Excipient("Talc", R.drawable.talc, "Lubricant, Glidant", "Anorganic molecule",
        "Magnesium silicate", "Solid dosage forms", "Neutral", "none", "none"),
    Excipient("Thiomersal", R.drawable.thiomersal, "Preservative", "Small organic molecule",
        "thimerosal, sodium ethylmercurithiosalicylate", "Eye drops", "Neutral in acidic environment (pKa of 3.5), negative otherwise.", "Water soluble (2.5 g/mL).", "Concentration: 0.002%-0.01%. Only use between pH 7 to 8. Precipitates in acidic environment. Best not to use due to presence of mercury."),
    Excipient("Triethanol amine stearate", R.drawable.triethanolaminestearate, "O/W emulsifier", "Small organic molecule",
        "Triethanol amine + stearic acid, triethanol amine monostearate", "Emulsions, Creams/ointments", "For stearine: neutral in acidic environment, negative in neutral-basic environment (pKa ≈ 5). Triethanolamine is positive except at alkaline pH (pKa of 7.8)", "Not very soluble in water but forms micelles so still a clear colloidal solution.", "The combination of triethanol amine and stearic acid forms an in situ emulsifier. This is the same as just using the triethanolamine stearate salt."),
    Excipient("Triethyl citrate", R.drawable.tec, "Plasticizer", "Small organic molecule",
        "TEC", "Solid dosage forms", "Neutral", "none", "none"),
    Excipient("Vitamin E", R.drawable.vitamine, "Antioxidant", "Small organic molecule", "Tocopherol, Tocopherol α",
        "Injectables, Emulsions, Creams/ointments", "Neutral (could technically be negative in highly alkaline aqueous solution but not relevant)", "Not water soluble. Soluble in oils.", "Concentration: 0.01-0.1%. Vitamin E actually consists of different tocopherols and tocotrienols, but α tocopherol is the most prominent and active."),
    Excipient("Vitamin C", R.drawable.vitaminc, "Antioxidant", "Small organic molecule", "Ascorbic acid",
        "Injectables, Emulsions, eye drops", "Neutral at acid pH, negative otherwise (pKa of 4).", "Soluble in water (330 mg/mL)", "Concentration: 0.1%. The sodium salt is also used (sodium ascorbate)."),
    Excipient("Waxes", R.drawable.wax, "Base, Emulsifier (W/O)", "Small organic molecule",
        "Cera alba, White wax, Beeswax (white, bleached, yellow)", "Solid dosage forms, Emulsions, Creams/ointments", "Neutral", "Not soluble in water.", "Waxes are a group of esters consisting of very long carbon chains, but also contain long carbon chains without functional groups as well as free long-chained fatty acids. White was is a pseudo W/O emulsifier in the sense that it can mechanically retain water. Hence, it makes up the oil phase of cooling ointments. They increase the consistency of ointments and creams.")
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
