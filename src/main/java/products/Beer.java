package products;

import discounts.OrderState;
import util.Constants;

import java.math.BigDecimal;
import java.util.Map;

public class Beer implements ShoppingItem{

    public enum BeerType{
        BELGIUM, DUTCH, GERMAN
    }

    public Beer(BeerType type) {
        this.type = type;
    }

    private BeerType type;

    private BeerType getType() {
        return type;
    }

    public void setType(BeerType type) {
        this.type = type;
    }

    public String getProductName(){
        return type.toString();
    }

    @Override
    public BigDecimal getPrice() {
        switch (type){
            case BELGIUM:
                return Constants.BELGIUM_BEER_COST;
            case DUTCH:
                return Constants.DUTCH_BEER_COST;
            case GERMAN:
                return Constants.GERMAN_BEER_COST;
            default:
                throw new IllegalArgumentException("Unknown type of beer");
        }

    }

    @Override
    public BigDecimal getDiscount(OrderState orderState) {
        Map<BeerType, Integer> beersToDiscount = orderState.getBeersToDiscount();

        beersToDiscount.put(this.getType(), beersToDiscount.get(this.getType()) - 1); // decrease the counter for this kind of beer
        if(beersToDiscount.get(this.getType()) == 0){ // if pack is reached apply discount, set counter to 6 for this kind of beer
            beersToDiscount.put(this.getType(), 6);
            switch (this.getType()) {
                case BELGIUM:
                    return Constants.BELGIUM_BEER_PACK_DISCOUNT;
                case DUTCH:
                    return Constants.DUTCH_BEER_PACK_DISCOUNT;
                case GERMAN:
                    return Constants.GERMAN_BEER_PACK_DISCOUNT;
            }
        }

        orderState.setBeersToDiscount(beersToDiscount);
        return BigDecimal.ZERO;

    }

}
