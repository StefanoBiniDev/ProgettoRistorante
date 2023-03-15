package Package1;

import java.beans.*;
import java.util.*;

public class SalaRistorante { //observable
	LinkedList<Tavolo> listaTavoli = new LinkedList<Tavolo>();
	LinkedList<Cliente> clientiAccomodati = new LinkedList<Cliente>();
	private CapoReparto capoSala;
	//private boolean occupato;
	private PropertyChangeSupport support;
	private static SalaRistorante instance = null;  

	static SalaRistorante getInstance() { //pattern Singleton
		if (instance == null) {
			instance = new SalaRistorante();
		}
		return instance;
	}

    public SalaRistorante() {
        support = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }
	
	public CapoReparto getCapoSala() {
		return capoSala;
	}
	public void setCapoSala(CapoReparto capoSala) {
		this.capoSala = capoSala;
	} 
	
	public LinkedList<Tavolo> getListaTavoli() {
		return listaTavoli;
	}

	public void addTavolo(Tavolo tavolo) {
		listaTavoli.add(tavolo);
	}
	
	public void mostraTavoli() {
		listaTavoli.forEach(tav -> System.out.println("Sala: Tavolo: "+ tav.getIdTavolo()+" ,numero posti: "+tav.getNumeroPosti()+
							" ,cameriere assegnato: "+tav.getCameriereAssegnato().getNome()+" ,occupato: "+ tav.isOccupato()));
	}
	
	public LinkedList<Cliente> getClientAccomodati() {
		return clientiAccomodati;
	}
	
	public int controlloClienti(String nome, String cognome) {
		int scelta = 0;
		Iterator<Cliente> iterator = clientiAccomodati.iterator();
		
		while(iterator.hasNext()) {
	      Cliente clienteProvv = iterator.next();
	      if(clienteProvv.getNome().equals(nome)) {
	    	  return scelta = 3;
	      }
	      if(clienteProvv.getNome() == nome) {
	    	  return scelta = 3;
	      }
	    }
		return scelta;
	}
	
	public void setCambiaOccupato(int idTavolo/*Tavolo tavoloCorrente*/) {
		//boolean valoreOccupato = false;
		//Tavolo tavoloCorrente = new Tavolo();
		//Tavolo tavoloNuovaVersione = new Tavolo();
		for(Tavolo tavolo:listaTavoli){
			if(/*tavoloCorrente.getIdTavolo()*/idTavolo == tavolo.getIdTavolo()) {
			//	tavoloCorrente = tavolo;
				if (!tavolo.isOccupato()) {
					tavolo.setOccupato(true);
					//valoreOccupato = true;
				}
				else {
					tavolo.setOccupato(false);
					//valoreOccupato = false;
				}
				//tavoloNuovaVersione = tavolo;
			}
		}	
		System.out.println("richiamo la lista anche di reception");
        //this.occupato = valoreOccupato;

        Reception.getInstance().faiAccomodareClienti();
        //support.firePropertyChange("occupato", this.occupato, idTavolo/*tavoloCorrente/*valoreOccupato*/); //forse deve passare anche l'oggetto tavolo all'observer
        //support.firePropertyChange("tavolo", tavoloCorrente, tavoloNuovaVersione);
	}
	
	public Cliente getSearchedCliente(String nome, String cognome) {
		Optional <Cliente> c = clientiAccomodati.stream().filter(cliente -> cliente.getNome().equals(nome) && cliente.getCognome().equals(cognome)).findFirst();
		Cliente cliente = c.get();
		return cliente;
	}
}
