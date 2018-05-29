package Pages;

import Driver.DriverController;

/**
 * Created by lloydasamoah on 28/05/2018.
 */
public class PageObjectMapper {

    private DriverController driverController;
    private EcsHomePage.WelcomeSection EcsHomePage_WelcomeSection;
    private EcsHomePage.ChallengeSection EcsHomePage_ChallengeSection;


    public PageObjectMapper(DriverController driverController){
        this.driverController = driverController;
        this.EcsHomePage_WelcomeSection = new EcsHomePage.WelcomeSection(driverController.driver);
        this.EcsHomePage_ChallengeSection = new EcsHomePage.ChallengeSection(driverController.driver);
    }

    public EcsHomePage.WelcomeSection getEcsHomePage_WelcomeSection() {
        return EcsHomePage_WelcomeSection;
    }

    public EcsHomePage.ChallengeSection getEcsHomePage_ChallengeSection(){
        return EcsHomePage_ChallengeSection;
    }



}
