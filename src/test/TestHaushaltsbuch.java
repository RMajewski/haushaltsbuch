package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	test.datas.TestLogData.class,
	test.datas.TestCategoryData.class,
	test.datas.TestSectionData.class,
	
	test.db.query.TestQueryInterface.class,
	test.db.query.TestQuery.class,
	test.db.query.TestCategory.class,
	test.db.query.TestSection.class
})
public class TestHaushaltsbuch {

}
