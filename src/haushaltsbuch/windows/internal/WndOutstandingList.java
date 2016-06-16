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

import java.util.ArrayList;
import java.util.List;

import javax.swing.SortOrder;
import javax.swing.RowSorter.SortKey;
import javax.swing.table.TableRowSorter;

import haushaltsbuch.comparators.CompDateString;
import haushaltsbuch.comparators.CompInt;
import haushaltsbuch.comparators.CompSum;
import haushaltsbuch.datas.OutstandingData;
import haushaltsbuch.db.DbController;
import haushaltsbuch.elements.Desktop;
import haushaltsbuch.menus.PopupStandardList;
import haushaltsbuch.tables.models.OutstandingListModel;

/**
 * In diesem Fenster werden die Datensätze zu den Schulden angezeigt.
 * 
 * @author René Majewski
 * 
 * @version 0.1
 * @since 0.4
 */
public class WndOutstandingList extends WndTableFrame {
	/**
	 * Speichert den Titel des Fensters.
	 */
	public static final String WND_TITLE = "Schulden";

	/**
	 * Serilisation ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Initalisiert das Fenster
	 * 
	 * @param desktop Desktop des Hauptfensters
	 */
	public WndOutstandingList(Desktop desktop) {
		super(desktop);

		// Titel
		setTitle(WND_TITLE);
		
		// Popup-Menü initalisieren
		_popup = new PopupStandardList(desktop.getToolBar());
		
		// Tabelle initalisieren
		OutstandingListModel model = new OutstandingListModel();
		initTable(model);
		
		// Namen der Tabellen-Spalten
		_table.getColumnModel().getColumn(0).setHeaderValue("ID");
		_table.getColumnModel().getColumn(1).setHeaderValue("Bei wem?");
		_table.getColumnModel().getColumn(2).setHeaderValue("Schulden");
		_table.getColumnModel().getColumn(3).setHeaderValue("Anzahl Monate");
		_table.getColumnModel().getColumn(4).setHeaderValue("Start");
		_table.getColumnModel().getColumn(5).setHeaderValue("Monatliche Rate");
		_table.getColumnModel().getColumn(6).setHeaderValue("Beschreibung");

		// Sorter initalisieren
		TableRowSorter<OutstandingListModel> sorter = 
				new TableRowSorter<OutstandingListModel>(model);
		sorter.setSortsOnUpdates(true);
		
		// Zusätzliche Comparatoren setzen
		sorter.setComparator(0, new CompInt());
		sorter.setComparator(1, new CompDateString());
		sorter.setComparator(3, new CompSum());
		
		// Welche Liste soll beim Start sortiert sein?
		List<SortKey> sk = new ArrayList<SortKey>();
		sk.add(new SortKey(0, SortOrder.ASCENDING));
		sorter.setSortKeys(sk);
		
		// Sorter in Tabelle einfügen
		_table.setRowSorter(sorter);
		
		// Fenster anzeigen
		pack();
		setVisible(true);
	}

	@Override
	protected void tableRowDoubleClick() {
		change();
	}

	/**
	 * Wird aufgerufen, wenn ein Datensatz eingefügt werden soll.
	 */
	@Override
	public void insert() {
		newWindow(new WndOutstandingChange(_desktop, null, this));
	}

	/**
	 * Wird aufgerufen, wenn der selektierte Datensatz geändert werden soll
	 */
	@Override
	public void change() {
		// Wurde ein Datensatz ausgewählt?
		if (_table.getSelectedRow() > -1) {
			// Datensatz ermitteln
			OutstandingData data = 
					((OutstandingListModel)_table.getModel()).getRowDataAt(
					_table.getRowSorter().convertRowIndexToModel(
							_table.getSelectedRow()));
			
			// Fenster zum ändern der Daten anzeigen
			newWindow(new WndOutstandingChange(_desktop, data, this));
		}
	}

	/**
	 * Wird aufgerufen, wenn der selektierte Datensatz gelöscht werden soll.
	 */
	@Override
	public void delete() {
		// Wurde ein Datensatz ausgewählt?
		if (_table.getSelectedRow() > -1)
			delete(((OutstandingListModel)_table.getModel()).getRowDataAt(
					_table.getRowSorter().convertRowIndexToModel(
							_table.getSelectedRow())).getId(), 
					DbController.queries().outstanding());
	}

}
