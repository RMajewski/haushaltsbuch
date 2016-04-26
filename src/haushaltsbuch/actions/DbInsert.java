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

package haushaltsbuch.actions;

import java.awt.event.ActionEvent;

import javax.swing.JDesktopPane;

/**
 * Stellt die Aktion dar, die vom obersten Datenbank-Fenster veranlasst, dass
 * sich das Fenster zum einfügen eines neuen Datensatzes öffnet.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 02.
 */
public class DbInsert extends Action {
	
	/**
	 * Speichert das Kommando der Aktiion.
	 */
	public static final String COMMAND = "DatabaseInsert";

	/**
	 * Serilisation ID
	 */
	private static final long serialVersionUID = 4873661764814196549L;

	/**
	 * Initalisiert die Aktion
	 * 
	 * @param desktop Desktop des Hauptfensters
	 */
	public DbInsert(JDesktopPane desktop) {
		super("insert_small.png", "insert_big.png", desktop);
		
		putValue(Action.NAME, "Neu");
		putValue(Action.MNEMONIC_KEY, 1);
		putValue(Action.ACTION_COMMAND_KEY, COMMAND);
	}

	/**
	 * Ermittelt das oberste Fenster und ruft dort die Funktion "dbNew" auf.
	 * 
	 * @param ae Daten des Ereignisses
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
	}

}
