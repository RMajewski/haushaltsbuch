/* 
* Copyright 2016 René Majewski
*  
* Lizenziert unter der EUPL, Version 1.1 oder - sobald diese von der
* Europäischen Kommission genehmigt wurden - Folgeversionen der EUPL
* ("Lizenz"); Sie dürfen dieses Werk ausschließlich gemäß dieser Lizenz
* nutzen. 
* 
* Eine Kopie der Lizenz finden Sie hier: 
* https://joinup.ec.europa.eu/software/page/eupl
*  
* Sofern nicht durch anwendbare Rechtsvorschriften gefordert oder in 
* schriftlicher Form vereinbart, wird die unter der Lizenz verbreitete 
* Software "so wie sie ist", OHNE JEGLICHE GEWÄHRLEISTUNG ODER BEDINGUNGEN -
* ausdrücklich oder stillschweigend - verbreitet.
* Die sprachspezifischen Genehmigungen und Beschränkungen unter der Lizenz
* sind dem Lizenztext zu entnehmen.
*/ 

package haushaltsbuch.db;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import haushaltsbuch.db.query.Queries;
import haushaltsbuch.elements.StatusBar;

/**
 * Stellt die Verbindung zur Datenbank bereit.
 * 
 * @author René Majewski
 *
 * @version 0.2
 * @since 0.1
 *
 */
public class DbController {
	/**
	 * Kontroller zur Datenbank
	 */
	private static DbController _controller = new DbController();
	
	/**
	 * Verbindung zur Datenbank
	 */
	private static Connection _connection;
	
	/**
	 * Speichert den Namen der Datenbank mit path
	 */
	private String _dbName;
	
