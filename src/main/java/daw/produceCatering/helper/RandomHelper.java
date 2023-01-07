package daw.produceCatering.helper;

import java.security.SecureRandom;
import java.util.Random;


public class RandomHelper {

    protected static SecureRandom random = new SecureRandom();

    public static int getRandomInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
    
}
