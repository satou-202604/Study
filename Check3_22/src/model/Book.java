package model;

public class Book extends Item {
    private String author;

    public Book(String name, int price, String author) {
        super(name, price);
        this.author = author;
    }

    @Override
    public void showInfo() {
        System.out.println("◇書籍名：" + getName());
        System.out.println("◇価格：" + getPrice() + "円");
        System.out.println("◇著者：" + author);
    }
}