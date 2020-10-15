package util;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class Util {

    public static void addDiscount(Map<String, BigDecimal> discounts, String productType, BigDecimal productDiscount){
        // TODO: check for null?
        BigDecimal discount = discounts.get(productType);
        if(discount == null){
            discounts.put(productType, productDiscount);
        }else{
            discounts.put(productType, discount.add(productDiscount));
        }
    }

    public static void printDiscounts(Map<String, BigDecimal> discounts){
        discounts.forEach((key, value) -> System.out.println(key + ": " + value));
    }
}
