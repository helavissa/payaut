package discounts;

import products.ShoppingItem;

import java.math.BigDecimal;

public interface DiscountPolicy<T extends ShoppingItem> {

    BigDecimal getDiscount(T t, OrderState orderState);

}
