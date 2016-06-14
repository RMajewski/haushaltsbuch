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

package haushaltsbuch.db.query;

/**
 * In dieser Klasse werden alle Klassen zusammengefasst, die Datenbank-Abfragen
 * enthalten.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.1
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
	 * Beinhaltet alle Abfragen für die Tabelle mit den einzelnen
	 * Zahlungsmitteln.
	 */
	private Payment _payment;
	
	/**
	 * Beinhaltet alle Abfragen für die Tabelle mit den Schulden.
	 */
	private Outstanding _outstanding;
	
	/**
	 * Beinhaltet alle Abfragen für die Tabelle mit den Rückzahlungen.
	 */
	private Repay _repay;

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
		_payment = new Payment();
		_outstanding = new Outstanding();
		_repay = new Repay();
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
	 * Gibt die Instanz der Datenbank-Abfragen für die Tabelle 'payment'
	 * zurück.
	 * 
	 * @return Instanz der Datenbank-Abfrage für die Zahlungsmittel.
	 */
	public Payment payment() {
		return _payment;
	}
	
	/**
	 * Gibt die Instanz der Datenbank-Abfragen für die Tabelle 'outstanding'
	 * zurück.
	 * 
	 * @return Instanz der Datenbank-Abfrage für die Schulden.
	 */
	public Outstanding outstanding() {
		return _outstanding;
	}
	
	/**
	 * Gibt die Instanz der Datenbank-Abfragen für die Tabelle 'repay'
	 * zurück.
	 * 
	 * @return Instanz der Datenbank-Abfrage für die Rückzahlungen.
	 */
	public Repay repay() {
		return _repay;
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
