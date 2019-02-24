package com.example.deepaksharma.androidcode.view.adapter;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.RowTextListBinding;
import com.example.deepaksharma.androidcode.view.fragment.RecyclerViewFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RecyclerListAdapter extends RecyclerView.Adapter<RecyclerListAdapter.ViewHolder> {
    private static final String TAG = RecyclerListAdapter.class.getSimpleName();
    private RecyclerViewFragment mRecyclerViewFragment;
    private List<String> mAction;
    private List<String> searchArray;
    private String searchText;

    public RecyclerListAdapter(RecyclerViewFragment recyclerViewFragment, List<String> action) {
        this.mRecyclerViewFragment = recyclerViewFragment;
        this.mAction = action;
        this.searchArray = new ArrayList<>();
        this.searchArray.addAll(action);
    }

    @NonNull
    @Override
    public RecyclerListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowTextListBinding mBinding = DataBindingUtil.
                inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.row_text_list,
                        parent, false);
        return new RecyclerListAdapter.ViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerListAdapter.ViewHolder holder, int position) {
        if (searchText != null && searchText.length() > 1) {
            String completeText = mAction.get(position);
            SpannableString spannableString = new SpannableString(mAction.get(position));
            for (int i = -1; (i = completeText.indexOf(searchText, i + 1)) != -1; i++) {
                int endText = searchText.length() + i;
                ForegroundColorSpan foregroundSpan = new ForegroundColorSpan(Color.GREEN);
                spannableString.setSpan(foregroundSpan, i, endText, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            holder.binding.txtName.setText(spannableString);
        } else {
            holder.binding.txtName.setText(mAction.get(position));
        }
        holder.binding();
    }

    @Override
    public int getItemCount() {
        return (mAction == null || mAction.size() == 0) ? 0 : mAction.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private RowTextListBinding binding;

        public ViewHolder(RowTextListBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }

        public void binding() {
//            binding.card.setOnClickListener(view -> mRecyclerViewFragment.onClicks(mAction.get(ViewHolder.this.getAdapterPosition())));
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
                if (wp != null && wp.toLowerCase(Locale.getDefault()).contains(charText)) {
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
