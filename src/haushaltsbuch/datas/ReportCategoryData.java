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

package haushaltsbuch.datas;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.TableColumnModel;

import haushaltsbuch.db.DbController;
import haushaltsbuch.elements.StatusBar;

/**
 * Speichert die Daten, die im Report Übersicht der Kategorien angezeigt werden
 * sollen.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class ReportCategoryData extends ReportData {
	/**
	 * Speichert die Namen der Kategorien
	 */
	private List<String> _categories;

	/**
	 * Initalisiert die Daten
	 * 
	 * @param preferences Einstellungen für den Report
	 */
	public ReportCategoryData(ReportPreferencesData preferences) {
		super(preferences);
		_categories = new ArrayList<String>();
		setPreferences(preferences);
	}

	/**
	 * Gibt die Anzahl der Spalten zurück.
	 * 
	 * @return Anzahl der Spalten
	 */
	@Override
	public int getColumnCount() {
		return 4;
	}


	/**
	 * Gibt die Anzahl der Zeilen zurück.
	 * 
	 * @return Anzahl der Zeilen
	 */
	@Override
	public int getRowCount() {
		return _categories.size();
	}

	/**
	 * Setzt die einzelnen Spalten-Überschriften
	 * 
	 * @param tcm Model für die Kopfzeilen
	 */
	@Override
	public void setColumnHeader(TableColumnModel tcm) {
		tcm.getColumn(0).setHeaderValue("Kategorie");
		tcm.getColumn(1).setHeaderValue("Einnahmen");
		tcm.getColumn(2).setHeaderValue("Ausgaben");
		tcm.getColumn(3).setHeaderValue("Differenz");
	}

	/**
	 * Gibt die angegebene Kategorie zurück.
	 * 
	 * @param index Kategorie, die zurück gegeben werden soll.
	 * 
	 * @return Angegebene Kategorie.
	 */
	public String getCategory(int index) {
		return _categories.get(index);
	}
	
	/**
	 * Speichert die Einstellungen und liest die Daten ein.
	 */
	@Override
	public void setPreferences(ReportPreferencesData data) {
		// Daten speichern
		super.setPreferences(data);
		
		try {
			// Listen vorbereiten
			ResultSet rs = 
					DbController.getInstance().createStatement().executeQuery(
							DbController.queries().category().count());
			_in = initDoubleList(rs.getInt(1));
			_out = initDoubleList(rs.getInt(1));
			
			// Counter initalisieren
			int count = 0;
			
			rs = DbController.getInstance().createStatement().executeQuery(
						DbController.queries().category().sort("name"));
			while (rs.next()) {
				String name = rs.getString("name");
				_categories.add(name);
				
				// Einnahmen
				ResultSet sum = 
					DbController.getInstance().createStatement().executeQuery(
							DbController.queries().moneyDetails().sumCategoryId(
									name, MoneyData.INT_INCOMING));
				_in.set(count, sum.getDouble(1));
				
				// Ausgaben
				sum = DbController.getInstance().createStatement().executeQuery(
						DbController.queries().moneyDetails().sumCategoryId(
								name, MoneyData.INT_OUTGOING));
				_out.set(count, sum.getDouble(1));
				
				// Counter erhöhen
				count++;
			}
		} catch (SQLException e) {
			StatusBar.getInstance().setMessage(DbController.statusDbError(), e);
		}
	}
}
