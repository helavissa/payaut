package discounts;

import products.Beer;

import java.util.HashMap;
import java.util.Map;

public class OrderState {

    private int amountFreeBreads; // amount of free breads for customer
    private Map<Beer.BeerType, Integer> beersToDiscount; // counter of beers of each type, once pack(6) is reached, apply discount

    public OrderState() {
        amountFreeBreads = 0;
        beersToDiscount = new HashMap<>();
        for(Beer.BeerType beerType: Beer.BeerType.values()){
            beersToDiscount.put(beerType, 0);
        }
    }

    int getAmountFreeBreads() {
        return amountFreeBreads;
    }

    void setAmountFreeBreads(int amountFreeBreads) {
        this.amountFreeBreads = amountFreeBreads;
    }

    Map<Beer.BeerType, Integer> getBeersToDiscount() {
        return beersToDiscount;
    }

    void setBeersToDiscount(Map<Beer.BeerType, Integer> beersToDiscount) {
        this.beersToDiscount = beersToDiscount;
    }
}
