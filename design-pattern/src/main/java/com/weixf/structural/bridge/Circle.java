package com.weixf.structural.bridge;

/*
 *
 * @author weixf
 * @date 2022-08-25
 */
public class Circle extends Shape {

    private final int x;

    private final int y;

    private final int radius;

    public Circle(int x, int y, int radius, DrawAPI drawAPI) {
        super(drawAPI);
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public void draw() {
        drawAPI.drawCircle(radius, x, y);
    }
}
