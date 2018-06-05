package generator.template;

import generator.ConfigUtils;
import generator.Constants;
import generator.Utils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/30.
 */
public class DaoTemplateHandler extends TemplateHandler {

    @Override
    public String getTemplateFileName() {
        return "dao.ftl";
    }

    @Override
    public Map<String, Object> getModel(String tableName) {
        Map<String, Object> params = new HashMap<>();
        params.put("packageName", ConfigUtils.getProperty(Constants.PACKAGE_DAO));
        params.put("entityPackageName", ConfigUtils.getProperty(Constants.PACKAGE_ENTITY));
        params.put("className", createClassName(tableName));
        params.put("instanceName", Utils.convert(tableName));
        params.put("currentDate", DateFormatUtils.format(new Date(), "yyyy/MM/dd"));
        return params;
    }

    @Override
    public String getFileName(String tableName) {
        String packageName = ConfigUtils.getProperty(Constants.PACKAGE_DAO);
        return String.format("%s/%sDao.java", packageName.replaceAll("\\.", "/"),  createClassName(tableName));
    }

    public static void main(String[] args){
        String packageName = ConfigUtils.getProperty(Constants.PACKAGE_DAO);
        System.out.println(packageName.replaceAll("\\.", "/"));
    }
}
