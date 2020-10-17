package products;

import discounts.DiscountPolicy;

import java.math.BigDecimal;

public interface ShoppingItem extends DiscountPolicy{
    BigDecimal getPrice();
    String getProductName();
}
