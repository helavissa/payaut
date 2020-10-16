import products.Beer;
import products.ShoppingItem;
import util.Util;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<ShoppingItem> shoppingList = new ArrayList<>();
        Util.addBreads(shoppingList, 3, 11);
        Util.addBreads(shoppingList, 1, 5);
        Util.addBreads(shoppingList, 6, 5);
        Util.addBreads(shoppingList, 4, 3);
        Util.addBeers(shoppingList, Beer.BeerType.BELGIUM, 20);
        Util.addBeers(shoppingList, Beer.BeerType.DUTCH, 6);
        Util.addBeers(shoppingList, Beer.BeerType.GERMAN, 11);

        Util.printReceipt(shoppingList);
    }

}
