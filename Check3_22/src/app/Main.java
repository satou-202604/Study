package app;

import constants.Constants;
import model.Book;
import model.Item;

public class Main {

    // 課題①
    private String firstName = "佐藤";
    private String lastName = "明日香";

    // 課題②
    private void printName(String firstName, String lastName) {

        System.out.println("氏名：" + firstName + lastName);
    }

    public static void main(String[] args) {

        Main main = new Main();

        // 氏名表示
        main.printName(main.firstName, main.lastName);

        // 課題③
        Item item =
                new Item(
                        Constants.ITEM_NAME,
                        Constants.ITEM_PRICE);

        Book book =
                new Book(
                        Constants.BOOK_NAME,
                        Constants.BOOK_PRICE,
                        Constants.BOOK_AUTHOR);

        item.showInfo();

        book.showInfo();
    }
}