package com.example.es8noauth.common;

import lombok.Data;

/**
 * ClassName： ExceptionDto
 * Description： ControllerAdvice的ExceptionHandler捕获到异常时，构造该类实例保存异常信息。
 * @Author： biw
 * @Date： 2020/8/15 15:55
 */
@Data

public class ExceptionDTO {

    private String status = "-1";

    private String errorCode;

    private String errorMsg;

    private Object data;
}
