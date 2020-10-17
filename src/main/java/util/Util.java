package util;

import products.Order;
import products.beer.BelgiumBeer;
import products.beer.DutchBeer;
import products.beer.GermanBeer;
import products.bread.Bread;

import java.time.LocalDate;

public class Util {

    public static void addBeers(Order order, Constants.ProductCode beerType, int amount){
        for(int i = 0; i < amount; i++) {
            if (Constants.ProductCode.BEER_BELGIUM == beerType) {
                order.addItem(new BelgiumBeer());
            } else if (Constants.ProductCode.BEER_DUTCH == beerType) {
                order.addItem(new DutchBeer());
            } else if (Constants.ProductCode.BEER_GERMAN == beerType) {
                order.addItem(new GermanBeer());
            }
        }
    }

    public static void addBreads(Order order, int daysOld, int amount){
        for(int i = 0; i < amount; i++){
            order.addItem(new Bread(LocalDate.now().minusDays(daysOld)));
        }
    }
}
