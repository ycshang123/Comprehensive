package com.soft1851.enums;

public enum YesOrNo {
    No(0,"否"),
    YES(1,"是");
    public final Integer type;
    public final String value;
    YesOrNo(Integer type,String value){
        this.type = type;
        this.value = value;
    }
}
