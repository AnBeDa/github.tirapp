package com.example.tirapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder>
                         implements Filterable {
//--------------------------------------------------------------------------------------------------    
    private ArrayList<tir_main_rvs_items> myExampleList;
    private ArrayList<tir_main_rvs_items> myExampleListFull;
    
    public static class ExampleViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImageView;
        public TextView  mTextView1;
        public TextView  mTextView2;

        public ExampleViewHolder(View itemView) {
            super(itemView);

            mImageView = itemView.findViewById(R.id.tir_rvs_img);
            mTextView1 = itemView.findViewById(R.id.tir_rvs_text1);
            mTextView2 = itemView.findViewById(R.id.tir_rvs_text2);
        }
    }

    public ExampleAdapter(ArrayList<tir_main_rvs_items> exampleList){

         myExampleList = exampleList;
         myExampleListFull = new ArrayList<>(exampleList);

    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {

        tir_main_rvs_items currentItem = myExampleList.get(position);

        holder.mImageView.setImageResource(currentItem.getmImageResource());
        holder.mTextView1.setText(currentItem.getmText1());
        holder.mTextView2.setText(currentItem.getmText2());

    }

    @Override
    public int getItemCount() {
        return myExampleList.size();
    }

    @Override
    public Filter getFilter() {
        return myExampleFilter;
    }

    private Filter myExampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            return null;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

        }
    };
}