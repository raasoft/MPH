sig Point{}

sig Segment {
	start: one Point,
	end: one Point
} {start != end} //Clausola che il punto inziale sia diverso dal punto finale

sig Line {
	startSeg: one Segment,
	endSeg: one Segment,
	intermediateSeg: set Segment
} //Consideriamo solo il primo e l'ultimo segmento come separati, il resto é un insieme di segmenti intermedi
{
	no x: intermediateSeg | startSeg = x or endSeg = x //I segmenti intermedi non devono comprendere quelli estremi
	lone x: intermediateSeg | startSeg.end = x.start //all'interno di quelli intermedi al piú un punto deve essere punto terminale di uno e di partenza dell'altro
	lone x: intermediateSeg | endSeg.start = x.end //duale del precedente
	no x: intermediateSeg | startSeg.start = x.end //non deve esserci un segmento che si richiude all'indietro
	no x: intermediateSeg | endSeg.end = x.start //duale del precedente
	all x: intermediateSeg | x.end = endSeg.start or one x1: intermediateSeg | x.end = x1.start //Dato un segmento interno, il punto finale di uno coincide con quello iniziale del secondo, oppure esiste un'altro segmento che fa da ponte
	all x: intermediateSeg | x.start = startSeg.end or one x1: intermediateSeg | x.start = x1.end //Duale del precedente
	#intermediateSeg = 0 implies (startSeg = endSeg or startSeg.end = endSeg.start) //se non ci sono lati intermedi, inizio e fine o sono lo stesso segmento oppure il primo é collegato al secondo
}

sig Polygon extends Line {}
{
	startSeg.end = endSeg.start //Il poligono non é nient'altro che una linea che si chiude su se stessa
}

//Intersect (versione semplificiata)
pred intersect(s1, s2: Segment) {
	s1 != s2 //se non sono lo stesso segmento
	and (	s1.start = s2.start or
							s1.start = s2.end or 
							s1.end = s2.end or 
							s1.end = s2.start)
	//non possiamo controllare se esistono punti intersecanti diversi dai punti estremi dei segmenti!!!
	//Il fatto é che non abbiamo specificato che un segmento é formato da infiniti punti, ergo per alloy esistono soltanto gli estremi!
}

pred linesIntersect(l1, l2: Line) {
	l1 != l2 //se non sono la stessa linea
	and (some s1: l1.intermediateSeg, s2: l2.intermediateSeg | intersect[s1, s2]) //Esiste almeno una coppia di segmenti intermedi tale per cui le linee si intersecano???
	//qui andrebbe specificato anche come fare a trovare se si intersecano i punti estremi di una delle due linee, che qui mancan
}

//Se vogliamo aggiungere dinamicitá dobbiamo aggiungere dei predicati che controllano se nel passaggio da uno stato all'altro vengono rispettate alcune regole
	//Dato che in alloy non sono previste variabili (cambiamenti di stati veri e proprii) in alloy si controllano se le regole di transizione tra uno stato iniziale l e uno finale l' vengano soddisfate (esse sono precondizioni e postcondizioni)
pred addToLine(l: Line, s: Segment, l': Line) { 

	//precondition 
	//Stato iniziale: avere una linea l normalissima alla quale voglio aggiungere un segmento s alla fine di l. 
	//Il punto finale della linea sia il punto iniziale del segmento s: se non sono uguali NON posso aggiungerlo alla linea!
	l.endSeg.end = s.start

	//postcondition
	//Stato finale: l'inizio di l' sia esattamente l'inizio di l
	//L'insieme intermedio di l' deve essere l'insieme intermedio di l piú l'ultimo segmento, che é diventato intermedio adesso
	l'.startSeg = l.startSeg and
	l'.intermediateSeg = l.intermediateSeg + l.endSeg and
	l'.endSeg = s
}

fact noTwoSegWithSameStartEnd {
	no disj x1, x2: Segment | x1.start = x2.start and x1.end = x2.end
}

run addToLine for 6 //Eseguiamo il predicato per vedere come si comporta il modello quando aggiungiamo dei segmenti alla nostra linea

//NON ABBIAMO DEFINITO COSA SUCCEDE SE PROVO AD AGGIUNGERE UNA LINEA AD UN POLIGONO!
//Il poligono essendo una linea, posso applicare ad un poligono la nostra addLine, ed ecco perché quando facciamo la execute a volte spuntano anhe dei poligoni xD
