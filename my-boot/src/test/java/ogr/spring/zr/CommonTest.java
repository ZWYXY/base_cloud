package ogr.spring.zr;


import org.junit.jupiter.api.Test;

public class CommonTest {

    @Test
    public void firstTest() {
        // 静态成员内部类
        OuterInner.T1 t1 = new OuterInner.T1();

        // 成员内部类
        OuterInner outerInner = new OuterInner();
        OuterInner.T2 t2 = outerInner.new T2();

    }

}
