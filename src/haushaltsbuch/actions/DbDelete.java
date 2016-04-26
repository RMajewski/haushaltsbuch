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
 * Implementiert die Aktion, die beim obersten Datenbankfenster veranlasst, dass
 * der selektierte Datensatz gelösch t wird.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class DbDelete extends Action {

	/**
	 * Speichert das Kommando dieser Aktion.
	 */
	public static final String COMMAND = "DatabaseDelete";
	
	/**
	 * Initalisiert die Aktion.
	 * 
	 * @param desktop Desktop des Hauptfensters.
	 */
	public DbDelete(JDesktopPane desktop) {
		super("delete_small.png", "delete_big.png", desktop);

		putValue(Action.NAME, "Löschen");
		putValue(Action.MNEMONIC_KEY, 1);
		putValue(Action.ACTION_COMMAND_KEY, COMMAND);
	}

	/**
	 * Führt die Aktion aus
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
