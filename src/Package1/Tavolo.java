package Package1;

public class Tavolo {
	private int idTavolo;
	private int numeroPosti;
	private Cameriere cameriereAssegnato;
	private boolean occupato;

	public Tavolo() {}
	
	public Tavolo(int idTavolo, int numeroPosti, Cameriere cameriereAssegnato) {
		this.idTavolo = idTavolo;
		this.numeroPosti = numeroPosti;
		this.cameriereAssegnato = cameriereAssegnato;
		this.occupato = false;
	}
	
	public int getIdTavolo() {
		return idTavolo;
	}

	public void setIdTavolo(int idTavolo) {
		this.idTavolo = idTavolo;
	}
	
	public int getNumeroPosti() {
		return numeroPosti;
	}

	public void setNumeroPosti(int numeroPosti) {
		this.numeroPosti = numeroPosti;
	}

	public Cameriere getCameriereAssegnato() {
		return cameriereAssegnato;
	}

	public void setCameriereAssegnato(Cameriere cameriereAssegnato) {
		this.cameriereAssegnato = cameriereAssegnato;
	}

	public boolean isOccupato() {
		return occupato;
	}

	public void setOccupato(boolean occupato) {
		this.occupato = occupato;
	}	
	
	/*METODI*/
	
	public void ShowMenu(Menu menu) {
		//menu.getInstance().getDolci();
	}
}
