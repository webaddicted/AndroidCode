package com.example.deepaksharma.androidcode.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.RowGridBinding;
import com.example.deepaksharma.androidcode.databinding.RowTextListBinding;
import com.example.deepaksharma.androidcode.view.fragment.RecyclerViewFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ElaborateRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = ElaborateRecyclerAdapter.class.getSimpleName();
    private RecyclerViewFragment mElaborateRecyclerFragment;
    private List<String> mAction;
    private List<String> searchArray;
    private String searchText;

    public ElaborateRecyclerAdapter(RecyclerViewFragment elaborateRecyclerFragment, List<String> action) {
        this.mElaborateRecyclerFragment = elaborateRecyclerFragment;
        this.mAction = action;
        this.searchArray = new ArrayList<>();
        this.searchArray.addAll(action);
    }

    private class VIEW_TYPES {
        public static final int Normal = 1;
        public static final int Footer = 2;
    }

    @Override
    public int getItemCount() {
        return (mAction == null || mAction.size() == 0) ? 0 : mAction.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount()-1) {
            return VIEW_TYPES.Footer;
        } else
            return VIEW_TYPES.Normal;


    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPES.Normal) {
            RowTextListBinding mBinding = DataBindingUtil.
                    inflate(LayoutInflater.from(parent.getContext()),
                            R.layout.row_text_list,
                            parent, false);
            return new NormalViewHolder(mBinding);
        } else if (viewType == VIEW_TYPES.Footer) {
            RowGridBinding mBinding = DataBindingUtil.
                    inflate(LayoutInflater.from(parent.getContext()),
                            R.layout.row_grid,
                            parent, false);

            return new FooterViewHolder(mBinding);
        }else {
             throw new IllegalArgumentException("unknown view type");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//        if (searchText != null && searchText.length() > 1) {
//            String completeText = mAction.get(position);
//            SpannableString spannableString = new SpannableString(mAction.get(position));
//            for (int i = -1; (i = completeText.indexOf(searchText, i + 1)) != -1; i++) {
//                int endText = searchText.length() + i;
//                ForegroundColorSpan foregroundSpan = new ForegroundColorSpan(Color.GREEN);
//                spannableString.setSpan(foregroundSpan, i, endText, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//            }
//            holder.binding.txtName.setText(spannableString);
//        } else {
//            holder.binding.txtName.setText(mAction.get(position));
//        }
        if (holder instanceof NormalViewHolder) {
            ((NormalViewHolder) holder).binding(position);
        } else if (holder instanceof FooterViewHolder) {
            ((FooterViewHolder) holder).binding(position);
        }
    }


    class NormalViewHolder extends RecyclerView.ViewHolder {
        private RowTextListBinding binding;

        public NormalViewHolder(RowTextListBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }

        public void binding(int position) {
//            binding.card.setOnClickListener(view -> mRecyclerViewFragment.onClicks(mAction.get(ViewHolder.this.getAdapterPosition())));
        }

    }

    class FooterViewHolder extends RecyclerView.ViewHolder {
        private RowGridBinding binding;

        public FooterViewHolder(RowGridBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }

        public void binding(int position) {
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
