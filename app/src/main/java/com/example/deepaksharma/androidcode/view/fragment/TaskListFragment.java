package com.example.deepaksharma.androidcode.view.fragment;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.FragmentTaskListBinding;
import com.example.deepaksharma.androidcode.view.base.BaseFragment;
import com.example.deepaksharma.androidcode.view.adapter.TaskAdapter;
import com.example.deepaksharma.androidcode.view.home.HomeActivity;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Locale;

public class TaskListFragment extends BaseFragment {
    public static final String TAG = TaskListFragment.class.getSimpleName();
    private FragmentTaskListBinding mBinding;
    private LinkedList<String> mTaskList;
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

    String worktask[] = {"widgets", "webview", "dialog", "location", "login signup flow", "select multiple image", "dynamic layout",
            "shared preference", "device info", "speech to text", "animation", "recycler view", "expendable spinner list view",
            "image", "share", "receiver", "services", "google map", "sqlite data base", "viewpager tab", "finger print",
            "barcode", "digital signature", "pdf","collapse", "ui design", "fab button","bottom navigation"};


    private TaskAdapter mHomeAdapter;

    public static TaskListFragment getInstance(Bundle bundle) {
        TaskListFragment fragment = new TaskListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_task_list;
    }

    @Override
    protected void onViewsInitialized(ViewDataBinding binding, View view) {
        mBinding = (FragmentTaskListBinding) binding;
        init();
    }

    private void init() {
        mTaskList = new LinkedList<String>(Arrays.asList(worktask));
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
                mHomeAdapter.filter(text);
//                mHomeAdapter.getFilter().filter(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void setAdapter() {
        mHomeAdapter = new TaskAdapter(TaskListFragment.this, mTaskList);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mBinding.recyclerView.setAdapter(mHomeAdapter);
    }

    public void onClicks(String click) {
        if (click.equals("widgets")) navigateScreen(WidgetFragment.TAG);
        else if (click.equals("webview")) navigateScreen(WebViewFragment.TAG);
        else if (click.equals("login signup flow")) navigateScreen(SelectMultipleFileFragment.TAG);
        else if (click.equals("select multiple image"))
            navigateScreen(SelectMultipleFileFragment.TAG);
        else if (click.equals("location")) navigateScreen(GpsLocationFragment.TAG);
        else if (click.equals("dialog")) navigateScreen(DialogFragment.TAG);
        else if (click.equals("dynamic layout")) navigateScreen(DynamicLayoutFragment.TAG);
        else if (click.equals("shared preference")) navigateScreen(SharePreferenceFragment.TAG);
        else if (click.equals("device info")) navigateScreen(GetPhoneDetailFragment.TAG);
        else if (click.equals("speech to text")) navigateScreen(SpeechToTextFragment.TAG);
        else if (click.equals("animation")) navigateScreen(AnimationFragment.TAG);
        else if (click.equals("recycler view")) navigateScreen(RecyclerViewFragment.TAG);
        else if (click.equals("expendable spinner list view"))
            navigateScreen(ExpendableSpinnerListFragment.TAG);
        else if (click.equals("image")) navigateScreen(ImageFragment.TAG);
        else if (click.equals("share")) navigateScreen(ShareFragment.TAG);
        else if (click.equals("receiver")) navigateScreen(ReceiverFragment.TAG);
        else if (click.equals("services")) navigateScreen(ServicesFragment.TAG);
        else if (click.equals("google map")) navigateScreen(GoogleMapFragment.TAG);
        else if (click.equals("sqlite data base")) navigateScreen(SqliteDataBaseFragment.TAG);
        else if (click.equals("viewpager tab")) navigateScreen(ViewPagerTabFragment.TAG);
        else if (click.equals("finger print")) navigateScreen(FingerPrintFragment.TAG);
        else if (click.equals("barcode")) navigateScreen(BarCodeFragment.TAG);
        else if (click.equals("digital signature")) navigateScreen(BarCodeFragment.TAG);
        else if (click.equals("pdf")) navigateScreen(PdfFragment.TAG);
        else if (click.equals("collapse")) navigateScreen(CollpaseViewFragment.TAG);
        else if (click.equals("ui design")) navigateScreen(UiDesignFragment.TAG);
        else if (click.equals("fab button")) navigateScreen(FabFragment.TAG);
        else if (click.equals("bottom navigation")) navigateScreen(BottomNaviFragment.TAG);



    }