	static {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			StatusBar.getInstance().setMessageAsError(
					"Fehler beim Laden des JDBC-Treibers", e);
		}
	}
	
	/**
	 * Initalisiert die Verbindung zur Datenbank
	 */
	private DbController() {
		_dbName = new String();
		initConnection();
	}
	
	/**
	 * Beendet die Verbindung zur Datenbank
	 */
	public void close() {
		try {
			if ( _connection != null && !_connection.isClosed()) {
				_connection.close();
			}
			_connection = null;
		} catch (SQLException e) {
			StatusBar.getInstance().setMessageAsError(
					DbController.statusDbError(), e);
		}
		

		if (_controller != null)
			_controller = null;
	}
	
	/**
	 * Gibt die Instanz des Controllers zurück.
	 * 
	 * @return Instanz des Controllers
	 */
	public static DbController getInstance() {
		if (_controller == null)
			_controller = new DbController();
		
		return _controller;
	}
	
	/**
	 * Initalisiert die Verbindung zur Datenbank.
	 */
	private void initConnection() {
		// Überprüfen ob getestet wird
		if (System.getProperty("testing") != null)
			// Beim Testen nur Datenbank im Speicher anlegen
			_dbName = new String(":memory:");
		else {
			// Überprüfen ob das Verzeichnis existiert
			_dbName = System.getProperty("user.home") + "/.majewski/haushaltsbuch";
			File file = new File(_dbName);
			if (!file.exists())
			{
				file.mkdirs();
			}
			
			// Datenbank-Namen anhängen
			if (System.getProperty("debugging") != null)
				_dbName += "/debugging.sqlite";
			else
				_dbName += "/haushaltsbuch.sqlite";
		}
		// Verbindung zur Datenbank herstellen
		try {
			// Wurde schon eine Verbindung hergestellt?
			if (_connection != null)
				return;
			
			// Verbindung zur Datenbank herstellen
			_connection = DriverManager.getConnection("jdbc:sqlite:" + _dbName);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				close();
			}
		});
	}
	
	/**
	 * Gibt die Verbindung zur Datenbank zurück.
	 * 
	 * @return Verbindung zur Datenbank
	 */
	public Connection getConnection()
	{
		return _connection;
	}
	
	/**
	 * Überprüft ob eine Verbindung zur Datenbank besteht.
	 * 
	 * @return Besteht eine Verbindung zur Datenbank?
	 */
	public boolean isConnection()
		throws SQLException
	{
		if (_connection == null)
			return false;
		
		if (_connection.isClosed())
			return false;
		
		return true;
	}

	/**
	 * Erzeugt ein {@link java.sql.Statement}
	 * 
	 * @return Erzeugtes SQL-Statement
	 * 
	 * @throws SQLException
	 */
	public Statement createStatement()
		throws SQLException
	{
		return _connection.createStatement();
	}
	
	/**
	 * Erzeugt ein {@link java.sql.PreparedStatement}
	 * 
	 * @param sql SQL-Befehl der ausgeführt werden soll
	 * 
	 * @return Erzeugtes SQL-PreparedStatement
	 * 
	 * @throws SQLException
	 */
	public PreparedStatement prepareStatement(String sql)
		throws SQLException
	{
		return _connection.prepareStatement(sql);
	}
	
	/**
	 * Legt fest, ob der AutoCommit ausgeführt werden soll oder nicht.
	 * <b>true</b>, der AutoCommit soll ausgeführt werden. <b>false</b>, der
	 * AutoCommit soll nicht ausgeführt werden.
	 * 
	 * @param autoCommit Soll der AutoCommit ausgeführt werden?
	 * 
	 * @throws SQLException
	 */
	public void setAutoCommit(boolean autoCommit)
		throws SQLException
	{
		_connection.setAutoCommit(autoCommit);
	}
	
	/**
	 * Gibt die Klasse mit den Abfragen zurück.
	 */
	public static Queries queries() {
		return Queries.getInstance();
	}
	
	/**
	 * Erzeugt die Status-Nachricht, wenn ein Datenbank-Fehler aufgetreten ist.
	 * 
	 * @return Status-Nachricht, bei Datenbank-Fehler
	 */
	public static String statusDbError() {
		return "Fehler beim Zugriff auf die Datenbank.";
	}
	
	/**
	 * Gibt den Namen der Datenbank zurück
	 * 
	 * @return Name der Datenbank
	 */
	public String getDatabaseName() {
		return _dbName;
	}
	
	/**
	 * Bereitet die Datenbank vor. Das heißt es werden die Tabellen erzeugt
	 * und die Standard-Einträge in die Tabellen geschrieben.
	 * 
	 * @throws SQLException 
	 */
	public void prepaireDatabase() throws SQLException {
		// Verbindung zur Datenbank herstellen
		Statement stm = createStatement();
		
		// Tabelle für die einzelnen Kategorien
		stm.executeUpdate(queries().category().createTable());
		
		// Überprüfen ob standard Kategorien schon eingetragen wurden
		ResultSet rs = stm.executeQuery(queries().category().count());
		if (rs.getInt(1) == 0)
		{
			// Kategorien anlegen
			PreparedStatement ps = prepareStatement(queries().category().insert(false));
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
			setAutoCommit(false);
			ps.executeBatch();
			setAutoCommit(true);
		}
		rs.close();
		StatusBar.getInstance().setMessage("Datenbank: Tabelle der Kategorien ist fertig vorbereitet");
		
		// Tabelle für die Geschäfte
		stm.executeUpdate(queries().section().createTable());
		
		// Überprüfen ob die Einträge für die Standart Geschäfte schon
		// enthalten sind
		rs = stm.executeQuery(queries().section().count());
		if (rs.getInt(1) == 0) {
			// Geschäfte anlegen
			PreparedStatement ps = prepareStatement(queries().section().insert(false));
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
			setAutoCommit(false);
			ps.executeBatch();
			setAutoCommit(true);
		}
		rs.close();
		StatusBar.getInstance().setMessage("Datenbank: Tabelle der Geschäfte ist fertig vorbereitet.");
		
		// Tabelle für die Zahlungsmittel
		stm.executeUpdate(queries().payment().createTable());
		
		// Überprüfen ob die Einträge für die Standart Zahlungsmittels schon
		// enthalten sind
		rs = stm.executeQuery(queries().payment().count());
		if (rs.getInt(1) == 0) {
			// Geschäfte anlegen
			PreparedStatement ps = prepareStatement(queries().payment().insert(false));
			ps.setString(1, "Keine Angabe");
			ps.addBatch();
			ps.setString(1, "Bar");
			ps.addBatch();
			ps.setString(1, "Ec-Karte");
			ps.addBatch();
			ps.setString(1, "Überweisung");
			ps.addBatch();
			ps.setString(1, "Paypal");
			ps.addBatch();
			ps.setString(1, "Kredit-Karte");
			ps.addBatch();
			
			// Geschäfte in die Datenbank schreiben
			setAutoCommit(false);
			ps.executeBatch();
			setAutoCommit(true);
		}
		rs.close();
		StatusBar.getInstance().setMessage("Datenbank: Tabelle der Zahlungsmittel ist fertig vorbereitet.");
		
		// Tabelle für die Ein- und Ausgaben
		stm.executeUpdate(queries().money().createTable());
		StatusBar.getInstance().setMessage("Datenbank: Tabelle der Ein- und Ausgaben ist fertig vorbereitet");
		
		// Tabelle für die Details zu den Ein- und Ausgaben
		stm.executeUpdate(queries().moneyDetails().createTable());
		StatusBar.getInstance().setMessage("Datenbank: Tabelle der Details für Ein- und Ausgaben ist fertig vorbereitet.");
		
		// Erweiterung für die Details zu den Ein- und Ausgaben
		if (queries().moneyDetails().upgrade1())
			StatusBar.getInstance().setMessage("Datenbank: Tabelle der Details für Ein- und Ausgaben erweitert (Erweiterung 1)");
	}
}
