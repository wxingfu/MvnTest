package com.wxf.pdm;

import org.springframework.stereotype.Component;

/**
 * @author weixf
 * @since 2022-01-21
 */
@Component
public class PDMUser {

    public String Id;
    private String Name;
    private String Code;

    public String getCode() {
        return Code;
    }

    public String getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
}
