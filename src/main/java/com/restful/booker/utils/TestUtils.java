package com.restful.booker.utils;

import java.util.Random;

public class TestUtils {
    public static String getRandomValue(){
        Random random = new Random();
        int randomInt = random.nextInt(100000);
        return Integer.toString(randomInt);
    }
    public static int getRandomNumber(){
        Random random = new Random();
        int number = random.nextInt(10000);
        return number;
    }

}
