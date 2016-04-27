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

import javax.swing.JDesktopPane;

import haushaltsbuch.datas.ReportPreferencesData;
import haushaltsbuch.elements.Desktop;
import haushaltsbuch.windows.internal.WndReports;

/**
 * Implementiert die Aktion, die ein Report öffnet.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class Report extends Action {
	/**
	 * Speichert das Kommando, um das Report-Fenster zu öffnen.
	 */
	public static final String COMMAND = "Report";
	
	/**
	 * Speichert, welches Fenster dieses Fenster aufgerufen hat.
	 */
	private Window _owner;

	/**
	 * Initalisiert die Aktion.
	 * 
	 * @param desktop Desktop des Hauptfensters
	 * 
	 * @param owner Zu diesem Fenster gehört die ToolBar
	 */
	public Report(Desktop desktop, Window owner) {
		super("report_small.png", "report_big.png", desktop);
		
		_owner = owner;

		putValue(Action.NAME, "Report");
		putValue(Action.MNEMONIC_KEY, 1);
		putValue(Action.ACTION_COMMAND_KEY, COMMAND);
	}
	
	/**
	 * Erzeugt ein einen neuen Report.
	 * 
	 * @param e Daten des Eventes
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		_desktop.add(new WndReports(null, ReportPreferencesData.TYPE_MONTH,
				_owner));
	}

}
