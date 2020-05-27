package ogr.spring.result;

public enum ResultEnum {



    success_code_msg(0, "成功"),
    error_code_msg(-1, "失败")
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
