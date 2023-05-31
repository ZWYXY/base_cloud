package utils;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OneUtil {

    public void updateStatistics() {
        // 判断当前时间有没有超过统计时间
        String statisticTimeCronExpression = "0 30 1 * * ?";
        //  呼叫发起时间，计算下一次执行时间
        LocalDateTime nextValidTime = CronUtil.getNextValidTime(statisticTimeCronExpression, LocalDateTime.now());
        // 判断当前时间是否在指定时间的下次执行之后
        System.err.println(LocalDateTime.now().isAfter(nextValidTime));
    }

    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        strings.add("a");
        strings.add("b");
        m1(strings.toArray(new String[0]));
    }

    public static void m1(String... abc) {
        System.err.println(Arrays.toString(Arrays.stream(abc).toArray()));
    }

}
