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

package haushaltsbuch.datas;

/**
 * Speichert die Daten eines Datensatzes der Tabelle 'money_details'
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.1
 */
public class MoneyDetailsData extends Data {
	
	/**
	 * Speichert die ID des zugehörigen Money-Datensatzes
	 */
	private int _moneyId;
	
	/**
	 * Speichert die ID der Kategorie
	 */
	private int _categoryId;
	
	/**
	 * Speichert die ID des Geschäftes
	 */
	private int _sectionId;
	
	/**
	 * Speichert das Geld
	 */
	private double _money;
	
	/**
	 * Speichert die Beschreibung
	 */
	private String _comment;
	
	/**
	 * Initalisiert die ID mit -1, das Geld mit 0.00 und eine leere
	 * Zeichenkette als Beschreibung
	 */
	public MoneyDetailsData() {
		super(-1);
		_moneyId = -1;
		_categoryId = -1;
		_sectionId = -1;
		setComment(null);
	}

	/**
	 * Initalisiert die ID des Datensatzen mit der übergebenen ID, die
	 * IDs für den dazugehörigenen Money-Datensatz, der Kategorie und des
	 * Geschäftes werden auf -1 gesetzt. Das Geld wird auf 0.00 gesetzt. Als
	 * Beschreibung wird eine leere Zeichenkette gespeichert.
	 * 
	 * @param id ID des Datensatzes
	 */
	public MoneyDetailsData(int id) {
		this();
		_id = id;
	}

	/**
	 * Initalisiert die Daten mit übergebenen Werten.
	 * 
	 * @param id ID des Datensatzes
	 * 
	 * @param moneyId ID des dazugehörigen Money-Datensatzes
	 * 
	 * @param categoryId ID der Kategorie
	 * 
	 * @param sectionId ID des Geschäftes
	 * 
	 * @param money Geld, welches ausgegeben oder eingenommen wurde.
	 * 
	 * @param comment Beschreibung des Datensatzes
	 */
	public MoneyDetailsData(int id, int moneyId, int categoryId, int sectionId, double money, String comment) {
		super(id);
		_moneyId = moneyId;
		_categoryId = categoryId;
		_sectionId = sectionId;
		_money = money;
		setComment(comment);
	}

	/**
	 * Gibt die gespeicherte ID der Kategorie zurück.
	 * 
	 * @return ID der Kategorie
	 */
	public int getCategoryId() {
		return _categoryId;
	}

	/**
	 * Speichert die neue ID der Kategorie.
	 * 
	 * @param categoryId Neue ID der Kategory
	 */
	public void setCategoryId(int categoryId) {
		_categoryId = categoryId;
	}

	/**
	 * Gibt die ID des Geschäftes zurück.
	 * 
	 * @return ID des Geschäftes
	 */
	public int getSectionId() {
		return _sectionId;
	}

	/**
	 * speichert die neue ID des Geschäftes.
	 * 
	 * @param sectionId Neue ID des Geschäftes
	 */
	public void setSectionId(int sectionId) {
		_sectionId = sectionId;
	}

	/**
	 * Gibt die ID des dazu gehörigen Money-Datensatzes zurück.
	 * 
	 * @return ID des dazugehörigen Money-Datensatzes
	 */
	public int getMoneyId() {
		return _moneyId;
	}

	/**
	 * Speichert die ID des neuen Money-Datensatzes
	 * 
	 * @param moneyId ID des neuen Money-Datensatzes
	 */
	public void setMoneyId(int moneyId) {
		_moneyId = moneyId;
	}

	/**
	 * Gibt das gespeicherte Geld zurück.
	 * 
	 * @return Gespeichertes Geld
	 */
	public double getMoney() {
		return _money;
	}

	/**
	 * Speichert den neuen Geld-Wert
	 * 
	 * @param money Neuer Geld-Wert
	 */
	public void setMoney(double money) {
		_money = money;
	}

	/**
	 * Gibt die gespeicherte Beschreibung zurück.
	 * 
	 * @return Gespeicherte Beschreibung
	 */
	public String getComment() {
		return _comment;
	}

	/**
	 * Speichert die neue Beschreibung.
	 * 
	 * @param comment Neue Beschreibung
	 */
	public void setComment(String comment) {
		if (comment == null)
			_comment = new String();
		else
			_comment = comment;
	}

}
