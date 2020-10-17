package util;

import java.math.BigDecimal;

public class Constants {

    public enum ProductCode {BREAD_A, BREAD_B, BREAD_C, BREAD_OLD, BEER_BELGIUM, BEER_DUTCH, BEER_GERMAN}

    public static final BigDecimal BELGIUM_BEER_COST =  new BigDecimal("2.00");
    public static final BigDecimal DUTCH_BEER_COST =  new BigDecimal("1.50");
    public static final BigDecimal GERMAN_BEER_COST =  new BigDecimal("1.00");

    public static final BigDecimal BREAD_PRICE =  new BigDecimal("2.00");
    public static final String RECEIPT_FORMAT = "%-30s%15s";
    public static final String RECEIPT_FORMAT_2 = "%45s";
}
