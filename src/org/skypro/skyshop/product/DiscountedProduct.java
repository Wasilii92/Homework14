package org.skypro.skyshop.product;

public class DiscountedProduct extends Product{
    private final int basePrice;
    private final int discount;
    public DiscountedProduct(String name, int basePrice, int discount) {
        super(name);
        this.basePrice = basePrice;
        this.discount = discount;
    }


    @Override
    public int getPrice() {
        double discountMultiplier = (100.0 - discount) / 100.0;
        return (int) (basePrice * discountMultiplier);
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return getName() + ": " + getPrice() + " (" + discount + "%)";
    }

}
