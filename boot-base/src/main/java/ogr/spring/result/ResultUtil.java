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
        result.setCode(ResultEnum.success_code_msg.getCode());
        result.setMsg(ResultEnum.success_code_msg.getMsg());
        result.setData(null);
        return result;
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setData(data);
        result.setMsg(ResultEnum.success_code_msg.getMsg());
        result.setCode(ResultEnum.success_code_msg.getCode());
        return result;
    }

}
