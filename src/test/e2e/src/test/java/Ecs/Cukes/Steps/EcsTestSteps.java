package Ecs.Cukes.Steps;

import Ecs.DependancyInjection.StepInjection;
import Pages.EcsHomePage;
import Pages.PageObjectMapper;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.ArrayList;

/**
 * Created by lloydasamoah on 28/05/2018.
 */
public class EcsTestSteps {

    StepInjection stepInjection;
    EcsHomePage.WelcomeSection welcomeSection;
    EcsHomePage.ChallengeSection challengeSection;
    ArrayList<Object> challengeAnswers;

    public EcsTestSteps(StepInjection stepInjection){
        this.stepInjection = stepInjection;
        welcomeSection = stepInjection.pageObjectMapper.getEcsHomePage_WelcomeSection();
        challengeSection = stepInjection.pageObjectMapper.getEcsHomePage_ChallengeSection();

    }

    @And("^I am on the Welcome Section of the ECS Test Homepage$")
    public void iAmOnTheWelcomSectionOfTheECSTestHomepage() throws Throwable {
        welcomeSection.welcomeSectionExists();
    }

    @And("^I navigate to the Challenge Section of the ECS Test Homepage$")
    public void iNavigateToTheChallengeSectionOfTheECSTestHomepage() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        welcomeSection.clickRenderChallengeButton();
        challengeSection.ChallengeSectionExists();
    }

    @And("^I execute the challenge on the Challenge Section$")
    public void iExecuteTheChallengeOnTheChallengeSection() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        challengeAnswers = challengeSection.executeChallenge();
    }



    @When("^I submit my answers along with my name (.*)$")
    public void iSubmitMyAnswersAlongWithMyNameLloyd(String name) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        challengeSection.enterTextIntoSubmitChallenge1Field(challengeAnswers.get(0).toString());
        challengeSection.enterTextIntoSubmitChallenge2Field(challengeAnswers.get(1).toString());
        challengeSection.enterTextIntoSubmitChallenge3Field(challengeAnswers.get(2).toString());
        challengeSection.enterTextIntoYourNameField(name);
        challengeSection.submitAnswers();
    }

    @Then("^a successful message is Displayed$")
    public void aSuccessfulMessageIsDisplayed() throws Throwable {
        challengeSection.assertSuccessfullMessageIsDisplayed();

    }
}
