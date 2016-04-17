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

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Führt alle JUnite-Tests aus.
 * 
 * @author René Majewski
 * 
 * @version 0.1
 * @since 0.1
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
	test.datas.TestData.class,
	test.datas.TestLogData.class,
	test.datas.TestIdNameData.class,
	test.datas.TestMoneyData.class,
	test.datas.TestMoneyDetailsData.class,
	
	test.db.query.TestQueryInterface.class,
	test.db.query.TestQuery.class,
	test.db.query.TestQueries.class,
	test.db.query.TestCategory.class,
	test.db.query.TestSection.class,
	test.db.query.TestMoney.class,
	test.db.query.TestMoneyDetails.class,
	test.db.TestDbController.class,
	
	test.elements.TestStatusBar.class,
	
	test.menus.TestPopupStandardList.class,
	test.menus.TestPopupMoneyList.class,
	
	test.renderer.TestLogViewListRenderer.class,
	
	test.tables.models.TestIdNameListModel.class,
	test.tables.models.TestMoneyDetailsListModel.class,
	test.tables.models.TestMoneyListModel.class
})
public class JUnitTestsHaushaltsbuch {

}
