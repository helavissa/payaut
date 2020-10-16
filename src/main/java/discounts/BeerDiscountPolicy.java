package discounts;

import products.Beer;

import java.math.BigDecimal;
import java.util.Map;

public class BeerDiscountPolicy implements DiscountPolicy<Beer> {

    @Override
    public BigDecimal getDiscount(Beer beer, Discounts discounts) {
        Map<Beer.BeerType, Integer> beersToDiscount = discounts.getBeersToDiscount();

        if(beersToDiscount.get(beer.getType()) >= 6){ // if pack is reached apply discount, set counter to 0 for this kind of beer
            beersToDiscount.put(beer.getType(), 0);
            switch (beer.getType()) {
                case BELGIUM:
                    return new BigDecimal("3.00");
                case DUTCH:
                    return new BigDecimal("2.00");
                case GERMAN:
                    return new BigDecimal("1.00");
            }
        }else{ // pack is not reached, increase the counter
            beersToDiscount.put(beer.getType(), beersToDiscount.get(beer.getType()) + 1);
        }
        discounts.setBeersToDiscount(beersToDiscount);
        return new BigDecimal("0.00");

    }

}
