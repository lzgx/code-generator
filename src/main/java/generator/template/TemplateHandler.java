package generator.template;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;
import generator.ConfigUtils;
import generator.Constants;
import generator.DBManager;
import generator.Utils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/30.
 */
public abstract class TemplateHandler {
    private static final Logger logger = LogManager.getLogger(DBManager.class.getCanonicalName());
    private Configuration cfg;


    protected Configuration getConfiguration() throws Exception {
        if (cfg == null) {
            cfg = new Configuration(new Version(2, 3, 23));
            // Where do we load the templates from:
            cfg.setClassLoaderForTemplateLoading(TemplateHandler.class.getClassLoader(), "templates");
            // Some other recommended settings:
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        }
        return cfg;
    }

    /**
     * 根据表名创建实体类名称
     *
     * @param tableName
     * @return
     */
    protected String createClassName(String tableName) {
        String name = Utils.convert(tableName);
        if (StringUtils.isBlank(name) || StringUtils.length(name) == 1) {
            return name;
        }
        return (name.toUpperCase().charAt(0) + name.substring(1));
    }

    public void process(String tableName) {
        try {
            // Get the template
            Template template = getConfiguration().getTemplate(getTemplateFileName());

            // For the sake of example, also write output into a file:
            File outputFile = new File(System.getProperty("user.dir") + File.separatorChar + getFileName(tableName));
            File dir = new File(outputFile.getCanonicalPath().substring(0, outputFile.getCanonicalPath().lastIndexOf(File.separator)));
            if (!dir.exists()) {
                dir.mkdirs();
            }
            Writer fileWriter = new FileWriter(outputFile);
            try {
                template.process(getModel(tableName), fileWriter);
            } finally {
                fileWriter.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 获取模板文件名称
     *
     * @return
     */
    public abstract String getTemplateFileName();

    /**
     * 获取模板填充参数
     *
     * @param tableName
     * @return
     */
    public abstract Map<String, Object> getModel(String tableName);

    /**
     * 获取输出文件名称
     *
     * @param tableName
     * @return
     */
    public abstract String getFileName(String tableName);


}
