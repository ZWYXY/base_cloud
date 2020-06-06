package ogr.spring.exception;


import ogr.spring.result.ResultEnum;

public class DBOPException extends RuntimeException {

    private String message;
    private ResultEnum resultEnum;

    public DBOPException() { }

    public DBOPException(ResultEnum resultEnum) {
        this.message = resultEnum.getMsg();
        this.resultEnum = resultEnum;
    }

    public String getMessage() {
        return message;
    }

    public ResultEnum getResultEnum() {
        return resultEnum;
    }
}
