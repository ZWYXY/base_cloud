package com.micro.cloud;

import org.junit.jupiter.api.Test;

public class TestDemo1 {

    @Test
    public void ShiftRowTest() {

        int num = 1;
        int num2= 1;
        printInfo(num);
//      0000 0000 0000 1010 2*8+12
        num = (num << 31);
        printInfo(num);

//        num2 = (num2 << 32);
//        printInfo(num2);
//        // 左移一位
//        num = num << 1;
//        printInfo(num);
//
//        // 右移一位
//        num = num >> 3;
//        printInfo(num);

    }

    public void printInfo(int num) {
        System.err.println(Integer.toBinaryString(num));
        System.err.println(Integer.toBinaryString(num).length());

        System.err.println(num);
    }


}
