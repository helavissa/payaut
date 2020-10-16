package discounts;

import products.Bread;

import java.math.BigDecimal;

public class BreadDiscountPolicy implements DiscountPolicy<Bread> {

    @Override
    public BigDecimal getDiscount(Bread bread, OrderState orderState) {
        if(bread.isTwoDaysOldOrLess()){
            return new BigDecimal("0.00");
        }

        if(orderState.getAmountFreeBreads() > 0){ // discount if applicable
            orderState.setAmountFreeBreads(orderState.getAmountFreeBreads() - 1);
            return bread.getPrice();
        }else{ // discount for next breads
            if(bread.isBetweenThreeAndFiveDaysOld()){
                orderState.setAmountFreeBreads(orderState.getAmountFreeBreads() + 1);
            }else if(bread.isSixDaysOld()){
                orderState.setAmountFreeBreads(orderState.getAmountFreeBreads() + 2);
            }
        }

        return new BigDecimal("0.00");
    }

}
