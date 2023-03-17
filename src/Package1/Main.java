package Package1;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class Main {

	static Scanner scan;
	static int menu() {
		System.out.println( "\t Cosa volete ordinare? ");
	    System.out.println(" ");
	    System.out.println("Digita  'antipasti' o '1'   se vuoi ordinare un antipasto");
	    System.out.println("Digita  'primi' o '2'  se vuoi ordinare un primo");
	    System.out.println("Digita  'dolci' o '3'   se vuoi ordinare un dolce ");
	    System.out.println("Digita  'pizze' o '4'   se vuoi ordinare una bevanda ");
	    System.out.println("Digita  'paga' o 5'   per pagare e uscire ");
	    System.out.println("Digita  'esci' o '6'   per terminare l'ordinazione ");
	    System.out.println("Luca è stato qui\nAttento a quando fai una commit senza controllare");
	    
	    scan = new Scanner(System.in);
	    String action = scan.nextLine();
	    int visitor_action;
	    //if (action.equals("1") || action.equals("2") || action.equals("3") || action.equals("4")|| action.equals("5")) 
	    if (Integer.valueOf(action) >=1 && Integer.valueOf(action) <= 6)
	    	visitor_action = Integer.valueOf(action);
	    else
	    	visitor_action = 0;
	    //s.close();
	    return visitor_action;
	    
	}
	
	@SuppressWarnings("resource")
	static void ordineMenuScelto(ArrayList<Pietanza> menuScelto,String nomeMenu, String nomeCliente, String cognomeCliente) {
		Optional <Pietanza> pietanza;
		boolean goBack = false;
		do {
			
			Menu.getInstance().showSelectedMenu(menuScelto,nomeMenu);
			/*mostrare le pietanze da una lista apposita*/
			System.out.println("");
			
			scan= new Scanner(System.in);
			//String num = scan.nextLine();
			System.out.println(scan.hasNextInt());
			if (scan.hasNextInt()) {
				int num = scan.nextInt();
				if( num>0 && num<=menuScelto.size() ) {
			    	
			    	System.out.println("In che quantità?");
			    	//scan = new Scanner(System.in);
				    int qta = scan.nextInt();
				    
				    /* lista ordini al tavolo */
				    
				    /* calcolo prezzo per il numero di pietanze ordinate*/
				    pietanza = menuScelto.stream().filter(obj -> obj.getId() == num).findFirst();
				    System.out.println(pietanza.isPresent()+" ++ " +pietanza.get().getId());
				    if(pietanza != null) {
					    float costo = (menuScelto.stream().filter(obj -> obj.getId() == num).findFirst().get().getPrezzo()) * qta;
					    
					    SalaRistorante.getInstance().getSearchedCliente(nomeCliente, cognomeCliente).setCostoServizio(costo);
					    
				    	System.out.println("Ordine eseguito");
				    }	
				
				}
				
			}else {
				goBack = true;
			}
		    //s1.close();
		}while(/*pietanza != null &&*/ goBack == false);
	}

	@SuppressWarnings("resource")
	public static void main(String[] args){
		
		int sceltaTipoMenu = 0; 
		boolean esecuzioneSecondaria = true;
    	//Scanner s = null;    	
		
		Inizializzazione init = new Inizializzazione();
		init.begin();
		Reception.getInstance().faiAccomodareClienti();
		
		/*Prova per fa alzare i clienti di un tavolo, per vedere se altri vengono accomodati*/
		
		int idTavolo = 1;
		SalaRistorante.getInstance().addPropertyChangeListener(Reception.getInstance()); //mettere in inizializzazione

		Reception.getInstance().mostraTavoli();
		Reception.getInstance().mostraCodaClienti();
		
		boolean esecuzione = true;
		
		do {
			/* identificazione utente */
			System.out.println("");
			System.out.println("Premi un tasto per continuare... ");
			new Scanner(System.in).nextLine();
			
			System.out.println("Buona sera! Benvenuti al nostro ristorante. Desiderate entrare nell'applicazione? (s/si/n/no)");
			scan = new Scanner(System.in);
		    String scelta = scan.nextLine();
		    
		    if(scelta.toLowerCase().equals("n")||scelta.toLowerCase().equals("no")) {
				System.out.println("Arrivederci!");
		    	esecuzione = false;	
		    }
		    else if(scelta.toLowerCase().equals("s")||scelta.toLowerCase().equals("si")){
				
				System.out.println("Vi preghiamo gentilmente di fornire i vostri dati");
				System.out.println("Nome: ");
				//s = new Scanner(System.in);
			    String nomeNuovoCliente = scan.nextLine().toLowerCase();
			    
			    System.out.println("Cognome: ");
				//s = new Scanner(System.in);
			    String cognomeNuovoCliente = scan.nextLine().toLowerCase();
				
			    /* CONTROLLO SE IL CLIENTE E' NUOVO OPPURE SE E' GIA IN CODA, O ANCORA SE E' AL TAVOLO E DESIDERA ORDINARE QUALCOSA */
			    
			    int accessoCliente = Reception.getInstance().identificazioneCliente(nomeNuovoCliente, cognomeNuovoCliente);
				switch(accessoCliente) {
					case 1: //caso cliente non identificato, ovvero nuovo cliente!
						 System.out.println(" Benvenuti, provvediamo ad inserirvi nella coda di attesa: ");
				    	    
						 System.out.println("quanti siete? ");
						 //s = new Scanner(System.in);
				    	 int numCommensali = scan.nextInt();
				    	    
						 Cliente nuovoCliente = new Cliente(nomeNuovoCliente,cognomeNuovoCliente,numCommensali);
						 Reception.getInstance().addCliente(nuovoCliente);
						 String messaggio = Reception.getInstance().prossimiInCoda(nomeNuovoCliente, cognomeNuovoCliente);
						 System.out.println(messaggio);
						break;
					case 2: //caso cliente in coda
						/*funzione per trovare il numero di clienti in coda al momento*/
						messaggio = Reception.getInstance().prossimiInCoda(nomeNuovoCliente, cognomeNuovoCliente);
						System.out.println(messaggio);
						
						break;
					case 3: //caso cliente a tavola
						
						do {
							System.out.println("");
							System.out.println("Premi un tasto per continuare... ");
							new Scanner(System.in).nextLine();
							
							/* INIZIO MENU DA CONSOLE PER CLIENTI GIA ACCOMODATI*/
							sceltaTipoMenu = menu();
							
							switch (sceltaTipoMenu) {
							/* ANCHE CHIEDERE PER OGNI COSA ORDINATA IN CHE QUANTITA' */
							case 1:
							    System.out.println(" size"+Menu.getInstance().getAntipasti().size());
								ordineMenuScelto(Menu.getInstance().getAntipasti(),"Antipasti",nomeNuovoCliente,cognomeNuovoCliente);
					            esecuzioneSecondaria = true;
								break;
							case 2:
								ordineMenuScelto(Menu.getInstance().getPrimi(),"Primi",nomeNuovoCliente,cognomeNuovoCliente);
					            esecuzioneSecondaria = true;
								break;
							case 3:
								ordineMenuScelto(Menu.getInstance().getDolci(),"Dolci",nomeNuovoCliente,cognomeNuovoCliente);
					            esecuzioneSecondaria = true;
								break;
							case 4:
								ordineMenuScelto(Menu.getInstance().getPizze(),"Pizze",nomeNuovoCliente,cognomeNuovoCliente);
					            esecuzioneSecondaria = true;
								break;
							case 5:			
								/* pagare e liberare il tavolo */
							    Cliente clienteUscente = SalaRistorante.getInstance().getSearchedCliente(nomeNuovoCliente,cognomeNuovoCliente);
							    /* Settare tavolo non più occupato nelle due liste  */
							    /* far accomodare nuovi clienti dalla coda */
							    
							    SalaRistorante.getInstance().setCambiaOccupato(clienteUscente.getIdTavolo());
							    
								SalaRistorante.getInstance().getClientAccomodati().remove(clienteUscente);
								
								System.out.println("Salve, il conto da saldare per il pasto è di : "+clienteUscente.getCostoServizio() +" euro");
								System.out.println("Le auguriamo buona giornata! A presto");
					            esecuzioneSecondaria = false;
					            
					            System.out.println("(vuoi terminare l'esecuzione del programma?) (s/si) per terminare, (altro) per proseguire");
								scan = new Scanner(System.in);
							    String nuovaScelta = scan.nextLine();
							    
							    if(nuovaScelta.toLowerCase().equals("s")||nuovaScelta.toLowerCase().equals("si")){
							    	esecuzione = false;
							    }
					    		break;
							case 6:			
								System.out.println("Uscita dal menu, ritorno alla schermata principale ");
					            esecuzioneSecondaria = false;
					    		break;
							default:
					        	System.out.println("Input non riconosciuto. Prova di nuovo o digita 'uscita / 4' per terminare.");
					            esecuzioneSecondaria = true;
					            break;
							}
						}while(esecuzioneSecondaria);
						break;
						
					/*case 4: 
						System.out.println("Uscita dall'applicazione, arrivederci! ");
			            esecuzione = false;
			    		break;*/
					default: 
						System.out.println("Input non riconosciuto. Prova di nuovo o digita 'uscita / 4' per terminare.");
			            esecuzione = true; //
			            break;
				}
			}
		    else {
		    	System.out.println("Input non riconosciuto. Prova di nuovo");
		    }
		}while(esecuzione);
		scan.close();
	}
}
