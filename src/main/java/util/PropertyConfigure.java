package util;

import java.util.ResourceBundle;

public class PropertyConfigure {

    private static String CONFIG_FILE = "config";

    private static ResourceBundle resourceBundle;

    static {
        resourceBundle = ResourceBundle.getBundle(CONFIG_FILE);
    }

    public static String getValue(String key) {
        return resourceBundle.getString(key);
    }
}
