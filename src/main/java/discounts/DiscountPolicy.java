package discounts;

import java.math.BigDecimal;

public interface DiscountPolicy{

    default BigDecimal getDiscount(OrderState orderState){
        return BigDecimal.ZERO;
    }

}
