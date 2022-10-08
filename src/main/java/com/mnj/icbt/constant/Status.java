package com.mnj.icbt.constant;

import java.util.HashMap;
import java.util.Map;

public enum Status {
    ACTIVE(0),
    DISABLE(1),
    DELETE(-1);

    private int value;
    private static Map map = new HashMap<>();

    private Status(int value){
        this.value=value;
    }
    static {
        for (Status status:Status.values()){
            map.put(status.value,status);
        }
    }

    public static Status valueOf(int type){
        return (Status) map.get(type);
    }

    public int getValue(){
        return value;
    }
}
