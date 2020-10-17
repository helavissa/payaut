package discounts;

import products.Beer;

import java.util.HashMap;
import java.util.Map;

public class OrderState {

    private int amountFreeBreadsB; // amount of free breads for customer of type isBetweenThreeAndFiveDaysOld
    private int amountFreeBreadsC; // amount of free breads for customer of type isSixDaysOld
    private Map<Beer.BeerType, Integer> beersToDiscount; // counter of beers of each type, quantity until discount (2 means 4 more for pack discount)

    public OrderState() {
        amountFreeBreadsB = 0;
        amountFreeBreadsC = 0;
        beersToDiscount = new HashMap<>();
        for(Beer.BeerType beerType: Beer.BeerType.values()){
            beersToDiscount.put(beerType, 6);
        }
    }

    public int getAmountFreeBreadsB() {
        return amountFreeBreadsB;
    }

    public void setAmountFreeBreadsB(int amountFreeBreadsB) {
        this.amountFreeBreadsB = amountFreeBreadsB;
    }

    public int getAmountFreeBreadsC() {
        return amountFreeBreadsC;
    }

    public void setAmountFreeBreadsC(int amountFreeBreadsC) {
        this.amountFreeBreadsC = amountFreeBreadsC;
    }

    public Map<Beer.BeerType, Integer> getBeersToDiscount() {
        return beersToDiscount;
    }

    public void setBeersToDiscount(Map<Beer.BeerType, Integer> beersToDiscount) {
        this.beersToDiscount = beersToDiscount;
    }
}
