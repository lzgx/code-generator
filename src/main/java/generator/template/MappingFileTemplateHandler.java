package generator.template;

import generator.ConfigUtils;
import generator.Constants;
import generator.DBManager;
import generator.Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/31.
 */
public class MappingFileTemplateHandler extends TemplateHandler {
    @Override
    public String getTemplateFileName() {
        return "mappingFile.ftl";
    }

    @Override
    public Map<String, Object> getModel(String tableName) {
        Map<String, Object> params = new HashMap<>();
        params.put("fields", DBManager.getInstance().getFields(tableName));
        params.put("packageName", ConfigUtils.getProperty(Constants.PACKAGE_MAPPING));
        params.put("entityPackageName", ConfigUtils.getProperty(Constants.PACKAGE_ENTITY));
        params.put("daoPackageName", ConfigUtils.getProperty(Constants.PACKAGE_DAO));
        params.put("tableName", tableName);
        params.put("className", createClassName(tableName));
        params.put("instanceName", Utils.convert(tableName));
        return params;
    }

    @Override
    public String getFileName(String tableName) {
        String packageName = ConfigUtils.getProperty(Constants.PACKAGE_MAPPING);
        return String.format("%s/%s.xml", packageName.replaceAll("\\.", "/"), createClassName(tableName));
    }
}
