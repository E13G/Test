package com.jpinto.amaris.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jpinto.amaris.R;
import com.jpinto.amaris.data.PostCode;
import com.jpinto.amaris.data.PostCodeOpenHelper;

/*
 * Created by JPinto on 1/25/2018.
 */

public class PostCodeListAdapter
        extends RecyclerView.Adapter<PostCodeListAdapter.PostalCodeViewHolder> {

    private PostCodeOpenHelper db;
    private LayoutInflater inflater;

    public PostCodeListAdapter(Context context, PostCodeOpenHelper db) {
        inflater = LayoutInflater.from(context);
        this.db = db;

    }

    @Override
    public PostalCodeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.postal_code_item, parent, false);
        return new PostalCodeViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(PostalCodeViewHolder holder, int position) {
        PostCode current = db.query(position);
        holder.postCodeItemView.setText(current.getPostCode());
    }

    @Override
    public int getItemCount() {
        return (int) db.count();
    }

    class PostalCodeViewHolder extends RecyclerView.ViewHolder {

        final TextView postCodeItemView;
        final PostCodeListAdapter postCodeAdapter;

        PostalCodeViewHolder(View itemView, PostCodeListAdapter adapter) {
            super(itemView);

            postCodeItemView = itemView.findViewById(R.id.txt_postal_code);
            this.postCodeAdapter = adapter;
        }
    }
}
