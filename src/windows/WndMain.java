package windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;

import db.DbController;
import dialogs.DlgCategoryList;
import dialogs.DlgLogView;
import elements.StatusBar;
import menus.MainTop;

// TODO Desktop für die Fenster initalisieren.

/**
 * Klasse für das Hauptfenster.
 * 
 * @author René Majewski
 */
public class WndMain extends JFrame implements ActionListener {
	/**
	 * Serialisation ID
	 */
	private static final long serialVersionUID = 2595951786930353918L;
	
	/**
	 * StatusBar
	 */
	private StatusBar status;

	/**
	 * Hauptfenster initalisieren
	 */
	public WndMain() {
		// Name des Hauptfensters
		super("René's Haushaltsbuch");
		
		// Fenster beim Beenden schließen
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// Größe
		setSize(250,100);
		setExtendedState(MAXIMIZED_BOTH);
		
		// Menü setzen
		setJMenuBar(new MainTop(this));
		
		// StatusBar initalisieren und anzeigen
		status = StatusBar.getInstance();
		getContentPane().add(status, java.awt.BorderLayout.SOUTH);
		
		// Datenbank vorbereiten
		prepaireDatabase();
		
		// anzeigen
		status.setMessageAsOk("Ready");
		setVisible(true);
	}

	/**
	 * Überprüft ob die Tabellen angelegt wurden. Ist dies nicht der Fall, so
	 * werden die Tabellen angelegt.
	 */
	private void prepaireDatabase() {
		try {
			// Verbindung zur Datenbank herstellen
			DbController db = DbController.getInstance();
			Statement stm = db.createStatement();
			
			// Tabelle für die einzelnen Kategorien
			stm.executeUpdate("CREATE TABLE IF NOT EXISTS 'category' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' TEXT);");
			
			// Überprüfen ob standard Kategorien schon eingetragen wurden
			ResultSet rs = stm.executeQuery("SELECT count(*) FROM 'category'");
			if (rs.getInt(1) == 0)
			{
				// Kategorien anlegen
				PreparedStatement ps = db.prepareStatement("INSERT INTO 'category' ('name') VALUES (?);");
				ps.setString(1, "Lebensmittel");
				ps.addBatch();
				ps.setString(1, "Getränke");
				ps.addBatch();
				ps.setString(1, "Kleidung");
				ps.addBatch();
				ps.setString(1, "Schuhe");
				ps.addBatch();
				ps.setString(1, "Fahrkarten");
				ps.addBatch();
				ps.setString(1, "Hygiene");
				ps.addBatch();
				ps.setString(1, "Bücher");
				ps.addBatch();
				ps.setString(1, "Zeitschriften");
				ps.addBatch();
				ps.setString(1, "Freizeit");
				ps.addBatch();
				ps.setString(1, "Anschaffungen");
				ps.addBatch();
				ps.setString(1, "Apotheke");
				ps.addBatch();
				ps.setString(1, "Arzt");
				ps.addBatch();
				ps.setString(1, "Versicherungen");
				ps.addBatch();
				ps.setString(1, "Sonstiges");
				ps.addBatch();
				
				// Kategorien in die Datenbank schreiben
				db.setAutoCommit(false);
				ps.executeBatch();
				db.setAutoCommit(true);
			}
			status.setMessage("Datenbank: Tabelle der Kategorien ist fertig vorbereitet");
			
			// Tabelle für die einzelnen Ausgaben
			stm.executeUpdate("CREATE TABLE IF NOT EXISTS 'outgoing' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'day' DATE, 'category' INTEGER, 'money' REAL, 'cash' BOOLEAN, 'comment' TEXT);");
			status.setMessage("Datenbank: Tabelle der einzelnen Ausgaben ist fertig vorbereitet");
			
			// Tabelle für die einzelnen Einnahmen
			stm.executeUpdate("CREATE TABLE IF NOT EXISTS 'ingoing' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'day' DATE, 'category' INTEGER, 'money' REAL, 'comment' TEXT)");
			status.setMessage("Datenbank: Tabelle der einzelnen Einnahmen ist fertig vorbereitet");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Es wird auf die einzelnen Drücke von Buttons oder Menü-Einträgen
	 * reagiert.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand())
		{
			// Programm beenden
			case MainTop.FILE_END:
				System.exit(0);
				break;
			
			// Kategorien anzeigen
			case MainTop.DB_CATEGORY_LIST:
				new DlgCategoryList(this);
				break;
				
			// Log anzeigen
			case MainTop.LOG_VIEW:
				new DlgLogView(this);
				break;
		}
		
	}

}
