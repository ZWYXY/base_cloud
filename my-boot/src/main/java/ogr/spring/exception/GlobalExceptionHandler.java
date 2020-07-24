package ogr.spring.exception;

//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import ogr.spring.result.Result;
import ogr.spring.result.ResultEnum;
import ogr.spring.result.ResultUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;


//@ControllerAdvice
//@ResponseBody
@Slf4j
@RestControllerAdvice
//        (basePackages = "ogr.spring.controller")
public class GlobalExceptionHandler {

    /**
     * 缺少请求参数异常
     *
     * @param e HttpMessageNotReadableException
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public <T> Result<T> handleHttpMessageNotReadableException(Exception e) {
        if(e instanceof MissingServletRequestParameterException) {
            log.error("缺少请求参数，{}", e.getMessage());
            return ResultUtil.error(ResultEnum.ERROR_ARGUMENTS_MISSING);
        }
        else if(e instanceof MethodArgumentNotValidException) {
            log.error("参数错误，{}", e.getMessage());
            MethodArgumentNotValidException ex = (MethodArgumentNotValidException) e;
            List<ObjectError> errors = ex.getBindingResult().getAllErrors();
            String message = errors.get(0).getDefaultMessage();
            return ResultUtil.error(ResultEnum.REQUEST_PARAMETER_ERROR, message);
        }
        else if (e instanceof HttpMessageNotReadableException){
            log.warn("【SpringMVC异常】{}", e.getMessage());
            return ResultUtil.error(ResultEnum.REQUEST_PARAMETER_ERROR);
        }
        else if (e instanceof HttpMediaTypeNotSupportedException){
            log.warn("【SpringMVC异常】{}", e.getMessage());
            return ResultUtil.error(ResultEnum.ERROR_CONTENT_TYPE);
        }
        else if (e instanceof DBOPException){
            log.warn("【SpringMVC异常】{}", e.getMessage());
            return ResultUtil.error(((DBOPException) e).getResultEnum());
        }
        else {
            log.error("系统异常，{}", e.getMessage());
            return ResultUtil.error("未知错误");
        }
    }

}
