package Ecs.Cukes.Hooks;

import Ecs.DependancyInjection.StepInjection;
import Settings.Settings;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

/**
 * Created by lloydasamoah on 28/05/2018.
 */
public class GeneralHook {
    StepInjection stepInjection;

    public GeneralHook(StepInjection stepInjection){
        this.stepInjection = stepInjection;
    }

    @Before
    public void before(Scenario scenario){
        Settings.intialise();
    }

    @After
    public void after(Scenario scenario){

        stepInjection.driverController.stop();

    }
}
