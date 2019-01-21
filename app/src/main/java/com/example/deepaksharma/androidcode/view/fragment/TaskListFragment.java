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
import com.example.deepaksharma.androidcode.view.BaseFragment;
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

    String worktask[] = {"widgets", "login signup flow"};


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
        if (click == mTaskList.get(0)) {
            navigateScreen(WidgetFragment.TAG);
        } else if (click == mTaskList.get(1)) {
            navigateScreen(WidgetFragment.TAG);
        }
    }

    /**
     * navigate on fragment
     *
     * @param tag represent navigation activity
     */
    private void navigateScreen(String tag) {
        Fragment frm;
        if (tag.equals(WidgetFragment.TAG)) {
            frm = WidgetFragment.getInstance(getArguments());
            navigateFragment(R.id.container, frm, true);
        }
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
