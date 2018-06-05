package generator;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Administrator on 2018/1/30.
 */
public class Utils {

    /**
     * 将下划线命名方式转为驼峰命名方式
     *
     * @param name,列名或表名
     * @return
     */
    public static String convert(String name) {
        if (StringUtils.isBlank(name)) {
            return "";
        }
        String[] names = name.split("_");
        StringBuffer fieldName = new StringBuffer(names[0].trim());
        for (int i = 1; i < names.length; i++) {
            if (StringUtils.isBlank(names[i])) {
                continue;
            }
            fieldName.append(names[i].trim().toUpperCase().charAt(0));
            if (StringUtils.length(names[i]) <= 1) {
                continue;
            }
            fieldName.append(names[i].trim().substring(1));
        }
        return fieldName.toString();
    }


}
