package com.example.konrad.cryptoalerts.utils.stringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FiatUtils {
    public static String DOLAR = "$";
    public static String EURO  = "€";


    public static String doubleToFiatValue(double value, int round, String symbol){
            BigDecimal bd = new BigDecimal(value);
            bd = bd.setScale(round, RoundingMode.HALF_UP);

            String fiatValue = String.valueOf(bd) + symbol;

            return fiatValue;
    }
}
