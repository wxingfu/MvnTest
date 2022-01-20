<#macro comment type>
/**
 * <p>自动生成的文件，不可手工修改！</p>
 * <p>ClassName: ${tableName}${type}</p>
<#if type=="Schema"||type=="Set"> * <p>Description: DB层 ${type} 类文件</p>
<#elseif type=="DBSet"> * <p>Description: DB层多记录数据库操作类文件</p>
<#elseif type=="DB"> * <p>Description: DB层数据库操作类文件</p>
</#if>
 * <p>Company: Sinosoft Co.,LTD</p>
 *
 * @Database: ${dataBase}
 * @author: Makerx
 * @CreateDatetime: ${createDateTime}
 * @vm: ${javaVmName}(build ${javaVmVersion}, ${javaVmVendor})
 * @o: ${osName}(${osArch})
 * @creator: ${userName}(${userCountry})
 */
</#macro>