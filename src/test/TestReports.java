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

package test;

import static org.mockito.Mockito.*;

import datas.ReportPreferencesData;

/**
 * Implementiert Methoden, die bei den Report-Tests gebraucht wird.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class TestReports extends TestHelper {

	/**
	 * Speichert die Mock-Klasse für die Einstellungen
	 */
	protected ReportPreferencesData _rpd;
	
	/**
	 * Speichert das Jahr
	 */
	protected int _year;
	
	/**
	 * Speichert den Monat.
	 */
	protected int _month;
	
	/**
	 * Speichert den Typ
	 */
	protected int _type;
	
	/**
	 * Initalisiert das Mock-Objekt für die Einstellungen
	 */
	protected void initPreferences() {
		_rpd = mock(ReportPreferencesData.class);
		when(_rpd.getYear()).thenReturn(_year);
		when(_rpd.getMonth()).thenReturn(_month);
		when(_rpd.getType()).thenReturn(_type);
	}
}
