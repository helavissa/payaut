package products.beer;

import util.Constants;

import java.math.BigDecimal;
import java.util.Objects;

public class DutchBeer extends Beer{
    @Override
    public BigDecimal getPrice() {
        return Constants.DUTCH_BEER_COST;
    }

    @Override
    public Constants.ProductCode getProductCode(){
        return Constants.ProductCode.BEER_DUTCH;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductCode());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DutchBeer beer = (DutchBeer) o;
        return getProductCode().equals(beer.getProductCode());
    }
}
