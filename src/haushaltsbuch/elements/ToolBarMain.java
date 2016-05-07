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

import javax.swing.JToolBar;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import haushaltsbuch.actions.DbChange;
import haushaltsbuch.actions.DbDelete;
import haushaltsbuch.actions.DbInsert;
import haushaltsbuch.actions.PdfReport;
import haushaltsbuch.actions.Report;
import haushaltsbuch.events.ToolBarDbElementEvent;
import haushaltsbuch.listener.ToolBarDbElementListener;
import haushaltsbuch.windows.internal.WndInternalFrame;

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
	 * Speichert den Button zum Erzeugen eines PDF-Reportes
	 */
	private PdfReport _pdf;

	/**
	 * Initalisiert die ToolBar
	 * 
	 * @param desktop Desktop des Hauptfensters.
	 * 
	 * @param owner Zu diesem Fenster gehört die ToolBar
	 */
	public ToolBarMain(Desktop desktop, Window owner) {
		// Toolbar initalisieren
		super();
		
		// Name
		setName("MainToolBar");
		
		// Neu, Ändern und Löschen
		_insert = new DbInsert(desktop);
		_insert.setEnabled(false);
		add(_insert);

		_change = new DbChange(desktop);
		_change.setEnabled(false);
		add(_change);

		_delete = new DbDelete(desktop);
		_delete.setEnabled(false);
		add(_delete);
		
		addSeparator();
		add(new Report(desktop, owner));
		
		_pdf = new PdfReport(desktop);
		_pdf.setEnabled(false);
		add(_pdf);
	}

	@Override
	public void internalFrameActivated(InternalFrameEvent e) {
		if (((WndInternalFrame)e.getInternalFrame()).isEnableDbElements()) {
			_insert.setEnabled(true);
			_insert.setFrame((WndInternalFrame)e.getInternalFrame());
		} else {
			_insert.setEnabled(false);
			_insert.deleteFrame();
		}
		
		if (((WndInternalFrame)e.getInternalFrame()).isEnablePdfReport()) {
			_pdf.setEnabled(true);
			_pdf.setFrame((WndInternalFrame)e.getInternalFrame());
		}
	}

	@Override
	public void internalFrameClosed(InternalFrameEvent e) {
		_insert.setEnabled(false);
		_insert.deleteFrame();
		
		_change.setEnabled(false);
		_change.deleteFrame();
		
		_delete.setEnabled(false);
		_delete.deleteFrame();
		
		_pdf.setEnabled(false);
	}

	@Override
	public void internalFrameClosing(InternalFrameEvent e) {
	}

	@Override
	public void internalFrameDeactivated(InternalFrameEvent e) {
		_insert.setEnabled(false);
		_insert.deleteFrame();
		
		_change.setEnabled(false);
		_change.deleteFrame();
		
		_delete.setEnabled(false);
		_delete.deleteFrame();
		
		_pdf.setEnabled(false);
	}

	@Override
	public void internalFrameDeiconified(InternalFrameEvent e) {
	}

	@Override
	public void internalFrameIconified(InternalFrameEvent e) {
	}

	/**
	 * Wird aufgerufen, wenn ein Fenster geöffnet wurde.
	 */
	@Override
	public void internalFrameOpened(InternalFrameEvent e) {
		if (((WndInternalFrame)e.getInternalFrame()).isEnableDbElements()) {
			_insert.setEnabled(true);
			_insert.setFrame((WndInternalFrame)e.getInternalFrame());
		} else {
			_insert.setEnabled(false);
			_insert.deleteFrame();
		}
		
		if (((WndInternalFrame)e.getInternalFrame()).isEnablePdfReport()) {
			_pdf.setEnabled(true);
			_pdf.setFrame((WndInternalFrame)e.getInternalFrame());
		}
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
		if (e.isEnable()) {
			_change.setFrame(e.getFrame());
			_delete.setFrame(e.getFrame());
		} else {
			_change.deleteFrame();
			_delete.deleteFrame();
		}
	}
}
