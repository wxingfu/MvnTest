package com.wxf.test.xml;

import lombok.Data;

import java.util.List;

/**
 * @author weixf
 * @since 2021-12-07
 */
@Data
public class Body {

    private String TranDate;
    private String InsuranceCode;
    private String Total;
    private List<Detail> Details;
}
