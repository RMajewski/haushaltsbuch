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

import datas.MoneyData;
import db.DbController;
import menus.PopupMoneyList;
import tables.models.MoneyListModel;

/**
 * In diesem Fenster werden die einzelnen Money-Datensätze angezeigt.
 * 
 * @author René Majewski
 * 
 * @version 0.1
 * @since 0.1
 */
public class WndMoneyList extends WndTableFrame {

	/**
	 * Serilisation ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Initalisiert das Fenster
	 */
	public WndMoneyList() {
		// Fenster initalisieren
		super();

		// Titel
		setTitle("Einnahmen und Ausgaben");
		
		// Popup-Menü initalisieren
		_popup = new PopupMoneyList(this);
		
		// Tabelle initalisieren
		initTable(new MoneyListModel());
		
		// Namen der Tabellen-Spalten
		_table.getColumnModel().getColumn(0).setHeaderValue("ID");
		_table.getColumnModel().getColumn(1).setHeaderValue("Datum");
		_table.getColumnModel().getColumn(2).setHeaderValue("Was?");
		_table.getColumnModel().getColumn(3).setHeaderValue("Gesamt Betrag");
		_table.getColumnModel().getColumn(4).setHeaderValue("Beschreibung");
		
		// Details auf nicht Benutzbar setzen
		setPopupItemEnable(false);
		
		// Fenster anzeigen
		pack();
		setVisible(true);
	}
	
	/**
	 * Erzeugt das Fenster mit den Details und gibt ihm den Fokus.
	 */
	@Override
	protected void tableRowDoubleClick() {
		if (_table.getSelectedRow() > -1) {
			MoneyData data = ((MoneyListModel)_table.getModel()).getRowDataAt(_table.getSelectedRow());
			newWindow(new WndMoneyDetailsList(data));
		}
	}
	
	/**
	 * Setzt die Einträge Ändern und Löschen des Stanard-Popup-Menü auf
	 * benutzbar (true) oder nicht benutzbar (false).
	 * 
	 * @param enable Standard-Popup-Einträge benutzbar?
	 */
	@Override
	protected void setPopupItemEnable(boolean enable) {
		super.setPopupItemEnable(enable);
		((PopupMoneyList)_popup).setMenuItemEnable(PopupMoneyList.VISIBLE_DETAILS, enable);
	}

	/**
	 * Reagiert auf die einzelnen Einträge im Popup-Menü
	 * 
	 * param ae Event-Daten
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		// Welcher Menü-Eintrag wurde gedrückt?
		switch (ae.getActionCommand()) {
			// Neuen Eintrag erstellen
			case PopupMoneyList.NEW:
				newWindow(new WndMoneyChange(null, this));
				break;
				
			// Einen Eintrag ändern
			case PopupMoneyList.CHANGE:
				// Wurde ein Datensatz ausgewählt?
				if (_table.getSelectedRow() > -1) {
					// Datensatz ermitteln
					MoneyData data = ((MoneyListModel)_table.getModel()).getRowDataAt(_table.getSelectedRow());
					
					// Fenster zum ändern der Daten anzeigen
					newWindow(new WndMoneyChange(data, this));
				}
				break;
				
			// Einen Eintrag löschen
			case PopupMoneyList.DELETE:
				// Wurde ein Datensatz ausgewählt?
				if (_table.getSelectedRow() > -1)
					delete(((MoneyListModel)_table.getModel()).getRowDataAt(_table.getSelectedRow()).getId(), DbController.queries().money());
				break;
				
			// Details anzeigen
			case PopupMoneyList.DETAILS:
				tableRowDoubleClick();
				break;
		}
	}
}
