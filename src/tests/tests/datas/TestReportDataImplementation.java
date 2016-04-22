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

package tests.tests.datas;

import java.util.List;

import javax.swing.table.TableColumnModel;

import haushaltsbuch.datas.ReportData;
import haushaltsbuch.datas.ReportPreferencesData;

/**
 * Beinhaltet eine Implementation der Klasse {@link datas.ReportData}, um die
 * dort implementierten Methoden zu testen.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class TestReportDataImplementation extends ReportData {

	public TestReportDataImplementation(ReportPreferencesData preferences) {
		super(preferences);
	}

	@Override
	public int getColumnCount() {
		return 0;
	}

	@Override
	public void setColumnHeader(TableColumnModel tcm) {
		
	}

	/**
	 * Zum testen, ob die protected-Methode richtig arbeitet.
	 */
	@Override
	public String getDateAsString(long date) {
		return super.getDateAsString(date);
	}
	
	/**
	 * Zum testen, ob die protected-Methode richtig arbeitet.
	 */
	@Override
	public List<Double> initDoubleList(int count) {
		return super.initDoubleList(count);
	}

	@Override
	public int getRowCount() {
		return 0;
	}
}
