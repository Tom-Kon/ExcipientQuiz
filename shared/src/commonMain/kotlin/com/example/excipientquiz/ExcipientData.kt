package com.example.excipientquiz
data class Excipient(
    val name: String,
    val imageRes: String,
    val function: String,
    val moleculetype: String,
    val alternativename: String,
    val usage: String,
    val charge: String,
    val aqsol: String,
    val note: String
)

val excipients = listOf(
    Excipient("Adeps solidus", "adepssolidus", "Base for suppositories", "Small organic molecule", "",
        "Suppositories", "Neutral", "Water insoluble", "Should not be heated above 40 °C, otherwise the cooling procedure will take a long time. The meling temperature is 35 °C."),
    Excipient("Agar-agar", "agaragar", "Thickener", "Polymer",
        "Agar", "Gels, Suspensions", "Negative due to acidic groups, including sulfate groups with very low pKa.", "Water soluble (in hot or boiling water)", "Agar consists of two polysaccharides: agarose (D-galactose and 3,6-anhydro-L-galactopyranose), and agaropectose (D-galactose and L-galactose with acidic side-groups such as sulfate, glucuronate, and pyruvate). The agaropectin part (around 30% of the mixture) explains the negative charge. Concentration: 0.2%. It only swells very slowly in cold water, so it needs to be dissolved in boiling water."),
    Excipient("Aluminium chloride", "aluminiumchloride", "Peptizing agent", "Inorganic molecule",
        "", "Suspensions", "Dissolves into negative chloride ions and positive aluminium ions.", "Water soluble", "Peptizing agents help with making a partially flocculated suspension by diminishing the zeta potential of the suspended particles. Deflocculated suspensions are much more stable. Particles with multiple charges are better at achieving this, which explains the common peptizing agents. Concentration: 0.001-0.1M."),
    Excipient("Arabic gum", "arabicgum", "Thickener, Emulsifier (O/W)", "Polymer",
        "", "Gels, Suspensions, Emulsions", "Neutral in very acidic conditions (pKa of around 3), negative otherwise", "Water soluble (up to 37%).", "Concentration: 5-10%. The viscosity maximum occurs around a pH of 7. Also soluble in glycerin (max 1 in 20), and propylene glycol (max 1 in 20). First mix with 1.5x amount of water before mixing with the rest. Incompatible with gelatin (precipitates) and insoluble above an ethanol content above 60%."),
    Excipient("Aspartame", "aspartame", "Sweetener", "Small organic molecule",
        "none", "Solid dosage forms, Solutions", "Zwitterionic (pKas of ≈ 3 and ≈ 8)", "none", "none"),
    Excipient("Bentonite", "bentonite", "Thickener", "Anorganic molecule", "Veegum, magnesium aluminium silicate",
        "Suspensions, Gels", "Negative", "Water insoluble but swells.", "Veegum is the trade name for a much more highly purified version of bentonite. Bentonite forms a complex crystal structure that has the ability to trap cations as well as, more interestingly, water. This results in its swelling properties and its rheology modifying behaviour. It has thixotropic properties. Kaolin is another aluminium silicate, but it is only weakly negative when compared to bentonite. More importantly, it swells much less and thus cannot be used as a thickener, and it also has a lot less surface area. It can be used as a diluent or filler and is sometimes added to pharmaceutical powders, among other minor uses. Kaolin is also susceptible to be contaminated, so it is best to sterilize it with dry heat before use and to always add a preservative."),
    Excipient("Benzyl alcohol", "benzylalcohol", "Preservative", "Small organic molecule",
        "none", "Injectables", "Neutral", "Water soluble (1 g/30 mL)", "Concentration: 1-2%. Can be active substance (local anesthetic). Is volatile. Safe for internal use but has a bad taste."),
    Excipient("Benzalkonium Chloride", "benzalkoniumchloride", "Preservative", "Small organic molecule",
        "none", "Injectables, eye drops, creams/ointments, Lotions & shampoos", "Positive", "Water soluble (1 g/1 mL)", "Concentration: 0,004-0,02%. Precipitates with a whole series of negative ions such as iodides, nitrates, citrates, tartrates, SDS, etc. Has emulsifying properties and can be used in combination with better emulsifiers."),
    Excipient("Benzoic acid", "benzoicacid", "Preservative", "Small organic molecule",
        "none", "Solutions, Injectables, eye drops, creams/ointments, Lotions & shampoos", "Neutral in acidic environment (pKa 4.2), otherwise negative.", "Water soluble (1 g/300 mL)", "Concentration: 0,1-0,3%. Sodium and potassium salts are also used."),
    Excipient("Betaines", "betaines", "Emulsifier (O/W)", "Small organic molecule", "Tego betaine",
        "Emulsions, Creams/ointments, Lotions & shampoos", "Zwitterionic. Permanent positive charge. Negative charge appears at neutral to alkaline pH (pKa around 5)", "Very water soluble (exact value depends on what betaine is used", "Betaines are a group of surfactants and emulsifiers. They are used in shampoos to improve foam quality."),
    Excipient("Boric acid", "boricacid", "Tonicity regulator, Buffer", "Anorganic molecule",
        "none", "Solutions, Eye drops", "Neutral unless very basic environment (pKas of 9.24, 12.4, and 13.3).", "Water soluble", "Borax is closely related to boric acid as it also contains hydrated boron. Neither can be used internally under any circumstance due to toxic effects. Boric acid has an E-value of 0.50, so a 1.8% m/V solution of boric acid is isotonic."),
    Excipient("Brij excipient class", "brij", "O/W emulsifier", "Small organic molecule",
        "Brij + number", "Emulsions, Creams/ointments", "Neutral", "Generally water soluble but value depends on the amount of PEG units.", "Brij's are a family of surfactants that resemble the structure of cetomacrogol. They are generally O/W emulsifiers, but their HLB value heavily depends on the number of PEG units and the length of the carbon change, so properties can differ."),
    Excipient("Butylhydroxyanisol", "butylhydroxyanisol", "Antioxidant", "Small organic molecule", "BHA",
        "Emulsions, creams/ointments", "Neutral (could be negative in highly alkaline aqueous solution but not relevant)", "Not water soluble. Soluble in fats and oils.", "Concentration: 0.005-0.1%."),
    Excipient("Butylhydroxytoluene", "butylhydroxytoluene", "Antioxidant", "Small organic molecule", "BHT",
        "Emulsions, creams/ointments", "Neutral (could be negative in highly alkaline aqueous solution but not relevant)", "Not water soluble. Soluble in fats and oils.", "Concentration: 0.01%."),
    Excipient("Calcium stearate", "castearate", "Emulsifier (W/O), Lubricant, Glidant", "Small organic molecule",
        "none", "Solid dosage forms", "Neutral in acidic environment, negative in neutral-basic environment (pKa ≈ 4.5)", "none", "none"),
    Excipient("Cellulose acetophtalate", "cap", "Enteric coatings", "Polymer",
        "none", "Solid dosage forms", "Neutral in acidic environment, negative in neutral-basic environment (pKa ≈ 5)", "none", "none"),
    Excipient("Cetomacrogol emulsifying wax", "cetomacrogolemulsifyingwax", "Emulsifier (Complex O/W)", "Small organic molecule",
        "4 parts cetostearylalcohol/Lanette O + 1 part cetomacrogol", "Emulsions, Creams/ointments", "Neutral", "none", "This is a complex emulsifier, thus consisting of several surface active molecules. A self emulsifying wax is called a wax because it has similar mechanical properties (greasy, firm), but it chemically completely incomparable to real waxes such as beeswax."),
    Excipient("Cetostearyl alcohol", "cetostearylalcohol", "Emulsifier (W/O)", "Small organic molecule",
        "Lanette O", "Emulsions, Creams/ointments", "Neutral", "Not soluble in water.", "Can be added to increase consistency. Is a weak emulsifier, so it is best to add another stronger emulsifier as well."),
    Excipient("Cetrimide", "cetrimide", "Emulsifier (O/W), Preservative", "Small organic molecule",
        "Mixture of Dodecyltrimethylammonium bromide, Tetradecyltrimethylammonium bromide, Hexadecyltrimethylammonium bromide.", "Creams/ointments", "Positive", "Soluble in water (200 mg/mL)", "Often used in antiseptic creams due to its emulsifying and antiseptic properties."),
    Excipient("Cetrimide emulsifying wax", "cetrimideemulsifyingwax", "Emulsifier (Complex O/W)", "Small organic molecule",
        "9 parts cetostearylalcohol/Lanette O + 1 part cetrimide", "Emulsions, Creams/ointments", "Positive (cetrimide)", "none", "This is a complex emulsifier, thus consisting of several surface active molecules. A self emulsifying wax is called a wax because it has similar mechanical properties (greasy, firm), but it chemically completely incomparable to real waxes such as beeswax."),
    Excipient("Cetyl alcohol", "cetylalcohol", "Emulsifier (W/O)", "Small organic molecule", "none",
        "Emulsions, Creams/ointments", "Neutral", "Not soluble in water.", "Quite a weak emulsifier; it is probably best to add other, stronger, emulsifiers."),
    Excipient("Chlorhexidine", "chlorhexidine", "Preservative", "Small organic molecule", "none",
        "Creams/ointments, Gels, eye drops", "Positively charged (+4) at very low pH (pKa of 2.2), positively charged (+2) between pH 4 to 8 (pKa of 10.3), Neutral at high pH.", "Water soluble (dihydrochloride salt: 0.6 mg/mL, diacetate salt: 19 mg/mL, digluconate salt: > 500 mg/mL)", "Concentration: max 0.3% in EU, often below 0.1%. Chlorhexidin digluconate is only available as a 20% m/V aqueous solution. Chlorhexidine is thermally unstable (degrades above 70 °C). The positive charges are due to the 4 guanidine functional groups."),
    Excipient("Chlorobutanol", "chlorobutanol", "Preservative", "Small organic molecule",
        "1,1,1-trichloro-2-methyl-2-propanol", "Injectables, creams/ointments, Lotions & shampoos", "Neutral", "Water soluble (8 mg/mL).", "Concentration: 0.5%. Only use for pH lower than 5.5."),
    Excipient("Chlorocresol", "chlorocresol", "Preservative", "Small organic molecule",
        "none", "Injectables, creams/ointments, Lotions & shampoos", "Neutral except for alkaline environments (negative)", "Water soluble (3.8-4 mg/mL). Also lipophilic.", "Concentration: 0.1%. Use at pH lower than 9."),
    Excipient("Comperlan", "comperlan", "Emulsifier (W/O)", "Small organic molecule", "Cocamide diethanolamine, cocamide monoethanolamine, cocamide triethanolamine",
        "Creams/ointments, Lotions & shampoos, Emulsions", "Neutral (Comperlan TEA is positive)", "Depends on type but not very water soluble", "Comperlans are often used as secondary tensides in shampoos. They are also used as foam stabilisers. Different types exist, as indicated by the alternative name."),
    Excipient("Cresol", "cresol", "Preservative", "Small organic molecule",
        "none", "Injectables, creams/ointments, Lotions & shampoos", "Neutral except for alkaline environments (negative)", "Water soluble (exact concentration depends on isomer but in order of 2 g/100mL and above). Also lipophilic.", "Concentration: 0.3%. Actually consists of the ortho, meta, and para isomers."),
    Excipient("Colloidal silicium dioxide", "sio2", "Lubricant, Glidant, Thickener", "Anorganic molecule",
        "Colloidal silica, diatomaceous earth, Aerosil", "Solid dosage forms, Suppositories, Suspensions", "Neutral", "Water insoluble (forms colloidal suspension).", "In solid dosage forms, Aerosil is often added to powders to increase their flowability and thus assure homogenous dosing. In suppositories, Aerosil can be used to prevent clumping of dry hygroscopic powders. If too much Aerosil is used in a suppository, its thickening properties might lead to a large viscosity increase, preventing efficient drug release. Thus, use sparingly. Often mixed with an inert powder like lactose or mannitol to ensure homogeneity within the suppository. It must be said that colloidal silica and Aerosil, although they both contain silicon dioxide, are not exactly synonyms. Colloidal silica is a premade colloidal dispersion in water, whereas Aerosil is a dry powder. Colloidal silica is only used as a glidant and is the only one used for that purpose, whereas the solid form is used for all the other applications listed."),
    Excipient("Cremophor EL", "cremophor", "Solubilizer, Emulsifier (O/W)", "Small organic molecule", "Kolliphor EL, polyethoxylated castor oil",
        "Lotions & Shampoos, Creams/ointments, Emulsions, Solutions, Injectables", "Neutral", "Water soluble", "Good solubilizer for use in shampoos since it is not irritating nor negatively charged, which would cause it to interact with positively charged hair."),
    Excipient("Dibutyl sebacate", "dbs", "Plasticizer", "Small organic molecule",
        "DBS", "Solid dosage forms", "none", "none", "none"),
    Excipient("Disodium ethylenediaminetetraacetic acid", "disodiumedta", "Antioxidant", "Small organic molecule",
        "Disodium EDTA", "Injectables, eye drops, creams/ointments, solutions", "Negative unless highly acidic pH (pH < 2). Three negative charges at neutral pH.", "Water soluble (1080 mg/mL)", "The mechanism of action is not the same as vitamin C or the antioxidants with sulfur. EDTA only complexes metal ions that could catalyse oxidation, so it is best used in conjunction with another antioxidant (e.g. vitamin C.)"),
    Excipient("Disodium hydrogen phosphate", "disodiumhydrogenphosphate", "Peptizing agent, Buffers", "Inorganic molecule",
        "", "Suspensions", "Neutral under very acid conditions (pKas of 2.15, 7.21, and 12.32)", "Water soluble", "Peptizing agents help with making a partially flocculated suspension by diminishing the zeta potential of the suspended particles. Deflocculated suspensions are much more stable. Particles with multiple charges are better at achieving this, which explains the common peptizing agents. Concentration: 0.001-0.1M."),
    Excipient("Ethanol", "ethanol", "Solvent, Preservative, Cosolvent", "Small organic molecule", "Alcohol",
        "Solutions, Suspensions, Gels", "Neutral", "Water soluble", "Ethanol has preserving properties starting at 20%. It has disinfectant properties at concentrations of 70% or higher."),
    Excipient("Ethyl cellulose", "ethylcellulose", "Sustained release", "Polymer",
        "none", "Solid dosage forms", "Neutral", "none", "none"),
    Excipient("Eudragit L100", "eudragitl100", "Enteric coatings", "Polymer",
        "Copolymer of methacrylic acid and methyl methacrylate", "Solid dosage forms", "Neutral in acidic environment, negative in neutral-basic environment (pKa ≈ 6)", "none", "none"),
    Excipient("Eudragit RL/RS", "eudragitrlrs", "Sustained release", "Polymer",
        "Copolymer of methacrylic acid and methacrylic acid esters", "Solid dosage forms", "Positive", "none", "none"),
    Excipient("Gelatin", "gelatin", "Binder, Capsule manufacture", "Polymer",
        "Hydrolyzed/denatured collagen", "Solid dosage forms", "Depends on type (can be negative or positive)", "Swells and dissolves in hot water.", "Can be used to make a hydrophilic base for suppositories with glycerin ."),
    Excipient("Glucose", "glucose", "Sweetener, Tonicity regulator", "Small organic molecule",
        "D-glucose", "Solutions, Injectables, Eye drops", "Neutral", "Water soluble", "Syrups (concentrated sugar in water solutions) have preserving properties when the concentration of the sugar is 62% or higher. One must be very careful when combining any reducing sugar (with a free aldehyde or ketone group) with any compound (API or excipient) having a primary or secondary amine, as a Maillard reaction could occur (replace sugar with mannitol in this case). Glucose should not be used in diabetic patients. Glucose degrades when heated. 5% aqueous glucose solutions are isotonic, with an E-value of 0.16."),
    Excipient("Glycerol", "glycerol", "Plasticizer, Solvent, Thickener, Sweetener, Preservative, Dispersing agent, Solvent", "Small organic molecule",
        "Glycerin(e)", "Solutions, Creams/ointments, Solid dosage forms, Suspensions, Gels, Injectables, Eye drops", "Neutral", "Water soluble.", "Has preserving properties in concentrations above 50%. Can be used internally. May never be autoclaved."),
    Excipient("Glycerol monostearate", "glycerolmonostearate", "Emulsifier (W/O)", "Small organic molecule", "Glycerin monostearate, monostearin, GMS",
        "Emulsions, Creams/ointments", "Neutral", "Not soluble in water.", "Two isomers exist for this molecule, depending on how the ester is formed. Arlacel is the name given to several products containing glycerin esterified with stearin. Arlacel 165 is composed of glycerin monostearate and PEG-100 stearate, whereas Arlacel 170 contains glycerin stearate and PEG-100 stearate. Arlacels are emulsifying waxes."),
    Excipient("Hydroxyethyl cellulose", "hydroxyethylcellulose", "Thickener", "Polymer",
        "Natrosol", "Gels, Suspensions", "Neutral", "Water soluble (cold and hot)", "Can be used without dispersing agent. Can be autoclaved. It is hygroscopic. Can be used at a broad range of pH values."),
    Excipient("Hydroxypropyl cellulose", "hydroxypropylcellulose", "Pore former, Binder, Thickener", "Polymer",
        "Hyprolose/Klucel", "Solid dosage forms, Lotions & shampoos, Suspensions, Gels", "Neutral", "Water soluble (but precipitates above cloud point at 40-45 °C)", "Needs wetting/dispersing agent to avoid clumping when adding water. Best used between pH 6-8. Incompatible with parabens because they adsorb to it, reducing effectiveness. Good for formulating larger quantities of ethanol. Protect from light."),
    Excipient("Hydroxypropyl methylcellulose", "hydroxypropylmethylcellulose", "Pore former, Thickener, Emulsifier (O/W)", "Polymer",
        "Hypromellose", "Solid dosage forms, Emulsions, Suspensions", "Neutral", "Water soluble.", "Best used in pH range 3-11 (solubility reasons). Can be sterilised using an autoclave. Needs wetting/dispersing agent."),
    Excipient("Hydroxypropyl methylcellulose acetate succinate", "hpmcas", "Enteric coatings, ASD stabilisation", "Polymer",
        "Hypromellose acetate succinate", "Solid dosage forms", "Neutral in acidic environment, negative in neutral-basic environment (pKa ≈ 5)", "none", "none"),
    Excipient("Hydroxypropyl methylcellulose phtalate", "hpmcp", "Enteric coatings", "Polymer",
        "Hypromellose phthalate", "Solid dosage forms", "Neutral in acidic environment, negative in neutral-basic environment (pKa ≈ 5)", "none", "none"),
    Excipient("Lactose", "lactose", "Filler, Diluent", "Small organic molecule",
        "Milk sugar", "Solid dosage forms", "Neutral", "Water soluble (195 g/L).", "Lactose is a reducing sugar, meaning that it should not be combined with APIs or other excipients having primary or secondary amine functions, as a Maillard reaction could occur. "),
    Excipient("Lanette N", "lanette_n", "Emulsifier (Complex O/W)", "Small organic molecule",
        "9d parts Lanette O (cetostearyl alcohol) and 1 part Lanette E (sodium cetostearyl sulfate)", "Emulsions, Creams/ointments", "Neutral in acidic environment, negative in neutral-basic environment (pKa ≈ 3.3)", "See separate molecules.", "This is a complex emulsifier, meaning it consists of molecules already discussed before."),
    Excipient("Lanette SX", "lanette_sx", "Emulsifier (Complex O/W)","Small organic molecule",
        "9d parts Lanette O (cetostearyl alcohol) + 1 part sodium alkyl sulfate", "Emulsions, Creams/ointments", "Neutral in acidic environment, negative in neutral-basic environment (pKa ≈ 3.3)", "See separate molecules.", "This is a complex emulsifier, meaning it consists of different surfactants already described elsewhere."),
    Excipient("Lanolin", "lanolin", "Base for creams, Emulsifier (W/O)", "Small organic molecule", "Wool fat, Sheep fat, Adeps lanae, Wool grease",
        "Creams/ointments", "Neutral", "Water insoluble", "Lanolin is a group of compounds, namely waxes and sterol esters secreted by the sebaceous glands of wool-bearing animals such as sheep. The image that is shown for Lanolin in this app may not be exact, as it consists of a mixture of many different compounds, including wool fat alcohols, which are not shown in the structure. Furthermore, although it is called a fat, it is actually a wax, since there are no triglyceride esters present in the material. Lanolin has W/O emulsifying properties as mentioned, and can incorporate up to 1/5 of its own weight in water into a formulation."),
    Excipient("Lecithin", "lecithin", "Emulsifier (O/W)", "Small organic molecule",
        "none", "Emulsions", "Zwitterionic; depends on pH", "none", "none"),
    Excipient("Magnesium Stearate", "magnesiumstearate", "Lubricant", "Small organic molecule",
        "none", "Solid dosage forms", "Neutral in acidic environment, negative in neutral-basic environment (pKa ≈ 5)", "none", "none"),
    Excipient("Mannitol", "mannitol", "Filler, Sweetener", "Small organic molecule",
        "none", "Solid dosage forms", "Neutral", "none", "none"),
    Excipient("Menthol", "menthol", "Counterirritant and sensory modifier", "Small organic molecule",
        "none", "Creams/ointments", "Neutral", "none", "none"),
    Excipient("Methyl cellulose", "methylcellulose", "Thickener", "Polymer",
        "", "Gels, Suspensions", "Neutral", "Water insoluble", "In order to prepare a gel, disperse in boiling water (it does not dissolve in it) and cool: this will form a gel. No dispersing agent necessary, so ideal for use in sterile formulations (since dispersing agents impact tonicity of a solution). Viscosity decreases upon heating solutions containing methyl cellulose, and it will coagulate above 70 °C. Optimal pH between 7 to 8, but full possible range is 3 to 11."),
    Excipient("Methyl paraben", "methylparaben", "Preservative", "Small organic molecule",
        "Nipagin", "Injectables, Creams/ointments", "Neutral", "Water soluble (1 g/400 mL)", "Concentration: 0.05-0.25%.Do not use in micellar solutions and O/W emulsions (will migrate to internal phase and loose activity.)"),
    Excipient("Methylene blue", "methyleneblue", "Verify emulsion type", "Small organic molecule",
        "", "Creams/ointments, Emulsions", "Dissociates into a positive and a negative ion.", "Water soluble", "When methylene blue is added to an O/W emulsion, the external phase colors blue. This would not happen with an W/O emulsion, since it is water soluble. It is also used to verify whether ampoules are completely closed."),
    Excipient("Microcrystalline Cellulose", "cellulose", "Binder, Disintegrant, Thickener", "Polymer",
        "Avicel", "Solid dosage forms", "Neutral", "Not soluble in water.", "Avicel RC is a combination of microcrystalline cellulose and NaCMC; the NaCMC fraction forms a colloidal dispersion. Avicel works as a thickener because the tiny crystalline MCC particles disperse in the water and forms a thixotropic gel; it is more of a rheology modifier than a thickener. Avicel RC is a true thickener thanks to the dissolving NaCMC. Avicel needs a dispersing/wetting agent."),
    Excipient("Paraffin", "paraffin", "Base for creams", "Small organic molecule", "Vaseline, Petroleum jelly, paraffin wax, mineral oil, paraffin oil, petroleum wax",
        "Creams/ointments", "Neutral", "Water insoluble", "Paraffin, vaseline, petrolemu jelly, etc. are essentially all names that mean the same thing: a mixture of (mostly linear aliphatic) alkanes with various chain lengths. This means that it is extremely hydrophobic and thus makes up the bulk of the \"oil\" phase in many creams and ointments. Vaseline is the brand name for a slightly more refined version of this product, hence why it is used in many healthcare applications. The length of the chains determine the melting point of all members of this group of substances, and thus explain there different states at room temperature: paraffin can be a waxy solid or a liquid, vaseline is a semisolid. Vaseline is generally used to give creams more consistency, since it is a tougher. Liquid paraffin on the other hand can be added to make formulations less tough (reduce their consistency)."),
    Excipient("Crosslinked poly acrylic acid", "polyacrylicacid", "Thickener", "Polymer",
        "Carbomer, Carbopol + number","Suspensions, Gels", "Neutral in acidic environment, negative in alkaline conditions (pKa acrylic acid 4.25)", "Polyacrylic acid is water soluble, but crosslinked polyacrylic acid (Carbopol) only swells in water - especially at higher pH.", "Protonated carbopol has no viscosity thickening properties. Deprotonated carbopol consists of negatively charged COO- groups in close proximity, resulting in a massive increase (up to x1000) in volume due to electrostatic repulsion. Water can get trapped in the resulting structure, forming a gel. Formulated by first dispersing in acid, then increasing pH (NaOH, triethanolamine). Viscosity might decrease due to light. Not compatible with phenols, cationic polymers, strong acids, and high electrolyte concentrations. It can be used to formulate around 50% ethanol. If the liquid phase is propylene glycol-water, use triethanolamine (TEA). If the liquid phase is water or glycerin, use NaOH or TEA (base/carbopol ratio = 0.4). If the liquid phase is ethanol-water, use triethylamine (base/carbopol ratio = 1)."),
    Excipient("Pectin", "pectin", "Thickener", "Polymer",
        "", "Gels, Suspensions", "Neutral under very acidic conditions (pKa of around 3.5), neutral otherwise", "Water soluble (up to 5% m/m soluble in cold water)", "The viscosity maximum occurs at pH = 6."),
    Excipient("Peanut oil", "peanutoil", "Lipophilic phase in emulsions", "Small organic molecule",
        "Arachis oil, earthnut oil, ground nut oil, oleum arachidis", "Creams/ointments, Emulsions", "Neutral", "Water insoluble", "Combining peanut oil with lime water (a solution (140 mg/100 mL) of calcium hydroxide) results in a mixture with W/O emulsifying properties, but the addition of another fatty acid such as oleic acid is required to further stabilize the emulsion, as peanut oil alone does not have sufficient fatty acids. Precaution is advised with patients allergic to nuts (although highly refined arachis oil might be safe). It is best to substitute with another medicinal oil such as Miglyol."),
    Excipient("Phenol", "phenol", "Preservative", "Small organic molecule",
        "none", "Injectables, creams/ointments, Lotions & shampoos", "Neutral except for alkaline environments (negative)", "Water soluble (1 g/15 mL). Also lipophilic.", "Concentration: 0.5%. Sensitive to oxidation. Most stable between a pH of 5 to 7."),
    Excipient("Phenyl mercuric borate", "phenylmercuricborate", "Preservative", "Small organic molecule",
        "none", "Eye drops", "Dissociates into positively charged phenyl mercury and negatively charged deprotonated boric acid", "Water soluble (54 mg/mL).", "Concentration: 0.001%. Only use for a pH above 6. Best not to use due to presence of mercury."),
    Excipient("Phenyl mercuric nitrate", "phenylmercuricnitrate", "Preservative", "Small organic molecule",
        "none", "Eye drops", "Dissociates into positively charged phenyl mercury and negatively charged nitrate ions.", "Water soluble (<1 mg/mL).", "Concentration: 0.001-0.005%. Adsorbs to polyethylene surfaces. Interactions with halides, anionic molecules, metals, ammonia and ammonia salts, sodium EDTA, sodium thiosulfate. Best not to use due to presence of mercury."),
    Excipient("Propylene glycol", "propyleneglycol", "Solvent, Dispersing agent, Preservative", "Small organic molecule",
        "Propane-1,2-diol", "Solutions, Suspensions, Gels", "Neutral", "Water soluble.", "Is not a preservative per se, but has preserving properties when the mass is 17.5% of the free water fraction in basic/neutral environment and 15% of the free water fraction in acidic environment. Best used externally only. Can only be autoclaved if formulation contains 20% water."),
    Excipient("Polyethylene glycol", "peg", "Pore former, Plasticizer, Solvent, Base", "Polymer",
        "Polyethylene oxide, macrogol", "Solutions, Creams/ointments, Suppositories, Solid dosage forms", "Neutral", "none", "none"),
    Excipient("Polyethylene glycol cetyl ether", "pegcetylether", "Emulsifier (O/W)", "Polymer",
        "polyethylene glycol hexadecyl ether, cetomacrogol", "Creams/ointments, Emulsions", "Neutral", "Soluble in water (but forms micelles).", "Does not give clear solutions so best replaced with tween 80 in micellar solutions.."),
    Excipient("Propyl gallate", "propylgallate", "Antioxidant", "Small organic molecule",
        "none", "Emulsions, Creams/ointments", "Neutral (could be negative in highly alkaline aqueous solutions but is irrelevant).", "Not soluble in water. Soluble in oils and fats.", "Concentration: 0.005-0.15%."),
    Excipient("Polysorbate 80", "polysorbate80", "Emulsifier (O/W), Solubilizer",
        "Polymer", "Tween 80, Polyoxyethylene sorbitan monooleate polyethylene glycol sorbitan monooleate", "Emulsions, Creams/ointments, Lotions & shampoos", "Neutral", "Miscible with water.", "Best not used internally due to foaming."),
    Excipient("Polyvinyl alcohol", "polyvinylalcohol", "Non-functional tablet coating, Binder", "Polymer",
        "none", "Solid dosage forms", "Neutral", "none", "none"),
    Excipient("Polyvinylpyrrolidone", "pvp", "Binder, Film former", "Polymer",
        "povidone, Kollidon", "Solid dosage forms", "Neutral", "none", "none"),
    Excipient("Polyvinylpyrrolidone-co-vinyl acetate", "pvpva", "Binder, Film former", "Polymer",
        "none", "Solid dosage forms", "Neutral", "none", "none"),
    Excipient("Potassium citrate", "potassiumcitrate", "Peptising agent", "Small organic molecule",
        "", "Suspensions", "Neutral under very acidic conditions (pKas of 3, 4.76, and 6.4), negative otherwise.", "Water soluble", "Peptizing agents help with making a partially flocculated suspension by diminishing the zeta potential of the suspended particles. Deflocculated suspensions are much more stable. Particles with multiple charges are better at achieving this, which explains the common peptizing agents. Concentration: 0.001-0.1M."),
    Excipient("Potassium nitrate", "potassiumnitrate", "Tonicity regulator", "Anorganic molecule",
        "", "Solutions, Eye drops", "Negative (nitric acid is a strong acid).", "Water soluble", "Never use internally. 1.63% m/V KNO3 solutions are isotonic, so it has an E-value of 0.55."),
    Excipient("Potassium tartrate", "potassiumtartrate", "Peptising agent", "Small organic molecule",
        "", "Suspensions", "Neutral under very acidic conditions (pKas of 3.0 and 4.3), negative otherwise.", "Water soluble", "Peptizing agents help with making a partially flocculated suspension by diminishing the zeta potential of the suspended particles. Deflocculated suspensions are much more stable. Particles with multiple charges are better at achieving this, which explains the common peptizing agents. Concentration: 0.001-0.1M."),
    Excipient("Propyl paraben", "propylparaben", "Preservative", "Small organic molecule",
        "Nipasol", "Injectables, Solutions, Creams/ointments, Suspensions", "Neutral", "Water soluble (1 g in 2500 mL).", "Concentration: 0.02-0.04%.Do not use in micellar solutions and O/W emulsions (will migrate to internal phase and loose activity)?"),
    Excipient("Sodium alginate", "sodiumalginate", "Thickener, Emulsifier (O/W)", "Polymer",
        "", "Gels, Suspensions, Emulsions", "Neutral under very acidic conditions (pKa of 3 to 4), negative otherwise", "Water soluble (pH dependant; soluble for relevant concentrations.)", "Concentration: 1-2%. The viscosity maximum occurs around pH 6 to 7. It is best to dissolve it in cold water (< 70 °C). The acid form precipitates below pH 4."),
    Excipient("Sodium alkyl sulfate", "sodiumalkylsulfate", "Emulsifier (Complex O/W)", "Small organic molecule",
        "Sodium cetostearyl sulfate + sodium lauryl sulfate", "Emulsions, Creams/ointments", "Neutral in acidic environment, negative in neutral-basic environment (pKa ≈ 4)", "See properties of separate materials.", "This is a complex emulsifier, consisting of several molecules already describes elsewhere."),
    Excipient("Sodium benzoate", "sodiumbenzoate", "Preservative", "Small organic molecule",
        "none", "Solutions", "Neutral in acidic environment, negative in neutral-basic environment (pKa ≈ 4)", "Water soluble (1 g/20 mL).", "Concentration: 0,1%-0,3%. The potassium salt is also used."),
    Excipient("Sodium cetostearyl sulfate", "sodiumcetostearylsulfate", "Emulsifier (O/W)", "Small organic molecule", "Lanette E",
        "Emulsions, Creams/ointments", "Neutral in very acidic condiitons (pKa around 2), negative otherwise", "Very low water solubility (16 mg/L)", "Sodium cetostearyl sulfate is a combination of C18 (stearyl) and C16 (ceto) sulfate esters. Despite its negative charge, it is not a very strong O/W emulsifier (due to long carbon chain) and should be used in conjunction with other emulsifiers. This is also why it is not a solubilizer while SDS is."),
    Excipient("Sodium chloride", "nacl", "Thickener, Tonicity regulator", "Inorganic molecule", "Table salt",
        "Lotions & shampoos, Solutions, Injectables, Eye drops", "Dissociates into positively charged sodium and negatively charged chloride ions.", "Water soluble.", "Sodium chloride is used to make solutions isotonic. In that role, a concentration of 0.9% m/V needs to be used, which results in a melting point depression of 0.52 °C and is equal to 280 milliosmole/L. It is not a true thickener, but it is used as such in shampoos because it affects the shape of micelles, causing them to become more cylinder-shaped, which pushes out water. Using too much NaCl in shampoos reduces the viscosity again due to the salting out of the micelles."),
    Excipient("Sodium dodecyl sulfate", "sodiumdodecylsulfate", "Emulsifier (O/W), Lubricant, Solubilizer, Wetting agent", "Small organic molecule",
        "Sodium lauryl sulfate, SLS, SDS", "Solid dosage forms, Emulsions, Suspensions, Creams/ointments, Lotions & shampoos", "Neutral in acidic environment, negative in neutral-basic environment (pKa ≈ 3.3)", "Water soluble (100 mg/mL).", "Irritating when used in shampoos; ammonium lauryl sulfate or sodium lauryl ether sulfate are better. It is not the best solubiliser because it forms unclear solutions (negative charge repulsion). It is best replaced by tween 80."),
    Excipient("Sodium bisulfite", "sodiumbisulfite", "Antioxidant", "Inorganic molecule", "none",
        "Injectables, eye drops, solutions, Emulsions", "Negatively charged at neutral and alkaline pH. Neutral at very acidic pH (pKa of 1.53 and 7.2)", "Water soluble (>200 mg/mL)", "Concentration: 0.1%. It is the same molecule as sodium sufite at neutral pH. Best used at neutral to alkaline pH."),
    Excipient("(cross-linked) Sodium carboxymethyl cellulose", "sodiumcarboxymethylcellulose", "Thickener, disintegrant", "Polymer",
        "(cross)Carmellose, NaCMC", "Suspensions, Gels, Solid dosage forms", "Neutral at very low pH (pKa of 4.30), negative otherwise", "Water soluble (boiling water only)", "NaCMC needs a dispersing agent when used in gels or suspensions. Viscosity decreases when a NaCMC solution is heated but no coagulation occurs. Optimal pH range is 7 to 8, but it is usable between pH 2 to 9. It is incompatible with cationic compounds, including soluble iron, zinc or aluminium salts (such as in calamine). It is also incompatible with high alcohol concentrations and forms complexes with pectin and gelatin. Side effects include eye irritation and a laxative effect at high concentration. Best to add glycerol to protect against dehydration. Only the cross-linked form of the polymer is used as a superdisintegrant in solid dosage forms. The non-cross-linked variant is the one used in gels and suspensions."),
    Excipient("Sodium citrate", "sodiumcitrate", "Peptising agent", "Small organic molecule",
        "", "Suspensions", "Neutral under very acidic conditions (pKas of 3, 4.76, and 6.4), negative otherwise.", "Water soluble", "Peptizing agents help with making a partially flocculated suspension by diminishing the zeta potential of the suspended particles. Deflocculated suspensions are much more stable. Particles with multiple charges are better at achieving this, which explains the common peptizing agents. Concentration: 0.001-0.1M."),
    Excipient("Sodium lauryl ether sulfate", "sodiumlaurylethersulfate", "Emulsifier (O/W), Solubilizer", "Small organic molecule",
        "Texapon, Sodium laureth sulfate (laureth can also be laureth-2 or laureth-3), Sodium dodecyl polyoxyethylene sulfate, Sodium lauryl polyoxyethylene sulfate, Sodium lauryl polyoxyethylene ether sulfate", "Emulsions, creams/ointments, Lotions & shampoos", "Neutral in acidic environment, negative in neutral-basic environment (pKa ≈ 1.5)", "Water soluble (1000 mg/mL).", "Less irritating than cetomacrogol."),
    Excipient("Sodium pyrophosphate", "sodiumpyrophosphate", "Peptizing agent", "Inorganic molecule",
        "", "Suspensions", "Essentially always negative (pKas of 0.85, 1.96, 6.60, and 9.41).", "Water soluble", "Peptizing agents help with making a partially flocculated suspension by diminishing the zeta potential of the suspended particles. Deflocculated suspensions are much more stable. Particles with multiple charges are better at achieving this, which explains the common peptizing agents. Concentration: 0.001-0.1M."),
    Excipient("Sodium metabisulfate", "sodiummetabisulfite", "Antioxidant", "Inorganic molecule", "none",
        "Injectables, eye drops, solutions, Emulsions", "Negatively charged at neutral and alkaline pH. Neutral at very acidic pH (pKa of 1.53 and 7.2)", "Water soluble (>200 mg/mL)", "Concentration: 0.1%. It transforms into the same molecule as sodium (bi)sufite at neutral pH. Best used at acidic to neutral pH."),
    Excipient("Sodium sulfite", "sodiumsulfite", "Antioxidant", "Inorganic molecule", "none",
        "Injectables, eye drops, solutions, Emulsions", "Negatively charged at neutral and alkaline pH. Neutral at very acidic pH (pKa of 1.53 and 7.2)","Water soluble (>200 mg/mL)", "Concentration: 0.1%. It is the same molecule as sodium bisufite at neutral pH. Best used at neutral to alkaline pH."),
    Excipient("Sodium thiosulfite", "sodiumthiosulfite", "Antioxidant", "Inorganic molecule", "none",
        "Injectables, eye drops, solutions, Emulsions", "Negatively charged (twice) at neutral and alkaline pH. Neutral at very acidic pH (pKa of 0.6 and 1.7)","Water soluble (>700 mg/mL)", "Concentration: 0.1%. Typically used with iodine and silver salts. Not as strong an antioxidant as the sulfites, so best used in conjunction."),
    Excipient("Sorbic acid", "sorbicacid", "Preservative", "Small organic molecule", "none",
        "Eye drops, Solutions, Gels, Creams/ointments, Lotions & shampoos, Emulsions, Injectables", "Neutral in acidic pH (pKa 4.8), negative otherwise", "Water soluble (1,6 mg/mL).", "Concentration: 0.05-2%. Only works at a pH < 5.5, and the same goes for the potassium salt."),
    Excipient("Sorbitan esters", "span80", "Emulsifier (W/O), Wetting agent",
        "Small organic molecule", "Span + number, Sorbitanmonooleate, Sorbitansesquioleate, Sorbitantrioleate, Arlacel + number", "Emulsions, Creams/ointments, Suspensions", "Neutral", "none", "The spans are a series of esters consisting of sorbitan and oleic acid. Monooleate = 1 C18 chain, sesquioleate = 1,5 C18 chain (stoichiometrically), trioleate = 3 C18 chains."),
    Excipient("Starch", "starch", "Filler, Binder, Disintegrant, Glidant, Thickener", "Polymer",
        "none", "Solid dosage forms", "Neutral", "Does not dissolve in water. Only swells in boiling water, not cold water.", "Not very thermally stable; the chain degrades if heated for too long."),
    Excipient("Stearic acid", "stearicacid", "Lubricant, Glidant, Emulsifier (W/O), Solubilizer", "Small organic molecule",
        "none", "Emulsions, Creams/ointments, Lotions & shampoos, Solid dosage forms", "Neutral in acidic environment, negative in neutral-basic environment (pKa ≈ 5)", "Practically water insoluble (0.55 mg/L).", "Stearic acid is rather apolar due to the long carbon chain. This is why it tends to be more of a W/O emulsifier, especially around neutral and acid pH, and even more so if little water is present. It has a high melting temperature (around 70 °C), so the water phase needs to be heated to sufficiently high temperatures (> 80 °C) to form good emulsions. It has a firm consistency, allowing it to be used in O/W emulsions where oil content is less than 30%, which is exceptional."),
    Excipient("Sudan III red", "sudaniii", "Verify emulsion type", "Small organic molecule",
        "", "Creams/ointments, Emulsions", "Neutral (unless very basic medium; then negative due to phenol group)", "Water insoluble", "When Sudan III is added to a W/O emulsion, the external phase colors red. This would not happen with an O/W emulsion, since it is water insoluble."),
    Excipient("Talc", "talc", "Lubricant, Glidant", "Anorganic molecule",
        "Magnesium silicate", "Solid dosage forms", "Neutral", "none", "none"),
    Excipient("Thiomersal", "thiomersal", "Preservative", "Small organic molecule",
        "thimerosal, sodium ethylmercurithiosalicylate", "Eye drops", "Neutral in acidic environment (pKa of 3.5), negative otherwise.", "Water soluble (2.5 g/mL).", "Concentration: 0.002%-0.01%. Only use between pH 7 to 8. Precipitates in acidic environment. Best not to use due to presence of mercury."),
    Excipient("Tragacanth", "tragacanth", "Thickener, Emulsifier (O/W)", "Polymer",
        "", "Gels, Suspensions, Emulsions, Tinctures", "Neutral under very acidic conditions (presence of carboxylic acids), negative otherwise.", "Practically water insoluble (but can be dispersed in cold water)", "Concentration: 0.2-2% for pseudoplastic gels, 2.5-5% for ointment-like gels. The viscosity maximum occurs at around a pH of 5. Needs a dispersing agent when mixing with water. When mixed with water, mix in a ratio of 1:20 (tragacanth:water) and then add the rest under vigorous stirring. It can be used to formulate tinctures that contain resins (benzoe, myrrh, or tolu) by precipitating these resins as a colloidal suspension in water. To do this, grind tragacanth together with glycerin in a mortar. Slowly add water until a slime is obtained and then add the rest of the water. This should result in a clump-free suspension medium. Finally, add the resin containing tincture dropwise into the middle of the mixture while stirring vigorously (the tincture should not come into direct contact with the walls of the mortar or the pestle). Here, the trgacanth acts as a protective colloid. The resins from the tincture precipitate due to a solvent shift but in a controlled manner, forming a colloidal suspension."),
    Excipient("Triethanol amine stearate", "triethanolaminestearate", "O/W emulsifier", "Small organic molecule",
        "Triethanol amine + stearic acid, triethanol amine monostearate", "Emulsions, Creams/ointments", "For stearine: neutral in acidic environment, negative in neutral-basic environment (pKa ≈ 5). Triethanolamine is positive except at alkaline pH (pKa of 7.8)", "Not very soluble in water but forms micelles so still a clear colloidal solution.", "The combination of triethanol amine and stearic acid forms an in situ emulsifier. This is the same as just using the triethanolamine stearate salt."),
    Excipient("Triethyl citrate", "tec", "Plasticizer", "Small organic molecule",
        "TEC", "Solid dosage forms", "Neutral", "none", "none"),
    Excipient("Vitamin E", "vitamine", "Antioxidant", "Small organic molecule", "Tocopherol, Tocopherol α",
        "Injectables, Emulsions, Creams/ointments", "Neutral (could technically be negative in highly alkaline aqueous solution but not relevant)", "Not water soluble. Soluble in oils.", "Concentration: 0.01-0.1%. Vitamin E actually consists of different tocopherols and tocotrienols, but α tocopherol is the most prominent and active."),
    Excipient("Vitamin C", "vitaminc", "Antioxidant", "Small organic molecule", "Ascorbic acid",
        "Injectables, Emulsions, eye drops", "Neutral at acid pH, negative otherwise (pKa of 4).", "Soluble in water (330 mg/mL)", "Concentration: 0.1%. The sodium salt is also used (sodium ascorbate)."),
    Excipient("Waxes", "wax", "Base, Emulsifier (W/O)", "Small organic molecule",
        "Cera alba, White wax, Beeswax (white, bleached, yellow)", "Solid dosage forms, Emulsions, Creams/ointments", "Neutral", "Not soluble in water.", "Waxes are a group of esters consisting of very long carbon chains, but also contain long carbon chains without functional groups as well as free long-chained fatty acids. White was is a pseudo W/O emulsifier in the sense that it can mechanically retain water. Hence, it makes up the oil phase of cooling ointments. They increase the consistency of ointments and creams. Spermaceti, also called sperm oil, is a wax that has been extracted from sperm whales for centuries. It contains mainly cetyl palmitate."),
    Excipient("Xantham gum", "xanthangum", "Thickener", "Polymer",
        "", "Gels, Suspensions", "Neutral under acidic conditions (carboxilic acid groups with pKas between 3 to 4.6), negative otherwise", "Water soluble", "Can be used between pH 3 to 12. Concentration: from 0.2 to 1%. It is generally water soluble, but it is best to dissolve it in boiling water.")
)

