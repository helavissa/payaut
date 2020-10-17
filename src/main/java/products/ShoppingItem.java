package products;

import util.Constants;

import java.math.BigDecimal;

public interface ShoppingItem{
    Constants.ProductCode getProductCode();
    BigDecimal getPrice();
}
