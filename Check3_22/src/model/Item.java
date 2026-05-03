package model;

public class Item {
    private String name;
    private int price;

    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }

    protected String getName() {
        return name;
    }

    protected int getPrice() {
        return price;
    }

    public void showInfo() {
        System.out.println("■商品名：" + name);
        System.out.println("■価格：" + price + "円");
    }
}