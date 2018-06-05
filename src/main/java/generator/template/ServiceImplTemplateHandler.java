package generator.template;

import generator.ConfigUtils;
import generator.Constants;
import generator.Utils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/30.
 */
public class ServiceImplTemplateHandler extends TemplateHandler {

    @Override
    public String getTemplateFileName() {
        return "serviceImpl.ftl";
    }

    @Override
    public Map<String, Object> getModel(String tableName) {
        Map<String, Object> params = new HashMap<>();
        params.put("packageName", ConfigUtils.getProperty(Constants.PACKAGE_SERVICE_IMPL));
        params.put("daoPackageName", ConfigUtils.getProperty(Constants.PACKAGE_DAO));
        params.put("entityPackageName", ConfigUtils.getProperty(Constants.PACKAGE_ENTITY));
        params.put("servicePackageName", ConfigUtils.getProperty(Constants.PACKAGE_SERVICE));
        params.put("className", createClassName(tableName));
        params.put("instanceName", Utils.convert(tableName));
        params.put("currentDate", DateFormatUtils.format(new Date(), "yyyy/MM/dd"));
        return params;
    }

    @Override
    public String getFileName(String tableName) {
        String packageName = ConfigUtils.getProperty(Constants.PACKAGE_SERVICE_IMPL);
        return String.format("%s/%sServiceImpl.java", packageName.replaceAll("\\.", "/"), createClassName(tableName));
    }
}
