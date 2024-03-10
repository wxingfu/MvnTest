package com.weixf.collection;

import java.util.ArrayList;
import java.util.List;

public class MyList {

    public static void main(String[] args) {
        List<String> list1 = new ArrayList<String>();
        list1.add("A");
        list1.add("B");
        list1.add("E");

        List<String> list2 = new ArrayList<String>();
        list2.add("B");
        list2.add("C");
        list2.add("D");

        test1(list1, list2);
        test2(list1, list2);
        test3(list1, list2);
        test4(list1, list2);
    }

    // 0.求差集 求List1中有的但是List2中没有的元素
    public static void test1(List<String> list1, List<String> list2) {
        list1.removeAll(list2);
        System.out.println(list1);
    }

    // 1.求并集(不去重)---将一个集合全部加入另一个集合
    public static void test2(List<String> list1, List<String> list2) {
        list1.addAll(list2);
        System.out.println(list1);
    }

    /**
     * 2.求并集(去重)
     * 例如:求List1和List2的并集,并实现去重。
     * 思路是:先将list中与list2重复的去掉，之后将list2的元素全部添加进去。
     */
    public static void test3(List<String> list1, List<String> list2) {
        list1.removeAll(list2);
        list1.addAll(list2);
        System.out.println(list1);
    }

    // 3.求交集 求List1和List2中都有的元素。
    public static void test4(List<String> list1, List<String> list2) {
        list1.retainAll(list2);
        System.out.println(list1);
    }
}
