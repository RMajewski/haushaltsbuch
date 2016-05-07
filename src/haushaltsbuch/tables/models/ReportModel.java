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

import javax.swing.table.AbstractTableModel;

import haushaltsbuch.datas.ReportData;

/**
 * Von diesem Tabellen-Model werden die Modelle für die Reports abgeleitet.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public abstract class ReportModel extends AbstractTableModel {

	/**
	 * Serilisation ID
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	 * Speichert die Daten
	 */
	protected ReportData _data;
	
	/**
	 * Initalisiert diese Klasse.
	 * 
	 * @param data Daten, die angezeigt werden sollen.
	 */
	public ReportModel(ReportData data) {
		// Initalisieren den Daten
		super();
		
		// Speichern der Daten
		_data = data;
	}
	
	/**
	 * Gibt die Daten zurück
	 */
	public ReportData getData() {
		return _data;
	}
}
