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

import javax.swing.JTable;

import datas.MoneyData;
import datas.MoneyDetailsData;
import db.DbController;
import menus.PopupStandardList;
import tables.models.MoneyDetailsListModel;

/**
 * In diesem Fenster wird zum angegeben Datensatz aus der Tabelle 'money' die
 * Detail-Datensätze als Liste angezeigt.
 * 
 * @author René Majewski
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
	 * @param money Zugehöriger Money-Datensatz
	 */
	public WndMoneyDetailsList(MoneyData money) {
		// Klasse initalisiern
		super();
		
		// Datsatz aus 'money' speichern
		_money = money;
		
		// Titel des Fensters
		setTitle("Details zur " + _money.getInOutAsString() + " vom " +
				_money.getDateAsString());
		
		// Größe
		setSize(1000, 400);
		
		// Tabelle initalisieren
		initTable(new MoneyDetailsListModel(_money.getId()));
		
		// Namen der Tabellen-Spalten
		_table.getColumnModel().getColumn(0).setHeaderValue("ID");
		_table.getColumnModel().getColumn(1).setHeaderValue("Kategorie");
		_table.getColumnModel().getColumn(2).setHeaderValue("Geschäft");
		_table.getColumnModel().getColumn(3).setHeaderValue("Betrag");
		_table.getColumnModel().getColumn(4).setHeaderValue("Beschreibung");
		
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
		// Datensatz ermitteln
		MoneyDetailsData data;
		if (_table.getSelectedRow() > -1) {
			data = ((MoneyDetailsListModel)_table.getModel()).getRowDataAt(_table.getSelectedRow());
		} else {
			data = new MoneyDetailsData(-1);
		}
		
		// Was soll ausgeführt werden?
		switch(ae.getActionCommand()) {
			// Neu
			case PopupStandardList.NEW:
				data = new MoneyDetailsData(-1);
				data.setMoneyId(_money.getId());
				newWindow(new WndMoneyDetailsChange(data, this));
				break;
				
			// Ändern
			case PopupStandardList.CHANGE:
				tableRowDoubleClick();
				break;
				
			// Löschen
			case PopupStandardList.DELETE:
				// Wurde ein Datensatz ausgewählt?
				if (_table.getSelectedRow() > -1)
					delete(((MoneyDetailsListModel)_table.getModel()).getRowDataAt(_table.getSelectedRow()).getId(), DbController.queries().moneyDetails());
				break;
		}
	}
	
	/**
	 * Gibt die Tabelle zurück.
	 * 
	 * @return Tabelle
	 */
	public JTable getTable() {
		return _table;
	}

	/**
	 * Wird aufgerufen, wenn auf eine Tabellen-Zeile doppelt geklickt wurde.
	 */
	@Override
	protected void tableRowDoubleClick() {
		if (_table.getSelectedRow() > -1)
			newWindow(new WndMoneyDetailsChange(((MoneyDetailsListModel)_table.getModel()).getRowDataAt(_table.getSelectedRow()), this));
	}
}
