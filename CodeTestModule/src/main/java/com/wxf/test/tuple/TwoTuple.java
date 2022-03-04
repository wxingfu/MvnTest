package com.wxf.test.tuple;

/**
 * @author weixf
 * @since 2022-03-04
 */
public class TwoTuple<A, B> {

    public final A first;

    public final B second;

    public TwoTuple(A a, B b) {
        first = a;
        second = b;
    }

    public String toString() {
        return "(" + first + ", " + second + ")";
    }

}
