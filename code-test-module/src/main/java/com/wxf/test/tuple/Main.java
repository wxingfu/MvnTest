package com.wxf.test.tuple;

import org.javatuples.Pair;

/**
 * @author weixf
 * @since 2022-03-04
 */
public class Main {

    static TwoTuple<String, Integer> f() {
        return new TwoTuple<>("hi", 47);
    }

    static ThreeTuple<Amphibian, String, Integer> g() {
        return new ThreeTuple<>(new Amphibian(), "hi", 47);
    }

    static FourTuple<Vehicle, Amphibian, String, Integer> h() {
        return new FourTuple<>(new Vehicle(), new Amphibian(), "hi", 47);
    }

    static class Amphibian {
    }

    static class Vehicle {
    }

    public static void main(String[] args) {
        // TwoTuple<String, Integer> ttsi = f();
        // System.out.println(ttsi);
        // System.out.println(g());
        // System.out.println(h());
        Pair<Integer, String> pair = new Pair<>(1, "dddd");

        Pair<Pair<Integer, String>, String> pair2 = new Pair<>(new Pair<>(1, "dddd"), "dddd");

        Integer value0 = pair2.getValue0().getValue0();
        System.out.println(value0);
    }
}
