package com.novembergave.apps.booklistingapp;

import java.util.ArrayList;


/**
 * Created by novembergave on 28/04/2017.
 */

public class BookData {

    private String bookTitle;
    private String bookAuthors;
    private String bookDescription;
    private String bookPublishedDate;
    private String bookPreviewUrl;

    public BookData(String title, String authorName, String description, String publishedDate, String previewUrl) {
        bookTitle = title;
        bookAuthors = authorName;
        bookDescription = description;
        bookPublishedDate = publishedDate;
        bookPreviewUrl = previewUrl;
    }

    public String getTitle() {
        return bookTitle;
    }

    public String getAuthor() {
        return bookAuthors;
    }

    public String getDescription() {
        return bookDescription;
    }

    public String getPublishedDate() {
        return bookPublishedDate;
    }

    public String getPreviewUrl() {
        return bookPreviewUrl;
    }

}
