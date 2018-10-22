package com.example;

import java.util.LinkedList;
import java.util.List;

public class DemoTest {

    public static void main(String[] args) {
        test();
    }

    public static void test() {
        List<Integer> list = new LinkedList<>();
        list.add(665);
        list.add(235);
        list.add(871);
        list.add(602);
        list.add(741);
        list.add(666);
        list.add(327);
        list.add(305);
        list.add(811);
        list.add(465);
        System.out.println(list);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) % 3 == 0) {
                list.remove(i);
                i--;
            }
        }
        System.out.println(list);
    }
}
