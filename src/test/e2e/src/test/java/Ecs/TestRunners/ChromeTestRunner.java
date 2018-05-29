package Ecs.TestRunners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/features", glue="Ecs.Cukes", tags = "@Chrome", plugin = {"json:target/cucumber-report/ChromeTests.json"})


/**
 * Created by lloydasamoah on 28/05/2018.
 */
public class ChromeTestRunner {


}
