package products.beer;

import util.Constants;

import java.math.BigDecimal;
import java.util.Objects;

public class GermanBeer extends Beer {
    @Override
    public BigDecimal getPrice() {
        return Constants.GERMAN_BEER_COST;
    }

    @Override
    public Constants.ProductCode getProductCode(){
        return Constants.ProductCode.BEER_GERMAN;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductCode());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GermanBeer beer = (GermanBeer) o;
        return getProductCode().equals(beer.getProductCode());
    }
}
