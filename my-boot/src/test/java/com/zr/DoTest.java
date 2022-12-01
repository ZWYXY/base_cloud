package com.zr;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class DoTest {

    public static void main(String[] args) {
        ArrayList<String> strings = Lists.newArrayList("1-2", "3-4", "5-6");
        String collect = strings.stream().collect(Collectors.joining());
        System.err.println(collect);
    }
}
