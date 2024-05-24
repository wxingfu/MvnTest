package com.weixf.fx;

import javafx.application.Application;
import javafx.stage.Stage;

public class Test1 extends Application {


    public static void main(String[] args) {
        Application.launch(args);
    }


    @Override
    public void init() throws Exception {
        System.out.println("1111111111111111111");
        super.init();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("33333333333333333333");
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        System.out.println("22222222222222222222");
        super.stop();
    }

}
