package discounts;

import products.ShoppingItem;

import java.math.BigDecimal;

public interface DiscountPolicy{
    BigDecimal getDiscount(int amountInOrder, ShoppingItem shoppingItem);
}
