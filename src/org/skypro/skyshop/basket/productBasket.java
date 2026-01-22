package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

public class productBasket { private Product[]products;
    private int count=0;
    public productBasket(){
        this.products=new Product[5];
    }
    public void addProduct (Product product){
        if (count < products.length) {
            products[count] = product;
            count++;
        } else {
            System.out.println("Невозможно добавить продукт");
        }
    }
    public int totalCost() {
        int total = 0;
        for (int i = 0; i < count; i++) {
            products[i].getPrice();
        }
        return total;
    }
    public void basketContents(){
        for(int i=0;i<count;i++){
            System.out.println(products[i].getName()+":"+products[i].getPrice());
        }
        if(count==0){
            System.out.println("В корзине пусто");
        }
    }

    public boolean productContents(String productName) {
        for (int i = 0; i < count; i++) {
            if (products[i].getName().equals(productName)) {
                return true;
            }
        }
        return false;
    }
    public void clear(){
        for (int i = 0; i < count; i++) {
            products[i] = null;
        }
        count = 0;
    }

}
