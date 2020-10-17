package discounts;

import products.ShoppingItem;

import java.math.BigDecimal;

public class MinusTwoForPackPolicy implements DiscountPolicy {

    @Override
    public BigDecimal getDiscount(int amountInOrder, ShoppingItem shoppingItem){
        if(amountInOrder % 6 == 0){
            return new BigDecimal("2.00");
        }
        return BigDecimal.ZERO;
    }

}
