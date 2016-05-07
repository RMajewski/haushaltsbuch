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

package haushaltsbuch.tables.models;

import java.text.DecimalFormat;

import haushaltsbuch.datas.ReportCategoryData;
import haushaltsbuch.datas.ReportData;


/**
 * Zeigt die erhoben Daten der Übersicht der Kategorien in der Tabelle an.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class ReportCategoryModel extends ReportModel {

	/**
	 * Serilisation ID
	 */
	private static final long serialVersionUID = -1257636130235009L;

	/**
	 * Initalisiert die Daten
	 * 
	 * @param data
	 */
	public ReportCategoryModel(ReportData data) {
		super(data);
	}

	/**
	 * Gibt die Anzahl der Spalten zurück.
	 * 
	 * @return Anzahl der Spalten
	 */
	@Override
	public int getColumnCount() {
		return _data.getColumnCount();
	}

	/**
	 * Gibt die Anzahl der Zeilen zurück.
	 * 
	 * @return Anzahl der Zeilen.
	 */
	@Override
	public int getRowCount() {
		return _data.getRowCount();
	}

	/**
	 * Gibt den Inhalt der angegeben Zelle zurück.
	 * 
	 * @param col Zeile, in der die Zelle liegt
	 * 
	 * @param row Spalte, in der die Zelle liegt.
	 * 
	 * @return Inhalt der angegeben Zelle
	 */
	@Override
	public Object getValueAt(int col, int row) {
		switch (row) {
			// Kategorie
			case 0:
				return ((ReportCategoryData)_data).getCategory(col);
				
			// Einnahmen
			case 1:
				return new DecimalFormat("0.00").format(_data.incoming(col));
				
			// Ausgaben
			case 2:
				return new DecimalFormat("0.00").format(_data.outgoing(col));
				
			// Differenz
			case 3:
				return new DecimalFormat("0.00").format(_data.deviation(col));
		}
	
		// Standard-Rückgabe
		return null;
	}

}
