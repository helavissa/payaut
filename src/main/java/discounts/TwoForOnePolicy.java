package discounts;

import products.ShoppingItem;

import java.math.BigDecimal;


public class TwoForOnePolicy implements DiscountPolicy {

    @Override
    public BigDecimal getDiscount(int amountInOrder, ShoppingItem shoppingItem){
        if (amountInOrder %2 == 0) {
            return shoppingItem.getPrice();
        }else {
            return BigDecimal.ZERO;
        }
    }

}
