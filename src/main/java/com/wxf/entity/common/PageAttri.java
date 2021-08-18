package com.wxf.entity.common;

import lombok.Data;

@Data
public class PageAttri {

    private int PageSize;
    private int CurrentPage;
    private String OrderBy;
    private boolean OrderType;
    private String where;
    private String Append;
    private int TotalCount;
}
