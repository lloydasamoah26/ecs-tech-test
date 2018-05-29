package Utility;

/**
 * Created by lloydasamoah on 28/05/2018.
 */
public class StringUtils {


    public static String append(String...strings){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i<strings.length; i++){
            stringBuilder.append(strings[i]);
        }

        return stringBuilder.toString();
    }

    public static String changePathFormatToWindows(String path){
        return path.replaceAll("/","\\");
    }

}