private fun createGroupedMap(excipients: List<Excipient>, keySelector: (Excipient) -> String, allItemsName: String): Map<String, List<Excipient>> {
    val allKeys = excipients.flatMap { keySelector(it).split(",") }.map { it.trim() }.filter { it.isNotEmpty() }.toSet()
    val keyMap = allKeys.associateWith { key ->
        excipients.filter { excipient -> keySelector(excipient).split(",").map { k -> k.trim() }.contains(key) }
    }

    val finalMap = mutableMapOf<String, List<Excipient>>()
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

    sortedModes.keys.sorted().forEach { key ->
        finalMap[key] = sortedModes[key]!!
    }

    if (others.isNotEmpty()) {
        finalMap["Other"] = others.distinct().sortedBy { it.name }
    }
    return finalMap
}

private fun createGroupedQuizModes(excipients: List<Excipient>, allItemsName: String): Map<String, List<Excipient>> {
    val finalMap = mutableMapOf<String, List<Excipient>>()

    finalMap[allItemsName] = excipients

    // 1. Prioritize Preservatives & Antioxidants
    val preservativesAndAntioxidants = excipients.filter {
        it.function.contains("Preservative", ignoreCase = true) ||
        it.function.contains("Antioxidant", ignoreCase = true)
    }.distinct().sortedBy { it.name }
    finalMap["Preservatives & antioxidants"] = preservativesAndAntioxidants

    // 2. Exclude these from the main pool for usage-based grouping
    val remainingExcipients = excipients.filter { it !in preservativesAndAntioxidants }

    // 3. Apply usage-based grouping to the *remaining* excipients
    val groupMapping = mapOf(
        "Creams/ointments" to "Creams & Emulsions",
        "Creams/ointments)" to "Creams & Emulsions", // Handles inconsistency in data
        "Emulsions" to "Creams & Emulsions",
        "Eye drops" to "Parenterals & Liquids",
        "Injectables" to "Parenterals & Liquids",
        "Solutions" to "Parenterals & Liquids",
        "Gels" to "Gels & Suspensions",
        "Suspensions" to "Gels & Suspensions",
        "Lotions & shampoos" to "Other"
    )

    val getGroupKeys: (Excipient) -> List<String> = { excipient ->
        excipient.usage.split(',').map { it.trim() }.mapNotNull { usage ->
            groupMapping[usage] ?: if(usage.isNotEmpty() && usage != "Suppositories") usage else null
        }.distinct()
    }

    val allGroupKeys = remainingExcipients.flatMap(getGroupKeys).filter { it != "Other" }.toSet()

    val keyMap = allGroupKeys.associateWith { key ->
        remainingExcipients.filter { excipient -> getGroupKeys(excipient).contains(key) }
    }

    val otherExcipients = remainingExcipients.filter { excipient ->
        val groups = getGroupKeys(excipient)
        groups.isEmpty() || groups.contains("Other") || (groups.size == 1 && keyMap[groups.first()]?.size == 1)
    }.distinct().sortedBy { it.name }


    keyMap.keys.sorted().forEach { key ->
        val groupName = key
        val excipientList = keyMap[key]!!
        if (excipientList.size > 1) {
            finalMap[groupName] = excipientList.distinct().sortedBy { it.name }
        }
    }

        if (otherExcipients.isNotEmpty()) {
        finalMap["Other"] = otherExcipients
    }

    return finalMap
}

val quizModes: Map<String, List<Excipient>> = createGroupedQuizModes(excipients, "All Excipients")
val encyclopediaFilters: Map<String, List<Excipient>> = createGroupedMap(excipients, { it.function }, "All Functions")
