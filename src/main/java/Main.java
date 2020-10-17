import products.Order;
import util.Constants;
import util.Util;

class Main {

    public static void main(String[] args) {

        Order order = new Order();
        Util.addBreads(order, 3, 5);
        Util.addBreads(order, 1, 5);
        Util.addBreads(order, 6, 5);
        Util.addBreads(order, 4, 3);
        Util.addBeers(order, Constants.ProductCode.BEER_BELGIUM, 20);
        Util.addBeers(order, Constants.ProductCode.BEER_DUTCH, 6);
        Util.addBeers(order, Constants.ProductCode.BEER_GERMAN, 11);

        order.printReceipt();
    }

}
