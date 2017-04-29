package com.novembergave.apps.booklistingapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by novembergave on 28/04/2017.
 */

public class DataLoader extends AsyncTaskLoader<List<BookData>> {

    private String bookUrl;

    public DataLoader(Context context, String url) {
        super(context);
        bookUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<BookData> loadInBackground() {
        if (bookUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of books.
        List<BookData> bookData = QueryUtils.fetchBooksData(bookUrl);
        return bookData;
    }
}
