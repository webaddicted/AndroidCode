package com.example.deepaksharma.androidcode.view.fragment;

import android.databinding.ViewDataBinding;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.FragmentRecylcerViewBinding;
import com.example.deepaksharma.androidcode.view.adapter.ElaborateRecyclerAdapter;
import com.example.deepaksharma.androidcode.view.adapter.EndLessScrollAdapter;
import com.example.deepaksharma.androidcode.view.adapter.RecyclerGridAdapter;
import com.example.deepaksharma.androidcode.view.adapter.RecyclerListAdapter;
import com.example.deepaksharma.androidcode.view.adapter.CommonRecyclerViewAdapter;
import com.example.deepaksharma.androidcode.view.adapter.StaggeredGridAdapter;
import com.example.deepaksharma.androidcode.view.adapter.SwipToDeleteAdapter;
import com.example.deepaksharma.androidcode.view.base.BaseFragment;
import com.example.deepaksharma.androidcode.view.base.BaseRecyclerViewAdapter;
import com.example.deepaksharma.androidcode.view.home.HomeActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RecyclerViewFragment extends BaseFragment {
    public static final String TAG = RecyclerViewFragment.class.getSimpleName();
    private FragmentRecylcerViewBinding mBinding;
    private RecyclerListAdapter mListAdapter;
    private RecyclerGridAdapter mGridAdapter;
    private CommonRecyclerViewAdapter mCommonAdapter;
    private ElaborateRecyclerAdapter mElaboarteAdapter;
    private EndLessScrollAdapter mEndLessScrollAdapter;
    private StaggeredGridAdapter mStaggeredGridAdapter;
    private SwipToDeleteAdapter mSwipToDeleteAdapter;
    private List<String> mEndLessList;


    public static RecyclerViewFragment getInstance(Bundle bundle) {
        RecyclerViewFragment fragment = new RecyclerViewFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recylcer_view;
    }

    @Override
    protected void onViewsInitialized(ViewDataBinding binding, View view) {
        mBinding = (FragmentRecylcerViewBinding) binding;
        init();
        clickListener();
        getListBean();
//        setListAdapter();

    }

    private void init() {
    }

    private void clickListener() {
        mBinding.btnGrid.setOnClickListener(this);
        mBinding.btnList.setOnClickListener(this);
        mBinding.btnCommon.setOnClickListener(this);
        mBinding.btnElaborate.setOnClickListener(this);
        mBinding.btnEndLessScroll.setOnClickListener(this);
        mBinding.btnStaggered.setOnClickListener(this);
        mBinding.btnSwipeToDelete.setOnClickListener(this);
        mBinding.edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String text = mBinding.edtSearch.getText().toString().toLowerCase(Locale.getDefault());
                if (mListAdapter != null) mListAdapter.filter(text);
                if (mGridAdapter != null) mGridAdapter.filter(text);
                if (mElaboarteAdapter != null) mElaboarteAdapter.filter(text);

//                mHomeAdapter.getFilter().filter(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        ((HomeActivity) getActivity()).showBackBtn();
        ((HomeActivity) getActivity()).setToolbarTitle(getResources().getString(R.string.recycler_view_title));
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_list:
                setListAdapter();
                break;
            case R.id.btn_grid:
                setGridAdapter();
                break;
            case R.id.btn_common:
                setCommonAdapter();
                break;
            case R.id.btn_elaborate:
                setElaborateAdapter();
                break;
            case R.id.btn_end_less_scroll:
                setEndLessScrollAdapter();
                break;
            case R.id.btn_staggered:
                setStaggeredAdapter();
                break;
            case R.id.btn_swipe_to_delete:
                swipeToDeleteAdapter();
                break;
        }
    }

    private List<String> getListBean() {
        List<String> mList = new ArrayList<>();
        for (int i = 0; i < 50; i++) mList.add("task - # " + i);
        return mList;
    }

    private List<String> getUrlBean() {
        List<String> mList = new ArrayList<>();
        mList.add("https://ya-webdesign.com/images/goofy-clipart-7.png");
        mList.add("https://upload.wikimedia.org/wikipedia/en/thumb/b/b4/Donald_Duck.svg/220px-Donald_Duck.svg.png");
        mList.add("https://banner2.kisspng.com/20171127/fdb/mickey-mouse-free-png-transparent-image-5a1c8ad550b294.6551180715118199893305.jpg");
        mList.add("https://c7.uihere.com/files/257/367/814/mickey-mouse-minnie-mouse-pluto-drawing-image-mickey-mouse.jpg");
        mList.add("https://stickeroid.com/uploads/pic/full-pngimg/182e1f18bc8883fd935234f79626cf303d3def38.png");
        mList.add("https://zorlu.com.mk/wp-content/uploads/2016/10/mickey-minnie-mouse-kiss.jpg");
        mList.add("https://wallimpex.com/data/out/596/imagenes-de-mickey-9561546.jpg");
        mList.add("http://www.pngall.com/wp-content/uploads/2017/03/Minnie-Mouse-PNG-Clipart.png");
        mList.add("http://ku.90sjimg.com/element_pic/17/09/29/c194786fd4036b233ebe6d441ec1d5d1.jpg");
        mList.add("https://telegram-stickers.github.io/public/stickers/unofficial/5.png");
        mList.add("https://steemitimages.com/DQmP64LEzof8MpyowfWaAdetv7YNtGrfvvkJqPfS4f5GRTa/WhatTheHell.png");
        mList.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTMuORxKRlJ-Md1C7KxBGmg56Hwkm0UAMEKxLAcZQ31foHDXAeO");
        mList.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQLqOB6WXgmKlKb_hpmzwqVo1Jf5uZ9_Oiy88lWAGAcTN_huX7s");
        mList.add("https://i.pinimg.com/originals/fd/2e/73/fd2e73df313e9e8d6e8b54c554d9ddcf.pnghttps://i.pinimg.com/originals/fd/2e/73/fd2e73df313e9e8d6e8b54c554d9ddcf.png");
        mList.add("https://i.pinimg.com/originals/4b/80/f7/4b80f7ddd0738a65a160d6aa1c84b24d.png");
        mList.add("https://stickershop.line-scdn.net/stickershop/v1/product/1235089/LINEStorePC/main.png");
        mList.add("https://i.pinimg.com/originals/53/04/d0/5304d0f506a8bec37120ab68ff5d3961.png");
        mList.add("https://stickershop.line-scdn.net/stickershop/v1/product/925/LINEStorePC/main.png");
        mList.add("https://clipground.com/images/aladdin-clipart-13.jpg");
        mList.add("https://www.disneyclips.com/imagesnewb/images/aladdin-jasmine-carpet3.png");
        mList.add("https://banner2.kisspng.com/20180509/lsq/kisspng-princess-jasmine-aladdin-genie-clip-art-5af39dbd2ce0c6.4068873515259150691838.jpg");
        mList.add("https://banner2.kisspng.com/20180514/ugw/kisspng-princess-jasmine-aladdin-prince-ali-clip-art-5af9f41e1cdf34.8730821715263303981183.jpg");
        mList.add("https://banner2.kisspng.com/20180509/efe/kisspng-genie-princess-jasmine-the-sultan-iago-clip-art-5af32a7391cd37.2063657615258855555972.jpg");
        mList.add("https://upload.wikimedia.org/wikipedia/en/thumb/7/71/Princess_Jasmine_disney.png/220px-Princess_Jasmine_disney.png");
        mList.add("https://i.pinimg.com/originals/4f/e1/44/4fe144ee29e8e95b00f708cb8b0685ba.png");
        mList.add("https://library.kissclipart.com/20180906/ocw/kissclipart-baby-disney-png-clipart-pluto-donald-duck-minnie-m-1e57853e9139a610.png");


        return mList;
    }

    private void setCommonAdapter() {
        mGridAdapter = null;
        mCommonAdapter = new CommonRecyclerViewAdapter(getListBean());
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mBinding.recyclerView.setAdapter(mCommonAdapter);
    }

    private void setListAdapter() {
        mGridAdapter = null;
        mListAdapter = new RecyclerListAdapter(RecyclerViewFragment.this, getListBean());
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mBinding.recyclerView.setAdapter(mListAdapter);
    }

    private void setGridAdapter() {
        mListAdapter = null;
        mGridAdapter = new RecyclerGridAdapter(RecyclerViewFragment.this, getUrlBean());
        mBinding.recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        mBinding.recyclerView.setAdapter(mGridAdapter);
    }

    private void setElaborateAdapter() {
        mElaboarteAdapter = new ElaborateRecyclerAdapter(RecyclerViewFragment.this, getListBean());
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mBinding.recyclerView.setAdapter(mElaboarteAdapter);
    }

    private void setEndLessScrollAdapter() {
        mEndLessList = new ArrayList<>();
//        mEndLessList = getListBean();
        mGridAdapter = null;
        mEndLessScrollAdapter = new EndLessScrollAdapter(RecyclerViewFragment.this, mEndLessList);
        mEndLessScrollAdapter.setProgressBarColor(getResources().getColor(R.color.green));
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mBinding.recyclerView.setAdapter(mEndLessScrollAdapter);
    }

    private void setStaggeredAdapter() {

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        mBinding.recyclerView.setLayoutManager(staggeredGridLayoutManager);
        mStaggeredGridAdapter = new StaggeredGridAdapter(RecyclerViewFragment.this, getUrlBean());
        mBinding.recyclerView.setAdapter(mStaggeredGridAdapter);
    }

    private void swipeToDeleteAdapter() {
        mGridAdapter = null;
        mSwipToDeleteAdapter = new SwipToDeleteAdapter(RecyclerViewFragment.this, getListBean());
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mBinding.recyclerView.setAdapter(mSwipToDeleteAdapter);
        mSwipToDeleteAdapter.setUndoOn(true);
        mSwipToDeleteAdapter.setUpItemTouchHelper();
        mSwipToDeleteAdapter.setUpAnimationDecoratorHelper();
    }


    public void loadNewItems() {
        mEndLessScrollAdapter.startLoading();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mEndLessList.addAll(getListBean());
                mEndLessScrollAdapter.stopLoading();
            }
        }, 3000);
    }

    public RecyclerView getRecyclerView() {
    return mBinding.recyclerView;
    }}
