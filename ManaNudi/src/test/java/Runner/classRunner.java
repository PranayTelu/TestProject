package Runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/manaNudi.feature/manaNudi.feature",glue = {"stepDefinition"},dryRun = false)

public class classRunner {

}
