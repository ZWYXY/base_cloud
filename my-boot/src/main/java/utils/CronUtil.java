package utils;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * 类描述：corn解析工具
 * @author 8593
 * @date 2023-05-09 9:48:56
 * 版权所有 Copyright www.dahantc.com
 */
//@Slf4j
public class CronUtil {

    /**
     * 判断当前时间是否符合cron表达式
     *
     * @param cronExpression cron表达式
     * @return true表示符合条件，false表示不符合条件
     */
    public static boolean isCronTriggerMatch(String cronExpression, LocalDateTime currentDate) {
        // 判断表达式是否合法
        if (!CronExpression.isValidExpression(cronExpression)) {
            throw new IllegalArgumentException("Invalid cron expression");
        }

        try {
            // 创建CronExpression对象
            CronExpression cron = new CronExpression(cronExpression);

            // 判断指定时间是否符合cron表达式
            return cron.isSatisfiedBy(DateUtil.convertToDate(currentDate));
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid cron expression");
        }
    }

    /**
     * 根据cron表达式计算下一次，执行时间
     * @param cronExpression cron 表达式
     * @param localDateTime 时间
     * @return 下一次执行时间
     */
    public static LocalDateTime getNextValidTime(String cronExpression, LocalDateTime localDateTime) {
        try {
            // 创建 CronExpression 对象
            CronExpression cron = new CronExpression(cronExpression);
            // 计算下一次执行时间
            Date nextExecutionTime = cron.getNextValidTimeAfter(DateUtil.convertToDate(localDateTime));

            // 将日期时间转换为 LocalDateTime 对象
            return LocalDateTime.ofInstant(nextExecutionTime.toInstant(), ZoneId.systemDefault());
        } catch (ParseException e) {
//            log.error("计算下一次执行时间失败，原因：", e);
        }
        return null;
    }

    public static void main(String[] args) {
        String cronExpression = "0 0 12 * * ?";
        boolean isMatch = isCronTriggerMatch(cronExpression,LocalDateTime.now());
        System.out.println(isMatch ? "符合条件" : "不符合条件");


        String cronExpression1 = "0 0 12 ? * MON,TUE,WED,THU,FRI,SAT,SUN *";
        CronExpression expression;
        try {
            expression = new CronExpression(cronExpression1);
            expression.getNextValidTimeAfter(new Date());
        } catch (ParseException e) {
//            log.error("error.....", e);
        }
    }

}


