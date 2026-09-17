package com.demoqa.core;

public class Config {
    public static final String BASE_URL = "https://demoqa.com";

    public static boolean headless(){
        return Boolean.parseBoolean(System.getProperty("headless", "false"));

    }
    public  static double slowMo(){
        return Double.parseDouble(System.getProperty("slowMo", "0"));
    }

    public static String browser(){
        return System.getProperty("browser", "chromium");
    }

    private Config(){

    }
}
