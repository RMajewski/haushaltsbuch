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

package haushaltsbuch.elements;

import java.awt.Window;

import javax.swing.JDesktopPane;
import javax.swing.JToolBar;

import haushaltsbuch.actions.DbChange;
import haushaltsbuch.actions.DbDelete;
import haushaltsbuch.actions.DbInsert;
import haushaltsbuch.actions.Report;

public class ToolBarMain extends JToolBar {

	/**
	 * Serilisation ID
	 */
	private static final long serialVersionUID = 2160111024076691110L;

	/**
	 * Initalisiert die ToolBar
	 * 
	 * @param desktop Desktop des Hauptfensters.
	 * 
	 * @param owner Zu diesem Fenster gehört die ToolBar
	 */
	public ToolBarMain(JDesktopPane desktop, Window owner) {
		// Toolbar initalisieren
		super();
		
		// Name
		setName("MainToolBar");
		
		// Neu, Ändern und Löschen
		add(new DbInsert(desktop));
		add(new DbChange(desktop));
		add(new DbDelete(desktop));
		addSeparator();
		add(new Report(desktop, owner));
	}
}
