package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * 
 * a test suite to run all tests from different classes. to add a test class add
 * "nameOfClass.class" into the @Suite.SuiteClasses({....} region, a comma is
 * needed at the end of each class (like in a list). now we can run all tests
 * class at once with ease
 * 
 * @author Michael
 * 
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({ 
	CardTests.class,
	MoveTests.class,
// classtests.class,

})
public class TestSuite {
}
