package com.jpinto.amaris.ui.ex3;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jpinto.amaris.R;
import com.jpinto.amaris.data.PostCodeOpenHelper;
import com.jpinto.amaris.utils.AdaptiveListAdapter;
import com.jpinto.amaris.utils.PostCodeListAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThirdFragment extends Fragment {

    private RecyclerView recyclerView;
    private AdaptiveListAdapter adapter;

    public ThirdFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.ex3_list_view, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerview3);


        adapter = new AdaptiveListAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return rootView;
    }

}
