package com.novembergave.apps.booklistingapp;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static com.novembergave.apps.booklistingapp.MainActivity.BOOK_SEARCH_QUERY;

public class ResultsActivity extends AppCompatActivity implements LoaderCallbacks<List<BookData>> {

    private BookAdapter mAdapter;
    private TextView mEmptyStateTextView;
    public String compiledLink;
    public static final String BASE_JSON_LINK =
            "https://www.googleapis.com/books/v1/volumes?q=";

    private static final int BOOK_LOADER_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        mAdapter = new BookAdapter(this, new ArrayList<BookData>());

        // Find a reference to the {@link ListView} in the layout
        ListView newsListView = (ListView) findViewById(R.id.results_view);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        newsListView.setAdapter(mAdapter);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);

        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                BookData currentBook = mAdapter.getItem(position);
                Uri previewUrl = Uri.parse(currentBook.getPreviewUrl());

                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, previewUrl);

                startActivity(websiteIntent);

            }
        });

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            Intent intent = getIntent();
            String query = intent.getStringExtra(BOOK_SEARCH_QUERY);
            compiledLink = BASE_JSON_LINK + query;

            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(BOOK_LOADER_ID, null, this);
        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            // Update empty state with no connection error message
            mEmptyStateTextView.setText(getString(R.string.internet_error));
        }
    }

    @Override
    public Loader<List<BookData>> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given URL
        return new DataLoader(this, compiledLink);
    }

    @Override
    public void onLoadFinished(Loader<List<BookData>> loader, List<BookData> book) {
        // Hide loading indicator because the data has been loaded
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        // Set empty state text to display "No information found."
        mEmptyStateTextView.setText(getString(R.string.information_error));

        // Clear the adapter of previous news data
        mAdapter.clear();

        // If there is a valid list of {@link News}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (book != null && !book.isEmpty()) {
            mAdapter.addAll(book);
            mEmptyStateTextView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<BookData>> loader) {
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }
}
