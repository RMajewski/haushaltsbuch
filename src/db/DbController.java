package db;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import db.query.Queries;

/**
 * Stellt die Verbindung zur Datenbank bereit.
 * 
 * @author René Majewski
 *
 */
public class DbController {
	/**
	 * Kontroller zur Datenbank
	 */
	private static final DbController controller = new DbController();
	
	/**
	 * Verbindung zur Datenbank
	 */
	private static Connection connection;
	
	static {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			System.err.println("Fehler beim Laden des JDBC-Treibers");
			e.printStackTrace();
		}
	}
	
	/**
	 * Contructor
	 */
	private DbController() {
		initConnection();
	}
	
	/**
	 * Gibt die Instanz des Controllers zurück.
	 * 
	 * @return Instanz des Controllers
	 */
	public static DbController getInstance() {
		return controller;
	}
	
	/**
	 * Initalisiert die Verbindung zur Datenbank.
	 */
	private void initConnection() {
		// Überprüfen ob das Verzeichnis existiert
		String path = System.getProperty("user.home") + "/.majewski/haushaltsbuch";
		File file = new File(path);
		if (!file.exists())
		{
			file.mkdirs();
		}
		
		// Verbindung zur Datenbank herstellen
		try {
			// Wurde schon eine Verbindung hergestellt?
			if (connection != null)
				return;
			
			System.out.print("Verbindung zur Datenbank ");
			
			// Überprüfen ob das Verzeichnis existiert
			
			
			// Verbindung zur Datenbank herstellen
			path += "/haushaltsbuch.sqlite";
			connection = DriverManager.getConnection("jdbc:sqlite:" + path);
			if (!connection.isClosed()) {
				System.out.println("wurde hergestellt.");
				System.out.println("\tName der Datenbank: " + path);
			} else
				System.out.println("konnte nicht hergestellt werden.");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				try {
					if (!connection.isClosed() && connection != null) {
						connection.close();
						
						if (connection.isClosed())
							System.out.println("Verbindung zur Datenbank wurde beendet.");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
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
		return connection;
	}
	
	/**
	 * Überprüft ob eine Verbindung zur Datenbank besteht.
	 * 
	 * @return Besteht eine Verbindung zur Datenbank?
	 */
	public boolean isConnection()
		throws SQLException
	{
		if (connection == null)
			return false;
		
		if (!connection.isClosed())
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
		return connection.createStatement();
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
		return connection.prepareStatement(sql);
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
		connection.setAutoCommit(autoCommit);
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
}
