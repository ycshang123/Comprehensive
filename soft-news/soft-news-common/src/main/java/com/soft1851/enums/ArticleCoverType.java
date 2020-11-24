package com.soft1851.enums;

public enum ArticleCoverType {
    ONE_IMAGE(1,"单图"),
    WOEDS(2,"纯文字");
    public final  Integer type;
    public  final String value;
    ArticleCoverType(Integer type,String value){
        this.type = type;
        this.value = value;
    }
}
