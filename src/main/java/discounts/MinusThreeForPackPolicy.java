package discounts;

import products.ShoppingItem;

import java.math.BigDecimal;

public class MinusThreeForPackPolicy implements DiscountPolicy {

    @Override
    public BigDecimal getDiscount(int amountInOrder, ShoppingItem shoppingItem){
        if(amountInOrder > 0 && amountInOrder % 6 == 0){
            return new BigDecimal("3.00");
        }
        return BigDecimal.ZERO;
    }

}

