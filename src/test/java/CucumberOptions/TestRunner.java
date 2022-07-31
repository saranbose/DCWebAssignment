package CucumberOptions;

//import org.junit.runner.RunWith;

//import io.cucumber.junit.Cucumber;
//import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/java/feature",
		glue="stepDefinitions",
				plugin = {
		                "summary",
		                "pretty",
		                "html:target/cucumber-reports/cucumber-pretty.html",
		                "json:target/cucumber-reports/CucumberTestReport.json"
		        }
		)
public class TestRunner extends AbstractTestNGCucumberTests{

}