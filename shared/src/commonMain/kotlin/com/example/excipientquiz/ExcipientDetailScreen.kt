package com.example.excipientquiz

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import excipientquiz.shared.generated.resources.Res
import excipientquiz.shared.generated.resources.adepssolidus
import excipientquiz.shared.generated.resources.agaragar
import excipientquiz.shared.generated.resources.aluminiumchloride
import excipientquiz.shared.generated.resources.arabicgum
import excipientquiz.shared.generated.resources.aspartame
import excipientquiz.shared.generated.resources.bentonite
import excipientquiz.shared.generated.resources.benzalkoniumchloride
import excipientquiz.shared.generated.resources.benzoicacid
import excipientquiz.shared.generated.resources.benzylalcohol
import excipientquiz.shared.generated.resources.betaines
import excipientquiz.shared.generated.resources.boricacid
import excipientquiz.shared.generated.resources.brij
import excipientquiz.shared.generated.resources.butylhydroxyanisol
import excipientquiz.shared.generated.resources.butylhydroxytoluene
import excipientquiz.shared.generated.resources.cap
import excipientquiz.shared.generated.resources.castearate
import excipientquiz.shared.generated.resources.cellulose
import excipientquiz.shared.generated.resources.cetomacrogolemulsifyingwax
import excipientquiz.shared.generated.resources.cetostearylalcohol
import excipientquiz.shared.generated.resources.cetrimide
import excipientquiz.shared.generated.resources.cetrimideemulsifyingwax
import excipientquiz.shared.generated.resources.cetylalcohol
import excipientquiz.shared.generated.resources.chlorhexidine
import excipientquiz.shared.generated.resources.chlorobutanol
import excipientquiz.shared.generated.resources.chlorocresol
import excipientquiz.shared.generated.resources.comperlan
import excipientquiz.shared.generated.resources.cremophor
import excipientquiz.shared.generated.resources.cresol
import excipientquiz.shared.generated.resources.dbs
import excipientquiz.shared.generated.resources.disodiumedta
import excipientquiz.shared.generated.resources.disodiumhydrogenphosphate
import excipientquiz.shared.generated.resources.ethanol
import excipientquiz.shared.generated.resources.ethylcellulose
import excipientquiz.shared.generated.resources.eudragitl100
import excipientquiz.shared.generated.resources.eudragitrlrs
import excipientquiz.shared.generated.resources.gelatin
import excipientquiz.shared.generated.resources.glucose
import excipientquiz.shared.generated.resources.glycerol
import excipientquiz.shared.generated.resources.glycerolmonostearate
import excipientquiz.shared.generated.resources.hpmcas
import excipientquiz.shared.generated.resources.hpmcp
import excipientquiz.shared.generated.resources.hydroxyethylcellulose
import excipientquiz.shared.generated.resources.hydroxypropylcellulose
import excipientquiz.shared.generated.resources.hydroxypropylmethylcellulose
import excipientquiz.shared.generated.resources.lactose
import excipientquiz.shared.generated.resources.lanette_n
import excipientquiz.shared.generated.resources.lanette_sx
import excipientquiz.shared.generated.resources.lanolin
import excipientquiz.shared.generated.resources.lecithin
import excipientquiz.shared.generated.resources.magnesiumstearate
import excipientquiz.shared.generated.resources.mannitol
import excipientquiz.shared.generated.resources.menthol
import excipientquiz.shared.generated.resources.methylcellulose
import excipientquiz.shared.generated.resources.methyleneblue
import excipientquiz.shared.generated.resources.methylparaben
import excipientquiz.shared.generated.resources.nacl
import excipientquiz.shared.generated.resources.paraffin
import excipientquiz.shared.generated.resources.peanutoil
import excipientquiz.shared.generated.resources.pectin
import excipientquiz.shared.generated.resources.peg
import excipientquiz.shared.generated.resources.pegcetylether
import excipientquiz.shared.generated.resources.phenol
import excipientquiz.shared.generated.resources.phenylmercuricborate
import excipientquiz.shared.generated.resources.phenylmercuricnitrate
import excipientquiz.shared.generated.resources.polyacrylicacid
import excipientquiz.shared.generated.resources.polysorbate80
import excipientquiz.shared.generated.resources.polyvinylalcohol
import excipientquiz.shared.generated.resources.potassiumcitrate
import excipientquiz.shared.generated.resources.potassiumnitrate
import excipientquiz.shared.generated.resources.potassiumtartrate
import excipientquiz.shared.generated.resources.propyleneglycol
import excipientquiz.shared.generated.resources.propylgallate
import excipientquiz.shared.generated.resources.propylparaben
import excipientquiz.shared.generated.resources.pvp
import excipientquiz.shared.generated.resources.pvpva
import excipientquiz.shared.generated.resources.sio2
import excipientquiz.shared.generated.resources.sodiumalginate
import excipientquiz.shared.generated.resources.sodiumalkylsulfate
import excipientquiz.shared.generated.resources.sodiumbenzoate
import excipientquiz.shared.generated.resources.sodiumbisulfite
import excipientquiz.shared.generated.resources.sodiumcarboxymethylcellulose
import excipientquiz.shared.generated.resources.sodiumcetostearylsulfate
import excipientquiz.shared.generated.resources.sodiumcitrate
import excipientquiz.shared.generated.resources.sodiumdodecylsulfate
import excipientquiz.shared.generated.resources.sodiumlaurylethersulfate
import excipientquiz.shared.generated.resources.sodiummetabisulfite
import excipientquiz.shared.generated.resources.sodiumpyrophosphate
import excipientquiz.shared.generated.resources.sodiumsulfite
import excipientquiz.shared.generated.resources.sodiumthiosulfite
import excipientquiz.shared.generated.resources.sorbicacid
import excipientquiz.shared.generated.resources.span80
import excipientquiz.shared.generated.resources.starch
import excipientquiz.shared.generated.resources.stearicacid
import excipientquiz.shared.generated.resources.sudaniii
import excipientquiz.shared.generated.resources.talc
import excipientquiz.shared.generated.resources.tec
import excipientquiz.shared.generated.resources.thiomersal
import excipientquiz.shared.generated.resources.tragacanth
import excipientquiz.shared.generated.resources.triethanolaminestearate
import excipientquiz.shared.generated.resources.vitaminc
import excipientquiz.shared.generated.resources.vitamine
import excipientquiz.shared.generated.resources.wax
import excipientquiz.shared.generated.resources.xanthangum
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
private fun getDrawableResourceByName(name: String): DrawableResource? {
    return when (name) {
        "adepssolidus" -> Res.drawable.adepssolidus
        "agaragar" -> Res.drawable.agaragar
        "aluminiumchloride" -> Res.drawable.aluminiumchloride
        "arabicgum" -> Res.drawable.arabicgum
        "aspartame" -> Res.drawable.aspartame
        "bentonite" -> Res.drawable.bentonite
        "benzylalcohol" -> Res.drawable.benzylalcohol
        "benzalkoniumchloride" -> Res.drawable.benzalkoniumchloride
        "benzoicacid" -> Res.drawable.benzoicacid
        "betaines" -> Res.drawable.betaines
        "boricacid" -> Res.drawable.boricacid
        "brij" -> Res.drawable.brij
        "butylhydroxyanisol" -> Res.drawable.butylhydroxyanisol
        "butylhydroxytoluene" -> Res.drawable.butylhydroxytoluene
        "castearate" -> Res.drawable.castearate
        "cap" -> Res.drawable.cap
        "cetomacrogolemulsifyingwax" -> Res.drawable.cetomacrogolemulsifyingwax
        "cetostearylalcohol" -> Res.drawable.cetostearylalcohol
        "cetrimide" -> Res.drawable.cetrimide
        "cetrimideemulsifyingwax" -> Res.drawable.cetrimideemulsifyingwax
        "cetylalcohol" -> Res.drawable.cetylalcohol
        "chlorhexidine" -> Res.drawable.chlorhexidine
        "chlorobutanol" -> Res.drawable.chlorobutanol
        "chlorocresol" -> Res.drawable.chlorocresol
        "comperlan" -> Res.drawable.comperlan
        "cresol" -> Res.drawable.cresol
        "sio2" -> Res.drawable.sio2
        "cremophor" -> Res.drawable.cremophor
        "dbs" -> Res.drawable.dbs
        "disodiumedta" -> Res.drawable.disodiumedta
        "disodiumhydrogenphosphate" -> Res.drawable.disodiumhydrogenphosphate
        "ethanol" -> Res.drawable.ethanol
        "ethylcellulose" -> Res.drawable.ethylcellulose
        "eudragitl100" -> Res.drawable.eudragitl100
        "eudragitrlrs" -> Res.drawable.eudragitrlrs
        "gelatin" -> Res.drawable.gelatin
        "glucose" -> Res.drawable.glucose
        "glycerol" -> Res.drawable.glycerol
        "glycerolmonostearate" -> Res.drawable.glycerolmonostearate
        "hydroxyethylcellulose" -> Res.drawable.hydroxyethylcellulose
        "hydroxypropylcellulose" -> Res.drawable.hydroxypropylcellulose
        "hydroxypropylmethylcellulose" -> Res.drawable.hydroxypropylmethylcellulose
        "hpmcas" -> Res.drawable.hpmcas
        "hpmcp" -> Res.drawable.hpmcp
        "lactose" -> Res.drawable.lactose
        "lanette_n" -> Res.drawable.lanette_n
        "lanette_sx" -> Res.drawable.lanette_sx
        "lanolin" -> Res.drawable.lanolin
        "lecithin" -> Res.drawable.lecithin
        "magnesiumstearate" -> Res.drawable.magnesiumstearate
        "mannitol" -> Res.drawable.mannitol
        "menthol" -> Res.drawable.menthol
        "methylcellulose" -> Res.drawable.methylcellulose
        "methylparaben" -> Res.drawable.methylparaben
        "methyleneblue" -> Res.drawable.methyleneblue
        "cellulose" -> Res.drawable.cellulose
        "paraffin" -> Res.drawable.paraffin
        "polyacrylicacid" -> Res.drawable.polyacrylicacid
        "pectin" -> Res.drawable.pectin
        "peanutoil" -> Res.drawable.peanutoil
        "phenol" -> Res.drawable.phenol
        "phenylmercuricborate" -> Res.drawable.phenylmercuricborate
        "phenylmercuricnitrate" -> Res.drawable.phenylmercuricnitrate
        "propyleneglycol" -> Res.drawable.propyleneglycol
        "peg" -> Res.drawable.peg
        "pegcetylether" -> Res.drawable.pegcetylether
        "propylgallate" -> Res.drawable.propylgallate
        "polysorbate80" -> Res.drawable.polysorbate80
        "polyvinylalcohol" -> Res.drawable.polyvinylalcohol
        "pvp" -> Res.drawable.pvp
        "pvpva" -> Res.drawable.pvpva
        "potassiumcitrate" -> Res.drawable.potassiumcitrate
        "potassiumnitrate" -> Res.drawable.potassiumnitrate
        "potassiumtartrate" -> Res.drawable.potassiumtartrate
        "propylparaben" -> Res.drawable.propylparaben
        "sodiumalginate" -> Res.drawable.sodiumalginate
        "sodiumalkylsulfate" -> Res.drawable.sodiumalkylsulfate
        "sodiumbenzoate" -> Res.drawable.sodiumbenzoate
        "sodiumcetostearylsulfate" -> Res.drawable.sodiumcetostearylsulfate
        "nacl" -> Res.drawable.nacl
        "sodiumdodecylsulfate" -> Res.drawable.sodiumdodecylsulfate
        "sodiumbisulfite" -> Res.drawable.sodiumbisulfite
        "sodiumcarboxymethylcellulose" -> Res.drawable.sodiumcarboxymethylcellulose
        "sodiumcitrate" -> Res.drawable.sodiumcitrate
        "sodiumlaurylethersulfate" -> Res.drawable.sodiumlaurylethersulfate
        "sodiumpyrophosphate" -> Res.drawable.sodiumpyrophosphate
        "sodiummetabisulfite" -> Res.drawable.sodiummetabisulfite
        "sodiumsulfite" -> Res.drawable.sodiumsulfite
        "sodiumthiosulfite" -> Res.drawable.sodiumthiosulfite
        "sorbicacid" -> Res.drawable.sorbicacid
        "span80" -> Res.drawable.span80
        "starch" -> Res.drawable.starch
        "stearicacid" -> Res.drawable.stearicacid
        "sudaniii" -> Res.drawable.sudaniii
        "talc" -> Res.drawable.talc
        "thiomersal" -> Res.drawable.thiomersal
        "tragacanth" -> Res.drawable.tragacanth
        "triethanolaminestearate" -> Res.drawable.triethanolaminestearate
        "tec" -> Res.drawable.tec
        "vitamine" -> Res.drawable.vitamine
        "vitaminc" -> Res.drawable.vitaminc
        "wax" -> Res.drawable.wax
        "xanthangum" -> Res.drawable.xanthangum
        else -> null
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExcipientDetailScreen(excipient: Excipient, onBack: () -> Unit) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(excipient.name) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Button(onClick = onBack) {
                    Text("Back")
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            getDrawableResourceByName(excipient.imageRes)?.let {
                Box(
                    modifier = Modifier
                        .height(250.dp)
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                ) {
                    SharedImage(
                        image = it,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Fit
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            DetailItem(label = "Function", value = excipient.function)
            DetailItem(label = "Molecule Type", value = excipient.moleculetype)
            DetailItem(label = "Alternative Name", value = excipient.alternativename.ifBlank { "None" })
            DetailItem(label = "Usage", value = excipient.usage)
            DetailItem(label = "Charge", value = excipient.charge)
            DetailItem(label = "Aqueous Solubility", value = excipient.aqsol)
            if (excipient.note != "none") {
                DetailItem(label = "Note", value = excipient.note)
            }
        }
    }
}

@Composable
private fun DetailItem(label: String, value: String) {
    if (value != "none") {
        Column(modifier = Modifier.padding(vertical = 8.dp).fillMaxWidth()) {
            Text(text = label, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text(text = value, fontSize = 16.sp)
        }
    }
}
