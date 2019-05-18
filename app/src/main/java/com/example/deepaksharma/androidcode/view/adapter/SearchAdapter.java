package com.example.deepaksharma.androidcode.view.adapter;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.v4.app.Fragment;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.RowTextListBinding;
import com.example.deepaksharma.androidcode.global.AppApplication;
import com.example.deepaksharma.androidcode.model.search.SearchRespo;
import com.example.deepaksharma.androidcode.view.base.BaseRecyclerViewAdapter;

import java.util.List;

public class SearchAdapter extends BaseRecyclerViewAdapter {
    private List<SearchRespo.ArticlesBean> mListBean;
    private Fragment mRecyclerViewFragment;
    private Context mContext = AppApplication.getInstance();

    public SearchAdapter(Fragment recyclerViewFragment, List<SearchRespo.ArticlesBean> listBean) {
        this.mRecyclerViewFragment = recyclerViewFragment;
        this.mListBean = listBean;
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.row_text_list;
    }

    @Override
    protected int getListSize() {
        return (mListBean == null || mListBean.size() == 0) ? 0 : mListBean.size();
    }

    @Override
    protected void onBindTo(ViewDataBinding rowBinding, int position) {
        if (rowBinding instanceof RowTextListBinding) {
            RowTextListBinding mRowBinding = (RowTextListBinding) rowBinding;
            mRowBinding.txtName.setText(mListBean.get(position).getTitle());
        }
    }

    public void updateList(List<SearchRespo.ArticlesBean> mArticleList) {
        this.mListBean = mArticleList;
        notifyDataSetChanged();
    }
}
