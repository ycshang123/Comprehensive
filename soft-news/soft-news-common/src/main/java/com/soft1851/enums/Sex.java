package com.soft1851.enums;

public enum Sex {
    Man(1,"男"),
    WoMan(2,"女");
    public final Integer type;
    public final String value;
    Sex(Integer type,String value){
        this.type = type;
        this.value = value;
    }
}
