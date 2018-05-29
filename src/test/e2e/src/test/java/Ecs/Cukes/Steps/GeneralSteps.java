package Ecs.Cukes.Steps;

import Driver.Browser;
import Driver.DriverController;
import Ecs.DependancyInjection.StepInjection;
import Pages.PageObjectMapper;
import cucumber.api.java.en.Given;
import cucumber.runtime.CucumberException;

/**
 * Created by lloydasamoah on 28/05/2018.
 */
public class GeneralSteps {
    StepInjection stepInjection;


    public GeneralSteps(StepInjection stepInjection){
        this.stepInjection = stepInjection;
    }


    @Given("^I am using the (.*) browser$")
    public void iAmUsingTheBrowserBrowser(String browser) throws Throwable {
        switch (browser.toLowerCase()){
            case "chrome":
            case "google chrome":
                stepInjection.driverController = new DriverController(Browser.CHROME);
                break;
            case "firefox":
                stepInjection.driverController = new DriverController(Browser.FIREFOX);
                break;
            default:
                throw new CucumberException("This browser: "+browser+" is not handled!");

        }
        stepInjection.driverController.start();
        stepInjection.pageObjectMapper = new PageObjectMapper(stepInjection.driverController);

    }
}
