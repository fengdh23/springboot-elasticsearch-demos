package com.example.es8noauth.common;

/**
 * @ClassName： ExceptionCode
 * @Description： Ufin全局异常码定义
 * @Author： biw
 * @Date： 2020/8/16 20:13
 */
public interface ExceptionCode {
    /**
     * Ufin全局默认异常
     */
    String UFIN_DEFAULT_EXCEPTION = "UFIN_DEFAULT_EXCEPTION";
    /**
     * 方法参数验证
     */
    String METHOD_ARGUMENT_NOT_VALID_EXCEPTION = "ARGUMENT_NOT_VALID";
    /**
     * 请求参数格式错误
     */
    String PARAMS_FORMAT_ERR = "params.format.err";

    /**
     * 请求参数不合法
     */
    String REQUEST_PARAMETER_ILLEGAL = "REQUEST_PARAMETER_ILLEGAL";

    /**
     * 拒绝访问
     */
    String ACCESS_DENIED = "ACCESS_DENIED";


}
