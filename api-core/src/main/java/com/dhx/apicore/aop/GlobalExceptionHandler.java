package com.dhx.apicore.aop;

import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicommon.common.exception.BusinessException;
import com.dhx.apicommon.common.exception.ErrorCode;
import com.dhx.apicommon.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * 异常处理器
 *
 * @author adorabled4
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    /**
     * 常见的参数异常处理
     */
    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class, MissingServletRequestParameterException.class,
            ConversionFailedException.class, ConstraintViolationException.class, HttpMessageNotReadableException.class})
    public BaseResponse handleMethodArgumentTypeMismatchException(HttpMessageNotReadableException e) {
        return ResultUtil.error(ErrorCode.PARAMS_ERROR,e.getMessage());
    }

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(BusinessException.class)
    public BaseResponse<Object> handleRRException(BusinessException e){
        log.error(e.getMessage(), e);
        return ResultUtil.error(e.getCode(),e.getMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public BaseResponse handlerNoFoundException(Exception e) {
        log.error(e.getMessage(), e);
        return ResultUtil.error(ErrorCode.NOT_FOUND,e.getMessage());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public BaseResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder sb = new StringBuilder();
        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            for (ObjectError objectError : allErrors) {
                sb.append(objectError.getDefaultMessage()).append(";");
            }
        }
        return ResultUtil.error(ErrorCode.PARAMS_ERROR,sb.toString());
    }

    @ExceptionHandler(Exception.class)
    public BaseResponse handleException(Exception e){
        log.error(e.getMessage(), e);
        return ResultUtil.error(ErrorCode.SYSTEM_ERROR,e.getMessage());
    }
}
