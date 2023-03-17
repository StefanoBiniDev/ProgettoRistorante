package Package1;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;
import java.util.stream.Collectors;

public class Reception implements PropertyChangeListener { // observer

	LinkedList<Cliente> codaClienti = new LinkedList<Cliente>();
	LinkedList<Tavolo> listaTavoli = new LinkedList<Tavolo>();
	private CapoReparto cassiere;

	private static Reception instance = null;

	private Reception() {}

	static Reception getInstance() { // pattern Singleton
		if (instance == null) {
			instance = new Reception();
		}
		return instance;
	}

	public CapoReparto getCassiere() {
		return cassiere;
	}

	public void setCassiere(CapoReparto cassiere) {
		this.cassiere = cassiere;
	}

	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println("entrato nel metodo pc di reception");
		System.out.println((int) evt.getNewValue());
		this.setOccupato((int) evt.getNewValue());
	}

	public void setOccupato(int idTavolo) {
		System.out.println("entrato nel metodo di reception"); 
		Optional<Tavolo> newTavolo2 = listaTavoli.stream().filter(tavoloReale -> tavoloReale.getIdTavolo() == idTavolo)
				.findAny();
		System.out.println("Occupato prima: " + newTavolo2.get().isOccupato());

		for (Tavolo tavoloCorrente : listaTavoli) {
			if (tavoloCorrente.getIdTavolo() == idTavolo) {
				System.out.println("IdTavolo: " + idTavolo);
				Optional<Tavolo> newTavolo = listaTavoli.stream()
						.filter(tavoloReale -> tavoloReale.getIdTavolo() == idTavolo).findAny();
				System.out.println("Occupato dopo: " + newTavolo.get().isOccupato());
				newTavolo.get().setOccupato(!tavoloCorrente.isOccupato());

				System.out.println("cambiato valore nella lista tavoli della reception");
			}
		}
	}

	public boolean getOccupato(int idTavolo) {

		Tavolo tavoloCorrente = (Tavolo) listaTavoli.stream().filter(tavolo -> tavolo.getIdTavolo() == idTavolo);
		return tavoloCorrente.isOccupato();
	}

	public void inserisciCliente(Cliente ultimoCliente) {
		codaClienti.add(ultimoCliente);
	}

	public Cliente rimuoviCliente() {
		Cliente prossimoCliente = codaClienti.removeFirst();
		return prossimoCliente;
	}

	public LinkedList<Cliente> getListaClienti() {
		return codaClienti;
	}

	public LinkedList<Tavolo> getListaTavoli() {
		return listaTavoli;
	}

	public void addTavolo(Tavolo tavolo) {
		listaTavoli.add(tavolo);
	}
	public void addCliente(Cliente cliente) {
		codaClienti.add(cliente);
	}
	

	public void faiAccomodareClienti() {
		@SuppressWarnings("unchecked")
		LinkedList<Cliente> codaClientiCopia = (LinkedList<Cliente>) codaClienti.clone();
		for (Cliente cliente : codaClientiCopia) {
			Optional<Tavolo> tavoloOccupabile = listaTavoli.stream().filter(
					tavolo -> tavolo.isOccupato() == false && tavolo.getNumeroPosti() == cliente.getNumeroCommensali())
					.findFirst();
			if (!tavoloOccupabile.isPresent()) {
				tavoloOccupabile = listaTavoli.stream().filter(tavolo -> tavolo.isOccupato() == false
						&& tavolo.getNumeroPosti() > cliente.getNumeroCommensali()).findFirst();
			}else {
			//if (tavoloOccupabile.isPresent()) {
				/*
				 * allora quel cliente si Accomoda a quel tavolo assieme ai suoi amici/familiari
				 */
				Tavolo tavoloReale = tavoloOccupabile.get();
				tavoloReale.setOccupato(true);
				final int tavoloRealeid = tavoloReale.getIdTavolo();

				Optional<Tavolo> tavoloSala = SalaRistorante.getInstance().getListaTavoli().stream()
						.filter(tav -> tav.getIdTavolo() == tavoloRealeid).findFirst();

				tavoloReale = tavoloSala.get();
				tavoloReale.setOccupato(true);	
				cliente.setSeduto(true);
				cliente.setIdTavolo(tavoloRealeid);
				
				SalaRistorante.getInstance().getClientAccomodati().add(cliente);
				
				System.out.println("Tavolo: " + tavoloReale.getIdTavolo() + " occupato dal cliente: "
						+ cliente.getNome() + " " + cliente.getCognome());
				codaClienti.remove(cliente);
			}
		}
	}

	public void mostraTavoli() {
		listaTavoli.forEach(tav -> System.out.println("Reception: Tavolo: " + tav.getIdTavolo() + " ,numero posti: "
				+ tav.getNumeroPosti() + " ,cameriere assegnato: " + tav.getCameriereAssegnato().getNome()
				+ " ,occupato: " + tav.isOccupato()));
	}

	public void mostraCodaClienti() {
		codaClienti.forEach(cliente -> System.out.println("Nome cliente: " + cliente.getNome() + " "
				+ cliente.getCognome() + " ,numero commensali: " + cliente.getNumeroCommensali()
				+ " ,costo accumulato del pasto: " + cliente.getCostoServizio()));
	}
	
	public int identificazioneCliente(String nomeNuovoCliente, String cognomeNuovoCliente) {
		int scelta = 1;
		
		/*controllo incrociato con la coda */
		
		Optional <Cliente> clienteProvv = codaClienti.stream().filter(cli -> cli.getNome().equals(nomeNuovoCliente.toLowerCase()) && cli.getCognome().equals(cognomeNuovoCliente.toLowerCase())).findAny();
		if(clienteProvv.isPresent()) {
			//Cliente cli = clienteProvv.get();
			return scelta = 2; //cliente in coda
		}
		else {
			System.out.println("Guardo se è 3 ");
			scelta = SalaRistorante.getInstance().controlloClienti(nomeNuovoCliente,cognomeNuovoCliente);
		}
		if(scelta == 0) {
			scelta = 1;
		}		
		return scelta;
	}
	
	public String prossimiInCoda(String nome, String cognome) {
		String messaggio = "";
		
		int indirizzo = codaClienti.stream().map(cliente -> cliente.getNome()).collect(Collectors.toList()).indexOf(nome);
		if (indirizzo == 0) {
			messaggio = "Sei il prossimo cliente ad accomodarsi!";
		}else if (indirizzo >0){
			int numCoda = codaClienti.size() - (codaClienti.size()-indirizzo);
			messaggio = " Ci scusiamo per l'attesa, avete "+ numCoda 
					 +" clienti in coda prima di voi, vi troveremo un tavolo il prima possibile :)";
		}else {
			messaggio = "Errore, non sei in lista";
		}
		return messaggio;	
	}
}
