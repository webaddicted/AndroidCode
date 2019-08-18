package com.example.deepaksharma.androidcode.view.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.FragmentApiBinding;
import com.example.deepaksharma.androidcode.global.api.apiModel.Resource;
import com.example.deepaksharma.androidcode.global.api.apiModel.Status;
import com.example.deepaksharma.androidcode.model.search.SearchRespo;
import com.example.deepaksharma.androidcode.utils.GlobalUtilities;
import com.example.deepaksharma.androidcode.view.adapter.SearchAdapter;
import com.example.deepaksharma.androidcode.view.base.BaseFragment;
import com.example.deepaksharma.androidcode.view.home.HomeActivity;
import com.example.deepaksharma.androidcode.viewModel.search.SearchViewModel;

import java.util.List;

public class ApiFragment extends BaseFragment {
    public static final String TAG = ApiFragment.class.getSimpleName();
    private FragmentApiBinding mBinding;
    private SearchAdapter mSearchAdapter;
    private SearchViewModel mSearchViewModel;
    private List<SearchRespo.ArticlesBean> mArticleList;

    public static ApiFragment getInstance(Bundle bundle) {
        ApiFragment fragment = new ApiFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_api;
    }

    @Override
    protected void onViewsInitialized(ViewDataBinding binding, View view) {
        mBinding = (FragmentApiBinding) binding;
        mSearchViewModel = ViewModelProviders.of(getActivity()).get(SearchViewModel.class);
        init();
        clickListener();
    }

    private void init() {
    }

    private void clickListener() {
        mBinding.btnCallApi.setOnClickListener(this);
        mBinding.btnScrollListener.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((HomeActivity) getActivity()).showBackBtn();
        ((HomeActivity) getActivity()).setToolbarTitle(getResources().getString(R.string.api_call_title));
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_call_api:
                callApi();
                setCommonAdapter();
                break;
            case R.id.btn_scroll_listener:
                break;
        }
    }

    private void callApi() {
        GlobalUtilities.showToast("start search");
        if (mSearchViewModel.getSearchResultRespo() != null && mSearchViewModel.getSearchResultRespo().hasActiveObservers())
            mSearchViewModel.getSearchResultRespo().removeObservers(this);
        String url = "https://newsapi.org/v2/everything?q=Aditya    birla&pageSize=20&page=1&apiKey=e5e212cc147343cb9389cfb497c52bc2";
        mSearchViewModel.searchReq(url);
        mSearchViewModel.getSearchResultRespo().observe(this, new Observer<Resource<SearchRespo>>() {
            @Override
            public void onChanged(@Nullable Resource<SearchRespo> searchRes) {
                if (searchRes.status == Status.SUCCESS) {
                    if (searchRes.data != null && searchRes.data.getArticles() != null &&
                            searchRes.data.getArticles().size() > 0) {
                        mArticleList = searchRes.data.getArticles();
                        mSearchAdapter.updateList(mArticleList);
                    } else {
                        GlobalUtilities.showToast(getResources().getString(R.string.something_went_wrong));
                    }
                } else if (searchRes.status == Status.ERROR) {
                    GlobalUtilities.showToast(searchRes.message);
                }
            }
        });
    }

    private void setCommonAdapter() {
        mSearchAdapter = new SearchAdapter(ApiFragment.this, mArticleList);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mBinding.recyclerView.setAdapter(mSearchAdapter);
    }
}
