package Utility;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.sql.Driver;

/**
 * Created by lloydasamoah on 28/05/2018.
 */
public class ActionUtils {

    public static void click(WebDriver driver, WebElement webElement){
        scrollToElement(webElement,driver);
        webElement.click();
        LogUtils.Log(StringUtils.append("Clicked on: ",webElement.toString()));
    }

    public static void enterTextInField(WebDriver driver, WebElement webElement, String text){
        scrollToElement(webElement,driver);
        webElement.sendKeys(text);
        LogUtils.Log(StringUtils.append("Entered the text: ",text," into the field: ",webElement.toString()));
    }

    public static void navigateToUrl(WebDriver driver, String url){
        driver.get(url);
        LogUtils.Log(StringUtils.append("Driver has navigated to: "+url));
    }

    public static void scrollToElement(WebElement webElement, WebDriver driver) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView(true);", webElement);
        jse.executeScript("window.scrollBy(0,-125)", "");
        LogUtils.Log(StringUtils.append("Scrolled to element: ", webElement.toString()));
    }

    public static void Assert(WebElement webElement, String expectedString){
        Assert.assertEquals(expectedString,webElement.getText());
    }

}
