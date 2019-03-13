package com.example.deepaksharma.androidcode.view.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

public abstract class BaseRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = BaseRecyclerViewAdapter.class.getSimpleName();
    protected abstract int getLayoutId();
    protected abstract int getListSize();
    protected abstract void onBindTo(ViewDataBinding rowBinding, int adapterPosition, int position);

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding rowBinding = DataBindingUtil.
                inflate(LayoutInflater.from(parent.getContext()),
                        getLayoutId(),
                        parent, false);
        return new BaseRecyclerViewAdapter.ViewHolder(rowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).binding(position);
    }

    @Override
    public int getItemCount() {
        return (getListSize() == 0) ? 0 : getListSize();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding mRowBinding;

        public ViewHolder(ViewDataBinding itemView) {
            super(itemView.getRoot());
            this.mRowBinding = itemView;
        }

        public void binding(int position) {
            onBindTo(mRowBinding, getAdapterPosition(), position);
        }
    }
}
