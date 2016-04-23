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

package tests.fixtures.dialogs;

import tests.tests.dialogs.TestDlgLogView;

/**
 * Implementiert die Aktionen für DlgLogView.fit.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class FixtureDlgLogView extends FixtureDialogs {
	/**
	 * Initalisiert dieses Fixture
	 * 
	 * @throws Exception
	 */
	public FixtureDlgLogView() throws Exception{
		_test = new TestDlgLogView();
	}

	/**
	 * Überprüft, ob eine Liste besteht
	 */
	public String haveList() {
		return String.valueOf(((TestDlgLogView)_test).haveList());
	}
}
