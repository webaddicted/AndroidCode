package com.example.deepaksharma.androidcode.view.adapter;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.RowTextListBinding;
import com.example.deepaksharma.androidcode.utils.GlobalUtilities;
import com.example.deepaksharma.androidcode.view.fragment.TaskListFragment;
import com.example.deepaksharma.androidcode.view.home.HomeActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private static final String TAG = TaskAdapter.class.getSimpleName();
    private TaskListFragment mTaskListFragment;
    private List<String> mAction;
    private List<String> searchArray;
    private String searchText;

    public TaskAdapter(TaskListFragment taskListFragment, List<String> action) {
        this.mTaskListFragment = taskListFragment;
        this.mAction = action;
        this.searchArray = new ArrayList<>();
        this.searchArray.addAll(action);
    }

    @NonNull
    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowTextListBinding mBinding = DataBindingUtil.
                inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.row_text_list,
                        parent, false);
        return new TaskAdapter.ViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.ViewHolder holder, int position) {
        if (searchText!=null && searchText.length()>1){
           String completeText = mAction.get(position);
            SpannableString spannableString = new SpannableString(mAction.get(position));
            ForegroundColorSpan foregroundSpan = new ForegroundColorSpan(Color.GREEN);
            for (int i = -1; (i = completeText.indexOf(searchText, i + 1)) != -1; i++) {
                int endText = searchText.length()+i;
                spannableString.setSpan(foregroundSpan, i, endText, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            holder.binding.txtName.setText(spannableString);
        }else {
            holder.binding.txtName.setText(mAction.get(position));
        }
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
            binding.card.setOnClickListener(view -> mTaskListFragment.onClicks(mAction.get(getAdapterPosition())));
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
