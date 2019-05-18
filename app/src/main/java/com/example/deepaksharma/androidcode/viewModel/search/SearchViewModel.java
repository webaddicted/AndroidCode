package com.example.deepaksharma.androidcode.viewModel.search;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.deepaksharma.androidcode.global.api.apiModel.Resource;
import com.example.deepaksharma.androidcode.model.search.SearchRespo;
import com.example.deepaksharma.androidcode.repository.search.SearchRepository;

public class SearchViewModel extends ViewModel {
    private LiveData<Resource<SearchRespo>> mSearchResp;
    SearchRepository searchRepository;

    public SearchViewModel() {
        searchRepository = new SearchRepository();
    }

    public LiveData<Resource<SearchRespo>> getSearchResultRespo() {
        return mSearchResp;
    }

    public void searchReq(String url) {
        mSearchResp = searchRepository.searchResult(url);
    }
}
