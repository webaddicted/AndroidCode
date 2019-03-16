package com.example.deepaksharma.androidcode.viewModel.home;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.theartofdev.edmodo.cropper.CropImage;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {
    public MutableLiveData<ArrayList<String>> mSpeechToText = new MutableLiveData<>();
    public  MutableLiveData<CropImage.ActivityResult>mCroppedImage = new MutableLiveData<>();
}
