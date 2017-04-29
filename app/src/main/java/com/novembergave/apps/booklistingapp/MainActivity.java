package com.novembergave.apps.booklistingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String BOOK_SEARCH_QUERY = "SEARCH_QUERY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText keywordField = (EditText) findViewById(R.id.keyword_field);
        Button searchButton = (Button) findViewById(R.id.search_button);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchQuery = keywordField.getText().toString().trim();
                Intent searchIntent = new Intent(MainActivity.this, ResultsActivity.class);
                searchIntent.putExtra(BOOK_SEARCH_QUERY, searchQuery);
                startActivity(searchIntent);
            }
        });
    }
}
