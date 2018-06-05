package generator;


import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Administrator on 2018/1/26.
 */
public class DBManager {
    private static final Logger logger = LogManager.getLogger(DBManager.class.getCanonicalName());
    private static DBManager instance;
    private static Properties dataTypes;

    private DBManager() {
    }

    public static synchronized DBManager getInstance() {
        try {
            if (instance == null) {
                instance = new DBManager();
                dataTypes = ConfigUtils.loadProperties("dataTypes.properties");
            }
            return instance;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 获取表中所有字段的信息
     *
     * @param tableName
     * @return
     */
    public List<Field> getFields(String tableName) {
        try {
            Connection connection = getConnection();
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet columns = databaseMetaData.getColumns(null, null, tableName, null);
            List<Field> fields = new ArrayList<>();
            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");
                String dataType = columns.getString("TYPE_NAME");
                fields.add(createField(columnName, dataType));
            }
            return fields;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    private Field createField(String columnName, String dataType) {
        String fieldType = dataTypes.getProperty(dataType);
        if (StringUtils.isBlank(fieldType)) {
            fieldType = "String";
        }
        String fieldName = Utils.convert(columnName);
        return new Field(fieldName, fieldType, columnName, dataType);
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(ConfigUtils.getProperty(Constants.JDBC_DRIVER_CLASS_NAME));
        Connection conn = DriverManager.getConnection(ConfigUtils.getProperty(Constants.JDBC_URL),
                ConfigUtils.getProperty(Constants.JDBC_USERNAME), ConfigUtils.getProperty(Constants.JDBC_PASSWORD));
        if (conn == null) {
            throw new RuntimeException("Not connect to the database");
        }
        return conn;
    }

    public static void main(String[] args) throws Exception {
        DBManager dbManager = DBManager.getInstance();
        List<Field> fields = dbManager.getFields("borrow_project_contract_info");
        for (Field field : fields) {
            System.out.println(field);
        }
    }

}
