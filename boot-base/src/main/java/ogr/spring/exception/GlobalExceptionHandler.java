package ogr.spring.exception;

//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import ogr.spring.result.Result;
import ogr.spring.result.ResultEnum;
import ogr.spring.result.ResultUtil;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;


//@ControllerAdvice
//@ResponseBody
@Slf4j
@RestControllerAdvice(basePackages = "ogr.spring.controller")
public class GlobalExceptionHandler {

    /**
     * 缺少请求参数异常
     *
     * @param ex HttpMessageNotReadableException
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public <T> Result<T> handleHttpMessageNotReadableException(Exception ex) {
        if(ex instanceof MissingServletRequestParameterException) {
            log.error("缺少请求参数，{}", ex.getMessage());
            return ResultUtil.error(ResultEnum.ERROR_ARGUMENTS_MISSING);
        }
        else if(ex instanceof MethodArgumentNotValidException) {
            log.error("参数错误，{}", ex.getMessage());
            MethodArgumentNotValidException exx = (MethodArgumentNotValidException) ex;
            List<ObjectError> errors = exx.getBindingResult().getAllErrors();
            String message = errors.get(0).getDefaultMessage();
            return ResultUtil.error(ResultEnum.REQUEST_PARAMETER_ERROR, message);
        }
        else {
           return ResultUtil.error(ex.getMessage());
        }
    }

}
