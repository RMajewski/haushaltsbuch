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

import haushaltsbuch.datas.Data;

/**
 * In diesem Interface werden Methoden deklariert, die auf den Zugriff auf
 * die Datenbank gewährleisten.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.1
 */
public interface DbModelInterface {
	/**
	 * Liest alle Datensätze ein und Speichert diese Daten in einer Liste.
	 * 
	 * @param repaint Soll die Tabelle neu gezeichnet werden, so muss
	 * <b>true</b> übergeben werden. Wenn nicht, dann <b>false</b>
	 */
	public void dataRefresh(boolean repaint);

	/**
	 * Gibt die Daten der angegeben Zeile zurück. Wurde als ID <b>-1</b>
	 * übergeben, so wird ein leerer Datensatz zurück gegeben.
	 * 
	 * @return Datensatz der angegeben Zeile.
	 */
	public Data getRowDataAt(int row);
}
