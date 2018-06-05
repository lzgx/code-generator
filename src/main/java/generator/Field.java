package generator;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Administrator on 2018/1/26.
 */
public class Field {
    private String fieldName;//字段名称
    private String fieldType;//字段类型
    private String columnName;//列 名称
    private String columnType;//列类型
    private String methodName;//方法名称

    public Field(String fieldName, String fieldType, String columnName, String columnType) {
        this.fieldName = fieldName;
        this.fieldType = fieldType;
        this.columnName = columnName;
        this.columnType = columnType;
        if (StringUtils.length(fieldName) == 1) {
            methodName = fieldName.toUpperCase();
        }
        if (StringUtils.length(fieldName) > 1) {
            methodName = fieldName.toUpperCase().charAt(0) + fieldName.substring(1);
        }
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public String toString() {
        return "Field{" +
                "fieldName='" + fieldName + '\'' +
                ", fieldType='" + fieldType + '\'' +
                ", columnName='" + columnName + '\'' +
                ", columnType='" + columnType + '\'' +
                ", methodName='" + methodName + '\'' +
                '}';
    }
}
