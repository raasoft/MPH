module TrafficLights

//Un semaforo puó avere due colori: ROSSO XOR VERDE
//Vengono messi semafori negli incroci tra DUE STRADE. Il semaforo deve garantire la marcia su un solo senso di marcia per strada.

abstract sig Color{} //Non é instanziabile come oggetto in quanto oggetto astratto
one sig GREEN extends Color{} //Forziamo alloy ad usare una e una sola instanza della signature GREEN
one sig RED extends Color{}	//Idem per RED

sig Semaphore {
	color: one Color //Uno e un solo colore!
}

abstract sig Traffic{}	//Non é instanziabile
one sig FLOWING extends Traffic{} //Esiste solo un tipo di traffico FLOWING
one sig STOPPED extends Traffic{}	//Esiste un solo tipo di traffico STOPPED

sig Road {
	traffic: one Traffic
}

sig Intersection {
	connection: Semaphore -> Road //Mapping tra semafori e strade
}
{ //Vincoli strutturali semplici
	#connection = 2	//Le strade sono due e i semafori ad essi associati saranno due
}

//Restituisce tutti i semafori di un incrocio
fun getSemaphores[i: Intersection]: set Semaphore {
	i.connection.Road //Filtro dell'insieme di ritorno: per ogni intersezione prendo le sue connessioni e all'interno filtro per Road
}

//Restituisce tutte le strade di un incrocio
fun getRoads[i: Intersection]: set Road {
	i.connection[Semaphore]	//Prendo in input le Intersection, in output ho un set di Road e per filtrare l'insieme di strade connesse a i é prendere ogni connessioni e utilizzare semaphore

	//La differenza tra le parentesi quadre e il punto é nell'ordine CHE NON HO CAPITO XD
}

fun getRoad[s: Semaphore]: one Road {
	Intersection.connection[s] //Dato un semaforo restituisci la sua strada corrispondente
}

//Adesso dobbiamo modellare seriamente i vincoli di questa realtá

fact intersectionStructure {

	//esattamente 2 strade e 2 semafori
	(all i: Intersection |
		(let s = getSemaphores[i] | #s = 2) and (let r = getRoads[i] | #r =2)
	)
	and
	//All semaphore connected to ONLY 1 road
	(all s: Semaphore |
		(let r = getRoad[s] | #r = 1)
	)
}

//I semafori di un incrocio DEVONO AVERE colori DIFFERENTI
fact greenIsExclusive {	

	//Prendo tutti gli incrodi del nostro mondo, prendo i semafori di ogni incrocio, allora il loro colore deve essere diverso!

	all i: Intersection |
		(let s = getSemaphores[i] |
			all s1, s2: Semaphore | (s1 != s2 and s1 in s and s2 in s)
			implies s1.color != s2.color
		)
}

//Traffic flows with GREEN
fact goWithGreen {
	(all s: Semaphore | 
		let r = getRoad[s] | s.color = RED iff r.traffic = STOPPED)
	and
	(all r: Road | r.traffic = STOPPED implies //Significa che se il traffico é fermo, allora questa strada appartiene ad un incrocio! Non possiamo mettere stop su una strada dove non ci sono incroci!
		#Intersection.connection.r > 0)
}

pred show() {
	#Intersection = 1
}

run show
