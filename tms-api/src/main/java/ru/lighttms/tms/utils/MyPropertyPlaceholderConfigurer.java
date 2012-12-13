package ru.lighttms.tms.utils;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: azee
 */

public class MyPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    // process the key and then return the associated value...
    protected String resolvePlaceholder(String placeholder, Properties props) {
        return props.getProperty(placeholder);
    }
}
