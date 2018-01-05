package com.petrushin.epam.auction.util;

import java.util.ResourceBundle;

/**
 * Util class witch helps with read value from
 * property file
 *
 * @author Andrei Petrushin
 * @version 1.0.0
 */
public final class ResourceReaderUtil {

    public static String getValue(String resPath, String key) {
        ResourceBundle bundle = ResourceBundle.getBundle(resPath);
        return bundle.getString(key);
    }

}
