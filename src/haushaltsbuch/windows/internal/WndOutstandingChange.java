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

package haushaltsbuch.windows.internal;

import java.awt.event.ActionEvent;

import haushaltsbuch.datas.Data;
import haushaltsbuch.elements.Desktop;

/**
 * Zeigt das Fenster an, um einen neuen Datensatz anzulegen oder einen
 * Datensa zu ändern in der Datenbank-Tabelle 'outstanding'.
 * 
 * @author René Majewski
 * 
 * @version 0.1
 * @since 0.4
 */
public class WndOutstandingChange extends WndChangeFrame  {

	/**
	 * Initalisiert das Fenster.
	 * 
	 * @param desktop Desktop des Hauptfensters
	 * 
	 * @param data Datensatz, der geändert werden soll. Wird <b>null</b>
	 * angegeben, so wird ein neuer Datensatz erstellt.
	 * 
	 * @param frame Fenster, aus dem dieses Fenster aufgerufen wurde.
	 */
	public WndOutstandingChange(Desktop desktop, Data data, WndTableFrame frame) {
		super(desktop, data, frame);
		
		// Fenster anzeigen und Fokus holen
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		// Da nich beendet, diese Methode in der Vater-Klasse aufrufen
		super.actionPerformed(ae);
	}

}
