package com.example.deepaksharma.androidcode.view.base;

import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.global.AppApplication;

public abstract class BaseEndLessRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = BaseEndLessRecyclerViewAdapter.class.getSimpleName();
    private Context mContext = AppApplication.getInstance();
    private int mProgressBarColor = 0;
    private boolean mEndlessScrollLoading = false;
    private long mStartTime;
    private long mEndTime;
    private RecyclerView mRecyclerView;

    protected abstract int getLayoutId(int viewType);

    protected abstract int getListSize();

    protected abstract void onBindTo(ViewDataBinding rowBinding, int position);

    protected abstract boolean isEndLessScroll();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case (SCROLL_VIEW_TYPES.NORMAL):
                ViewDataBinding rowBinding = DataBindingUtil.
                        inflate(LayoutInflater.from(parent.getContext()),
                                getLayoutId(viewType),
                                parent, false);
                mRecyclerView = (RecyclerView) parent;
                return new BaseEndLessRecyclerViewAdapter.ViewHolder(rowBinding);
            case SCROLL_VIEW_TYPES.LOADER:
                LinearLayout ll = new LinearLayout(mContext);
                RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
                        RecyclerView.LayoutParams.WRAP_CONTENT);
                ll.setLayoutParams(layoutParams);
                ll.setBackgroundColor(mContext.getResources().getColor(R.color.transprant));
                return new BaseEndLessRecyclerViewAdapter.LoaderViewHolder(ll);
            default:
                return null;
        }
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
        if (isEndLessScroll() && getListSize() == 0) {
            mStartTime = System.currentTimeMillis();
            if (mStartTime > mEndTime - 800 && !mEndlessScrollLoading) {
                loadData();
                mEndTime = System.currentTimeMillis();
            }
        }
        if (isEndLessScroll() && mEndlessScrollLoading) return getListSize() + 1;
        else return (getListSize() == 0) ? 0 : getListSize();
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
            if (isEndLessScroll() && getListSize() - 1 == getAdapterPosition()) {
                mStartTime = System.currentTimeMillis();
                if (mStartTime > mEndTime - 500 && !mEndlessScrollLoading) {
                    loadData();
                    mEndTime = System.currentTimeMillis();
                }
            }
        }
    }

    /**
     * loader view holder
     */
    public class LoaderViewHolder extends RecyclerView.ViewHolder {
        private final ProgressBar progressBar;

        public LoaderViewHolder(View view) {
            super(view);
            progressBar = new ProgressBar(mContext);
            if (mProgressBarColor != 0)
                progressBar.getIndeterminateDrawable().setColorFilter(mProgressBarColor, PorterDuff.Mode.MULTIPLY);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            int padding = (int) mContext.getResources().getDimension(R.dimen.dp5);
            layoutParams.gravity = Gravity.CENTER;
            layoutParams.setMargins(padding, padding, padding, padding);
            progressBar.setLayoutParams(layoutParams);
            progressBar.setPadding(padding, padding, padding, padding);
            LinearLayout linearLayout = (LinearLayout) itemView;
            linearLayout.addView(progressBar);
        }
    }

    /**
     * scroll type in normal case and end less scroll time
     */
    private class SCROLL_VIEW_TYPES {
        public static final int NORMAL = 1;
        public static final int LOADER = 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1 && isEndLessScroll() && mEndlessScrollLoading) return SCROLL_VIEW_TYPES.LOADER;
        else return SCROLL_VIEW_TYPES.NORMAL;
    }

    /**
     * start process bar & data loading process
     */
    public void startLoading() {
        this.mEndlessScrollLoading = true;
        new Handler().postDelayed(() -> {
            notifyDataSetChanged();
            if (mRecyclerView != null) mRecyclerView.smoothScrollToPosition(getListSize() + 1);
        }, 200);
    }

    /**
     * stop process bar & data loading process
     */
    public void stopLoading() {
        this.mEndlessScrollLoading = false;
        new Handler().postDelayed(() -> notifyDataSetChanged(), 200);
    }

    /**
     * load data when adapter count reach to complete
     */
    protected void loadData() {
    }

    /**
     * set progress bar color
     *
     * @param progressBarColor hax code of color
     */
    public void setProgressBarColor(int progressBarColor) {
        this.mProgressBarColor = progressBarColor;
    }
}
