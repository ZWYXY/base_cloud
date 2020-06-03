package ogr.spring.result;

public enum ResultEnum {

    SUCCESS_CODE_MSG(0, "成功"),
    ERROR_CODE_MSG(-1, "失败"),
    ERROR_ARGUMENTS_MISSING(400, "参数缺失"),
    ERROR_NULL_POINTER(500, "空指针异常"),
    REQUEST_PARAMETER_ERROR(10001, "请求参数错误"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误")
    ;

    private final Integer code;
    private final String  msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
