package Driver;

import Settings.Settings;
import Utility.LogUtils;
import Utility.StringUtils;
import cucumber.runtime.CucumberException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by lloydasamoah on 28/05/2018.
 */
public class DriverController {

    public  Browser browser;
    public WebDriver driver;


    public DriverController (Browser browser){
        this.browser = browser;
    }

    public void start() throws MalformedURLException {
        //if the start method is called and their driver has not been closed then the open driver instance should be used
        if (driver != null) {
            LogUtils.Log(StringUtils.append("WARNING: a new driver has not been created as the existing driver: ",driver.toString()," is being used instead"));
            return;
        }

        //Creates the driver dependant on the browser used to create an instance of the driver controller
        switch(browser){
            case FIREFOX:
                System.setProperty("webdriver.gecko.driver",Settings.getGeckoDriverPath());
                DesiredCapabilities capabilities = DesiredCapabilities.firefox();
                capabilities.setCapability("marionette",true);
                driver = new FirefoxDriver(capabilities);
                break;
            case CHROME:
               System.setProperty("webdriver.chrome.driver",Settings.getChromeDriverPath());
               driver = new ChromeDriver();
                break;
                default:
                    throw new CucumberException(StringUtils.append(browser.name()," is not handled in the Driver Controller class"));
        }
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        LogUtils.Log(StringUtils.append("The ",browser.name()," browser has been created and is being used"));
    }

    public void stop() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }


}
