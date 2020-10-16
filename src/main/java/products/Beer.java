package products;

import discounts.DiscountPolicies;
import discounts.DiscountPolicy;

import java.math.BigDecimal;

public class Beer implements ShoppingItem{

    public enum BeerType{
        BELGIUM, DUTCH, GERMAN
    }

    public Beer(BeerType type) {
        this.type = type;
    }

    private BeerType type;

    public BeerType getType() {
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
                return new BigDecimal("2.00");
            case DUTCH:
                return new BigDecimal("1.50");
            case GERMAN:
                return new BigDecimal("1.00");
        }
        return null; // TODO: throw exception for unknown types?
    }

    @Override
    public DiscountPolicy getDiscountPolicy() {
        return DiscountPolicies.beerDiscountPolicy;
    }

}