    /**
     * navigate on fragment
     *
     * @param tag represent navigation activity
     */
    private void navigateScreen(String tag) {
        Fragment frm = null;
        if (tag.equals(WidgetFragment.TAG)) frm = WidgetFragment.getInstance(getArguments());
        else if (tag.equals(WebViewFragment.TAG)) frm = WebViewFragment.getInstance(getArguments());
        else if (tag.equals(SelectMultipleFileFragment.TAG))
            frm = SelectMultipleFileFragment.getInstance(getArguments());
        else if (tag.equals(GpsLocationFragment.TAG))
            frm = GpsLocationFragment.getInstance(getArguments());
        else if (tag.equals(DialogFragment.TAG)) frm = DialogFragment.getInstance(getArguments());
        else if (tag.equals(DynamicLayoutFragment.TAG))
            frm = DynamicLayoutFragment.getInstance(getArguments());
        else if (tag.equals(SharePreferenceFragment.TAG))
            frm = SharePreferenceFragment.getInstance(getArguments());
        else if (tag.equals(GetPhoneDetailFragment.TAG))
            frm = GetPhoneDetailFragment.getInstance(getArguments());
        else if (tag.equals(SpeechToTextFragment.TAG))
            frm = SpeechToTextFragment.getInstance(getArguments());
        else if (tag.equals(AnimationFragment.TAG))
            frm = AnimationFragment.getInstance(getArguments());
        else if (tag.equals(RecyclerViewFragment.TAG))
            frm = RecyclerViewFragment.getInstance(getArguments());
        else if (tag.equals(ExpendableSpinnerListFragment.TAG))
            frm = ExpendableSpinnerListFragment.getInstance(getArguments());
        else if (tag.equals(ImageFragment.TAG))
            frm = ImageFragment.getInstance(getArguments());
        else if (tag.equals(ShareFragment.TAG))
            frm = ShareFragment.getInstance(getArguments());
        else if (tag.equals(ReceiverFragment.TAG))
            frm = ReceiverFragment.getInstance(getArguments());
        else if (tag.equals(ServicesFragment.TAG))
            frm = ServicesFragment.getInstance(getArguments());
        else if (tag.equals(GoogleMapFragment.TAG))
            frm = GoogleMapFragment.getInstance(getArguments());
        else if (tag.equals(SqliteDataBaseFragment.TAG))
            frm = SqliteDataBaseFragment.getInstance(getArguments());
        else if (tag.equals(ViewPagerTabFragment.TAG))
            frm = ViewPagerTabFragment.getInstance(getArguments());
        else if (tag.equals(FingerPrintFragment.TAG))
            frm = FingerPrintFragment.getInstance(getArguments());
        else if (tag.equals(BarCodeFragment.TAG))
            frm = BarCodeFragment.getInstance(getArguments());
        else if (tag.equals(PdfFragment.TAG))
            frm = PdfFragment.getInstance(getArguments());
        else if (tag.equals(FabFragment.TAG))
            frm = FabFragment.getInstance(getArguments());
        else if (tag.equals(UiDesignFragment.TAG))
            frm = UiDesignFragment.getInstance(getArguments());
        else if (tag.equals(BottomNaviFragment.TAG))
            frm = BottomNaviFragment.getInstance(getArguments());
        else if (tag.equals(CollpaseViewFragment.TAG))
            frm = CollpaseViewFragment.getInstance(getArguments());
        navigateFragment(R.id.container, frm, true);
    }

    @Override
    public void onResume() {
        super.onResume();
        updateTitle();
    }

    public void updateTitle() {
        ((HomeActivity) getActivity()).hideBackBtn();
        ((HomeActivity) getActivity()).setToolbarTitle(getResources().getString(R.string.app_name));
    }
}
