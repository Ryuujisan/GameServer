package io.yuri.yuriserver.utils;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    static {
        reloadConfig();
    }


    private static Properties prop;

    private static void reloadConfig() {

        prop = new Properties();

        InputStream input = null;


        try {
            input = Config.class.getResourceAsStream("config.properties");
            prop.load(input);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if(input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static String getString(String key){
        return prop.getProperty(key);
    }

    public static Integer getInteger(String key) {
        return Integer.parseInt((getString(key)));
    }

    public static float getFloat(String key) {
        return Float.parseFloat(getString(key));
    }

    /*
    Converte float seconds to long miliseconds
     */

    public static long getMiliseconds(String key) {
        float seconds = Float.parseFloat((getString(key)));
        return (long)(seconds * 1000l);
    }


}
