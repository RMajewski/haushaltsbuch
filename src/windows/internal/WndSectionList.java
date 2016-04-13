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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import datas.IdNameData;
import db.DbController;
import elements.StatusBar;
import listener.PopupMenuMouseListener;
import menus.PopupStandardList;
import tables.models.IdNameListModel;

/**
 * In diesen Dialog werden die einzelnen Geschäfte angezeigt.
 * 
 * @author René Majewski
 */
public class WndSectionList extends WndTableFrame implements ActionListener {

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
		initTable(new IdNameListModel(DbController.queries().section().select()),
				new PopupStandardList(this));
		
		// Namen der Tabellen-Spalten
		_table.getColumnModel().getColumn(0).setHeaderValue("ID");
		_table.getColumnModel().getColumn(1).setHeaderValue("Geschäft");

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
					if (nc != null) {
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
					if (_table.getSelectedRow() >= 0) {
						// Daten ermitteln
						IdNameData data = model.getRowDataAt(_table.getSelectedRow());
						
						// Geschäft ändern
						String cc = JOptionPane.showInputDialog(this, "Neuer Name", "Geschäft ändern", JOptionPane.OK_CANCEL_OPTION);
						if ((cc != null) && (cc.compareTo(data.getName()) != 0)) {
							if (stm.executeUpdate(DbController.queries().section().update(data.getId(), cc)) > 0) {
								StatusBar.getInstance().setMessageAsOk(DbController.queries().section().statusUpdateOk(data.getId()));
							} else {
								StatusBar.getInstance().setMessageAsError(DbController.queries().section().statusUpdateError(data.getId()));
							}

							// Tabelle neu zeichnen
							model.dataRefresh(true);
						}
					}
			}
		} catch (SQLException e) {
			StatusBar.getInstance().setMessageAsError(DbController.statusDbError());
			e.printStackTrace();
		}
	}

}
