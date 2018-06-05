package ${packageName};

import ${entityPackageName}.${className};

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on ${currentDate}.
 */
public interface ${className}Service {

    int insert${className}(${className} ${instanceName});

    int update${className}(${className} ${instanceName});

    int deleteById(Integer id);

    ${className} getById(Integer id);

    List<${className}> query${className}s(Map<String, Object> params);

}