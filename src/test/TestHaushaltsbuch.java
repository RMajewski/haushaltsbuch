package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	test.datas.TestLogData.class,
	test.datas.TestCategoryData.class,
	
	test.db.query.JQueryInterface.class,
	test.db.query.TestQuery.class
})
public class TestHaushaltsbuch {

}
