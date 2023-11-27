package testRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions
		(
		        features = {"Features"},
		        glue = {"pages", "steps"},
		        plugin = {"pretty", "html:target/cucumber-reports.html"},
		        tags = "@tag122"
		)
public class TestRun {
}
