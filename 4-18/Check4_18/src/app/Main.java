package app;

import constants.Constants;
import model.Book;
import model.Item;

public class Main {

    // 課題①
    private String firstName = "佐藤";
    private String lastName = "明日香";

    public static void main(String[] args) {

        Main main = new Main();

        // 課題② 呼び出し
        main.printName(main.firstName, main.lastName);

        // 課題③ インスタンス化
        Item item = new Item(Constants.ITEM_NAME, Constants.ITEM_PRICE);
        Book book = new Book(Constants.BOOK_NAME, Constants.BOOK_PRICE, Constants.BOOK_AUTHOR);

        // 表示
        item.showInfo();
        book.showInfo();

        // 課題④ 割引計算
        int discountedPrice = book.getDiscountPrice();
        System.out.println("割引後の書籍価格：" + discountedPrice + "円");
    }

 
    private void printName(String firstName, String lastName) {
        System.out.println("氏名：" + firstName + lastName);
    }
}