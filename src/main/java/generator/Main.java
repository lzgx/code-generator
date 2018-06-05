package generator;

import generator.template.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Administrator on 2018/1/30.
 */
public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class.getCanonicalName());
    private static TemplateHandler eth = new EntityTemplateHandler();
    private static TemplateHandler dth = new DaoTemplateHandler();
    private static TemplateHandler sth = new ServiceTemplateHandler();
    private static TemplateHandler ith = new ServiceImplTemplateHandler();
    private static TemplateHandler fth = new MappingFileTemplateHandler();

    public static void main(String[] args) throws Exception {
        try {
            long startTime = System.currentTimeMillis();
            String value;
            if (args.length > 0 && StringUtils.isNotBlank(args[0])) {
                logger.info("load configuration file name {}", args[0]);
                value = ConfigUtils.getProperty(Constants.TABLE_NAME, null, args[0]);
            } else {
                value = ConfigUtils.getProperty(Constants.TABLE_NAME);
            }
            if (StringUtils.isBlank(value)) {
                logger.error("table name is not empty!");
                return;
            }
            String[] tableNames = value.split(",");
            for (String tableName : tableNames) {
                eth.process(tableName);
                dth.process(tableName);
                sth.process(tableName);
                ith.process(tableName);
                fth.process(tableName);
            }
            logger.info("Code generation takes time：{} millisecond。", (System.currentTimeMillis() - startTime));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            System.in.read();
        }
    }
}
