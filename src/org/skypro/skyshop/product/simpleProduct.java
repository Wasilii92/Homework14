package org.skypro.skyshop.product;

public class simpleProduct extends Product {
private final int price;
    public simpleProduct(String name,int price){
        super (name);
this.price=price;

    }

    @Override
    public int getPrice() {
        return price;
    }
    @Override
    public boolean isSpecial() {
        return false;
    }

    @Override
    public String toString() {
        return getName()+":"+getPrice();

    }


}
