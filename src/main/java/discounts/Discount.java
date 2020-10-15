package discounts;

import products.ShoppingItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface Discount<T extends ShoppingItem> {

    void applyDiscounts(Map<String, BigDecimal> discounts, List<T> list);

}
