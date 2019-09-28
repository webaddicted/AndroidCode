package com.example.deepaksharma.androidcode.view.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.global.AppApplication;

public abstract class BaseRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = BaseRecyclerViewAdapter.class.getSimpleName();
    protected Context mContext = AppApplication.getInstance();
    protected abstract int getLayoutId(int viewType);
    protected abstract int getListSize();
    protected abstract void onBindTo(ViewDataBinding rowBinding, int position);

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                ViewDataBinding rowBinding = DataBindingUtil.
                        inflate(LayoutInflater.from(parent.getContext()),
                                getLayoutId(viewType),
                                parent, false);
                return new BaseRecyclerViewAdapter.ViewHolder(rowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder)
            ((ViewHolder) holder).binding(position);
    }

    /**
     * placeholder type for image
     *
     * @param placeholderType position of string array placeholder
     * @return
     */
    public String getPlaceHolder(int placeholderType) {
        String[] placeholderArray = mContext.getResources().getStringArray(R.array.image_loader);
        return placeholderArray[placeholderType];
    }

    @Override
    public int getItemCount() {
            return (getListSize() == 0) ? 0 : getListSize();
    }

    /**
     * view holder
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding mRowBinding;

        public ViewHolder(ViewDataBinding itemView) {
            super(itemView.getRoot());
            this.mRowBinding = itemView;
        }

        /**
         * @param position current item position
         */
        public void binding(int position) {
//            sometime adapter position  is -1 that case handle by position
            if (getAdapterPosition() >= 0) onBindTo(mRowBinding, getAdapterPosition());
            else onBindTo(mRowBinding, position);
        }
    }
}
