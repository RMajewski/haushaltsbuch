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

package dialogs;

import javax.swing.JDialog;

/**
 * Zeigt den Dialog an, um die Einstellungen für die Reports vorzunehmen.
 * 
 * @author René Majewski
 */
public class DlgReport extends JDialog {

	/**
	 * Serilisation ID
	 */
	private static final long serialVersionUID = -6290197426436360379L;
	
	/**
	 * Report-Erstellung wurde abgebrochen
	 */
	public static final int CANCLE = 1;
	
	/**
	 * Es soll die Wochenübersicht erstellt werden
	 */
	public static final int WEEK = 2;
	
	/**
	 * Es soll die Monatsübersicht erstellt werden
	 */
	public static final int MONTH = 3;
	
	/**
	 * Es soll die Jahresübersicht erstellt werden
	 */
	public static final int YEAR = 4;
	
	/**
	 * Initalisiert das Report-Fenster
	 * 
	 * @param report Welcher Report soll voreingestellt sein?
	 */
	public DlgReport(int report) {
		// Klasse initalisieren
		super();
		
		// setTitle
		setTitle("Einstellungen um Report zu erzeugen");
		
		// Größe
		setSize(600, 400);
		
		// modal
		setModal(true);
		
		// Anzeigen
		setVisible(true);
		
	}

}
