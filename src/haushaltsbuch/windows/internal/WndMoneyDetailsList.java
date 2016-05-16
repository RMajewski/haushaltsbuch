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

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.RowSorter.SortKey;
import javax.swing.SortOrder;
import javax.swing.table.TableRowSorter;

import haushaltsbuch.comparators.CompDouble;
import haushaltsbuch.comparators.CompInt;
import haushaltsbuch.comparators.CompSum;
import haushaltsbuch.datas.MoneyData;
import haushaltsbuch.datas.MoneyDetailsData;
import haushaltsbuch.db.DbController;
import haushaltsbuch.elements.Desktop;
import haushaltsbuch.menus.PopupStandardList;
import haushaltsbuch.tables.models.MoneyDetailsListModel;

/**
 * In diesem Fenster wird zum angegeben Datensatz aus der Tabelle 'money' die
 * Detail-Datensätze als Liste angezeigt.
 * 
 * @author René Majewski
 * 
 * @version 0.1
 * @since 0.1
 */
public class WndMoneyDetailsList extends WndTableFrame {

	/**
	 * Serilisation ID
	 */
	private static final long serialVersionUID = 8514536537238375532L;
	
	/**
	 * Speichert den Datensatz aus der Tabelle 'money'
	 */
	private MoneyData _money;

	/**
	 * Initalisiert das Fenster und speichert die Daten des angegebenen
	 * Money-Datensatzes.
	 * 
	 * @param desktop Dekstop des Hauptfensters.
	 * 
	 * @param money Zugehöriger Money-Datensatz
	 */
	public WndMoneyDetailsList(Desktop desktop, MoneyData money) {
		// Klasse initalisiern
		super(desktop);
		
		// Datsatz aus 'money' speichern
		_money = money;
		
		// Titel des Fensters
		setTitle("Details zur " + _money.getInOutAsString() + " vom " +
				_money.getDateAsString());
		
		// Größe
		setSize(1000, 400);
		setMinimumSize(new Dimension(1000, 400));
		
		// Tabelle initalisieren
		MoneyDetailsListModel model = new MoneyDetailsListModel(_money.getId());
		initTable(model);
		
		// Namen der Tabellen-Spalten
		_table.getColumnModel().getColumn(0).setHeaderValue("ID");
		_table.getColumnModel().getColumn(1).setHeaderValue("Kategorie");
		_table.getColumnModel().getColumn(2).setHeaderValue("Geschäft");
		_table.getColumnModel().getColumn(3).setHeaderValue("Betrag");
		_table.getColumnModel().getColumn(4).setHeaderValue("Beschreibung");

		// Sorter initalisieren
		TableRowSorter<MoneyDetailsListModel> sorter = 
				new TableRowSorter<MoneyDetailsListModel>(model);
		sorter.setSortsOnUpdates(true);
		
		// Zusätzliche Comparatoren setzen
		sorter.setComparator(0, new CompInt());
		sorter.setComparator(3, new CompSum());
		
		// Welche Liste soll beim Start sortiert sein?
		List<SortKey> sk = new ArrayList<SortKey>();
		sk.add(new SortKey(0, SortOrder.ASCENDING));
		sorter.setSortKeys(sk);
		
		// Sorter in Tabelle einfügen
		_table.setRowSorter(sorter);
		
		// Fenster anzeigen
		//pack();
		setVisible(true);
	}

	/**
	 * Reagiert auf die einzelnen Einträge im Popup-Menü
	 * 
	 * @param ae Event-Daten
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		// Was soll ausgeführt werden?
		switch(ae.getActionCommand()) {
			// Neu
			case PopupStandardList.NEW:
				insert();
				break;
				
			// Ändern
			case PopupStandardList.CHANGE:
				tableRowDoubleClick();
				break;
				
			// Löschen
			case PopupStandardList.DELETE:
				delete();
				break;
		}
	}
	
	/**
	 * Gibt die Tabelle zurück.
	 * 
	 * @return Tabelle
	 */
	@Override
	public JTable getTable() {
		return _table;
	}

	/**
	 * Wird aufgerufen, wenn auf eine Tabellen-Zeile doppelt geklickt wurde.
	 */
	@Override
	protected void tableRowDoubleClick() {
		if (_table.getSelectedRow() > -1)
			newWindow(new WndMoneyDetailsChange(_desktop, 
					((MoneyDetailsListModel)_table.getModel()).getRowDataAt(
							_table.getRowSorter().convertRowIndexToModel(
									_table.getSelectedRow())), this));
	}

	/**
	 * Wird auf gerufen, wenn ein neuer Datensatz eingefügt werden soll.
	 */
	@Override
	public void insert() {
		MoneyDetailsData data = new MoneyDetailsData(-1);
		data.setMoneyId(_money.getId());
		newWindow(new WndMoneyDetailsChange(_desktop, data, this));
	}

	/**
	 * Wird aufgerufen, wenn ein selektierter Datensatz geändert werden soll.
	 */
	@Override
	public void change() {
		tableRowDoubleClick();
	}

	/**
	 * Wird aufgerufen, wenn ein selektierter Datensatz gelöscht werden soll.
	 */
	@Override
	public void delete() {
		// Wurde ein Datensatz ausgewählt?
		if (_table.getSelectedRow() > -1)
			delete(((MoneyDetailsListModel)_table.getModel()).getRowDataAt(
					_table.getRowSorter().convertRowIndexToModel(
							_table.getSelectedRow())).getId(), 
					DbController.queries().moneyDetails());
	}
}
