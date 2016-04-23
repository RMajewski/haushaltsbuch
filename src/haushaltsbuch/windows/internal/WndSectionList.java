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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.RowSorter.SortKey;
import javax.swing.SortOrder;
import javax.swing.table.TableRowSorter;

import haushaltsbuch.comparators.CompInt;
import haushaltsbuch.datas.IdNameData;
import haushaltsbuch.db.DbController;
import haushaltsbuch.elements.StatusBar;
import haushaltsbuch.menus.PopupStandardList;
import haushaltsbuch.tables.models.IdNameListModel;

/**
 * In diesen Unterfenster werden die einzelnen Geschäfte angezeigt.
 * 
 * @author René Majewski
 * 
 * @version 0.1
 * @since 0.1
 */
public class WndSectionList extends WndTableFrame {

	/**
	 * Serilisation ID
	 */
	private static final long serialVersionUID = -6848374575961659809L;
	
	/**
	 * Initalisiert den Dialog und die Tabelle. Anschließend wird der Dialog
	 * angezeigt.
	 */
	public WndSectionList() {
		// Dialog initalisieren
		super();
		
		// Title
		setTitle("Geschäfte");
		
		// Tabelle initalisieren
		IdNameListModel model = new IdNameListModel(DbController.queries().section().select()); 
		initTable(model);
		
		// Namen der Tabellen-Spalten
		_table.getColumnModel().getColumn(0).setHeaderValue("ID");
		_table.getColumnModel().getColumn(1).setHeaderValue("Geschäft");

		// Sorter initalisieren
		TableRowSorter<IdNameListModel> sorter = 
				new TableRowSorter<IdNameListModel>(model);
		sorter.setSortsOnUpdates(true);
		
		// Zusätzliche Comparatoren setzen
		sorter.setComparator(0, new CompInt());
		
		// Welche Liste soll beim Start sortiert sein?
		List<SortKey> sk = new ArrayList<SortKey>();
		sk.add(new SortKey(0, SortOrder.ASCENDING));
		sorter.setSortKeys(sk);
		
		// Sorter in Tabelle einfügen
		_table.setRowSorter(sorter);

		// Anzeigen
		pack();
		setVisible(true);
	}

	
	/**
	 * Reagiert auf die einzelnen Einträge im PopupMenu.
	 * 
	 * @param ae Event-Daten
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		IdNameListModel model = (IdNameListModel)_table.getModel();

		try {
			Statement stm = DbController.getInstance().createStatement();
			
			// Welcher Popup-Menü-Punkt wurde ausgewählt?
			switch (ae.getActionCommand()) {
				// Neu
				case PopupStandardList.NEW:
					String nc = JOptionPane.showInputDialog(this, "Neues Geschäft", "Geschäft erstellen", JOptionPane.OK_CANCEL_OPTION);
					if (nc != null && !nc.isEmpty()) {
						if (stm.executeUpdate(DbController.queries().section().insert(nc)) > 0) {
							StatusBar.getInstance().setMessageAsOk(DbController.queries().section().statusInsertOk());
						} else {
							StatusBar.getInstance().setMessageAsError(DbController.queries().section().statusInsertError());
						}

						// Tabelle neu zeichnen
						model.dataRefresh(true);
					}
					break;
					
				// Löschen
				case PopupStandardList.DELETE:
					if (_table.getSelectedRow() >= 0)
						delete(model.getRowDataAt(_table.getSelectedRow()).getId(), DbController.queries().section());
					break;
					
				// Ändern
				case PopupStandardList.CHANGE:
					tableRowDoubleClick();
			}
		} catch (SQLException e) {
			StatusBar.getInstance().setMessageAsError(DbController.statusDbError());
			e.printStackTrace();
		}
	}

	/**
	 * Wird aufgerufen, wenn auf eine Tabellen-Zeile doppelt geklickt wurde.
	 * 
	 * Es wird das Fenster zum Ändern des Datensatzes geöffnet.
	 */
	@Override
	protected void tableRowDoubleClick() {
		if (_table.getSelectedRow() >= 0) {
			try {
				// Daten ermitteln
				IdNameData data = ((IdNameListModel)_table.getModel()).getRowDataAt(_table.getSelectedRow());
				
				// Geschäft ändern
				String cc = JOptionPane.showInputDialog(this, "Neuer Name", "Geschäft ändern", JOptionPane.OK_CANCEL_OPTION);
				if ((cc != null) && !cc.isEmpty() && (cc.compareTo(data.getName()) != 0)) {
					Statement stm = DbController.getInstance().createStatement();
					if (stm.executeUpdate(DbController.queries().section().update(data.getId(), cc)) > 0) {
					}
						StatusBar.getInstance().setMessageAsOk(DbController.queries().section().statusUpdateOk(data.getId()));
					} else {
						StatusBar.getInstance().setMessageAsError(DbController.queries().section().statusUpdateError(data.getId()));
					}
	
					// Tabelle neu zeichnen
					((IdNameListModel)_table.getModel()).dataRefresh(true);
				} catch (SQLException e) {
					StatusBar.getInstance().setMessageAsError(DbController.statusDbError());
					e.printStackTrace();
				}
			}
	}

}