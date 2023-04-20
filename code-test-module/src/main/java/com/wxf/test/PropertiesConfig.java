package com.wxf.test;

import org.apache.log4j.Logger;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/*
 *
 * @author weixf
 * @date 2023-03-06
 */
public class PropertiesConfig {


    private static Logger logger = Logger.getLogger(PropertiesConfig.class);

    /**
     * 成员变量
     */
    private static String filename = "PropertiesConfig";

    private static final String FINAL_BUNDLE_NAME = "PropertiesConfig";

    private static ResourceBundle res = ResourceBundle.getBundle(FINAL_BUNDLE_NAME);

    private PropertiesConfig() {
    }

    /**
     * 根据键获取对应字符串的方法
     */
    public static String getString(String key) {
        try {
            boolean keyExist = res.containsKey(key);
            if (!keyExist) {
                boolean envExist = res.containsKey("properties.config.active");
                if (envExist) {
                    String env = res.getString("properties.config.active");
                    ResourceBundle envRes = ResourceBundle.getBundle(FINAL_BUNDLE_NAME + "-" + env);
                    return envRes.getString(key).trim();
                }
            }
            return res.getString(key).trim();
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }
}
