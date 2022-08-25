package com.wxf.structural.bridge;

/*
 *
 * @author weixf
 * @date 2022-08-25
 */
public class RedCircle implements DrawAPI {

    @Override
    public void drawCircle(int radius, int x, int y) {
        System.out.println("Drawing Circle[ color: red, radius: " + radius + ", x: " + x + ", " + y + "]");
    }

}
