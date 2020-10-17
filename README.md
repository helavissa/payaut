
Implementation of Payaut Code Assignment by Dina.

Products are divided by the following product codes:
BREAD_A - bread that is two days old or newer
BREAD_B - bread between 3 and 5 days old 
BREAD_C - bread that is 6 days old
BREAD_OLD - bread that is more that 6 days old
BEER_BELGIUM - Belgium beer
BEER_DUTCH - Dutch beer
BEER_GERMAN - German beer

Products with same codes are considered equal.
Types of discounts are named as policies, and the rules as to which applies to which product are in DiscountRules.
For simplicity, only one rule applies to a particular product. 

Discounts are calculated as we add items to an order.
Methods Util.addBeers and Util.addBreads are added only for test convenience.
For simplicity, we cannot take BREAD_B and receive BREAD_C for free (even though it is logical), because they are of different codes.
