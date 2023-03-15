package Package1;

public class Cliente {
	private String nome;
	private String cognome;
	private float costoServizio;
	private int numeroCommensali;
	private boolean seduto;
	private int idTavolo;
	
	public Cliente(String nome, String cognome, int numeroCommensali) {
		this.nome = nome;
		this.cognome = cognome;
		this.numeroCommensali = numeroCommensali;
		this.seduto = false;
		this.idTavolo = 0;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public float getCostoServizio() {
		return costoServizio;
	}
	public void setCostoServizio(float costoServizio) {
		this.costoServizio += costoServizio;
	}
	public int getNumeroCommensali() {
		return numeroCommensali;
	}
	public void setNumeroCommensali(int numeroCommensali) {
		this.numeroCommensali = numeroCommensali;
	}

	public boolean isSeduto() {
		return seduto;
	}

	public void setSeduto(boolean seduto) {
		this.seduto = seduto;
	}

	public int getIdTavolo() {
		return idTavolo;
	}

	public void setIdTavolo(int idTavolo) {
		this.idTavolo = idTavolo;
	}
	
	public void ordina() {
		
	}
	
}
