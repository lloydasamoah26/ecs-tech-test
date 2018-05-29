package Settings;

import Utility.LogUtils;
import Utility.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by lloydasamoah on 28/05/2018.
 */
public class Settings {
    private static boolean debugEnabled = false;
    private static String domainURL = null;
    private static String chromeDriverPath = null;
    private static String geckoDriverPath = null;
    private static String current;
    private static Properties prop;
    private static InputStream input;
    private static String propertyFile;
    private static int defaultWaitTime = 0;
    private static int defaultLongWaitTime = 0;


    public static void intialise() {

        try {
            //Load the env.properties
            propertyFile = "env.properties";
            current = new java.io.File(".").getCanonicalPath();
            input = new FileInputStream(current + "/src/main/resources/" + propertyFile);
            prop = new Properties();
            prop.load(input);

            if (debugEnabled == false) {
                debugEnabled = Boolean.parseBoolean(prop.getProperty("debugEnabled"));
            }

            if (domainURL == null) {
                domainURL = prop.getProperty("domainUrl");
            }

            if (geckoDriverPath == null){
                if(isMachineWindows()){
                    geckoDriverPath = StringUtils.changePathFormatToWindows(StringUtils.append(current + prop.getProperty("geckoDriverPath")));
                }else {
                    geckoDriverPath = StringUtils.append(current + prop.getProperty("geckoDriverPath"));
                }
            }

            if (chromeDriverPath == null){
                if(isMachineWindows()) {
                    chromeDriverPath = StringUtils.changePathFormatToWindows(StringUtils.append(current + prop.getProperty("chromeDriverPath")));
                }else{
                    chromeDriverPath = StringUtils.append(current + prop.getProperty("chromeDriverPath"));
                }

            }

            if(defaultWaitTime == 0){
                defaultWaitTime = Integer.parseInt(prop.getProperty("defaultWaitTime"));
            }

            if(defaultLongWaitTime == 0){
                defaultLongWaitTime = Integer.parseInt(prop.getProperty("defaultLongWaitTime"));
            }

            LogUtils.Log(StringUtils.append("Settings have been initialised: ",StringUtils.append("{debugEnabled = ",Boolean.toString(debugEnabled)," domainURL = ",domainURL, " ChromeDriverPath = ",
                    chromeDriverPath, " GeckoDriverPath = ", geckoDriverPath, "DefaultWaitTime = ", Integer.toString(defaultWaitTime), "DefaultLongWaitTime = ",Integer.toString(defaultLongWaitTime))));


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }

    }

    public static boolean isDebugEnabled(){
        return debugEnabled;
    }

    public static String getDomainURL(){
        return domainURL;
    }

    public static String getChromeDriverPath(){
        return chromeDriverPath;
    }

    public static String getGeckoDriverPath(){
        return geckoDriverPath;
    }

    public static int getDefaultWaitTime(){return defaultWaitTime;}

    public static int getDefaultLongWaitTime(){return  defaultLongWaitTime;}

    public static boolean isMachineWindows(){
        return System.getProperty("os.name").toLowerCase().contains("windows");
    }


}
