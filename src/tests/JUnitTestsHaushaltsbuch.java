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

package tests;

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
	tests.tests.comparators.TestCompDouble.class,
	tests.tests.comparators.TestCompId.class,
	tests.tests.comparators.TestCompInt.class,
	tests.tests.datas.TestData.class,
	tests.tests.datas.TestLogData.class,
	tests.tests.datas.TestIdNameData.class,
	tests.tests.datas.TestMoneyData.class,
	tests.tests.datas.TestMoneyDetailsData.class,
	tests.tests.datas.TestReportPreferenceData.class,
	tests.tests.datas.TestReportData.class,
	tests.tests.datas.TestReportWeekData.class,
	tests.tests.datas.TestReportMonthData.class,
	tests.tests.datas.TestReportYearData.class,
	
	tests.tests.db.query.TestQueryInterface.class,
	tests.tests.db.query.TestQuery.class,
	tests.tests.db.query.TestQueries.class,
	tests.tests.db.query.TestCategory.class,
	tests.tests.db.query.TestSection.class,
	tests.tests.db.query.TestMoney.class,
	tests.tests.db.query.TestMoneyDetails.class,
	tests.tests.db.TestDbController.class,
	
	tests.tests.elements.TestStatusBar.class,
	
	tests.tests.helper.TestHelperCalendar.class,
	
	tests.tests.menus.TestPopupStandardList.class,
	tests.tests.menus.TestPopupMoneyList.class,
	
	tests.tests.renderer.TestLogViewListRenderer.class,
	
	tests.tests.tables.models.TestIdNameListModel.class,
	tests.tests.tables.models.TestMoneyDetailsListModel.class,
	tests.tests.tables.models.TestMoneyListModel.class,
	tests.tests.tables.models.TestReportWeekModel.class,
	tests.tests.tables.models.TestReportMonthModel.class,
	tests.tests.tables.models.TestReportYearModel.class
})
public class JUnitTestsHaushaltsbuch {
}
