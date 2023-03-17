package Package1;

import java.util.ArrayList;

public class Menu {

	private ArrayList<Pietanza> antipasti = new ArrayList<Pietanza>();
	private ArrayList<Pietanza> primi = new ArrayList<Pietanza>();
	private ArrayList<Pietanza> dolci = new ArrayList<Pietanza>();
	private ArrayList<Pietanza> pizze = new ArrayList<Pietanza>();
	private ArrayList<Pietanza> ingredientiPizza = new ArrayList<Pietanza>();
	private static Menu instance = null;

	private Menu() {}

	static Menu getInstance() { // pattern Singleton
		if (instance == null) {
			instance = new Menu();
		}
		return instance;
	}

	public ArrayList<Pietanza> getPrimi() {
		return primi;
	}

	public void setPrimi(ArrayList<Pietanza> primi) {
		this.primi = primi;
	}

	public ArrayList<Pietanza> getDolci() {
		return dolci;
	}

	public void setDolci(ArrayList<Pietanza> dolci) {
		this.dolci = dolci;
	}

	public ArrayList<Pietanza> getIngredientiPizza() {
		return ingredientiPizza;
	}

	public void setIngredientiPizza(ArrayList<Pietanza> ingredientiPizza) {
		this.ingredientiPizza = ingredientiPizza;
	}

	public ArrayList<Pietanza> getAntipasti() {
		return antipasti;
	}

	public void setAntipasti(ArrayList<Pietanza> antipasti) {
		this.antipasti = antipasti;
	}

	public ArrayList<Pietanza> getPizze() {
		return pizze;
	}

	public void setPizze(ArrayList<Pietanza> pizze) {
		this.pizze = pizze;
	}
	
	public void showSelectedMenu(ArrayList<Pietanza> menuScelto,String nomeMenu) {
	    System.out.println("Ecco il menu "+ nomeMenu +": " );
	    System.out.println(" ");
	    menuScelto.forEach(item ->  System.out.println(item.getId()+") "+"nome:"+ item.getNome()+" ,prezzo: "+item.getPrezzo()));
	    System.out.println(" ");
	    System.out.println("Che desideri ordinare? (Digita altro per tornare alla selezione menu)");
	}
}
