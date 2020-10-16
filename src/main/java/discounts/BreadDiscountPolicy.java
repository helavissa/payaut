package discounts;

import products.Bread;

import java.math.BigDecimal;

public class BreadDiscountPolicy implements DiscountPolicy<Bread> {

    @Override
    public BigDecimal getDiscount(Bread bread, Discounts discounts) {
        if(bread.isTwoDaysOldOrLess()){
            return new BigDecimal("0.00");
        }

        if(discounts.getAmountFreeBreads() > 0){ // discount if applicable
            discounts.setAmountFreeBreads(discounts.getAmountFreeBreads() - 1);
            return bread.getPrice();
        }else{ // discount for next breads
            if(bread.isBetweenThreeAndFiveDaysOld()){
                discounts.setAmountFreeBreads(discounts.getAmountFreeBreads() + 1);
            }else if(bread.isSixDaysOld()){
                discounts.setAmountFreeBreads(discounts.getAmountFreeBreads() + 2);
            }
        }

        return new BigDecimal("0.00");
    }

}
