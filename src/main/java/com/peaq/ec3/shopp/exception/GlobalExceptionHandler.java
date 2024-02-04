package com.peaq.ec3.shopp.exception;

import com.peaq.ec3.shopp.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;
import java.util.stream.Collectors;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({Ec3Exception.class})
    public Result handleException(Ec3Exception e) {
        log.error("系统业务异常 : {}", e.getMessage(), e);
        return Result.returnResult(null == e.getCode() ? "9" : e.getCode() + "", e.getMessage(), null);
    }

    @ExceptionHandler({Exception.class})
    public Result handleException(Exception e) {
        log.error("Global Exception : {}", e.getMessage(), e);
        return Result.returnFail("业务处理超时，请稍后再试");
    }

    @ResponseBody
    @ExceptionHandler(value = {BindException.class, MethodArgumentNotValidException.class})
    public Result validationExceptionHandler(Exception exception) {
        BindingResult bindResult = null;
        if (exception instanceof BindException) {
            bindResult = ((BindException) exception).getBindingResult();
        } else if (exception instanceof MethodArgumentNotValidException) {
            bindResult = ((MethodArgumentNotValidException) exception).getBindingResult();
        }
        String msg;
        if (Objects.nonNull(bindResult) && bindResult.hasErrors()) {
            msg = bindResult.getFieldErrors().stream().map(
                    fieldError -> fieldError.getDefaultMessage()
            ).collect(Collectors.joining("; "));
        } else {
            msg = "The system is busy, please try again later.";
        }
        return Result.returnFail(msg);
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public Result handleException(HttpRequestMethodNotSupportedException e) {
        log.error("Global Exception", e);
        return Result.returnFail("不支持的请求方式：" + e.getSupportedMethods());
    }

    @ExceptionHandler({Throwable.class})
    public Result handleException(Throwable t) {
        log.error("Global Throwable : {}", t.getMessage(), t);
        return Result.returnFail("系统响应超时，请稍后再试");
    }

}
