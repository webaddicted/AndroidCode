package com.example.deepaksharma.androidcode.view.adapter;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.RowTextListBinding;
import com.example.deepaksharma.androidcode.view.fragment.TaskListFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        String title = mAction.get(position);
        if (searchText!=null && searchText.length()>1){
            SpannableStringBuilder sb = new SpannableStringBuilder(title);
            Pattern word = Pattern.compile(searchText.toLowerCase());
            Matcher match = word.matcher(title.toLowerCase());
            while (match.find()) {
                ForegroundColorSpan fcs = new ForegroundColorSpan(Color.RED); //specify color here
                sb.setSpan(fcs, match.start(), match.end(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            }
            holder.binding.txtName.setText(sb);
        }else {
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

        ViewHolder(RowTextListBinding itemView) {
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
