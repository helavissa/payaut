package products;

import discounts.DiscountPolicy;

import java.math.BigDecimal;

public interface ShoppingItem {
    BigDecimal getPrice();
    DiscountPolicy getDiscountPolicy();
    String getProductName();
}
