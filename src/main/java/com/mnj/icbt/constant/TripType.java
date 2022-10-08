package com.mnj.icbt.constant;

import java.util.HashMap;
import java.util.Map;

public enum TripType {

    UP(0),
    DOWN(1);

    private int value;
    private static Map map = new HashMap<>();

    private TripType(int value){
        this.value=value;
    }
    static {
        for (TripType type:TripType.values()) {
            map.put(type.value,type);
        }
    }

    public static TripType valueOf(int type){
        return (TripType) map.get(type);
    }

    public int getValue(){
        return value;
    }
}
