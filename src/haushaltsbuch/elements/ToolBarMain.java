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
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import haushaltsbuch.actions.DbChange;
import haushaltsbuch.actions.DbDelete;
import haushaltsbuch.actions.DbInsert;
import haushaltsbuch.actions.Report;
import haushaltsbuch.events.ToolBarDbElementEvent;
import haushaltsbuch.listener.ToolBarDbElementListener;

public class ToolBarMain extends JToolBar 
	implements InternalFrameListener, ToolBarDbElementListener {

	/**
	 * Serilisation ID
	 */
	private static final long serialVersionUID = 2160111024076691110L;
	
	/**
	 * Speichert den Button zum einfügen eines neuen Datensatzes
	 */
	private DbInsert _insert;
	
	/**
	 * Speichert den Button zum ändern eines Datensatzes
	 */
	private DbChange _change;
	
	/**
	 * Speichert den Button zum löschen eines Datensatzes
	 */
	private DbDelete _delete;

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
		_insert = new DbInsert(desktop);
		add(_insert);

		_change = new DbChange(desktop);
		add(_change);

		_delete = new DbDelete(desktop);
		add(_delete);
		
		setDbEnable(false);
		
		addSeparator();
		add(new Report(desktop, owner));
	}
	
	/**
	 * Änder die Benutzbarkeit der Buttons zum Einfügen einen Neuen Datensatzes,
	 * zum Ändern einen Datensatzes und zum Löschen eines Datensatzes.
	 * 
	 * @param enable Können die Buttons benutzt werden?
	 */
	private void setDbEnable(boolean enable) {
		_insert.setEnabled(enable);
		_change.setEnabled(enable);
		_delete.setEnabled(enable);
	}

	@Override
	public void internalFrameActivated(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameClosed(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameClosing(InternalFrameEvent e) {
	}

	@Override
	public void internalFrameDeactivated(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameDeiconified(InternalFrameEvent e) {
	}

	@Override
	public void internalFrameIconified(InternalFrameEvent e) {
	}

	@Override
	public void internalFrameOpened(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Wird ausgelöst, wenn im Datenbank-Fenster eine Zeile selektiert wurde
	 * oder die Selektion aufgehoben wurde.
	 * 
	 * @param e Ereignis-Daten.
	 */
	@Override
	public void setDbElementsEnable(ToolBarDbElementEvent e) {
		_change.setEnabled(e.isEnable());
		_delete.setEnabled(e.isEnable());
	}
}
