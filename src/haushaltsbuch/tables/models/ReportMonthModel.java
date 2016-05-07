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

import haushaltsbuch.datas.ReportMonthData;

/**
 * Zeigt die erhoben Daten der Monatsübersicht in der Tabelle an.
 * 
 * In Version 0.2 werden die Daten im {@link ReportModel} gespeichert.
 * 
 * @author René Majewski
 *
 * @version 0.2
 * @since 0.2
 */
public class ReportMonthModel extends ReportModel {

	/**
	 * Serilisation ID
	 */
	private static final long serialVersionUID = -3177528619056754348L;
	
	/**
	 * Initalisiert diese Klasse.
	 * 
	 * @param preferences
	 */
	public ReportMonthModel(ReportMonthData data) {
		// Initalisieren den Daten
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
			// Tag
			case 0:
				return ((ReportMonthData)_data).getDay(col);
				
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
