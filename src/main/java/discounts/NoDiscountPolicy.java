package discounts;

import products.ShoppingItem;

import java.math.BigDecimal;

public class NoDiscountPolicy implements DiscountPolicy {
    @Override
    public BigDecimal getDiscount(int amountInOrder, ShoppingItem productType){
        return BigDecimal.ZERO;
    }
}
