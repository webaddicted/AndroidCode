package com.example.deepaksharma.androidcode.view.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.ActivityHomeBinding;
import com.example.deepaksharma.androidcode.view.BaseActivity;
import com.example.deepaksharma.androidcode.view.adapter.HomeAdapter;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class HomeActivity extends BaseActivity {
    private static final String TAG = HomeActivity.class.getSimpleName();
    private ActivityHomeBinding mBinding;
    String action[] = {"Date And time Calender", "All about edittext", "All Different button and widget",
            "Read and Write Data In File as share preferance", "GPS Location Best", "GPS Location", "GPS Current Location",
            "Menu ( Option And Context menu )", "Web View", "Rating Bar", "Alert Dialog Box", "Dynamic Layout", "GIF Image",
            "Shared Preferences", "Phone And SIM Detail", "Speech to timerText", "Stop Watch", "Toast And ButtonFunction",
            "XML Pull Parser", "Animations", "Phone Contacts", "List View", "Expendable List View", "Grid View", "Spinner View",
            "Auto Complete Text View", "Google Search Place Api", "BarCode", "FCM Notification",
            "Global Class And Check Internet Connection", "Button handle In Custom view (Dynamic Date Picker)", "Gmail Send Mail",
            "Image Auto Slide", "Phone Image All Function", "Zoom Image", "Splash Screen", "Pdf and Chart", "Share On Social",
            "View Pager Or Swipe Page", "Internal SQLite DataBase", "Dynamic Grid view or movable Grid view", "Exit From Program",
            "Thread and Call Method Every Fix time", "Navigation Title Bar And hide side Panel data", "Google Map",
            "Login Api Integration", "WebService calling", "Activity Title bar", "BroadCast Receiver Service", "Pending Intent Service",
            "Intent", "Change App Theme", "Scroll Down then list load Data", "Sms Verification", "Captcha auto change word",
            "Slide Left to delete of list item", "Color Animation On Login", "Collapsion  show image", "Digital Signature",
            "Finger Print Check", "Create Calender Event", "Record Audio Act", "Sensor App Act", "Vibrate Sensor Act",
            "Swipe to Delete Act", "Capture Image using Service ", "Different type of image load", "All Service Work", "Phone SMS",
            "WIFI", "UI Design", "Payment Integration", "ButterKnife", "Image Crop and WhatsUp Type Image Selection",
            "Best Site And Ui Page Link Best Code", "Recycle View"};
    private HomeAdapter homeAdapter;
    private List<String> mFeature ;//= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        mFeature = new LinkedList<String>(Arrays.asList(action));
        mBinding.include.imgBack.setVisibility(View.GONE);
        setAdapter();
        clickListener();
    }

    private void clickListener() {
        mBinding.edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String text = mBinding.edtSearch.getText().toString().toLowerCase(Locale.getDefault());
                homeAdapter.filter(text);
//                homeAdapter.getFilter().filter(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void setAdapter() {
        homeAdapter = new HomeAdapter(HomeActivity.this, mFeature);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mBinding.recyclerView.setAdapter(homeAdapter);
    }

    public void onClicks(String click) {
        if (click == "Date And time Calender") {
//            Intent jbjbjh = new Intent(HomeActivity.this, DateAndTimeAct.class);
//            startActivity(jbjbjh);
        }
//        else if (click == "All about edittext") {
//            Intent jbjbjh = new Intent(HomeActivity.this, AboutEditTextAct.class);
//            startActivity(jbjbjh);
//        } else if (click == "All Different button and widget") {
//            Intent jbjbjh = new Intent(HomeActivity.this, AllDifferentButtonAct.class);
//            startActivity(jbjbjh);
//        } else if (click == "Read and Write Data In File as share preferance") {
//            Intent jbjbjh = new Intent(HomeActivity.this, ReadWriteFileAct.class);
//            startActivity(jbjbjh);
//        } else if (click == "GPS Location Best") {
////                    need permission
//            Intent jbjbjh = new Intent(HomeActivity.this, GPSLocationBest.class);
//            startActivity(jbjbjh);
//        } else if (click == "GPS Location") {
////                    need permission
//            Intent jbjbjh = new Intent(HomeActivity.this, GPSLoactionAct.class);
//            startActivity(jbjbjh);
//        } else if (click == "GPS Current Location") {
////                    need permission
////                    compile 'com.google.android.gms:play-services-location:10.0.1'
////                        <uses-feature
////                    android:name="android.hardware.location.gps"
////                    android:required="true" />
//            Intent jbjbjh = new Intent(HomeActivity.this, GPSCurrentLocAct.class);
//            startActivity(jbjbjh);
//        } else if (click == "Menu ( Option And Context menu )") {
//            Intent jbjbjh = new Intent(HomeActivity.this, OptionMenuAct.class);
//            startActivity(jbjbjh);
//        } else if (click == "Web View") {
//            Intent jbjbjh = new Intent(HomeActivity.this, WebViewAct.class);
//            startActivity(jbjbjh);
//        } else if (click == "Rating Bar") {
//            Intent jbjbjh = new Intent(HomeActivity.this, RatingBarAct.class);
//            startActivity(jbjbjh);
//        } else if (click == "Alert Dialog Box") {
//            Intent jbjbjh = new Intent(HomeActivity.this, AlertDialogBoxAct.class);
//            startActivity(jbjbjh);
//        } else if (click == "Dynamic Layout") {
//            Intent jbjbjh = new Intent(HomeActivity.this, DynamicLayoutAct.class);
//            startActivity(jbjbjh);
//        } else if (click == "GIF Image") {
////                    first connect with internet
////            compile  dependencies
////                        compile 'com.github.bumptech.glide:glide:3.7.0'
////                        compile 'pl.droidsonroids.gif:android-gif-drawable:1.2.+'
////                        mavenCentral()    in allProjecr repositories  jcenter  in Gradle
//            Intent jbjbjh = new Intent(HomeActivity.this, GIFImageAct.class);
//            startActivity(jbjbjh);
//        } else if (click == "Shared Preferences") {
//            Intent jbjbjh = new Intent(HomeActivity.this, SharedPreferencesAct.class);
//            startActivity(jbjbjh);
//        } else if (click == "Phone And SIM Detail") {
////                    <uses-permission android:name="android.permission.READ_PHONE_STATE"/>
////                    <uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED"/>
//            Intent jbjbjh = new Intent(HomeActivity.this, PhoneSimDetailAct.class);
//            startActivity(jbjbjh);
//        } else if (click == "Speech to timerText") {
//            Intent jbjbjh = new Intent(HomeActivity.this, SpeechToTextAct.class);
//            startActivity(jbjbjh);
//        } else if (click == "Stop Watch") {
//            Intent jbjbjh = new Intent(HomeActivity.this, StopWatchAct.class);
//            startActivity(jbjbjh);
//        } else if (click == "Toast And ButtonFunction") {
//            Intent jbjbjh = new Intent(HomeActivity.this, ToastAct.class);
//            startActivity(jbjbjh);
//        } else if (click == "XML Pull Parser") {
////                    asset contain a file
//            Intent jbjbjh = new Intent(HomeActivity.this, XMLPullPareseAct.class);
//            startActivity(jbjbjh);
//        } else if (click == "Animations") {
////                    make file in res --> anim folder contain a file
//            Intent jbjbjh = new Intent(HomeActivity.this, AnimationAct.class);
//            startActivity(jbjbjh);
//        } else if (click == "Phone Contacts") {
////                     <uses-permission android:name="android.permission.READ_CONTACTS"/>
//            Intent jbjbjh = new Intent(HomeActivity.this, ContactDetails.class);
//            startActivity(jbjbjh);
//        } else if (click == "List View") {
//            Intent jbjjkjh = new Intent(HomeActivity.this, ListViewExampleAct.class);
//            startActivity(jbjjkjh);
//        } else if (click == "Expendable List View") {
//            Intent jbjjkjh = new Intent(HomeActivity.this, ExpendableListViewExampleAct.class);
//            startActivity(jbjjkjh);
//        } else if (click == "Grid View") {
//            Intent jbjjkjh = new Intent(HomeActivity.this, GridViewAct.class);
//            startActivity(jbjjkjh);
//        } else if (click == "Spinner View") {
//            Intent jbjjkjh = new Intent(HomeActivity.this, SpinnerViewAct.class);
//            startActivity(jbjjkjh);
//        } else if (click == "Auto Complete Text View") {
//            Intent jbjjkjh = new Intent(HomeActivity.this, AutoComplTextVieweAct.class);
//            startActivity(jbjjkjh);
//        } else if (click == "Google Search Place Api") {
//
////                  here is a problem only one think is open in google map ya google search place api
////                    in maniest file i comment search place api thats why map work
////                    in Manifest File
//
////                    https://developers.google.com/places/web-service/search
////                    go above site it give api key
////                    compile 'com.google.android.gms:play-services:8.4.0'
////                    In Manifest File
////                 <meta-data
////                    android:name="com.google.android.geo.API_KEY"
////                    android:value="AIzaSyC7Sm20zva5UdaSFUmOyQM9T7oJKFmDfks" />
//            Intent jbjjkjh = new Intent(HomeActivity.this, SearchPlaceAPI.class);
//            startActivity(jbjjkjh);
//        } else if (click == "BarCode") {
////                    compile 'com.journeyapps:zxing-android-embedded:3.4.0'
////                    take permission of camera
//            Intent jbjjkjh = new Intent(HomeActivity.this, BarCodeAct.class);
//            startActivity(jbjjkjh);
//        } else if (click == "FCM Notification") {
////                    https://console.firebase.google.com/project/mobile-munim-267a1/settings/general/android:com.mobilemunim.crm.project
////                   add json file in src folder all three service name must be same
////                    compile 'com.google.android.gms:play-services-auth:9.6.1'
////                    compile 'com.google.android.gms:play-services:9.6.1'
////                    compile 'com.google.firebase:firebase-messaging:9.6.1'
////                    apply plugin: 'com.google.gms.google-services'
////                    classpath 'com.google.gms:google-services:3.1.0'
////               manifest file is
////                        <meta-data android:name="com.google.firebase.messaging.default_notification_icon"
////                    android:resource="@drawable/subzi"/>
////                   <meta-data android:name="com.google.firebase.messaging.default_notification_color"
////                    android:resource="@android:color/holo_orange_dark" />
////                   <service android:name=".MyFirebaseMessagingService">
////                     <intent-filter>
////                         <action android:name="com.google.firebase.MESSAGING_EVENT" />
////                     </intent-filter>
////                 </service>
////                 <service android:name=".MyFirebaseInstanceIDService">
////                     <intent-filter>
////                <action android:name="com.google.firebase.INSTANCE_ID_EVENT" />
////                     </intent-filter>
////                 </service>
////                    first on token refresh method call
//            Intent jbjjkjh = new Intent(HomeActivity.this, FCMNotificationAct.class);
//            startActivity(jbjjkjh);
//        } else if (click == "Global Class And Check Internet Connection") {
//            Intent jbjjkjh = new Intent(HomeActivity.this, CheckInternetConnectionAct.class);
//            startActivity(jbjjkjh);
//        } else if (click == "Button handle In Custom view (Dynamic Date Picker)") {
//            Intent jbjjkjh = new Intent(HomeActivity.this, DynamicDatePickerAct.class);
//            startActivity(jbjjkjh);
//        } else if (click == "Gmail Send Mail") {
////                    compile 'com.github.yesidlazaro:GmailBackground:1.2.0'
//            Intent jbjjkjh = new Intent(HomeActivity.this, GmailSendMailAct.class);
//            startActivity(jbjjkjh);
//        } else if (click == "Image Auto Slide") {
////                    compile 'com.squareup.picasso:picasso:2.3.2'
////                    compile 'com.nineoldandroids:library:2.4.0'
////                    compile 'com.daimajia.slider:library:1.1.5@aar'
//
////                    other method is
////                    compile 'com.ss.bannerslider:bannerslider:1.8.0'
////                    compile 'com.android.support:cardview-v7:25.0.1'
//
//            Intent jbjjkjh = new Intent(HomeActivity.this, ImageAutoSlideAct.class);
//            startActivity(jbjjkjh);
//        } else if (click == "Phone Image All Function") {
//            Intent jbjjkjh = new Intent(HomeActivity.this, AllPhoneImage.class);
//            startActivity(jbjjkjh);
//        } else if (click == "Zoom Image") {
////              maven{url "https://jitpack.io"}
////              compile 'com.github.chrisbanes:PhotoView:1.2.6'
//            Intent jbjjkjh = new Intent(HomeActivity.this, ZoomImageAct.class);
//            startActivity(jbjjkjh);
//        } else if (click == "Splash Screen") {
////                    4 layout intro_slid1 to 4 xml layout require
////                     SplashStoreInstall java file
////                    in global class copy
////                    @Override
////                    public void onCreate() {
////                        super.onCreate();
////                        SplashStoreInstall.init(this);
////                    }
//            Intent jbjjkjh = new Intent(HomeActivity.this, SplashScreenAct.class);
//            startActivity(jbjjkjh);
//        } else if (click == "Pdf and Chart") {
//            Intent jbjjkjh = new Intent(HomeActivity.this, PdfAndChartAct.class);
//            startActivity(jbjjkjh);
//        } else if (click == "Share On Social") {
//            Intent jbjjkjh = new Intent(HomeActivity.this, ShareOnSocialAct.class);
//            startActivity(jbjjkjh);
//        } else if (click == "View Pager Or Swipe Page") {
////                    compile 'com.android.support:design:24.0.0'
//            Intent jbjjkjh = new Intent(HomeActivity.this, ViewPagerAndTabAct.class);
//            startActivity(jbjjkjh);
//        } else if (click == "Internal SQLite DataBase") {
//            Intent jbjjkjh = new Intent(HomeActivity.this, SQLiteDataBase.class);
//            startActivity(jbjjkjh);
//        } else if (click == "Dynamic Grid view or movable Grid view") {
//            Intent jbjjkjh = new Intent(HomeActivity.this, DragableGridView.class);
//            startActivity(jbjjkjh);
//        } else if (click == "Exit From Program") {
//            Intent jbjjkjh = new Intent(HomeActivity.this, ExitFromProgramAct.class);
//            startActivity(jbjjkjh);
//        } else if (click == "Thread and Call Method Every Fix time") {
//            Intent jbjjkjh = new Intent(HomeActivity.this, ThreadAndTimerAct.class);
//            startActivity(jbjjkjh);
//        } else if (click == "Navigation Title Bar And hide side Panel data") {
//
////                  option menu icon show ............thats why we remove  option menu from menu and its code
////                  height 3 dp provide of title bar  which help to show title bar color
//
////                    add these line in AppBar Layout
////                    android:backgroundTint="@color/appMainColor"
////                    android:background="@color/appMainColor"
////                    android:titleTextColor="@color/appMainColor"
//
////                    add these line in Toolbar
////                    android:layout_height="3dp"
////                    android:background="@color/appMainColor"
////                    android:titleTextColor="@color/appMainColor"
////                    app:subtitleTextColor="@color/appMainColor"
//
////                    this is used to hide navigation bar icon Done
////                    toggle.setDrawerIndicatorEnabled(false);
//
////                    remove this line from other fragment other wise it create problem of overlapping of view
////                    android:id="@+id/replaceFragment"
//
//            Intent jbjjkjh = new Intent(HomeActivity.this, NavigationTitleBar.class);
//            startActivity(jbjjkjh);
//        } else if (click == "Google Map") {
////                  here is a problem only one think is open in google map ya google search place api
////                    in maniest file i comment search place api thats why map work
//            //in Manifest File
//
////                        <permission
////                    android:name="com.example.mapapplication.permission.MAP_RECEIVE"
////                    android:protectionLevel="signature"></permission>
////
////                 <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
////                 <uses-permission android:name="com.google.android.providers.gsf.permission.READ_GSERVICE" />
////                 <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
////                 <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
////
////                    In Application
////                 <meta-data
////                    android:name="com.google.android.maps.v2.API_KEY"
////                    android:value="AIzaSyC1Q6uo9xzg1iEBAZxvkr1aDiUfPSjy1ec" />
////                 <meta-data
////                    android:name="com.google.android.gms.version"
////                    android:value="@integer/google_play_services_version" />
//
//
////                    compile 'com.google.android.gms:play-services-auth:8.3.0'
////                    compile 'com.google.android.gms:play-services:10.2.1'
//            Intent jbjjkjh = new Intent(HomeActivity.this, GoogleMapAct.class);
//            startActivity(jbjjkjh);
//        } else if (click == "Login Api Integration") {
//            Intent jbjjkjh = new Intent(HomeActivity.this, DifferentLoginApi.class);
//            startActivity(jbjjkjh);
//        } else if (click == "WebService calling") {
//            Intent jbjjkjh = new Intent(HomeActivity.this, ServiceCall.class);
//            startActivity(jbjjkjh);
//        } else if (click == "Activity Title bar") {
////                    in Style.xml file
//
////                  <style name = "ChangeAppThemeColor" parent = "Theme.AppCompat.Light.DarkActionBar" >
////                 <!--Customize your theme here.-- >
////                 <item name = "colorPrimary" >#e040fb</item >
////                 <item name = "colorPrimaryDark" >#e040fb</item >
////                 <item name = "colorAccent" >#e040fb</item >
////                 <item name = "android:windowTitleBackgroundStyle" > @style/WindowTitleBackground</item >
////                 </style >
////                 <style name = "WindowTitleBackground" >
////                 <item name = "android:background" >#e040fb</item >
////                 </style >
//
////                     <activity android:name=".ActivityTitleBarAct"
////                    android:theme="@style/ChangeAppThemeColor"></activity>
//            Intent
//                    jbjjkjh = new Intent(HomeActivity.this, ActivityTitleBarAct.class);
//            startActivity(jbjjkjh);
//        } else if (click == "BroadCast Receiver Service") {
//            Intent jbjjkjh = new Intent(HomeActivity.this, BroadCastReceiverServiceAct.class);
//            startActivity(jbjjkjh);
//        } else if (click == "Pending Intent Service") {
//            Intent jbjjkjh = new Intent(HomeActivity.this, PendingIntentAct.class);
//            startActivity(jbjjkjh);
//        } else if (click == "Intent") {
//            Intent jbjjkjh = new Intent(HomeActivity.this, IntentResponse.class);
//            startActivity(jbjjkjh);
//        } else if (click == "Change App Theme") {
//            Intent jbjjkjh = new Intent(HomeActivity.this, ChangeAppThemeAct.class);
//            startActivity(jbjjkjh);
//        } else if (click == "Scroll Down then list load Data") {
////                    compile 'com.github.ugurcany:InfiniteListView:1.1.1'
//            Intent jbjjkjh = new Intent(HomeActivity.this, ListScrollLoadData.class);
//            startActivity(jbjjkjh);
//        } else if (click == "Sms Verification") {
////                    compile 'com.github.stfalcon:smsverifycatcher:0.3.1'
////                        <uses-permission android:name="android.permission.RECEIVE_SMS" />
////                          <uses-permission android:name="android.permission.READ_SMS" />
//            Intent jbjjkjh = new Intent(HomeActivity.this, SmsVerificationAct.class);
//            startActivity(jbjjkjh);
//        } else if (click == "Captcha auto change word") {
////                    copy three file
////                            CaptchaMain
////                            CaptchaMathOperation
////                            CaptchaText
//            Intent jbjjkjh = new Intent(HomeActivity.this, CaptchaAutoChangeWordAct.class);
//            startActivity(jbjjkjh);
//        } else if (click == "Slide Left to delete of list item") {
//            Intent jbjjkjh = new Intent(HomeActivity.this, SlideLeftToRemoveListAct.class);
//            startActivity(jbjjkjh);
//        } else if (click == "Color Animation On Login") {
//            Intent jbjjkjh = new Intent(HomeActivity.this, ColorLoginAnimAct.class);
//            startActivity(jbjjkjh);
//        } else if (click == "Collapsion  show image") {
//            Intent jbjjkjh = new Intent(HomeActivity.this, CollapsiveImageAct.class);
//            startActivity(jbjjkjh);
//        } else if (click == "Digital Signature") {
//            Intent jbjjkjh = new Intent(HomeActivity.this, DigitalSignatureAct.class);
//            startActivity(jbjjkjh);
//        } else if (click == "Finger Print Check") {
////                    <uses-permission android:name="android.permission.USE_FINGERPRINT" />
//            Intent jbjjkjh = new Intent(HomeActivity.this, FingerPrintAct.class);
//            startActivity(jbjjkjh);
//        } else if (click == "Create Calender Event") {
////                    <uses-permission android:name="android.permission.WRITE_CALENDAR" />
////                    <uses-permission android:name="android.permission.READ_CALENDAR" />
//            Intent jbjjkjh = new Intent(HomeActivity.this, CreateCalenderEventAct.class);
//            startActivity(jbjjkjh);
//        } else if (click == "Record Audio Act") {
////                    <uses-permission android:name="android.permission.RECORD_AUDIO" />
//            Intent jbjjkjh = new Intent(HomeActivity.this, RecorderAct.class);
//            startActivity(jbjjkjh);
//        } else if (click == "Sensor App Act") {
//            Intent jbjjkjh = new Intent(HomeActivity.this, SensorAct.class);
//            startActivity(jbjjkjh);
//        } else if (click == "Vibrate Sensor Act") {
////                   <uses-permission android:name="android.permission.VIBRATE" />
//            Intent intent = new Intent(HomeActivity.this, VibrateService.class);
//            startService(intent);
//        } else if (click == "Swipe to Delete Act") {
//            Intent intent = new Intent(HomeActivity.this, SwipeToDeleteAct.class);
//            startActivity(intent);
////                    Toast.makeText(HomeActivity.this, "work", Toast.LENGTH_SHORT).show();
//        } else if (click == "Capture Image using Service ") {
//            Intent intent = new Intent(HomeActivity.this, CaptureImageUsingService.class);
//            startActivity(intent);
//        } else if (click == "Different type of image load") {
////                    compile 'com.facebook.fresco:fresco:0.7.0'
////                    in xml
////                    xmlns:fresco="http://schemas.android.com/apk/res-auto"
////                    Fresco.initialize(this);
////                    in same code
//            Intent intent = new Intent(HomeActivity.this, DifferentImageLoadAct.class);
//            startActivity(intent);
//        } else if (click == "All Service Work") {
////                    compile 'com.facebook.fresco:fresco:0.7.0'
////                    in xml
////                    xmlns:fresco="http://schemas.android.com/apk/res-auto"
////                    Fresco.initialize(this);
////                    in same code
//            Intent intent = new Intent(HomeActivity.this, ServiceMainPage.class);
//            startActivity(intent);
//        } else if (click == "Phone SMS") {
//            Intent intent = new Intent(HomeActivity.this, Phone_SMS.class);
//            startActivity(intent);
//        } else if (click == "WIFI") {
//            Intent intent = new Intent(HomeActivity.this, WIFI.class);
//            startActivity(intent);
//        } else if (click == "UI Design") {
//            Intent intent = new Intent(HomeActivity.this, UIDesignAct.class);
//            startActivity(intent);
//        } else if (click == "Payment Integration") {
//            Intent intent = new Intent(HomeActivity.this, MoneyIntegrationAct.class);
//            startActivity(intent);
//        } else if (click == "ButterKnife") {
////               Butter knife is used to connect  id of widget
////               compile 'com.jakewharton:butterknife:8.8.1'
////               annotationProcessor 'com.jakewharton:butterknife-compiler:8.8.1'
////               provide id in all widget
////               right click on    setContentView(R.layout.activity_main); click on genegarte
//            globalClass.showAlertDialog(HomeActivity.this, "Butter Knife", "1) Add Two Dependencies\n" +
//                    "compile 'com.jakewharton:butterknife:8.8.1'\n" +
//                    "annotationProcessor 'com.jakewharton:butterknife-compiler:8.8.1' " + "\n" +
//                    "    classpath 'com.jakewharton:butterknife-gradle-plugin:8.8.1'" + "\n" +
//                    "2) Go to setting plugin Butterknife search and download or install  max rating plugin\n"
//                    + "3) Right click on setContentView(R.layout.activity_main); generate then select butterknife\n", true);
//        } else if (click == "Image Crop and WhatsUp Type Image Selection") {
//            Intent intent = new Intent(HomeActivity.this, ImageCropActivity.class);
//            startActivity(intent);
//        } else if (click == "Best Site And Ui Page Link Best Code") {
//            globalClass.showAlertDialog(HomeActivity.this, "Best code And Ui library Site",
//                    "1) All Code Library \n" +
//                            " ====> https://github.com/amitshekhariitbhu/awesome-android-1\n"
//                            + "2) UI Code Library (Material Design). \n" +
//                            " ====> https://github.com/wasabeef/awesome-android-ui \n"
//                            + "3) All Type Of Code And UI Code. \n " +
//                            "====> https://android-arsenal.com/tag/191?sort=created \n"
//                            + "4) Different kind Of UI Page Design. \n" +
//                            " ====> https://www.uplabs.com/ \n"
//                            + "5) All type of vector image. \n" +
//                            " ====> https://www.flaticon.com/ ", true);
//        } else if (click == "Recycle View") {
//            Intent intent = new Intent(HomeActivity.this, RecycleViewActivity.class);
//            startActivity(intent);
//        }
    }
}

