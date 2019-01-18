package com.example.deepaksharma.androidcode.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.RowTextListBinding;
import com.example.deepaksharma.androidcode.view.home.HomeActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private HomeActivity mHomeActivity ;
    private List<String> mAction;
    private List<String> searchArray ;
private String searchText;
    public HomeAdapter(HomeActivity homeActivity, List<String> action) {
        this.mHomeActivity = homeActivity;
        this.mAction = action;
        this.searchArray = new ArrayList<>();
        this.searchArray.addAll(action);
    }
    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowTextListBinding mBinding = DataBindingUtil.
                inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.row_text_list,
                        parent, false);
        return new HomeAdapter.ViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder holder, int position) {
        holder.binding();
    }

    @Override
    public int getItemCount() {
        return mAction.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private RowTextListBinding binding;

        public ViewHolder(RowTextListBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }

        public void binding() {
//            if (searchText!=null && searchText.length()>0){
//
//            }
            binding.txtName.setText(mAction.get(getAdapterPosition()));
            binding.card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mHomeActivity.onClicks(mAction.get(getAdapterPosition()));
                }
            });
        }
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        searchText = charText;
        mAction.clear();
        if (charText == null && charText.length() == 0) {
            mAction.addAll(searchArray);
        } else {
            for (String wp : searchArray) {
                if (wp!=null && wp.toLowerCase(Locale.getDefault()).contains(charText)) {
                    mAction.add(wp);
                }
//                else if (wp.toLowerCase(Locale.getDefault()).contains(charText)) {
//                    mAction.add(wp);
//                }
            }
        }
        notifyDataSetChanged();
    }

}