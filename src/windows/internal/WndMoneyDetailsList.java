package windows.internal;

import datas.MoneyData;

/**
 * In diesem Fenster wird zum angegeben Datensatz aus der Tabelle 'money' die
 * Detail-Datensätze als Liste angezeigt.
 * 
 * @author René Majewski
 */
public class WndMoneyDetailsList extends WndInternalFrame {

	/**
	 * Serilisation ID
	 */
	private static final long serialVersionUID = 8514536537238375532L;
	
	/**
	 * Speichert den Datensatz aus der Tabelle 'money'
	 */
	private MoneyData _money;

	/**
	 * Initalisiert das Fenster und speichert die Daten des angegebenen
	 * Money-Datensatzes.
	 * 
	 * @param money Zugehöriger Money-Datensatz
	 */
	public WndMoneyDetailsList(MoneyData money) {
		// Klasse initalisiern
		super();
		
		// Datsatz aus 'money' speichern
		_money = money;
		
		// Titel des Fensters
		setTitle("Details zur " + _money.getInOutAsString() + " vom " +
				_money.getDateAsString());
		
		// Größe
		setSize(600, 400);
		
		
	}
}
