package windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;

import db.DbController;
import dialogs.DlgLogView;
import elements.StatusBar;
import menus.MainTop;
import windows.internal.WndCategoryList;
import windows.internal.WndMoneyList;
import windows.internal.WndSectionList;

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
	 * Speichert den Desktop
	 */
	private JDesktopPane _desktop;

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
		
		// Dekstop initalisieren
		_desktop = new JDesktopPane();
		add(_desktop);
		
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
			stm.executeUpdate(DbController.queries().category().createTable());
			
			// Überprüfen ob standard Kategorien schon eingetragen wurden
			ResultSet rs = stm.executeQuery(DbController.queries().category().count());
			if (rs.getInt(1) == 0)
			{
				// Kategorien anlegen
				PreparedStatement ps = db.prepareStatement(DbController.queries().category().insert());
				System.out.println(ps.getFetchSize());
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
			
			// Tabelle für die Geschäfte
			stm.executeUpdate(DbController.queries().section().createTable());
			
			// Überprüfen ob die Einträge für die Standart Geschäfte schon
			// enthalten sind
			rs = stm.executeQuery(DbController.queries().section().count());
			if (rs.getInt(1) == 0) {
				// Geschäfte anlegen
				PreparedStatement ps = db.prepareStatement(DbController.queries().section().insert());
				ps.setString(1, "jobcenter");
				ps.addBatch();
				ps.setString(1, "Mutti");
				ps.addBatch();
				ps.setString(1, "Schwester");
				ps.addBatch();
				ps.setString(1, "EDEKA");
				ps.addBatch();
				ps.setString(1, "Netto");
				ps.addBatch();
				ps.setString(1, "Lidl");
				ps.addBatch();
				ps.setString(1, "Penny");
				ps.addBatch();
				ps.setString(1, "DM");
				ps.addBatch();
				ps.setString(1, "Rossmann");
				ps.addBatch();
				ps.setString(1, "Obi");
				ps.addBatch();
				ps.setString(1, "Hornbach");
				ps.addBatch();
				ps.setString(1, "würtembergische");
				ps.addBatch();
				ps.setString(1, "Stadtwerke");
				ps.addBatch();
				ps.setString(1, "Sparkasse");
				ps.addBatch();
				
				// Geschäfte in die Datenbank schreiben
				db.setAutoCommit(false);
				ps.executeBatch();
				db.setAutoCommit(true);
			}
			status.setMessage("Datenbank: Tabelle der Geschäfte ist fertig vorbereitet.");
			
			// Tabelle für die Ein- und Ausgaben
			stm.executeUpdate(DbController.queries().money().createTable());
			status.setMessage("Datenbank: Tabelle der Ein- und Ausgaben ist fertig vorbereitet");
			
			// Tabelle für die Details zu den Ein- und Ausgaben
			stm.executeUpdate(DbController.queries().moneyDetails().createTable());
			status.setMessage("Datenbank: Tabelle der Details für Ein- und Ausgaben ist fertig vorbereitet.");
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
			case MainTop.DB_CATEGORY:
				_desktop.add(new WndCategoryList());
				break;
				
			// Geschäfte anzeigen
			case MainTop.DB_SECTION:
				_desktop.add(new WndSectionList());
				break;
				
			// Money-Datensätze anzeigen
			case MainTop.DB_MONEY:
				_desktop.add(new WndMoneyList());
				break;
				
			// Log anzeigen
			case MainTop.LOG_VIEW:
				new DlgLogView(this);
				break;
		}
		
	}

}
