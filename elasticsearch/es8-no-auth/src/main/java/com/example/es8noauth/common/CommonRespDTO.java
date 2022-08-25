package com.example.es8noauth.common;

import lombok.Data;

@Data
public class CommonRespDTO<T> {
    private String status;
    private String errorCode;
    private String errorMsg;
    private T data;
}
