package Package1;

public @interface ToDo {
	
	/*
	 *  
	 * 2) implementare tutti i singleton (reception, menu, cucina, sala da pranzo)
	 * 3) Dire quale tavolo è assegnato ad ogni cliente ( e assengnare quello con meno posti possibili per il cliente che non ha 
	 * 		trovato un tavolo con posti precisi
	 * 4) Forse serve una lista di clienti accomodati che dovranno pagare
	 * 5) inizializzare anche camerieri e altre figure di riferimento del ristorante
	 * 6) implementazione tabelle da cui prendere i dati
	 * 7) controllo delmetodo per far scorrere la coda nel caso si liberi un tavolo
	 * 8) Probabile che debba mettere i packages per usare observer con firesupportproperty (possibile altra soluzione:
	 *      dare a reception solo la copia della lista di sala ristorante, cosi la prima non punterà direttamente agli oggetti
	 *      di tipo tavolo e avrà bisogno di aggiornarsi con l'observer
	 * 9) Menu può essere fatto: 
	 * 		- con switch case molto lungo;
	 * 		- con linked list di oggetti;
	 * 		- alternativa: switch per categoria + linked list di oggetti
	 * 		- pizza realizzata con pattern builder.
	 * 10) Alla chiusura del programma, sovrascrivere i db coi nuovi dati
	 * 11) Applicare pattern factory su menu per creare oggetti diversi di tipo "primo","Antipasto"...
	 * 12) Possibile implementazione del pattern composite per strutturare la gerarchia del personale nel ristorante 
	 *  Fatti :
	 * 
	 * 1) Riguardarsi bene pattern Observer per implementarlo nella monitorazione dei tavoli appena si sono liberati

	 * */
}
