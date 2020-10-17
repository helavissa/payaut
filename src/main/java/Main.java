import products.Beer;
import products.Order;
import util.Util;

public class Main {

    public static void main(String[] args) {

        Order order = new Order();
        Util.addBreads(order, 3, 11);
        Util.addBreads(order, 1, 5);
        Util.addBreads(order, 6, 5);
        Util.addBreads(order, 4, 3);
        Util.addBeers(order, Beer.BeerType.BELGIUM, 20);
        Util.addBeers(order, Beer.BeerType.DUTCH, 6);
        Util.addBeers(order, Beer.BeerType.GERMAN, 11);

        order.printReceipt();
    }

}
