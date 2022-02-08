<#import "lib/copyright.ftl" as copyright>
<@copyright.copyright/>

package com.sinosoft.lis.vschema;

import com.sinosoft.lis.schema.${tableName}Schema;
import com.sinosoft.utility.SysConst;
import com.sinosoft.utility.CError;
import com.sinosoft.utility.CErrors;
import com.sinosoft.utility.SchemaSet;


<#import "lib/class_comments.ftl" as classComments>
<@classComments.comment type="Set"/>
public class ${tableName}Set extends SchemaSet {

    // @Method
    public boolean add(${tableName}Schema schema) {
        return super.add(schema);
    }

    public boolean add(${tableName}Set set) {
        return super.add(set);
    }

    public boolean remove(${tableName}Schema schema) {
        return super.remove(schema);
    }

    public ${tableName}Schema get(int index) {
        ${tableName}Schema schema = (${tableName}Schema)super.getObj(index);
        return schema;
    }

    public boolean set(int index, ${tableName}Schema schema) {
        return super.set(index, schema);
    }

    public boolean set(${tableName}Set set) {
        return super.set(set);
    }

    public boolean copy(${tableName}Set set) {
        if(set == null){
            return false;
        }
        this.clear();
        for (int i = 1; i <= set.size(); i++) {
            this.add(set.get(i).getSchema());
        }
        return true;
    }

    public ${tableName}Set copy() {
        ${tableName}Set set = new ${tableName}Set();
        for (int i = 1; i <= this.size(); i++) {
            set.add(this.get(i).getSchema());
        }
        return set;
    }


    /**
     * 数据打包，按 XML 格式打包，顺序参见<A href ={@docRoot}/dataStructure/tb.html#Prp${tableName}描述/A>表字段
     * @return: String 返回打包后字符串
     */
    public String encode() {
        StringBuffer strReturn = new StringBuffer("");
        int n = this.size();
        for (int i = 1; i <= n; i++) {
            ${tableName}Schema schema = this.get(i);
            strReturn.append(schema.encode());
            if(i != n) {
                strReturn.append(SysConst.RECORDSPLITER);
            }
        }
        return strReturn.toString();
    }

    /**
     * 数据解包
     * @param: str String 打包后字符串
     * @return: boolean
     */
    public boolean decode(String str) {
        int nBeginPos = 0;
        int nEndPos = str.indexOf('^');
        this.clear();
        while (nEndPos != -1) {
            ${tableName}Schema aSchema = new ${tableName}Schema();
            if(aSchema.decode(str.substring(nBeginPos, nEndPos))) {
                this.add(aSchema);
                nBeginPos = nEndPos + 1;
                nEndPos = str.indexOf('^', nEndPos + 1);
            } else {
                // @@错误处理
                this.mErrors.copyAllErrors(aSchema.mErrors);
                return false;
            }
        }
        ${tableName}Schema tSchema = new ${tableName}Schema();
        if (tSchema.decode(str.substring(nBeginPos))) {
            this.add(tSchema);
            return true;
        } else {
            // @@错误处理
            this.mErrors.copyAllErrors(tSchema.mErrors);
            return false;
        }
    }
}
