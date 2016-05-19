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

import java.awt.Window;
import java.awt.event.ActionEvent;

import haushaltsbuch.elements.Desktop;

/**
 * Implementiert die Action, die ein offenes Fenster ausdruckt.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.3
 */
public class Print extends Action {

	/**
	 * Serialisation ID
	 */
	private static final long serialVersionUID = 5173502947676166666L;
	
	/**
	 * Speichert das Kommando, um ein Fenster auszudrucken.
	 */
	public static final String COMMAND = "Print";
	
	/**
	 * Speichert, welches Fenster dieses Fenster aufgerufen hat.
	 */
	private Window _owner;

	/**
	 * Initialisiert die Aktion.
	 * 
	 * @param desktop Desktop des Hauptfensters
	 * 
	 * @param owner Zu diesem Fenster gehört die ToolBar
	 */
	public Print(Desktop desktop, Window owner) {
		super("printer_small.png", "printer_big.png", desktop);
		_owner = owner;
	}

	/**
	 * Ruft zuerst den Drucker-Dialog auf. Wurde hier ein Drucker ausgewählt,
	 * so wird im PrinterJob veranlasst, dass das Fenster ausgedruckt wird.
	 * 
	 * @param e Daten des Eventes
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
	}

}
