package products.beer;

import util.Constants;

import java.math.BigDecimal;
import java.util.Objects;

public class BelgiumBeer extends Beer{
    @Override
    public BigDecimal getPrice() {
        return Constants.BELGIUM_BEER_COST;
    }

    @Override
    public Constants.ProductCode getProductCode(){
        return Constants.ProductCode.BEER_BELGIUM;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductCode());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BelgiumBeer beer = (BelgiumBeer) o;
        return getProductCode().equals(beer.getProductCode());
    }
}
