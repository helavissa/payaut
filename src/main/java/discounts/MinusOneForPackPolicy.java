package discounts;

import products.ShoppingItem;

import java.math.BigDecimal;

public class MinusOneForPackPolicy implements DiscountPolicy {

    @Override
    public BigDecimal getDiscount(int amountInOrder, ShoppingItem shoppingItem){
        if(amountInOrder % 6 == 0){
            return BigDecimal.ONE;
        }
        return BigDecimal.ZERO;
    }

}
