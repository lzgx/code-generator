package generator.template;

import generator.ConfigUtils;
import generator.Constants;
import generator.DBManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/30.
 */
public class EntityTemplateHandler extends TemplateHandler {

    @Override
    public String getTemplateFileName() {
        return "entity.ftl";
    }

    @Override
    public Map<String, Object> getModel(String tableName) {
        Map<String, Object> params = new HashMap<>();
        params.put("fields", DBManager.getInstance().getFields(tableName));
        params.put("packageName", ConfigUtils.getProperty(Constants.PACKAGE_ENTITY));
        params.put("className", createClassName(tableName));
        return params;
    }

    @Override
    public String getFileName(String tableName) {
        String packageName = ConfigUtils.getProperty(Constants.PACKAGE_ENTITY);
        return String.format("%s/%s.java", packageName.replaceAll("\\.", "/"), createClassName(tableName));
    }
}
