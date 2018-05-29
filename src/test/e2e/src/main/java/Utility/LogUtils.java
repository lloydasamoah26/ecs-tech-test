package Utility;


import Settings.Settings;

import static java.lang.System.lineSeparator;

/**
 * Created by lloydasamoah on 28/05/2018.
 */
public class LogUtils {


    public static void Log(Object object){
        if (Settings.isDebugEnabled()){

            if (object instanceof String){
                System.out.println(StringUtils.append("\n"+object));
            } else {
               System.out.println(StringUtils.append("\n"+object.toString()));
            }
        }
    }

    public static void Log(int number){
        Log(String.valueOf(number));

    }
}
