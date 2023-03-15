package Package1;

public class Pietanza {
	private int id;
	private String nome;
	private float prezzo;
	private String tipologia;
	
	public Pietanza(int id,String nome, float prezzo, String tipologia) {
		//this.setId(id);
		this.id = id;
		this.nome = nome;
		this.prezzo = prezzo;
		this.tipologia = tipologia;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public float getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}
	public String getTipologia() {
		return tipologia;
	}
	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
