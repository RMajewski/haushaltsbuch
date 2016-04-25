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

import javax.swing.table.AbstractTableModel;

import haushaltsbuch.datas.ReportYearData;

/**
 * Zeigt die erhoben Daten der Jahresübersicht in der Tabelle an.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class ReportYearModel extends AbstractTableModel {

	/**
	 * Serilisation ID
	 */
	private static final long serialVersionUID = 3659761143959889153L;
	
	/**
	 * Speichert die Daten
	 */
	private ReportYearData _data;
	
	/**
	 * Initalisiert dieses Model
	 * 
	 * @param data Daten, die angezeigt werden sollen
	 */
	public ReportYearModel(ReportYearData data) {
		_data = data;
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
	 * @return Anzahl der Zeilen
	 */
	@Override
	public int getRowCount() {
		return _data.getRowCount();
	}

	/**
	 * Ermittelt den Inhalt der angegeben Zelle und gibt diesen zurück.
	 * 
	 * @param row Zeile, in der die Zelle sich befindet.
	 * 
	 * @param col Spalte, in der die Zelle sich befindet.
	 * 
	 * @return Inhalt der angegeben Zelle.
	 */
	@Override
	public Object getValueAt(int row, int col) {
		switch (col) {
			// Monats-Name
			case 0:
				return _data.getMonthName(row);
				
			// Einnahmen
			case 1:
				return new DecimalFormat("0.00").format(_data.incoming(row));
				
			// Ausgaben
			case 2:
				return new DecimalFormat("0.00").format(_data.outgoing(row));
				
			// Differenz
			case 3:
				return new DecimalFormat("0.00").format(_data.deviation(row));
		}
		
		// Standart-Rückgabe-Wert
		return null;
	}

}
