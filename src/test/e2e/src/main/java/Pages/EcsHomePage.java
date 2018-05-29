package Pages;

import Settings.Settings;
import Utility.ActionUtils;
import Utility.StringUtils;
import Utility.WaitUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by lloydasamoah on 28/05/2018.
 */
public class EcsHomePage {

    public static class WelcomeSection{

        WebDriver driver;
        String baseURL;

        public WelcomeSection(WebDriver driver) {
            this.driver = driver;
            this.baseURL = Settings.getDomainURL();
            PageFactory.initElements(driver, this);
        }

        @FindBy(how = How.ID, using = "WelcomeSectionLogo")
        private WebElement logo;

        @FindBy(how = How.ID, using = "WecomeSectionTitle")
        private WebElement title;

        @FindBy(how = How.ID, using = "WelcomeSectionIntro")
        private WebElement intro;

        @FindBy(how = How.ID, using = "renderChallengeButton")
        private WebElement renderChallengeButton;


        public void welcomeSectionExists(){
            ActionUtils.navigateToUrl(driver,baseURL);
            WaitUtils.waitForPage(driver,logo,title,intro,renderChallengeButton);
        }

        public void clickRenderChallengeButton(){
            ActionUtils.click(driver,renderChallengeButton);
        }

    }


    public static class ChallengeSection{
        WebDriver driver;
        String baseURL;

        public ChallengeSection(WebDriver driver) {
            this.driver = driver;
            this.baseURL = Settings.getDomainURL();
            PageFactory.initElements(driver, this);
        }

        @FindBy(how = How.ID, using = "ChallengeSectionTitle")
        private WebElement title;

        @FindBy(how = How.ID, using = "ArrayTable")
        private WebElement table;

        @FindBy(how = How.NAME, using = "submitField1")
        private WebElement submitChallenge1Field;

        @FindBy(how = How.NAME, using = "submitField2")
        private WebElement submitChallenge2Field;

        @FindBy(how = How.NAME, using = "submitField3")
        private WebElement submitChallenge3Field;

        @FindBy(how = How.NAME, using = "yourNameField")
        private WebElement yourNameField;

        @FindBy(how = How.NAME, using = "SubmitAnswersButton")
        private WebElement submitAnswerButton;

        @FindBy(how = How.XPATH, using = "/html/body/div[2]/div/div[1]/div/div/div[1]")
        private WebElement dialogueBox;


        public void ChallengeSectionExists(){
            WaitUtils.waitForPage(driver,title,table);
        }


        public void enterTextIntoSubmitChallenge1Field(String challengeAnswer1){
            ActionUtils.enterTextInField(driver,submitChallenge1Field,challengeAnswer1);

        }

        public void enterTextIntoSubmitChallenge2Field(String challengeAnswer2){
            ActionUtils.enterTextInField(driver,submitChallenge2Field,challengeAnswer2);

        }

        public void enterTextIntoSubmitChallenge3Field(String challengeAnswer3){
            ActionUtils.enterTextInField(driver,submitChallenge3Field,challengeAnswer3);

        }

        public void enterTextIntoYourNameField(String name){
            ActionUtils.enterTextInField(driver,yourNameField,name);
        }

        public void submitAnswers(){
            ActionUtils.click(driver,submitAnswerButton);
        }

        public void assertSuccessfullMessageIsDisplayed(){
            try {
                Thread.sleep(750);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ActionUtils.Assert(dialogueBox,"Congratulations you have suceeded. Please submit your challenge ✅");
        }


        public ArrayList<Object> executeChallenge(){

            int noOfRowsInTable = driver.findElements(By.className("TableRows")).size();

             ArrayList<ArrayList<Integer>> arrayContainingArraysFromRows = new ArrayList<>();

            for(int i=1; i<= noOfRowsInTable; i++ ){
               String nameOfKey = StringUtils.append("Array"+Integer.toString(i));
               List<WebElement>  listofElementsInRow = driver.findElements(By.id(StringUtils.append("row",Integer.toString(i))));
               ArrayList<Integer> listOfValuesFromRow = new ArrayList<>();
               for(int j=0; j<listofElementsInRow.size(); j++){
                   listOfValuesFromRow.add(Integer.parseInt(listofElementsInRow.get(j).getText()));
               }

               arrayContainingArraysFromRows.add(listOfValuesFromRow);
            }

            ArrayList<Object> challengeAnswers = new ArrayList<>();

            for(int i=0; i<arrayContainingArraysFromRows.size();i++){
                challengeAnswers.add(findIndexofArray(arrayContainingArraysFromRows.get(i)));
            }


            Iterator challengeAnswerIterator = challengeAnswers.iterator();

            while(challengeAnswerIterator.hasNext()){
                challengeAnswerIterator.next().toString();
            }

            Collections.replaceAll(challengeAnswers,0,"null");

            return challengeAnswers;


        }

        private int findIndexofArray(ArrayList<Integer> arrayList) {

            int challengeAnswer=0;

            for(int i=1; i<(arrayList.size()-1);i++){
                int sumOfSubListBeforeIndex;
                int sumOfSubListAfterIndex;

                if(i==1){
                    sumOfSubListBeforeIndex = arrayList.get(0);
                 }else{
                    List<Integer> subListBeforeIndex = arrayList.subList(0,i);
                    sumOfSubListBeforeIndex = subListBeforeIndex.stream().mapToInt(Integer::intValue).sum();
                }


                if(i == arrayList.size()-2){
                    sumOfSubListAfterIndex = arrayList.get(arrayList.size()-1);
                }else {
                    List<Integer> subListAfterIndex = arrayList.subList(i + 1, arrayList.size());
                    sumOfSubListAfterIndex = subListAfterIndex.stream().mapToInt(Integer::intValue).sum();
                }


                if(sumOfSubListBeforeIndex == sumOfSubListAfterIndex){
                     challengeAnswer = i;
                    break;

                }

            }
          return challengeAnswer;
        }


    }


}
