package Package1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Inizializzazione {

	private final String DB_URL = "jdbc:mysql://localhost:3306/Ristorante";

	private final String USER = "root";

	private final String PASS = "Anaoi96!";

	public void begin() {
		// popolaListe();
		try {
			popolaListaClientiDaDB();

		} catch (SQLException sqe) {
			System.out.println("Nessun collegamento col database per la tabella Clienti");
			System.out.println(sqe);
			System.exit(0);
		}
		try {
			popolaListaTavoliDaDB();
		} catch (SQLException sqe) {
			System.out.println("Nessun collegamento col database per la tabella Tavoli");
			System.out.println(sqe);
			System.exit(0);
		}
		try {
			popolaMenuDaDB();
		} catch (SQLException sqe) {
			System.out.println("Nessun collegamento col database per la tabella Menu");
			System.out.println(sqe);
			System.exit(0);
		}
	}

	public void popolaListaClientiDaDB() throws SQLException {
	//	Reception.getInstance().getListaClienti();

		Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

		Statement s = conn.createStatement();
		
		/*si può usare anche prepare statement*/
		ResultSet rs = s.executeQuery("""
				SELECT * FROM Clienti
				""");

		while (rs.next()) {

			Cliente cliente = new Cliente(rs.getString(1), rs.getString(2), rs.getInt(3));
			Reception.getInstance().inserisciCliente(cliente);
		}
		conn.close();
	}

	public void popolaListaTavoliDaDB() throws SQLException {
		//Reception.getInstance().getListaClienti();

		Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

		Statement s = conn.createStatement();
		ResultSet rs = s.executeQuery("""
				SELECT * FROM Tavoli
				""");

		while (rs.next()) {

			Cameriere cameriere = new Cameriere(1, "Rossano", "");

			Tavolo tavolo = new Tavolo(rs.getInt(1), rs.getInt(2), /* rs.getInt(3) */cameriere);
			SalaRistorante.getInstance().addTavolo(tavolo);
			Reception.getInstance().addTavolo(tavolo);
		}
		conn.close();
	}

	public void popolaMenuDaDB() throws SQLException {

		/*for(Object o:Reception.getInstance().getListaClienti()) {
			System.out.println("o: "+o);
		}*/
		
		Connection conn2 = DriverManager.getConnection(DB_URL, USER, PASS);
		//System.out.println("o: "+conn2);
		Statement s2 = conn2.createStatement();
		//System.out.println("o: "+s2);

		ResultSet rs = s2.executeQuery("""
				SELECT * FROM Menu
				""");

		int id_antipasto = 1, id_primo = 1, id_pizza = 1, id_dolce = 1;

		while (rs.next()) {
			//System.out.println("o: "+rs.getInt(1));

			if (rs.getString(4).equals("Antipasto")) {
				Pietanza pietanza = new Pietanza(id_antipasto,rs.getString(2), rs.getFloat(3), rs.getString(4));
				Menu.getInstance().getAntipasti().add(pietanza);
				System.out.println(Menu.getInstance().getAntipasti().size());
				id_antipasto++;
			} else if (rs.getString(4).equals("Primo")) {
				Pietanza pietanza = new Pietanza(id_primo,rs.getString(2), rs.getFloat(3), rs.getString(4));
				Menu.getInstance().getPrimi().add(pietanza);
				id_primo++;
			} else if (rs.getString(4).equals("Pizza")) {
				Pietanza pietanza = new Pietanza(id_pizza,rs.getString(2), rs.getFloat(3), rs.getString(4));
				Menu.getInstance().getPizze().add(pietanza);
				id_pizza++;
			} else if (rs.getString(4).equals("Dolce")) {
				Pietanza pietanza = new Pietanza(id_dolce,rs.getString(2), rs.getFloat(3), rs.getString(4));
				Menu.getInstance().getDolci().add(pietanza);	
				id_dolce++;
			}

			//System.out.println("oo "+rs.getString(4));
		}
		conn2.close();
	}
}

/*
 * public void popolaListe() {
 * 
 * Reception.getInstance().getListaClienti(); Cliente cliente = new
 * Cliente("Stefano","Bini",5);
 * Reception.getInstance().inserisciCliente(cliente); cliente = new
 * Cliente("Mauro","Icardi",2);
 * Reception.getInstance().inserisciCliente(cliente); cliente = new
 * Cliente("Matteo","Salvini",1);
 * Reception.getInstance().inserisciCliente(cliente); cliente = new
 * Cliente("Dux","MeaLux",4); Reception.getInstance().inserisciCliente(cliente);
 * cliente = new Cliente("Carpe","Diem",3);
 * Reception.getInstance().inserisciCliente(cliente); cliente = new
 * Cliente("Silvio","Ungabunga",6);
 * Reception.getInstance().inserisciCliente(cliente); cliente = new
 * Cliente("Ubaldo","Mussolini",3);
 * 
 * int conteggioTavoli = 1;
 * 
 * Cameriere cameriere = new Cameriere(1,"Rossano", ""); Tavolo tavolo = new
 * Tavolo(conteggioTavoli, 4, cameriere);
 * SalaRistorante.getInstance().addTavolo(tavolo);
 * Reception.getInstance().addTavolo(tavolo); conteggioTavoli ++;
 * 
 * //cameriere = new Cameriere(1); tavolo = new Tavolo(conteggioTavoli, 2,
 * cameriere); SalaRistorante.getInstance().addTavolo(tavolo);
 * Reception.getInstance().addTavolo(tavolo); conteggioTavoli ++;
 * 
 * //cameriere = new Cameriere(1); tavolo = new Tavolo(conteggioTavoli, 6,
 * cameriere); SalaRistorante.getInstance().addTavolo(tavolo);
 * Reception.getInstance().addTavolo(tavolo); conteggioTavoli ++;
 * 
 * //cameriere = new Cameriere(1); tavolo = new Tavolo(conteggioTavoli, 3,
 * cameriere); SalaRistorante.getInstance().addTavolo(tavolo);
 * Reception.getInstance().addTavolo(tavolo);
 * 
 * }
 */
