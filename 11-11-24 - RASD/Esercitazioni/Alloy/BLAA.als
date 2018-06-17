sig Data {}

sig Patient {
	healthCard: one Int, 	//Tesserino sanitario
	patientCode: one Int	//Numero del codice assegnato al paziente per poter poi ritirare le analisi
}
{ //Vincoli semplici
	healthCard > 0
	patientCode > 0
}

sig Request {
	issue_data: one Data,		//Il problema da affrontare nell'analisi
	delivery_data: one Data	//Giorno in cui ritirare le analisi
}

sig Sample { //Campioni di sangue
	sampleNum: one Int,	//Numero di campioni di sangue
	sampleCode: one Int //Codice da attaccare alle provette
}
{ //Vincoli semplici
	sampleCode > 0
	sampleNum > 0
}

sig Check {	//Controlli effettuati sul sangue
	sample: one Sample,	//Ogni controllo é riferito ad ogni singola provetta
	results: lone Result	//Il risultato puó anche non essere ancora stato calcolato
}

sig Result {}

sig Diagnosis {}

sig Analysis {
	request: one Request, //Una analisi completa deve avere sempre una richiesta
	checks: some Check,	//Una analisi completa é fatta da alcuni controlli (uno o piú, non si puó fare una analisi senza nemmeno un controllo xDDD)
	diagnosis: lone Diagnosis,	//Ogni analisi puó avere zero o una diagnosi
	patient: one Patient,	//Ogni analisi ha esattamente un paziente
	samples: some Sample	//E ogni analisi ha almeno un campione di sangue...
	//Tutte queste molteplicitá sono state definite nel diagramma delle classi, ma le abbiamo date IN UN SENSO SOLO: non abbiamo mai detto che
	//ad una richiesta deve corrispondere una sola analisi! Quindi é sbagliato! XD
}

pred show() {
#Analysis > 2
}

run show
