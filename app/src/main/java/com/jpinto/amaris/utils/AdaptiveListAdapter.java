package com.jpinto.amaris.utils;

/*
 * Created by JPinto on 1/25/2018.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.jpinto.amaris.R;

public class AdaptiveListAdapter
        extends RecyclerView.Adapter<AdaptiveListAdapter.AdaptiveViewHolder> {

    private LayoutInflater inflater;

    public AdaptiveListAdapter(Context context) {
        inflater = LayoutInflater.from(context);

    }

    @Override
    public AdaptiveViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.ex3_list_item, parent, false);
        return new AdaptiveViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(AdaptiveViewHolder holder, int position) {
        switch (position%3){
            case 0:
                holder.textView.setText("Normal Text");
                holder.editText.setInputType(InputType.TYPE_CLASS_TEXT);
                break;
            case 1:
                holder.textView.setText("Caps Text");
                holder.editText.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
                break;
            case 2:
                holder.textView.setText("Number 123");
                holder.editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                break;
            default:
        }
    }

    @Override
    public int getItemCount () {
        return 50;
    }


    class AdaptiveViewHolder extends RecyclerView.ViewHolder {

        final TextView textView;
        final EditText editText;
        final AdaptiveListAdapter adaptiveAdapter;

        AdaptiveViewHolder(View itemView, AdaptiveListAdapter adapter) {
            super(itemView);

            textView = itemView.findViewById(R.id.txt_description);
            editText = itemView.findViewById(R.id.edit_normal);
            this.adaptiveAdapter = adapter;
        }
    }
}
