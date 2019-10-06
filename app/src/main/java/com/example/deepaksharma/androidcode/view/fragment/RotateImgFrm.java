package com.example.deepaksharma.androidcode.view.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.FragmentApiBinding;
import com.example.deepaksharma.androidcode.databinding.FrmRotateImgBinding;
import com.example.deepaksharma.androidcode.global.api.apiModel.Resource;
import com.example.deepaksharma.androidcode.global.api.apiModel.Status;
import com.example.deepaksharma.androidcode.model.search.SearchRespo;
import com.example.deepaksharma.androidcode.utils.GlobalUtilities;
import com.example.deepaksharma.androidcode.view.adapter.SearchAdapter;
import com.example.deepaksharma.androidcode.view.base.BaseFragment;
import com.example.deepaksharma.androidcode.view.home.HomeActivity;
import com.example.deepaksharma.androidcode.viewModel.search.SearchViewModel;

import java.util.List;

public class RotateImgFrm extends BaseFragment {
    public static final String TAG = RotateImgFrm.class.getSimpleName();
    private FrmRotateImgBinding mBinding;

    public static RotateImgFrm getInstance(Bundle bundle) {
        RotateImgFrm fragment = new RotateImgFrm();
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.frm_rotate_img;
    }

    @Override
    protected void onViewsInitialized(ViewDataBinding binding, View view) {
        mBinding = (FrmRotateImgBinding) binding;
        init();
        clickListener();
    }

    private void init() {
        Bitmap source = BitmapFactory.decodeResource(this.getResources(), R.drawable.logo);
        mBinding.img.setImageBitmap(source);
    }

    private void clickListener() {
        mBinding.btnRotate.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((HomeActivity) getActivity()).showBackBtn();
        ((HomeActivity) getActivity()).setToolbarTitle(getResources().getString(R.string.rotate));
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_rotate:
                rotateImg();
                break;
        }
    }

    private void rotateImg() {
        BitmapDrawable drawable = (BitmapDrawable) mBinding.img.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        Matrix matrix = new Matrix();

        matrix.postRotate(90);

        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getHeight(), bitmap.getWidth(), true);

        Bitmap rotatedBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix, true);
        mBinding.img.setImageBitmap(rotatedBitmap);
    }
}
