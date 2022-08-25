package com.example.es8noauth.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * ClassName： ControllerResponseAdvice
 * Description： 用于对Controller中 @ResponseBody 方法的返回对象统一进行再次封装，包括对正常对象的封装以及异常信息的封装。
 * 注解是SpringMVC提供的
 *
 * @Author： biw
 * @Date： 2020/8/15 15:55
 */
@Slf4j
@ControllerAdvice
public class ControllerResponseAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    /**
     * 异常类型，全部(404 为什么捕获不到)
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ExceptionDTO exceptionHandler(Exception e) {
        // 业务代码内不用打印异常信息，统一在此处打印
        log.error("exceptionHandler:{}", e.getMessage());
        ExceptionDTO exceptionDTO = new ExceptionDTO();
        if (e instanceof IllegalArgumentException) {
            exceptionDTO.setErrorCode(ExceptionCode.METHOD_ARGUMENT_NOT_VALID_EXCEPTION);
            exceptionDTO.setErrorMsg(e.getMessage());
        } else if (e instanceof HttpMessageNotReadableException) {
            exceptionDTO.setErrorCode(ExceptionCode.PARAMS_FORMAT_ERR);
        } else if (e instanceof MissingServletRequestParameterException || e instanceof MissingRequestHeaderException) {
            exceptionDTO.setErrorCode(ExceptionCode.REQUEST_PARAMETER_ILLEGAL);
        } else { // 发现未捕获的非UfinExc，转换为默认异常
            exceptionDTO.setErrorCode(ExceptionCode.UFIN_DEFAULT_EXCEPTION);
        }
        return exceptionDTO;
    }

    @ResponseBody
    @Override
    public CommonRespDTO beforeBodyWrite(Object origin, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        CommonRespDTO commonRespDTO = new CommonRespDTO();
        if (origin instanceof ExceptionDTO) {
            ExceptionDTO exceptionDTO = (ExceptionDTO) origin;
            commonRespDTO.setStatus(exceptionDTO.getStatus());
            commonRespDTO.setErrorCode(exceptionDTO.getErrorCode());
            commonRespDTO.setErrorMsg(exceptionDTO.getErrorMsg());
            commonRespDTO.setData(exceptionDTO.getData());
        } else if (origin instanceof IllegalArgumentException) {
            IllegalArgumentException argumentException = (IllegalArgumentException) origin;
            commonRespDTO.setErrorCode(ExceptionCode.METHOD_ARGUMENT_NOT_VALID_EXCEPTION);
            commonRespDTO.setErrorMsg(argumentException.getMessage());
        } else if (origin instanceof CommonRespDTO) {
            return (CommonRespDTO) origin;
        } else {
            commonRespDTO.setStatus("0");
            commonRespDTO.setData(origin);
        }
        return commonRespDTO;
    }


}
