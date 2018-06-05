package ${packageName};

import ${daoPackageName}.${className}Dao;
import ${entityPackageName}.${className};

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import ${servicePackageName}.${className}Service;

/**
 * Created by Administrator on ${currentDate}.
 */
@Service("${instanceName}Service")
public class ${className}ServiceImpl implements ${className}Service {
    @Resource
    private ${className}Dao ${instanceName}Dao;

    @Override
    public int insert${className}(${className} ${instanceName}) {
        return ${instanceName}Dao.insert${className}(${instanceName});
    }

    @Override
    public int update${className}(${className} ${instanceName}) {
        return ${instanceName}Dao.update${className}(${instanceName});
    }

    @Override
    public int deleteById(Integer id) {
        return ${instanceName}Dao.deleteById(id);
    }

    @Override
    public ${className} getById(Integer id) {
        return ${instanceName}Dao.getById(id);
    }

    @Override
    public List<${className}> query${className}s(Map<String, Object> params) {
        return ${instanceName}Dao.query${className}s(params);
    }
}
