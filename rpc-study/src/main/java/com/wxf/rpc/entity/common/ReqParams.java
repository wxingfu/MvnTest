package com.wxf.rpc.entity.common;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqParams {

    private int PageSize;
    private int CurrentPage;
    private String OrderBy;
    private boolean OrderType;
    private String where;
    private String Append;
    private int TotalCount;
}
