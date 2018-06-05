<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${daoPackageName}.${className}Dao">
    <resultMap type="${entityPackageName}.${className}" id="${instanceName}ResultMap">
        <#list fields as field>
        <result property="${field.fieldName}" column="${field.columnName}"/>
        </#list>
    </resultMap>

    <sql id="${tableName}_columns">
        <#list fields?chunk(10) as row>
            <#list row as cell>t1.${cell.columnName}<#sep>,</#list><#sep>,
        </#list>

    </sql>

    <#assign idField = fields[0]/>
    <#list fields as field>
        <#if field.columnName == 'id'>
            <#assign idField = field/>
            <#break/>
        </#if>
    </#list>
    <#assign str = '#\{'/>
    <insert id="insert${className}" parameterType="${entityPackageName}.${className}">
        INSERT INTO ${tableName}(
        <#list fields?chunk(10) as row>
            <#list row as cell><#if cell.columnName != idField.columnName>${cell.columnName}<#sep>,</#if></#list><#sep>,
        </#list>)
        VALUES(
        <#list fields?chunk(10) as row>
            <#list row as cell><#if cell.columnName != idField.columnName>${str}${cell.fieldName}}<#sep>,</#if></#list><#sep>,
        </#list>);
    </insert>

    <update id="update${className}" parameterType="java.util.Map">
        UPDATE ${tableName}
        <set>
        <#list fields as field>
            <#if field.columnName != idField.columnName>
            <if test="${field.fieldName} != null">
                ,${field.columnName}=${str}${field.fieldName}}
            </if>
            </#if>
        </#list>
        </set>
        WHERE ${idField.columnName} = ${str}${idField.fieldName}}
    </update>

    <delete id="deleteById" parameterType="int">
        DELETE FROM ${tableName}
        WHERE ${idField.columnName} = ${str}${idField.fieldName}}
    </delete>

    <select id="getById" parameterType="int" resultMap="${instanceName}ResultMap">
        SELECT
        <include refid="${tableName}_columns"/>
        FROM ${tableName} t1
        WHERE t1.${idField.columnName} = ${str}${idField.fieldName}}
    </select>


    <select id="query${className}s" parameterType="map" resultMap="${instanceName}ResultMap">
        SELECT
        <include refid="${tableName}_columns"/>
        FROM ${tableName} t1
        WHERE t1.${idField.columnName} = ${str}${idField.fieldName}}
    </select>

</mapper>