package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features=".//Features/AddNewCustomer.feature",
		glue="stepdefinitionfile",
		dryRun=false,
		monochrome=true,
		publish=true,
		tags="@regression"
		
		
		)


public class TestRunner {

}
