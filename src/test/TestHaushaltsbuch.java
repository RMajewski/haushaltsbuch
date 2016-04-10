package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
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
	test.db.query.TestMoneyDetails.class
})
public class TestHaushaltsbuch {

}
