package com.dhx.apigateway.global;

import cn.hutool.json.JSONUtil;
import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicommon.common.exception.BusinessException;
import com.dhx.apicommon.common.exception.ErrorCode;
import com.dhx.apicommon.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintViolationException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author adorabled4
 * @className GlobalExceptionHandler
 * @date : 2023/05/01/ 10:21
 **/
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        // Log the exception here
        log.error(e.getMessage(), e);
        BaseResponse baseResponse = new BaseResponse<>(ErrorCode.SYSTEM_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(JSONUtil.toJsonStr(baseResponse));
    }


    /**
     * 常见的参数异常处理
     */
    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class, MissingServletRequestParameterException.class,
            ConversionFailedException.class, ConstraintViolationException.class, HttpMessageNotReadableException.class})
    public BaseResponse handleMethodArgumentTypeMismatchException(HttpMessageNotReadableException e) {
        return ResultUtil.error(ErrorCode.PARAMS_ERROR);
    }

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(BusinessException.class)
    public BaseResponse<Object> handleRRException(BusinessException e){
        log.error(e.getDescription(), e);
        return ResultUtil.error(e.getCode(),e.getMessage(),e.getDescription());
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

}

