package com.jpinto.amaris.ui.ex2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jpinto.amaris.R;
import com.jpinto.amaris.data.PostCodeOpenHelper;
import com.jpinto.amaris.utils.PostCodeListAdapter;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {

    private RecyclerView recyclerView;
    private ImageView imageView;

    private PostCodeListAdapter adapter;
    private PostCodeOpenHelper db;

    public SecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.ex2_list_view, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerview2);
        imageView = rootView.findViewById(R.id.img_header);

        Picasso.with(getContext())
                .load("https://cdn.pixabay.com/photo/2014/01/04/13/53/suspension-bridge-238519_960_720.jpg")
                .into(imageView);

        db = new PostCodeOpenHelper(getContext());

        adapter = new PostCodeListAdapter(getActivity(), db);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return rootView;
    }

}
