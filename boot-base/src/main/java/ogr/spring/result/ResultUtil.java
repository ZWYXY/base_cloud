package ogr.spring.result;

public class ResultUtil {
//
//    @Data
//    @NoArgsConstructor
//    @AllArgsConstructor
//    public static class Result<T> {
//        private Integer code;
//        private String  msg;
//        private T data;
//    }

    public static <T> Result<T> success() {
        Result<T> result = new Result<>();
        result.setCode(ResultEnum.SUCCESS_CODE_MSG.getCode());
        result.setMsg(ResultEnum.SUCCESS_CODE_MSG.getMsg());
        result.setData(null);
        return result;
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setData(data);
        result.setMsg(ResultEnum.SUCCESS_CODE_MSG.getMsg());
        result.setCode(ResultEnum.SUCCESS_CODE_MSG.getCode());
        return result;
    }

    public static <T> Result<T> error(ResultEnum resultEnum) {
        Result<T> result = new Result<>();
        result.setMsg(resultEnum.getMsg());
        result.setCode(resultEnum.getCode());
        result.setData(null);
        return result;
    }

    public static <T> Result<T> error(String msg) {
        Result<T> result = new Result<>();
        result.setMsg(msg);
        result.setCode(ResultEnum.INTERNAL_SERVER_ERROR.getCode());
        result.setData(null);
        return result;
    }

    public static <T> Result<T> error(ResultEnum resultEnum, String msg) {
        Result<T> result = new Result<>();
        result.setMsg(resultEnum.getMsg() + msg);
        result.setCode(ResultEnum.INTERNAL_SERVER_ERROR.getCode());
        result.setData(null);
        return result;
    }

}
