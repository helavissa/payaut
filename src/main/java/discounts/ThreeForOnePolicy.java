package discounts;

import products.ShoppingItem;

import java.math.BigDecimal;

public class ThreeForOnePolicy implements DiscountPolicy {

    @Override
    public BigDecimal getDiscount(int amountInOrder, ShoppingItem shoppingItem){
        if ((amountInOrder-1) %3 == 0) {
            return BigDecimal.ZERO;
        }else {
            return shoppingItem.getPrice();
        }
    }

}
