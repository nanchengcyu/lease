package cn.nanchengyu.lease.common.exception;

import cn.nanchengyu.lease.common.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ClassName: GlobalExceptioonHandler
 * Package: cn.nanchengyu.lease.common.exception
 * Description:
 *
 * @Author 南城余
 * @Create 2024/6/4 19:23
 * @Version 1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result exceptionHandler(Exception e) {
        e.printStackTrace();
        return Result.fail();
    }
}
