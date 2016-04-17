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

package windows.internal;

import dialogs.DlgReport;

/**
 * Zeigt die einzelnen Reports an.
 * 
 * @author René Majewski
 */
public class WndReports extends WndInternalFrame {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = -8914036564878348162L;
	
	/**
	 * Initalisiert das Fenster
	 * 
	 * @param report Welcher Report soll erstellt werden?
	 */
	public WndReports(int report) {
		// Klasse initalisieren
		super();
		
		// Titel setzen
		setTitle("Monatsübersicht");
		
		// Fenster anzeigen
		setVisible(true);
		
		// Einstellungen aufrufen
		new DlgReport(report);
	}
}
