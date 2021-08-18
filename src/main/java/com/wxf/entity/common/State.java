package com.wxf.entity.common;

import lombok.Data;

@Data
public class State {

    private boolean IsError;
    private int RecordAffected;
    private boolean IsSucess;
    private int Code;
}
