package com.novembergave.apps.booklistingapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by novembergave on 28/04/2017.
 */

public class BookAdapter extends ArrayAdapter<BookData> {

    public BookAdapter(Context context, List<BookData> data) {
        super(context, 0, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.book_item, parent, false);
        }

        BookData currentBookItem = getItem(position);

        TextView title = (TextView) listItemView.findViewById(R.id.item_title);
        title.setText(currentBookItem.getTitle());

        TextView author = (TextView) listItemView.findViewById(R.id.item_author);
        author.setText(currentBookItem.getAuthor());

        TextView date = (TextView) listItemView.findViewById(R.id.item_date);
        date.setText(currentBookItem.getPublishedDate());

        TextView description = (TextView) listItemView.findViewById(R.id.item_description);
        description.setText(currentBookItem.getDescription());

        return listItemView;
    }
}

