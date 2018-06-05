package generator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Administrator on 2018/1/30.
 */
public class ConfigUtils {
    private static final Logger logger = LogManager.getLogger(DBManager.class.getCanonicalName());
    private static Properties properties;

    /**
     * 获取属性值
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public static synchronized String getProperty(String key, String defaultValue, String fileName) {
        try {
            if (properties == null) {
                properties = loadProperties(fileName);
            }
            return properties.getProperty(key, defaultValue);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 获取属性值
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public static synchronized String getProperty(String key, String defaultValue) {
        return getProperty(key, defaultValue, "config.properties");
    }

    /**
     * 获取属性值
     *
     * @param key
     * @return
     */
    public static String getProperty(String key) {
        return getProperty(key, null, "config.properties");
    }


    /**
     * 加载属性文件
     *
     * @param fileName
     * @return
     */
    public static Properties loadProperties(String fileName) {
        try {
            Properties p = new Properties();
            File configFile = new File(System.getProperty("user.dir") + File.separatorChar + fileName);
            InputStream input;
            if (configFile.exists()) {
                input = new FileInputStream(configFile);
            } else {
                ClassLoader classLoader = DBManager.class.getClassLoader();
                input = classLoader.getResourceAsStream(fileName);
            }
            p.load(input);
            return p;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 加载属性文件
     *
     * @param configFile
     * @return
     */
//    public static Properties loadProperties(File configFile) {
//        try {
//            Properties p = new Properties();
//            p.load(new FileInputStream(configFile));
//            return p;
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//            throw new RuntimeException(e.getMessage(), e);
//        }
//    }
}
