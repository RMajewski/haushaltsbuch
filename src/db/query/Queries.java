package db.query;

/**
 * In dieser Klasse werden alle Klassen zusammengefasst, die Datenbank-Abfragen
 * enthalten.
 * 
 * @author René Majewski
 *
 */
public class Queries {
	/**
	 * Beinhaltet alle Abfragen für die Tabelle mit den Kategorien
	 */
	private Category _category;
	
	/**
	 * Beinhaltet alle Abfragen für die Tabelle mit den einzelnen Geschäften
	 */
	private Section _section;

	/**
	 * Beinhaltet alle Abfragen für die Tabelle zu den Details der Ein- und
	 * Ausgaben.
	 */
	private MoneyDetails _moneyDetails;

	/**
	 * Beinhaltet alle Anfragen für die Tabelle der Ein- und Ausgaben.
	 */
	private Money _money;
	
	/**
	 * Speichert die Instanz dieser Klasse
	 */
	private static final Queries _query = new Queries();
	
	/**
	 * Initalisiert die einzelnen Abfragen-Klassen
	 */
	public Queries() {
		_category = new Category();
		_section = new Section();
		_moneyDetails = new MoneyDetails();
		_money = new Money();
	}
	
	/**
	 * Gibt die Instanz zurück
	 * 
	 * @return Instanz
	 */
	public static Queries getInstance() {
		return _query;
	}
	
	/**
	 * Gibt die Instanz der Datenbank-Abfragen für die Tabelle 'category'
	 * zurück.
	 * 
	 * @return Instanz der Datenbank-Abfrage für die Kategorien.
	 */
	public Category category() {
		return _category;
	}
	
	/**
	 * Gibt die Instanz der Datenbank-Abfragen für die Tabelle 'section'
	 * zurück.
	 * 
	 * @return Instanz der Datenbank-Abfrage für die Geschäft.
	 */
	public Section section() {
		return _section;
	}
	
	/**
	 * Gibt die Instanz der Datenbank-Abfragen für die Tabelle 'money'
	 * zurück.
	 * 
	 * @return Instanz der Datenbank-Abfrage für die Ein- und Ausgaben.
	 */
	public Money money() {
		return _money;
	}
	
	/**
	 * Gibt die Instanz der Datenbank-Abfragen für die Tabelle 'money_details'
	 * zurück.
	 * 
	 * @return Instanz der Datenbank-Abfrage für die Details zu den Ein- und
	 * Ausgaben.
	 */
	public MoneyDetails moneyDetails() {
		return _moneyDetails;
	}
}
