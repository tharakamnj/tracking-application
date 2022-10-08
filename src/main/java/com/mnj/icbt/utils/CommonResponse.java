package com.mnj.icbt.utils;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CommonResponse {
    private List<Object> payload = null;
    private List<String> errorMessages = new ArrayList<>();
    private int status = 200;
}
