package com.example.mylibrary;

import java.util.ArrayList;

public class Util {
    private static ArrayList<Book> allBooks;
    private static ArrayList<Book> currentlyReadingBooks;
    private static ArrayList<Book> wantToReadBooks;
    private static ArrayList<Book> alreadyReadBooks;

    private static int id = 0;

    public Util() {
        if (allBooks == null){
            allBooks = new ArrayList<>();
            initAllBooks();
        }
        if (currentlyReadingBooks == null){
            currentlyReadingBooks = new ArrayList<>();
        }
        if (wantToReadBooks == null){
            wantToReadBooks = new ArrayList<>();
        }
        if (alreadyReadBooks == null){
            alreadyReadBooks = new ArrayList<>();
        }

    }

    public static ArrayList<Book> getAllBooks() {
        return allBooks;
    }

    public static ArrayList<Book> getCurrentlyReadingBooks() {
        return currentlyReadingBooks;
    }

    public static ArrayList<Book> getWantToReadBooks() {
        return wantToReadBooks;
    }

    public static ArrayList<Book> getAlreadyReadBooks() {
        return alreadyReadBooks;
    }

    public boolean addCurrentlyReadingBooks(Book book){
       return currentlyReadingBooks.add(book);
    }

    public boolean addWantToReadBooks(Book book){
       return wantToReadBooks.add(book);
    }

    public boolean addAlreadyReadBooks(Book book){
       return alreadyReadBooks.add(book);
    }

    public boolean removeCurrentlyReadingBooks(Book book){
       return currentlyReadingBooks.remove(book);
    }

    public boolean removeWantToReadBooks(Book book){
       return wantToReadBooks.remove(book);
    }

    public boolean removeAlreadyReadBooks(Book book){
       return alreadyReadBooks.remove(book);
    }

    public static void initAllBooks(){

        String name = "";
        String author = "";
        int pages;
        String imageURL = "";
        String description = "";

        id++;
        name = "Data and Golaith";
        author = "Bruce Schneier";
        pages = 207;
        imageURL = "https://m.media-amazon.com/images/I/51rddzqd88L.jpg";
        description ="In Data and Goliath, Schneier reveals the full extent of surveillance, censorship, and propaganda in society today, examining the risks of cybercrime, cyberterrorism, and cyberwar. He shares technological, legal, and social solutions that can help shape a more equal, private, and secure world.";

        allBooks.add(new Book(id, name, author, pages, imageURL, description));

        id++;
        name = "The Art of Invisibility";
        author = "Kevin Mitnick";
        pages = 320;
        imageURL = "https://images-na.ssl-images-amazon.com/images/I/41w3qUAvejL._SX332_BO1,204,203,200_.jpg";
        description = "Kevin Mitnick, the world's most famous hacker, teaches you easy cloaking and counter-measures for citizens and consumers in the age of Big Brother and Big Data.";
        allBooks.add(new Book(id, name, author, pages, imageURL, description));

        id++;
        name = "Permanent Record";
        author = "Edward Snowden";
        pages = 352;
        imageURL = "https://m.media-amazon.com/images/I/51ib1G1AKcL.jpg";
        description = "Permanent Record is a 2019 autobiography by Edward Snowden, whose revelations sparked a global debate about surveillance.";
        allBooks.add(new Book(id, name, author, pages, imageURL, description));

    }

}
