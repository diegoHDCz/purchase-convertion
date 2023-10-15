package dev.diegoczajka.conversionpurchase.utils;

public class ValidCountryString {
    public static String transform(String country){
        String result = country.trim();
        return result.substring(0,1).toUpperCase() + result.substring(1).toLowerCase();
    }

}
