package Package1;

public class Cameriere extends Impiegato{

	//private int id;
	private boolean occupato;
	
	public Cameriere(int id, String nome, String cognome) {
		//this.id = id;
		super.setId(id);
		super.setNome(nome);
		super.setCognome(cognome);
		this.occupato = false;
	}
	
	/*public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	*/
	
	public boolean isOccupato() {
		return occupato;
	}

	public void setOccupato(boolean occupato) {
		this.occupato = occupato;
	}
}
