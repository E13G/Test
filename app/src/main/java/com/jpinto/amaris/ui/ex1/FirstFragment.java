package com.jpinto.amaris.ui.ex1;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jpinto.amaris.utils.PostCodeListAdapter;
import com.jpinto.amaris.R;
import com.jpinto.amaris.data.PostCodeOpenHelper;


public class FirstFragment extends Fragment {

    private RecyclerView recyclerView;
    private EditText searchEditText;
    private TextView searchResultTextView;

    private ImageButton searchButton;
    private ImageButton refreshButton;

    private PostCodeListAdapter adapter;
    private PostCodeOpenHelper db;

    public FirstFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.ex1_list_view, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerview);
        searchButton = rootView.findViewById(R.id.search_btn);
        refreshButton = rootView.findViewById(R.id.refresh_btn);
        searchEditText = rootView.findViewById(R.id.edit_search);
        searchResultTextView = rootView.findViewById(R.id.search_result);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyBoard(v);
                showSearchResult();
            }
        });

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyBoard(v);
                showList();
            }
        });

        db = new PostCodeOpenHelper(getContext());

        adapter = new PostCodeListAdapter(getActivity(), db);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return rootView;
    }

    public void showSearchResult() {

        String word = searchEditText.getText().toString();
        if(word.length() == 0) {
            showList();
            return;
        }
        searchResultTextView.setText("Result for " + word + ":\n\n");

        // Search for the word in the database.
        Cursor cursor = db.search(word);
        // You must move the cursor to the first item.
        cursor.moveToFirst();
        // Only process a non-null cursor with rows.
        if (cursor != null & cursor.getCount() > 0) {
            int index;
            String result;
            // Iterate over the cursor, while there are entries.
            do {
                // Don't guess at the column index. Get the index for the named column.
                index = cursor.getColumnIndex(PostCodeOpenHelper.KEY_CODE);
                // Get the value from the column for the current cursor.
                result = cursor.getString(index);
                // Add result to what's already in the text view.
                searchResultTextView.append(result + "\n");

            } while (cursor.moveToNext());
            cursor.close();
        } else {
            searchResultTextView.append(getString(R.string.no_result));

        }
        searchResultTextView.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    public void showList(){
        recyclerView.setVisibility(View.VISIBLE);
        searchResultTextView.setVisibility(View.GONE);
    }

    public void hideKeyBoard(View v){

        if (v != null) {
            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

}
