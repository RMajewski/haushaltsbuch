package db.query;

/**
 * In dieser Klasse werden alle Klassen zusammengefasst, die Datenbank-Abfragen
 * enthalten.
 * 
 * @author René Majewski
 *
 */
public class Query {
	/**
	 * Beinhaltet alle Abfragen für die Tabelle mit den Kategorien
	 */
	public static final Category category = new Category();
	
	/**
	 * Beinhaltet alle Abfragen für die Tabelle mit den einzelnen Geschäften
	 */
	public static final Section section = new Section();

	/**
	 * Beinhaltet alle Abfragen für die Tabelle zu den Details der Ein- und
	 * Ausgaben.
	 */
	public static final MoneyDetails moneyDetails = new MoneyDetails();

	/**
	 * Beinhaltet alle Anfragen für die Tabelle der Ein- und Ausgaben.
	 */
	public static final Money money = new Money();
}
