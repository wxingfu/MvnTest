package com.wxf.schema.maker.table;

import com.wxf.schema.maker.utility.DBTypes;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @author weixf
 * @since 2022-01-21
 */
@Component
public class Key {

    private String ConstraintName;
    private int KeyType = 0;
    private ArrayList<String> Columns = new ArrayList<>();
    private int ColumnNum = 0;
    private String RefTable;
    private String RefKey;
    private ArrayList<String> RefColumns = new ArrayList<>();
    private int DeleteType = -1;
    private int UpdateType = -1;

    public Key() {
    }

    public int getKeyType() {
        return KeyType;
    }

    public void setKeyType(int keytype) {
        KeyType = keytype;
    }

    public void setConstraintName(String constraint) {
        ConstraintName = constraint;
    }

    public String getConstraintName() {
        return ConstraintName;
    }

    public void setRefTable(String reftable) {
        RefTable = reftable;
    }

    public String getRefTable() {
        if (KeyType == DBTypes.ForeignKey) {
            return RefTable;
        } else {
            return null;
        }
    }

    public void setRefKey(String refkey) {
        RefKey = refkey;
    }

    public String getRefKey() {
        if (KeyType == DBTypes.ForeignKey) {
            return RefKey;
        } else {
            return null;
        }
    }

    public void setDeleteType(int deletetype) {
        DeleteType = deletetype;
    }

    public int getDeleteType() {
        if (KeyType == DBTypes.ForeignKey) {
            return DeleteType;
        } else {
            return 0;
        }
    }

    public void setUpdateType(int updatetype) {
        UpdateType = updatetype;
    }

    public int getUpdateType() {
        if (KeyType == DBTypes.ForeignKey) {
            return UpdateType;
        } else {
            return 0;
        }
    }

    public void addColumn(String col, String refcol) {
        Columns.add(col);
        if (refcol != null && !refcol.equals("")) {
            RefColumns.add(refcol);
        }
        ColumnNum++;
    }

    public int getColumnNum() {
        return ColumnNum;
    }

    public String getColumn(int i) {
        return (String) Columns.get(i);
    }

    public String getRefColumn(int i) {
        if (KeyType == DBTypes.ForeignKey) {
            return (String) RefColumns.get(i);
        } else {
            return null;
        }
    }

    public String[] getRefJoin(int i) {
        if (KeyType == DBTypes.ForeignKey) {
            String[] a = new String[2];
            a[0] = (String) Columns.get(i);
            a[1] = (String) RefColumns.get(i);
            return a;
        } else {
            return null;
        }
    }

}
