package com.example.deepaksharma.androidcode.viewModel.home;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.location.Location;

import com.google.android.gms.location.places.Place;
import com.google.zxing.integration.android.IntentResult;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {
    public MutableLiveData<ArrayList<String>> mSpeechToText = new MutableLiveData<>();
    public MutableLiveData<CropImage.ActivityResult> mCroppedImage = new MutableLiveData<>();
    public MutableLiveData<Boolean> mIsPermissionGranted = new MutableLiveData<>();
    public MutableLiveData<Location> mCurrentLocation = new MutableLiveData<>();
    public MutableLiveData<String> mCurrentAddress = new MutableLiveData<>();
    public MutableLiveData<Place> mSearchedPlace = new MutableLiveData<>();
    public MutableLiveData<IntentResult> mBarCodeResult = new MutableLiveData<>();

    public void setCurrentLocationAddress(Location location, String address) {
        if (location != null) mCurrentLocation.postValue(location);
        if (address != null) mCurrentAddress.postValue(address);
    }
}
