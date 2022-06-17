package com.wxf.pdm;

import org.springframework.stereotype.Component;

/**
 * @author weixf
 * @since 2022-01-21
 */
@Component
public class PDMReferenceJoin {

    private String Id;
    private String ParentTable_Col_Ref; //Object1
    private String ChildTable_Col_Ref; //Object2

    public PDMReferenceJoin() {
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getParentTableCol() {
        return ParentTable_Col_Ref;
    }

    public void setParentTableCol(String id) {
        ParentTable_Col_Ref = id;
    }

    public String getChildTableCol() {
        return ChildTable_Col_Ref;
    }

    public void setChildTableCol(String id) {
        ChildTable_Col_Ref = id;
    }
}
