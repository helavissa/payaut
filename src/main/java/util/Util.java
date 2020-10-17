package util;

import products.Beer;
import products.Bread;
import products.Order;

import java.time.LocalDate;

public class Util {

    public static void addBeers(Order order, Beer.BeerType beerType, int amount){
        for(int i = 0; i < amount; i++){
            order.addItem(new Beer(beerType));
        }
    }

    public static void addBreads(Order order, int daysOld, int amount){
        for(int i = 0; i < amount; i++){
            order.addItem(new Bread(LocalDate.now().minusDays(daysOld)));
        }
    }
}
