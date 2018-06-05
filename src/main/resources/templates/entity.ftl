package ${packageName};
<#assign found = false/>
<#list fields as field>
    <#if field.fieldType == 'Date'>
        <#assign found = true/>
        <#break>
    </#if>
</#list>

import java.io.Serializable;
<#if found>
import java.util.Date;
</#if>


public class ${className} implements Serializable {
    <#list fields as field>
    private ${field.fieldType} ${field.fieldName};
    </#list>

    <#list fields as field>
    public ${field.fieldType} get${field.methodName}() {
        return ${field.fieldName};
    }

    public void set${field.methodName}(${field.fieldType} ${field.fieldName}) {
        this.${field.fieldName} = ${field.fieldName};
    }

    </#list>

}
