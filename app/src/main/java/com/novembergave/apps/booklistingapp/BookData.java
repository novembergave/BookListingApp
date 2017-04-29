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

    public void setTitle(String title) {
        bookTitle = title;
    }

    public void setAuthor(String author) {
        bookAuthors = author;
    }

    public void setAuthor(ArrayList author) {
        String authorName = "";
        for (int i = 0; author.size() > 0; i++) {
            authorName += ", " + author.get(i).toString();
        }
        bookAuthors = authorName;
    }

    public void setDescription(String description) {
        bookDescription = description;
    }

    public void setPublishedDate(String publishedDate) {
        bookPublishedDate = publishedDate;
    }

    public void setPreviewUrl(String previewUrl) {
        bookPreviewUrl = previewUrl;
    }


}
