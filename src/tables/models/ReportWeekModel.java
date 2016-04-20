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

package tables.models;

import javax.swing.table.AbstractTableModel;

import datas.ReportWeekData;

/**
 * Zeigt die erhoben Daten in der Tabelle an.
 * 
 * @author René Majewski
 *
 * @since 0.2
 * @version 0.1
 */
public class ReportWeekModel extends AbstractTableModel {

	/**
	 * Seriliastion ID
	 */
	private static final long serialVersionUID = -6288084097390790965L;
	
	/**
	 * Speichert die Daten
	 */
	private ReportWeekData _data;
	
	/**
	 * Initalisiert die Klasse und die Daten
	 */
	public ReportWeekModel(ReportWeekData data) {
		super();
		_data = data;
	}

	/**
	 * Gibt die Anzahl der Spalten zurück
	 * 
	 * @return Anzahl der Spalten
	 */
	@Override
	public int getColumnCount() {
		return _data.getColumnCount();
	}

	/**
	 * Gibt die Anzahl der Zeilen zurück
	 */
	@Override
	public int getRowCount() {
		return _data.getWeekCount();
	}

	/**
	 * Gibt den Inhalt der Zelle wieder
	 * 
	 * @param col Zeile in der die Zelle ist.
	 * 
	 * @param row Spalte in der die Zelle ist.
	 */
	@Override
	public Object getValueAt(int row, int col) {
		switch (col) {
			// Wochennummer
			case 0:
				return _data.getWeekNumber(row);
				
			// 2. Spalte
			case 1:
				// 'von' aus gewählt
				if (_data.drawDateFrom())
					return _data.getDateFrom(row);
				// 'bis' ausgewählt, aber 'von' nicht
				if (!_data.drawDateFrom() && _data.drawDateTo())
					return _data.getDateTo(row);
				
				// 'von' und 'bis' nicht ausgewählt
				return _data.incoming(row);
				
			// 3. Spalte
			case 2:
				// 'bis' ausgewählt und 'von' ausgewählt
				if (_data.drawDateTo() && _data.drawDateFrom())
					return _data.getDateTo(row);
				
				if (_data.drawDateFrom() || _data.drawDateTo())
					return _data.incoming(row);
				
				// 'von' und 'bis' nicht ausgewählt
				return _data.outgoing(row);
				
			// 4. Spalte
			case 3:
				// 'von' und 'bis' ausgewählt
				if (_data.drawDateFrom() && _data.drawDateTo())
					return _data.incoming(row);
				
				// 'von' oder 'bis' ausgewählt
				if (_data.drawDateFrom() || _data.drawDateTo())
					return _data.outgoing(row);
				
				// 'von' und 'bis' nicht ausgewählt
				return _data.deviation(row);
				
			// 5. Spalte
			case 4:
				// 'von' und 'bis' ausgewählt
				if (_data.drawDateFrom() && _data.drawDateTo())
					return _data.outgoing(row);
				
				// 'von' oder 'bis' ausgewählt
				if (_data.drawDateFrom() || _data.drawDateTo())
					return _data.deviation(row);
				
				return null;
				
			// 6. Spalte
			case 5:
				// 'von' und 'bis' ausgewählt
				if (_data.drawDateFrom() && _data.drawDateTo())
					return _data.deviation(row);
				
				return null;
		}
		
		// Standard Rückgabe
		return null;
	}

}
