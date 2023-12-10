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


    private static final String FINAL_BUNDLE_NAME = "PropertiesConfig";
    private static final String ENV_PROFILE_NAME = "profile.active";
    private static final ResourceBundle res = ResourceBundle.getBundle(FINAL_BUNDLE_NAME);
    private static final Logger logger = Logger.getLogger(PropertiesConfig.class);
    /**
     * 成员变量
     */
    private static final String filename = "PropertiesConfig";

    private PropertiesConfig() {
    }

    /**
     * 根据键获取对应字符串的方法
     */
    public static String getString(String key) {
        try {
            // 判断环境配置
            boolean envExist = res.containsKey(ENV_PROFILE_NAME);
            if (envExist) {
                String env = res.getString(ENV_PROFILE_NAME);
                ResourceBundle envRes = ResourceBundle.getBundle(FINAL_BUNDLE_NAME + "-" + env.toLowerCase());
                // 环境配置文件中是否存在，存在则获取，不存在则尝试公共配置文件获取
                if (envRes.containsKey(key)) {
                    return envRes.getString(key).trim();
                } else {
                    return res.getString(key).trim();
                }
            }
            // 默认公共配置文件获取
            return res.getString(key).trim();
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }
}
