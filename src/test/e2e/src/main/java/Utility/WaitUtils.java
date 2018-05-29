package Utility;

import Settings.Settings;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by lloydasamoah on 28/05/2018.
 */
public class WaitUtils {

    private static WebDriverWait wait;
    private static int defaultWaitTime = Settings.getDefaultWaitTime();
    private static int defaultLongWaitTime =  Settings.getDefaultLongWaitTime();

    public static void waitForPage(WebDriver driver, WebElement...webElements){
        for (int i=0; i < webElements.length; i++){
            wait = new WebDriverWait(driver,defaultWaitTime);
            wait.until(ExpectedConditions.visibilityOf(webElements[i]));
            LogUtils.Log( StringUtils.append(webElements[i].toString()," has been located"));
        }
    }

    public static void waitForPage(WebDriver driver,int waitTime, WebElement...webElements){
        for (int i=0; i < webElements.length; i++){
            wait = new WebDriverWait(driver,waitTime);
            wait.until(ExpectedConditions.visibilityOf(webElements[i]));
            LogUtils.Log( webElements[i] + " has been located");
        }
    }


}
