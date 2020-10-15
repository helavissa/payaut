package discounts;

import products.Bread;
import util.Util;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class BreadDiscount implements Discount<Bread>{

    public void applyDiscounts(Map<String, BigDecimal> discounts, List<Bread> breadList){
        // TODO: checks for null for map

        // TODO: sort array, rewrite in java 8
        int amountFreeBreads = 0;
        for(Bread bread: breadList){
            if(amountFreeBreads > 0){ // apply the discount if applicable
                if(!bread.isTwoDaysOldOrLess()){
                    Util.addDiscount(discounts, bread.getProductName(), bread.getPrice());
                    amountFreeBreads--;
                }
            }else{ // discount for next breads
                if(bread.isBetweenThreeAndFiveDaysOld()){
                    amountFreeBreads++;
                }else if(bread.isSixDaysOld()){
                    amountFreeBreads = amountFreeBreads + 2;
                }
            }
        }
    }

}
