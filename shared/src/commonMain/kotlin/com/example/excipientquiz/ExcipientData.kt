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
    Excipient("Adeps solidus", "adepssolidus", "Base for suppositories", "Small organic molecule", "Hard fat, Suppocire, Witepsol",
        "Suppositories", "Neutral", "Water insoluble", "Should not be heated above 40 °C, otherwise the cooling procedure will take a long time. The meling temperature is 35 °C. The structures shown here only mention C12 and C14 esters, but suppo bases in general can in fact have C7 to C14 groups."),
    Excipient("Agar", "agaragar", "Thickener", "Polymer",
        "Agar-agar", "Gels, Suspensions", "Negative due to acidic groups, including sulfate groups with very low pKa.", "Water soluble (in hot or boiling water)", "Agar consists of two polysaccharides: agarose (D-galactose and 3,6-anhydro-L-galactopyranose), and agaropectose (D-galactose and L-galactose with acidic side-groups such as sulfate, glucuronate, and pyruvate). The agaropectin part (around 30% of the mixture) explains the negative charge. Agaropectin is the nongelling fraction, whereas agarose is the gelling fraction. Concentration: 0.2%. It only swells very slowly in cold water, so it needs to be dissolved in boiling water."),
    Excipient("Aluminium chloride", "aluminiumchloride", "Peptizing agent", "Inorganic molecule",
        "", "Suspensions", "Dissolves into negative chloride ions and positive aluminium ions.", "Water soluble", "Peptizing agents help with making a partially flocculated suspension by diminishing the zeta potential of the suspended particles. Deflocculated suspensions are much more stable. Particles with multiple charges are better at achieving this, which explains the common peptizing agents. Concentration: 0.001-0.1M."),
    Excipient("Arabic gum", "arabicgum", "Thickener, Emulsifier (O/W)", "Polymer",
        "Acacia, acaciae gummi, acacia gum", "Gels, Suspensions, Emulsions", "Neutral in very acidic conditions (pKa of around 3), negative otherwise", "Water soluble (up to 37%).", "Concentration: 5-10%. The viscosity maximum occurs around a pH of 7. Also soluble in glycerin (max 1 in 20), and propylene glycol (max 1 in 20). First mix with 1.5x amount of water before mixing with the rest. Incompatible with gelatin (precipitates) and insoluble above an ethanol content above 60%."),
    Excipient("Aspartame", "aspartame", "Sweetener", "Small organic molecule",
        "none", "Solid dosage forms, Solutions", "Zwitterionic (pKas of ≈ 3 and ≈ 8)", "none", "none"),
    Excipient("Bentonite", "bentonite", "Thickener, Adsorbent", "Anorganic molecule", "Veegum, hydrated aluminium silicate",
        "Suspensions, Gels", "Negative", "Water insoluble but swells.", "Bentonite consists mainly of the natural mineral montmorillonite. Bentonite can also contain calcium, magnesium, and iron. Veegum is the trade name for a much more highly purified version of bentonite. Bentonite forms a complex crystal structure that has the ability to trap cations as well as, more interestingly, water. This results in its swelling properties and its rheology modifying behaviour. It has thixotropic properties. Kaolin is another aluminium silicate, but it is only weakly negative when compared to bentonite. More importantly, it swells much less and thus cannot be used as a thickener, and it also has a lot less surface area. It can be used as a diluent or filler and is sometimes added to pharmaceutical powders, among other minor uses. Kaolin is also susceptible to be contaminated, so it is best to sterilize it with dry heat before use and to always add a preservative."),
    Excipient("Benzyl alcohol", "benzylalcohol", "Preservative", "Small organic molecule",
        "none", "Injectables", "Neutral", "Water soluble (1 g/30 mL)", "Concentration: 1-2% v/v as a preservative, but best concentration depends on application. Cosmetics can contains up to 3% v/v. Solutions of 5% have solubilizing properties, and 10% v/v solutions have disinfectant properties. Should be avoided in parenteral solutions given to neonates. Can be active substance (local anesthetic). Is volatile. Safe for internal use but has a bad taste."),
    Excipient("Benzalkonium Chloride", "benzalkoniumchloride", "Preservative", "Small organic molecule",
        "none", "Injectables, eye drops, creams/ointments, Lotions & shampoos", "Positive", "Water soluble (1 g/1 mL)", "Concentration: 0,004-0,02%, but exact concentration depends on application. Benzalkonium chloride is one of the most widely used preservatives in ophtalmic formulations. Precipitates with a whole series of negative ions such as iodides, nitrates, citrates, tartrates, SDS, etc. Has emulsifying properties and can be used in combination with better emulsifiers."),
    Excipient("Benzoic acid", "benzoicacid", "Preservative", "Small organic molecule",
        "none", "Solutions, Injectables, eye drops, creams/ointments, Lotions & shampoos", "Neutral in acidic environment (pKa 4.2), otherwise negative.", "Water soluble (1 g/300 mL)", "Concentration: 0,1-0,2%, but best concentration depends on application. Sodium and potassium salts are also used. It is most active between pH 2.5-4.5."),
    Excipient("Betaines", "betaines", "Emulsifier (O/W)", "Small organic molecule", "Tego betaine",
        "Emulsions, Creams/ointments, Lotions & shampoos", "Zwitterionic. Permanent positive charge. Negative charge appears at neutral to alkaline pH (pKa around 5)", "Very water soluble (exact value depends on what betaine is used", "Betaines are a group of surfactants and emulsifiers. They are used in shampoos to improve foam quality."),
    Excipient("Boric acid", "boricacid", "Tonicity regulator, Buffer", "Anorganic molecule",
        "Orthoboric acid, Trihydroxyborene", "Eye drops, Creams/ointments", "Neutral unless very basic environment (pKas of 9.24, 12.4, and 13.3).", "Water soluble", "Borax is closely related to boric acid as it also contains hydrated boron. Borax/boric acid systems form a good buffer for more alkaline pH. Neither boric acid nor borax can be used internally under any circumstance due to toxic effects. Boric acid has an E-value of 0.50, so a 1.8% m/V solution of boric acid is isotonic."),
    Excipient("Polyoxyethylene Alkyl Ethers", "brij", "O/W emulsifier (generally)", "Small organic molecule",
        "Cetomacrogol, Brij,  laureth-N, myreth-N, ceteth-N, steareth-N, Cremophor A, macrogoli aether + group in latin", "Emulsions, Creams/ointments", "Neutral", "Generally water soluble but value depends on the amount of PEG units.", "Polyoxyethylene Alkyl Ethers are a family of various structures that are mainly used for their emulsifying properties. They are generally O/W emulsifiers, but their HLB value heavily depends on the number of PEG units and the length of the carbon chain, so properties can differ. Polyoxyethylene Alkyl Ethers have been used in suppositories to enhance release. Some, like Laureth-23 have been used as solubilizer. "),
    Excipient("Butylhydroxyanisol", "butylhydroxyanisol", "Antioxidant", "Small organic molecule", "BHA",
        "Emulsions, creams/ointments", "Neutral (could be negative in highly alkaline aqueous solution but not relevant)", "Not water soluble. Soluble in fats and oils.", "Concentration: 0.005-0.5%, but the ideal concentration depends heavily on the application (can differ by orders of magnitude)."),
    Excipient("Butylhydroxytoluene", "butylhydroxytoluene", "Antioxidant", "Small organic molecule", "BHT",
        "Emulsions, creams/ointments", "Neutral (could be negative in highly alkaline aqueous solution but not relevant)", "Not water soluble. Soluble in fats and oils.", "Concentration: 0.0009-0.5%, but the ideal concentration depends heavily on the application (can differ by orders of magnitude)."),
    Excipient("Calcium stearate", "castearate", "Emulsifier (W/O), Lubricant, Glidant", "Small organic molecule",
        "Calcii stearas", "Solid dosage forms, Creams/ointments", "Neutral in acidic environment, negative in neutral-basic environment (pKa ≈ 4.5)", "Insoluble in water (0.004 g/100 mL)", "Calcium stearate actually also contains calcium palmitate and calcium oxide."),
    Excipient("Cellulose acetophtalate", "cap", "Enteric coatings", "Polymer",
        "CAP", "Solid dosage forms", "Neutral in acidic environment, negative in neutral-basic environment (pKa ≈ 5)", "Practically insoluble in water.", "Contains 21.5–26.0% of acetyl groups, and 30.0–36.0% of phthalyl (o-carboxybenzoy) groups"),
    Excipient("Cetomacrogol emulsifying wax", "cetomacrogolemulsifyingwax", "Emulsifier (Complex O/W)", "Small organic molecule",
        "4 parts cetostearylalcohol/Lanette O + 1 part cetomacrogol", "Emulsions, Creams/ointments", "Neutral", "none", "This is a complex emulsifier, thus consisting of several surface active molecules. A self emulsifying wax is called a wax because it has similar mechanical properties (greasy, firm), but it chemically completely incomparable to real waxes such as beeswax."),
    Excipient("Cetostearyl alcohol", "cetostearylalcohol", "Emulsifier (W/O)", "Small organic molecule",
        "Lanette O", "Emulsions, Creams/ointments, Solid dosage forms, Suppositories", "Neutral", "Water insoluble.", "Can be added to increase consistency in creams and ointments. It is a weak emulsifier, so it is best to add another stronger emulsifier as well."),
    Excipient("Cetrimide", "cetrimide", "Emulsifier (O/W), Preservative, Surfactant", "Small organic molecule",
        "Mixture of Dodecyltrimethylammonium bromide, Tetradecyltrimethylammonium bromide, Hexadecyltrimethylammonium bromide.", "Creams/ointments, Eye drops", "Positive", "Soluble in water (200 mg/mL)", "Often used in antiseptic creams due to its emulsifying and antiseptic properties. It is used as a preservative in eye drops with a concentration of maximum 0.005% m/V."),
    Excipient("Cetrimide emulsifying wax", "cetrimideemulsifyingwax", "Emulsifier (Complex O/W)", "Small organic molecule",
        "9 parts cetostearylalcohol/Lanette O + 1 part cetrimide", "Emulsions, Creams/ointments", "Positive (cetrimide)", "none", "This is a complex emulsifier, thus consisting of several surface active molecules. A self emulsifying wax is called a wax because it has similar mechanical properties (greasy, firm), but it chemically completely incomparable to real waxes such as beeswax."),
    Excipient("Cetyl alcohol", "cetylalcohol", "Emulsifier (W/O)", "Small organic molecule", "none",
        "Emulsions, Creams/ointments, Suppositories, Solid dosage forms", "Neutral", "Water insoluble.", "Quite a weak emulsifier; it is probably best to add other, stronger, emulsifiers. In solid dosage forms it is used to form a permeable barrier to slow drug release. It is also added to suppositories to increase the melting point of the base."),
    Excipient("Chlorhexidine", "chlorhexidine", "Preservative, Desinfectant", "Small organic molecule", "none",
        "Creams/ointments, Gels, eye drops", "Positively charged (+4) at very low pH (pKa of 2.2), positively charged (+2) between pH 4 to 8 (pKa of 10.3), Neutral at high pH.", "Water soluble (dihydrochloride salt: 0.6 mg/mL, diacetate salt: 19 mg/mL, digluconate salt: > 500 mg/mL)", "Concentration: max 0.3% in EU, often below 0.1%. Chlorhexidine digluconate is only available as a 20% m/V aqueous solution. Chlorhexidine is thermally unstable (degrades above 70 °C). The positive charges are due to the 4 guanidine functional groups."),
    Excipient("Chlorobutanol", "chlorobutanol", "Preservative", "Small organic molecule",
        "1,1,1-trichloro-2-methyl-2-propanol", "Injectables, Eye drops, creams/ointments, Lotions & shampoos", "Neutral", "Water soluble (8 mg/mL).", "Chlorobutanol is mainly used as a preservative in parenteral or ophtalmic preparations. Concentration: 0.5%. Only use for pH lower than 5.5."),
    Excipient("Chlorocresol", "chlorocresol", "Preservative", "Small organic molecule",
        "none", "Injectables, creams/ointments, Lotions & shampoos, Eye drops", "Neutral except for very alkaline environments (negative due to phenol group)", "Water soluble (3.8-4 mg/mL), but also lipophilic.", "Concentration: up to 0.2%, but the ideal concentration depends on the application. Use at pH lower than 9, since it loses its antimicrobial activity otherwise."),
    Excipient("Comperlan", "comperlan", "Emulsifier (W/O)", "Small organic molecule", "Cocamide diethanolamine, cocamide monoethanolamine, cocamide triethanolamine",
        "Creams/ointments, Lotions & shampoos, Emulsions", "Neutral (Comperlan TEA is positive)", "Depends on type but not very water soluble", "Comperlans are often used as secondary tensides in shampoos. They are also used as foam stabilisers. Different types exist, as indicated by the alternative name."),
    Excipient("Cresol", "cresol", "Preservative, Desinfectant", "Small organic molecule",
        "Hydroxytoluene", "Injectables, Solutions", "Neutral except for highly alkaline environments (negative due to phenol group)", "Water soluble (exact concentration depends on isomer but in order of 2 g/100mL and above), but also lipophilic.", "Concentration: 0.15-0.3% either in injectables or in topical preparations. Actually consists of the ortho, meta, and para isomers. Only use below pH 9 since it loses its activity otherwise."),
    Excipient("Colloidal silicon dioxide", "sio2", "Lubricant, Glidant, Thickener, Adsorbent", "Anorganic molecule",
        "Colloidal silica, diatomaceous earth, Aerosil", "Solid dosage forms, Suppositories, Suspensions", "Negative due to the deprotonation of surface silicon hydroxide groups.", "Water insoluble (forms colloidal suspension).", "In solid dosage forms, Aerosil is often added to powders to increase their flowability and thus assure homogenous dosing. In suppositories, Aerosil can be used to prevent clumping of dry hygroscopic powders. If too much Aerosil is used in a suppository, its thickening properties might lead to a large viscosity increase, preventing efficient drug release. Thus, use sparingly. Often mixed with an inert powder like lactose or mannitol to ensure homogeneity within the suppository. It is also used to stabilize emulsions and as a thixotropic thickening agent in the case of suspensions (note that at a pH higher than 7.5 the thickening properties are reduced, and they disappear entirely over pH 10). It must be noted that colloidal silica and Aerosil, although they both contain silicon dioxide, are not exactly synonyms. Colloidal silica is a premade colloidal dispersion in water, whereas Aerosil is a dry powder. Colloidal silica is only used as a glidant and is the only one used for that purpose, whereas the solid form is used for all the other applications listed."),
    Excipient("Cremophor EL", "cremophor", "Solubilizer, Emulsifier (O/W)", "Small organic molecule", "Kolliphor EL, polyethoxylated castor oil",
        "Lotions & Shampoos, Creams/ointments, Emulsions, Solutions, Injectables", "Neutral", "Water soluble", "Good solubilizer for use in shampoos since it is not irritating nor negatively charged, which would cause it to interact with positively charged hair."),
    Excipient("Dibutyl sebacate", "dbs", "Plasticizer", "Small organic molecule",
        "DBS", "Solid dosage forms", "none", "none", "none"),
    Excipient("Disodium ethylenediaminetetraacetic acid", "disodiumedta", "Antioxidant", "Small organic molecule",
        "Disodium EDTA, Dinatrii edetas", "Injectables, eye drops, creams/ointments, solutions", "Negative unless highly acidic pH (pH < 2). Three negative charges at neutral pH.", "Water soluble (1080 mg/mL)", "Concentration: 0.005-0.1% m/V. The mechanism of action is not the same as vitamin C or the antioxidants with sulfur. EDTA only complexes metal ions that could catalyse oxidation, so it is best used in conjunction with another antioxidant (e.g. vitamin C.). Since it chelates calcium it is used as an anticoagulant therapeutically and to prevent the coagulation of blood in vitro."),
    Excipient("Disodium hydrogen phosphate", "disodiumhydrogenphosphate", "Peptizing agent, Buffers", "Inorganic molecule",
        "", "Suspensions", "Neutral under very acid conditions (pKas of 2.15, 7.21, and 12.32)", "Water soluble", "Peptizing agents help with making a partially flocculated suspension by diminishing the zeta potential of the suspended particles. Deflocculated suspensions are much more stable. Particles with multiple charges are better at achieving this, which explains the common peptizing agents. Concentration: 0.001-0.1M."),
    Excipient("Ethanol", "ethanol", "Solvent, Preservative, Cosolvent", "Small organic molecule", "Alcohol",
        "Solutions, Suspensions, Gels", "Neutral", "Water soluble", "Ethanol has preserving properties starting at 20%. It has disinfectant properties at concentrations of 70% or higher."),
    Excipient("Ethyl cellulose", "ethylcellulose", "Sustained release", "Polymer",
        "Aquacoat, Surelease", "Solid dosage forms", "Neutral", "Water insoluble.", "Ethyl cellulose coatings can be manufactured either by spraying from an organic solvent such as ethanol, or by using an aqueous polymer dispersion such as for aquacoat."),
    Excipient("Eudragit L100", "eudragitl100", "Enteric coatings", "Polymer",
        "Copolymer of methacrylic acid and methyl methacrylate", "Solid dosage forms", "Neutral in acidic environment, negative in neutral-basic environment (pKa ≈ 6)", "none", "none"),
    Excipient("Eudragit RL/RS", "eudragitrlrs", "Sustained release", "Polymer",
        "Copolymer of methacrylic acid and methacrylic acid esters", "Solid dosage forms", "Positive", "none", "none"),
    Excipient("Gelatin", "gelatin", "Binder, Capsule manufacture, Base for suppositories", "Polymer",
        "Hydrolyzed/denatured collagen", "Solid dosage forms, Suppositories", "Depends on type (can be negative or positive)", "Swells and dissolves in hot water (< 30 °C), which is why gelatin capsules do not really impede drug release.", "Gelatin is mainly used to make hard or soft gelatin capsules. Can be used to make a hydrophilic base for suppositories with glycerin ."),
    Excipient("Glucose", "glucose", "Sweetener, Tonicity regulator", "Small organic molecule",
        "D-glucose", "Solutions, Injectables, Eye drops", "Neutral", "Water soluble", "Syrups (concentrated sugar in water solutions) have preserving properties when the concentration of the sugar is 62% or higher. One must be very careful when combining any reducing sugar (with a free aldehyde or ketone group) with any compound (API or excipient) having a primary or secondary amine, as a Maillard reaction could occur (replace sugar with mannitol in this case). Glucose should not be used in diabetic patients. Glucose degrades when heated. 5% aqueous glucose solutions are isotonic, with an E-value of 0.16."),
    Excipient("Glycerol", "glycerol", "Plasticizer, Solvent, Thickener, Sweetener, Preservative, Dispersing agent, Solvent", "Small organic molecule",
        "Glycerin(e)", "Solutions, Creams/ointments, Solid dosage forms, Suspensions, Gels, Injectables, Eye drops", "Neutral", "Water soluble.", "Has preserving properties in concentrations above 50%. Can be used internally. May never be autoclaved. It is used as a preservative in gelatin capsules."),
    Excipient("Glycerol monostearate", "glycerolmonostearate", "Emulsifier (W/O), Sustained release agent, Lubricant", "Small organic molecule", "Glycerin monostearate, monostearin, GMS",
        "Emulsions, Creams/ointments", "Neutral", "Not soluble in water.", "Two isomers exist for this molecule, depending on how the ester is formed. Arlacel is the name given to several products containing glycerin esterified with stearin. Arlacel 165 is composed of glycerin monostearate and PEG-100 stearate, whereas Arlacel 170 contains glycerin stearate and PEG-100 stearate. Arlacels are emulsifying waxes. In sustained release, it is used as an insoluble matrix to delay drug release. Glyceryl Behenate, Glyceryl Monooleate, and Glyceryl Palmitostearate have comparable properties and are thus also used in comparable applications."),
    Excipient("Hydroxyethyl cellulose", "hydroxyethylcellulose", "Thickener, Coating agent, Binder", "Polymer",
        "Natrosol", "Gels, Suspensions, Solid dosage forms", "Neutral", "Water soluble (cold and hot)", "Can be used without dispersing agent. Can be autoclaved. It is hygroscopic. Can be used at a broad range of pH values."),
    Excipient("Hydroxypropyl cellulose", "hydroxypropylcellulose", "Pore former, Binder, Thickener", "Polymer",
        "Hyprolose/Klucel", "Solid dosage forms, Lotions & shampoos, Suspensions, Gels", "Neutral", "Water soluble (but precipitates above cloud point at 40-45 °C)", "Hydroxypropyl cellulose is mainly used in coated sustained release solid dosage forms to increase the permeability of the coatings by acting as a pore former. High viscosity HPC dissolves slower than low viscosity HPC, which affects drug release kinetics. Needs wetting/dispersing agent to avoid clumping when adding water. Best used between pH 6-8. Incompatible with parabens because they adsorb to it, reducing effectiveness. Good for formulating larger quantities of ethanol. Protect from light."),
    Excipient("Hydroxypropyl methylcellulose", "hydroxypropylmethylcellulose", "Binder, Pore former, Thickener, Emulsifier (O/W)", "Polymer",
        "Hypromellose", "Solid dosage forms, Emulsions, Suspensions", "Neutral", "Water soluble.", "The water solubility of HPMC depends on its viscosity grade (or molecular weight), meaning that it can both be a pore former and a sustained release matrix former depending on the exact composition. Best used in pH range 3-11 (solubility reasons). Can be sterilised using an autoclave. Needs wetting/dispersing agent."),
    Excipient("Hydroxypropyl methylcellulose acetate succinate", "hpmcas", "Enteric coatings, ASD stabilisation", "Polymer",
        "Hypromellose acetate succinate", "Solid dosage forms", "Neutral in acidic environment, negative in neutral-basic environment (pKa ≈ 5)", "Water soluble in neutral and alkaline environment, water insoluble otherwise.", "HPMCAS degrades when exposed to strong acids or bases, oxidizing agents, and sustained levels of elevated humidity."),
    Excipient("Hydroxypropyl methylcellulose phtalate", "hpmcp", "Enteric coatings", "Polymer",
        "Hypromellose phthalate", "Solid dosage forms", "Neutral in acidic environment, negative in neutral-basic environment (pKa ≈ 5)", "Water soluble in neutral and alkaline environment, water insoluble otherwise.", "Sometimes the name of the polymer is appended with HP + number, such as HP 55. The number is the pH at which it dissolved x10, so HP 55 dissolves at pH 5.5. When an S is also present in this added designation, it points to a higher molecular weight which results in films with better resistance to cracking (which might, thus, not need a plasticizer). Example: HP-55S."),
    Excipient("Lactose", "lactose", "Filler, Diluent", "Small organic molecule",
        "Milk sugar", "Solid dosage forms", "Neutral", "Water soluble (195 g/L).", "There are different types of lactose: it can be amorphous or crystalline, but it can also form different hydrates. These different forms can have slightly different properties. Lactose is often used because it can be compressed directly (especially the anhydrous form). Lactose is a reducing sugar, meaning that it should not be combined with APIs or other excipients having primary or secondary amine functions, as a Maillard reaction could occur. "),
    Excipient("Lanette N", "lanette_n", "Emulsifier (Complex O/W)", "Small organic molecule",
        "9d parts Lanette O (cetostearyl alcohol) and 1 part Lanette E (sodium cetostearyl sulfate)", "Emulsions, Creams/ointments", "Neutral in acidic environment, negative in neutral-basic environment (pKa ≈ 3.3)", "See separate molecules.", "This is a complex emulsifier, meaning it consists of molecules already discussed before."),
    Excipient("Lanette SX", "lanette_sx", "Emulsifier (Complex O/W)","Small organic molecule",
        "9d parts Lanette O (cetostearyl alcohol) + 1 part sodium alkyl sulfate", "Emulsions, Creams/ointments", "Neutral in acidic environment, negative in neutral-basic environment (pKa ≈ 3.3)", "See separate molecules.", "This is a complex emulsifier, meaning it consists of different surfactants already described elsewhere."),
    Excipient("Lanolin", "lanolin", "Base for creams, Emulsifier (W/O)", "Small organic molecule", "Wool fat, Sheep fat, Adeps lanae, Wool grease",
        "Creams/ointments", "Neutral", "Water insoluble", "Lanolin is a group of compounds, namely waxes and sterol esters secreted by the sebaceous glands of woolly animals such as sheep. The image that is shown for Lanolin in this app may not be exact, as it consists of a mixture of many different compounds, including wool fat alcohols, which are not shown in the structure. Furthermore, although it is called a fat, it is actually a wax, since there are no triglyceride esters present in the material. Lanolin has W/O emulsifying properties as mentioned, and can incorporate up to twice its own weight of water into a stable formulation. Lanolin alcohols are also commonly used as emulsifiers and cream bases and are a result of the saponification of lanolin."),
    Excipient("Lecithin", "lecithin", "Emulsifier (O/W)", "Small organic molecule",
        "none", "Emulsions", "Zwitterionic; depends on pH", "Lecithins are not water soluble but do hydrate to form emulsions in water.", "Lecithins consists of a complex mixture of phospholipids and other components. In other word, the structure shown here may not be exact. There are many types of lecithin, which depend on how it was extracted."),
    Excipient("Magnesium Stearate", "magnesiumstearate", "Lubricant", "Small organic molecule",
        "none", "Solid dosage forms", "Neutral in acidic environment, negative in neutral-basic environment (pKa ≈ 5)", "Practically insoluble in water.", "Magnesium stearate displays polymorphism and several different hydrates exist. It has been shown that its hydrophobic properties slow down drug release from formulations containing it."),
    Excipient("Mannitol", "mannitol", "Filler, Sweetener, Plasticizer, Tonicity regulator", "Small organic molecule",
        "none", "Solid dosage forms", "Neutral", "Water soluble (up to 18% m/v).", "Mannitol is a useful filler because it is not hygroscopic and can thus be used with moisture-sensitive APIs. Mannitol can also be used for direct compression tablets and for wet granulation. Mannitol has an E-value of 0.18, which means that a 5,07% m/V aqueous mannitol solution is isotonic."),
    Excipient("Menthol", "menthol", "Counterirritant and sensory modifier", "Small organic molecule",
        "none", "Creams/ointments", "Neutral", "none", "none"),
    Excipient("Methyl cellulose", "methylcellulose", "Thickener, Binder, Disintegrant", "Polymer",
        "Methocel", "Gels, Suspensions, Solid dosage forms", "Neutral", "Water insoluble", "Lower and medium molecular weight MC can be used as a binder. High molecular weight MC can be used as a disintegrant. It also has some emulsifying properties and can be used to emulsify mineral oils. It may also be added to tablet formulations to for sustained release effects. In order to prepare a gel, disperse in boiling water (it does not dissolve in it) and cool: this will form a gel. No dispersing agent necessary, so ideal for use in sterile formulations (since dispersing agents impact tonicity of a solution). Viscosity decreases upon heating solutions containing methyl cellulose, and it will coagulate above 70 °C. Optimal pH between 7 to 8, but full possible range is 3 to 11."),
    Excipient("Methyl paraben", "methylparaben", "Preservative", "Small organic molecule",
        "Nipagin", "Creams/ointments", "Neutral", "Water soluble (1 g/400 mL)", "Concentration: 0.015-0.3%, but the ideal concentration depends on the application. Parabens were used previously to preserve ophtalmic and parenteral solutions, but this can lead to adverse reactions and is thus no longer recommended. Do not use in micellar solutions and O/W emulsions (will migrate to internal phase and loose activity.). Methyl paraben is the weakest preservative of the parabens and is thus often combined with other parabens such as propyl paraben, which gives rise to a synergistic effect. Aqua conservans is an aqueous solution containing 0.08% methyl paraben and 0.02% propyl paraben."),
    Excipient("Methylene blue", "methyleneblue", "Verify emulsion type", "Small organic molecule",
        "", "Creams/ointments, Emulsions", "Dissociates into a positive and a negative ion.", "Water soluble", "When methylene blue is added to an O/W emulsion, the external phase colors blue. This would not happen with an W/O emulsion, since it is water soluble. It is also used to verify whether ampoules are completely closed."),
    Excipient("Microcrystalline Cellulose", "cellulose", "Binder, Disintegrant, Thickener, Adsorbent, Filler", "Polymer",
        "Avicel PH", "Solid dosage forms", "Neutral", "Not soluble in water.", "Microcrystalline cellulose is widely used in solid dosage forms, both in wet granulation and in direct compression processes. Avicel RC is a combination of microcrystalline cellulose and NaCMC; the NaCMC fraction forms a colloidal dispersion. Avicel works as a thickener because the tiny crystalline MCC particles disperse in the water and forms a thixotropic gel; it is more of a rheology modifier than a thickener. Avicel RC is a true thickener thanks to the dissolving NaCMC. Avicel needs a dispersing/wetting agent."),
    Excipient("Paraffin", "paraffin", "Base for creams", "Small organic molecule", "Vaseline, Petroleum jelly, paraffin wax, mineral oil, paraffin oil, petroleum wax",
        "Creams/ointments", "Neutral", "Water insoluble", "Paraffin, vaseline, petrolatum, petroleum jelly, etc. are essentially all names that mean the same thing: a mixture of (mostly linear aliphatic) alkanes with various chain lengths. This means that it is extremely hydrophobic and thus makes up the bulk of the \"oil\" phase in many creams and ointments. Vaseline is the brand name for a slightly more refined version of this product, hence why it is used in many healthcare applications. The length of the chains determine the melting point of all members of this group of substances, and thus explain there different states at room temperature: paraffin can be a waxy solid or a liquid, vaseline is a semisolid. Vaseline is generally used to give creams more consistency, since it is a tougher. Liquid paraffin on the other hand can be added to make formulations less tough (reduce their consistency)."),
    Excipient("Crosslinked poly acrylic acid", "polyacrylicacid", "Thickener, Binder", "Polymer",
        "Carbomer, Carbopol + number","Suspensions, Gels, Solid dosage forms", "Neutral in acidic environment, negative in alkaline conditions (the pKa of acrylic acid 4.25)", "Polyacrylic acid is water soluble, but crosslinked polyacrylic acid (Carbopol) only swells in water - especially at higher pH.", "Carbomers can also be used as controlled release agents in solid dosage forms. Protonated carbopol has no viscosity increasing properties. Deprotonated carbopol consists of negatively charged COO- groups in close proximity, resulting in a massive increase (up to x1000) in volume due to electrostatic repulsion. Water can get trapped in the resulting structure, forming a gel. Formulated by first dispersing in acid, then increasing pH (NaOH, triethanolamine). Viscosity might decrease due to light. Not compatible with phenols, cationic polymers, strong acids, and high electrolyte concentrations. It can be used to formulate around 50% ethanol. If the liquid phase is propylene glycol-water, use triethanolamine (TEA). If the liquid phase is water or glycerin, use NaOH or TEA (base/carbopol ratio = 0.4). If the liquid phase is ethanol-water, use triethylamine (base/carbopol ratio = 1)."),
    Excipient("Pectin", "pectin", "Thickener", "Polymer",
        "", "Gels, Suspensions", "Neutral under very acidic conditions (pKa of around 3.5), neutral otherwise", "Water soluble (up to 5% m/m soluble in cold water)", "The viscosity maximum occurs at pH = 6."),
    Excipient("Peanut oil", "peanutoil", "Lipophilic phase in emulsions", "Small organic molecule",
        "Arachis oil, earthnut oil, ground nut oil, oleum arachidis", "Creams/ointments, Emulsions", "Neutral", "Water insoluble", "Combining peanut oil with lime water (a solution (140 mg/100 mL) of calcium hydroxide) results in a mixture with W/O emulsifying properties, but the addition of another fatty acid such as oleic acid is required to further stabilize the emulsion, as peanut oil alone does not have sufficient fatty acids. Precaution is advised with patients allergic to nuts (although highly refined arachis oil might be safe). It is best to substitute with another medicinal oil such as Miglyol."),
    Excipient("Phenol", "phenol", "Preservative", "Small organic molecule",
        "none", "Injectables, creams/ointments, Lotions & shampoos", "Neutral except for alkaline environments (negative)", "Water soluble (1 g/15 mL). Also lipophilic.", "Concentration: 0.5%. Phenol also has disinfecting properties (5% m/V solutions) and can be used as a local anesthetic (0.5-1% m/V).Sensitive to oxidation. Most stable between a pH of 5 to 7."),
    Excipient("Phenyl mercuric borate", "phenylmercuricborate", "Preservative", "Small organic molecule",
        "none", "Eye drops, Injectables", "Dissociates into positively charged phenyl mercury and negatively charged deprotonated boric acid", "Water soluble (54 mg/mL).", "Concentration: 0.002-0.004%. Only use for a pH above 6. Best to avoid due to presence of mercury."),
    Excipient("Phenyl mercuric nitrate", "phenylmercuricnitrate", "Preservative", "Small organic molecule",
        "none", "Eye drops", "Dissociates into positively charged phenyl mercury and negatively charged nitrate ions.", "Water soluble (<1 mg/mL).", "Concentration: 0.001-0.005%. Only use for pH 5-8. Adsorbs to polyethylene surfaces. Interactions with halides, anionic molecules, metals, ammonia and ammonia salts, sodium EDTA, sodium thiosulfate. Best not to use due to presence of mercury."),
    Excipient("Propylene glycol", "propyleneglycol", "Solvent, Dispersing agent, Preservative", "Small organic molecule",
        "Propane-1,2-diol", "Solutions, Suspensions, Gels", "Neutral", "Water soluble.", "Is not a preservative per se, but has preserving properties when the mass is 17.5% of the free water fraction in basic/neutral environment and 15% of the free water fraction in acidic environment. Best used externally only. Can only be autoclaved if formulation contains 20% water."),
    //TODO add poloxamers
    Excipient("Polyethylene glycol", "peg", "Pore former, Plasticizer, Solvent, Ointment base, Suppository base, Lubricant", "Polymer",
        "Polyethylene oxide, Macrogol", "Solutions, Creams/ointments, Suppositories, Solid dosage forms", "Neutral", "PEG is always water soluble, no matter the molecular weight.", "Low molecular weight PEG (such as PEG 300 and PEG 400) have been used as the vehicle in parenteral formulations. "),
    Excipient("Polyethylene glycol cetyl ether", "pegcetylether", "Emulsifier (O/W)", "Polymer",
        "polyethylene glycol hexadecyl ether, cetomacrogol", "Creams/ointments, Emulsions", "Neutral", "Soluble in water (but forms micelles).", "Cetomacrogol is already mentioned under polyoxyethylene alkyl ethers, but it is used so often that it gets its own entry as well. Does not give clear solutions so best replaced with tween 80 in micellar solutions.."),
    Excipient("Propyl gallate", "propylgallate", "Antioxidant", "Small organic molecule",
        "none", "Emulsions, Creams/ointments", "Neutral (could be negative in highly alkaline aqueous solutions but is irrelevant).", "Not soluble in water. Soluble in oils and fats.", "Concentration: mostly 0.1% m/V, but the exact concentration depends on the application. "),
    Excipient("Polysorbate 80", "polysorbate80", "Emulsifier (O/W), Solubilizer",
        "Polymer", "Tween 80, Polyoxyethylene sorbitan monooleate polyethylene glycol sorbitan monooleate", "Emulsions, Creams/ointments, Lotions & shampoos", "Neutral", "Miscible with water.", "Best not used internally due to foaming."),
    Excipient("Polyvinyl alcohol", "polyvinylalcohol", "Non-functional tablet coating, Binder, Thickener", "Polymer",
        "PVA", "Solid dosage forms", "Neutral", "Water soluble.", "PVA can also used as a stabiliser for emulsions."),
    Excipient("Polyvinylpyrrolidone", "pvp", "Binder, Film former", "Polymer",
        "Povidone, Kollidon", "Solid dosage forms", "Neutral", "Freely soluble in water.", "The molecular weight of povidone is generally expressed in terms of viscosity via the K-value. Low molecular weight (K12 grade) is very hygroscopic, which can even lead to deliquescence (which causes a solid to absorb so much moisture from the atmosphere that it dissolves in it and becomes liquid). Other povidone grades are also hygroscopic but less so, and the hygroscopicity decreases with increasing K-value. PVP has been used successfully to increase the solubility of poorly soluble APIs via amorphous solid dispersions. The crosslinked form of povidone, crospovidone, is used in solid dosage forms as a tablet disintegrant."),
    Excipient("Polyvinylpyrrolidone-co-vinyl acetate", "pvpva", "Binder, Film former", "Polymer",
        "Copovidone, Kollidon VA 64, PVP/VA", "Solid dosage forms", "Neutral", "Water soluble (greater than 10%)", "The average molecular weight of copovidone is usually expressed as a K-value, just like for povidone."),
    Excipient("Potassium citrate", "potassiumcitrate", "Peptising agent, Buffer", "Small organic molecule",
        "", "Suspensions", "Neutral under very acidic conditions (pKas of 3, 4.76, and 6.4), negative otherwise.", "Water soluble", "Citrate buffers are good to formulate systems that require a more acidic pH. Peptizing agents help with making a partially flocculated suspension by diminishing the zeta potential of the suspended particles. Deflocculated suspensions are much more stable. Particles with multiple charges are better at achieving this, which explains the common peptizing agents. Concentration: 0.001-0.1M."),
    Excipient("Potassium nitrate", "potassiumnitrate", "Tonicity regulator", "Anorganic molecule",
        "", "Solutions, Eye drops", "Negative (nitric acid is a strong acid).", "Water soluble", "Never use internally. 1.63% m/V KNO3 solutions are isotonic, so it has an E-value of 0.55."),
    Excipient("Potassium tartrate", "potassiumtartrate", "Peptising agent", "Small organic molecule",
        "", "Suspensions", "Neutral under very acidic conditions (pKas of 3.0 and 4.3), negative otherwise.", "Water soluble", "Peptizing agents help with making a partially flocculated suspension by diminishing the zeta potential of the suspended particles. Deflocculated suspensions are much more stable. Particles with multiple charges are better at achieving this, which explains the common peptizing agents. Concentration: 0.001-0.1M."),
    Excipient("Propyl paraben", "propylparaben", "Preservative", "Small organic molecule",
        "Nipasol", "Injectables, Solutions, Creams/ointments, Suspensions", "Neutral", "Water soluble (1 g in 2500 mL).", "Concentration: 0.02-0.04%.Do not use in micellar solutions and O/W emulsions (will migrate to internal phase and loose activity). Aqua conservans is an aqueous solution containing 0.08% methyl paraben and 0.02% propyl paraben."),
    Excipient("Sodium alginate", "sodiumalginate", "Thickener, Emulsifier (O/W), Disintegrant, Binder", "Polymer",
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
        "Injectables, Eye drops, Solutions, Emulsions", "Negatively charged at neutral and alkaline pH. Neutral at very acidic pH (pKa of 1.53 and 7.2)", "Water soluble (>200 mg/mL)", "Concentration: 0.1%. It is the same molecule as sodium sufite at neutral pH. Best used at neutral to alkaline pH."),
    Excipient("(cross-linked) Sodium carboxymethyl cellulose", "sodiumcarboxymethylcellulose", "Thickener, disintegrant", "Polymer",
        "(cross)Carmellose, NaCMC", "Suspensions, Gels, Solid dosage forms", "Neutral at very low pH (pKa of 4.30), negative otherwise", "Water soluble (boiling water only)", "NaCMC needs a dispersing agent when used in gels or suspensions. Viscosity decreases when a NaCMC solution is heated but no coagulation occurs. Optimal pH range is 7 to 8, but it is usable between pH 2 to 9. It is incompatible with cationic compounds, including soluble iron, zinc or aluminium salts (such as in calamine). It is also incompatible with high alcohol concentrations and forms complexes with pectin and gelatin. Side effects include eye irritation and a laxative effect at high concentration. Best to add glycerol to protect against dehydration. Only the cross-linked form of the polymer is used as a superdisintegrant in solid dosage forms. The non-cross-linked variant is the one used in gels and suspensions."),
    Excipient("Sodium citrate", "sodiumcitrate", "Peptising agent, Buffer", "Small organic molecule",
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
        "Eye drops, Solutions, Gels, Creams/ointments, Lotions & shampoos, Emulsions, Injectables", "Neutral in acidic pH (pKa 4.8), negative otherwise", "Water soluble (1,6 mg/mL).", "Concentration: 0.05-0.2%. Only works at a pH < 5.5, and the same goes for the potassium salt. It is often used together with other preservatives since it is mainly an antifungal rather than an antibacterial agent, and adding other preservatives or glycols has a synergistic effect."),
    Excipient("Sorbitan esters", "span80", "Emulsifier (W/O), Wetting agent",
        "Small organic molecule", "Span + number, Sorbitanmonooleate, Sorbitansesquioleate, Sorbitantrioleate, Arlacel + number", "Emulsions, Creams/ointments, Suspensions", "Neutral", "Water insoluble, but generally dispersible.", "The spans are a series of esters consisting of sorbitan and oleic acid. Monooleate = 1 C18 chain, sesquioleate = 1,5 C18 chain (stoichiometrically), trioleate = 3 C18 chains. Soap formation occurs when exposed to strong acids or bases for extended times."),
    Excipient("Starch", "starch", "Filler, Binder, Disintegrant, Thickener", "Polymer",
        "none", "Solid dosage forms", "Neutral", "Does not dissolve in water. Only swells in boiling water, not cold water.", "Not very thermally stable; the chain degrades if heated for too long."),
    Excipient("Stearic acid", "stearicacid", "Lubricant, Glidant, Emulsifier (W/O), Solubilizer", "Small organic molecule",
        "none", "Emulsions, Creams/ointments, Lotions & shampoos, Solid dosage forms", "Neutral in acidic environment, negative in neutral-basic environment (pKa ≈ 5)", "Practically water insoluble (0.55 mg/L).", "Stearic acid is rather apolar due to the long carbon chain. This is why it tends to be more of a W/O emulsifier, especially around neutral and acid pH, and even more so if little water is present. It has a high melting temperature (around 70 °C), so the water phase needs to be heated to sufficiently high temperatures (> 80 °C) to form good emulsions. It has a firm consistency, allowing it to be used in O/W emulsions where oil content is less than 30%, which is exceptional. Similar acids, such as lauric acid, can also be used as emulsifiers and lubricants but are less common."),
    Excipient("Sudan III red", "sudaniii", "Verify emulsion type", "Small organic molecule",
        "", "Creams/ointments, Emulsions", "Neutral (unless very basic medium; then negative due to phenol group)", "Water insoluble", "When Sudan III is added to a W/O emulsion, the external phase colors red. This would not happen with an O/W emulsion, since it is water insoluble."),
    Excipient("Talc", "talc", "Lubricant, Glidant, Filler", "Anorganic molecule",
        "Hydrous magnesium silicate", "Solid dosage forms", "Neutral", "Practically insoluble in water.", "Talc is also used in topical formulations as a dusting powder."),
    Excipient("Thiomersal", "thiomersal", "Preservative", "Small organic molecule",
        "thimerosal, sodium ethylmercurithiosalicylate", "Eye drops", "Neutral in acidic environment (pKa of 3.5), negative otherwise.", "Water soluble (2.5 g/mL).", "Concentration: 0.001%-0.15%, but the exact concentration depends on the application. Only use between pH 7 to 8. Precipitates in acidic environment. Best to avoid due to presence of mercury."),
    Excipient("Tragacanth", "tragacanth", "Thickener, Emulsifier (O/W)", "Polymer",
        "Gum tragacanth", "Gels, Suspensions, Emulsions, Tinctures", "Neutral under very acidic conditions (presence of carboxylic acids), negative otherwise.", "Practically water insoluble (but can be dispersed in cold water)", "Concentration: 0.2-2% for pseudoplastic gels, 2.5-5% for ointment-like gels. The viscosity maximum occurs at around a pH of 5. Needs a dispersing agent when mixing with water. When mixed with water, mix in a ratio of 1:20 (tragacanth:water) and then add the rest under vigorous stirring. It can be used to formulate tinctures that contain resins (benzoe, myrrh, or tolu) by precipitating these resins as a colloidal suspension in water. To do this, grind tragacanth together with glycerin in a mortar. Slowly add water until a slime is obtained and then add the rest of the water. This should result in a clump-free suspension medium. Finally, add the resin containing tincture dropwise into the middle of the mixture while stirring vigorously (the tincture should not come into direct contact with the walls of the mortar or the pestle). Here, the trgacanth acts as a protective colloid. The resins from the tincture precipitate due to a solvent shift but in a controlled manner, forming a colloidal suspension."),
    Excipient("Triethanol amine stearate", "triethanolaminestearate", "O/W emulsifier", "Small organic molecule",
        "Triethanol amine + stearic acid, triethanol amine monostearate", "Emulsions, Creams/ointments", "For stearine: neutral in acidic environment, negative in neutral-basic environment (pKa ≈ 5). Triethanolamine is positive except at alkaline pH (pKa of 7.8)", "Not very soluble in water but forms micelles so still a clear colloidal solution.", "The combination of triethanol amine and stearic acid forms an in situ emulsifier. This is the same as just using the triethanolamine stearate salt."),
    Excipient("Triethyl citrate", "tec", "Plasticizer", "Small organic molecule",
        "TEC", "Solid dosage forms", "Neutral", "Water soluble (up to 6,67% m/V)", "Tributyl citrate, and acetyltributyl citrate are also used."),
    Excipient("Vitamin E", "vitamine", "Antioxidant", "Small organic molecule", "Tocopherol, Tocopherol α",
        "Injectables, Emulsions, Creams/ointments", "Neutral (could technically be negative in highly alkaline aqueous solution but not relevant)", "Not water soluble. Soluble in oils.", "Concentration: 0.01-0.1%. Vitamin E actually consists of different tocopherols and tocotrienols, but α tocopherol is the most prominent and active. A commonly used variation of Vitamin E is Vitamin E Polyethylene Glycol Succinate, also called α-Tocopherol polyethylene glycol succinate (TPGS). It is a water soluble version of Vitamin E that can also act as an emulsifier and solubilizer all while retaining the antioxidant properties of Vitamin E."),
    Excipient("Vitamin C", "vitaminc", "Antioxidant", "Small organic molecule", "Ascorbic acid",
        "Injectables, Emulsions, eye drops", "Neutral at acid pH, negative otherwise (pKa of 4).", "Soluble in water (330 mg/mL)", "Concentration: 0.01-0.1% m/V. The sodium salt is also used (sodium ascorbate)."),
    Excipient("Waxes", "wax", "Base for creams, Emulsifier (W/O), Sustained release agent", "Small organic molecule",
        "Cera alba, White wax, Beeswax (white, bleached, yellow)", "Solid dosage forms, Emulsions, Creams/ointments", "Neutral", "Not soluble in water.", "Waxes are a group of esters consisting of very long carbon chains, but also contain long carbon chains without functional groups as well as free long-chained fatty acids. White was is a pseudo W/O emulsifier in the sense that it can mechanically retain water. Hence, it makes up the oil phase of cooling ointments. They increase the consistency of ointments and creams. Spermaceti, also called sperm oil, is a wax that has been extracted from sperm whales for centuries. It contains mainly cetyl palmitate. Carnauba wax is often used to polish the surface of sugar coated tablets."),
    Excipient("Xantham gum", "xanthangum", "Thickener", "Polymer",
        "", "Gels, Suspensions", "Neutral under acidic conditions (carboxilic acid groups with pKas between 3 to 4.6), negative otherwise", "Water soluble", "Can be used between pH 3 to 12. Concentration: from 0.2 to 1%. Xanthum gum gels display pseudo-plastic behaviour. Xantham gum is generally water soluble, but it is best to dissolve it in boiling water.")
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
