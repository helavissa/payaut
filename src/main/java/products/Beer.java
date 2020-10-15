package products;

import java.math.BigDecimal;

public class Beer implements ShoppingItem{

    private enum BeerType{
        BELGIUM, DUTCH, GERMAN;
    }

    private BeerType type;

    public BeerType getType() {
        return type;
    }

    public void setType(BeerType type) {
        this.type = type;
    }

    public String getProductType(){
        return type + " beer";
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

}
