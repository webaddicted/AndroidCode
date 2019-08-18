package com.example.deepaksharma.androidcode.view.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.SeekBar;

import androidx.appcompat.widget.PopupMenu;
import androidx.databinding.ViewDataBinding;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.FragmentWidgetBinding;
import com.example.deepaksharma.androidcode.global.ValidationHelper;
import com.example.deepaksharma.androidcode.global.constant.AppConstant;
import com.example.deepaksharma.androidcode.utils.DatePickerCustomDialog;
import com.example.deepaksharma.androidcode.utils.GlobalUtilities;
import com.example.deepaksharma.androidcode.view.base.BaseFragment;
import com.example.deepaksharma.androidcode.view.adapter.OptionItemMenu;
import com.example.deepaksharma.androidcode.view.home.HomeActivity;
import com.google.android.material.textfield.TextInputEditText;

public class WidgetFragment extends BaseFragment {
    public static final String TAG = WidgetFragment.class.getSimpleName();
    private FragmentWidgetBinding mBinding;
    private int mProgress;
    String selectionArray[] = {"America", "Belgium", "Canada", "Denmark", "England", "France", "Germany", "Holland", "India", "Indonesia", "Italy", "Spain"};


    public static WidgetFragment getInstance(Bundle bundle) {
        WidgetFragment fragment = new WidgetFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_widget;
    }

    @Override
    protected void onViewsInitialized(ViewDataBinding binding, View view) {
        mBinding = (FragmentWidgetBinding) binding;
        init();
        clickListener();
    }

    private void init() {
        mBinding.txtMarquee.setSelected(true);
        GlobalUtilities.setSpannable(mBinding.txtSpannable, getResources().getString(R.string.marquee_txt),5,50);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, selectionArray);
        mBinding.autoCompleteTextVie.setAdapter(adapter);
        mBinding.autoCompleteTextVie.setThreshold(1);
        //multi.setAdapter(adapter);
        //multi.setThreshold(1);
        mBinding.multiAutoCompleteTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        mBinding.multiAutoCompleteTextView.setAdapter(adapter);
        mBinding.multiAutoCompleteTextView.setThreshold(1);
    }



    private void clickListener() {
        mBinding.edtEmail.addTextChangedListener(new EditTextListener(mBinding.edtEmail));
        mBinding.edtPwd.addTextChangedListener(new EditTextListener(mBinding.edtPwd));
        mBinding.rg.setOnCheckedChangeListener((group, checkedId) -> {
            int selectedId = mBinding.rg.getCheckedRadioButtonId();
            RadioButton rb = getView().findViewById(selectedId);
            mBinding.cbNone.setTextColor(getResources().getColor(R.color.app_color));
            mBinding.cbUnselected.setTextColor(getResources().getColor(R.color.app_color));
            mBinding.cbSelected.setTextColor(getResources().getColor(R.color.app_color));
            rb.setTextColor(getResources().getColor(R.color.green));
        });
        mBinding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                mBinding.txtSeekbar.setText(progress + "/" + seekBar.getMax());
            }
        });
        mBinding.autoCompleteTextVie.setOnItemClickListener((adapterView, view, position, l) -> {
            String selection = (String) adapterView.getItemAtPosition(position);
        });
        mBinding.btnLogin.setOnClickListener(this);
        mBinding.btnDataPicker.setOnClickListener(this);
        mBinding.btnTimePicker.setOnClickListener(this);
        mBinding.btnStartProgress.setOnClickListener(this);
        mBinding.imgOptionMenu.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_login:
                if (validate()) GlobalUtilities.showToast(getResources().getString(R.string.done));
                break;
            case R.id.btn_data_picker:
                StringBuilder stringBuilder = new StringBuilder();
                GlobalUtilities.getDate(getActivity(), mBinding.txtDateValue);
                String dateformate = mBinding.txtDateValue.getText().toString();
                stringBuilder.append(dateformate+"\n");
                stringBuilder.append(GlobalUtilities.dateFormate(dateformate, AppConstant.DATE_FORMAT_D_M_Y,AppConstant.DATE_FORMAT_Y_M_D)+"\n");
                stringBuilder.append(GlobalUtilities.dateFormate(dateformate, AppConstant.DATE_FORMAT_D_M_Y,AppConstant.DATE_FORMAT_D_M_Y_H)+"\n");
                stringBuilder.append(GlobalUtilities.dateFormate(dateformate, AppConstant.DATE_FORMAT_D_M_Y,AppConstant.DATE_FORMAT_SRC)+"\n");
                mBinding.txtDateValue.setText(stringBuilder.toString());
                break;
            case R.id.btn_time_picker:
                DatePickerCustomDialog.getTime(getActivity(), (view, hourOfDay, minute) -> mBinding.txtTimeValue.setText("Time is - " + hourOfDay + " : " + minute)).show();
                break;
            case R.id.btn_start_progress:
                new Thread(progressBarThread).start();
                break;
            case R.id.img_option_menu:
                showPopupMenu(mBinding.imgOptionMenu);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ((HomeActivity) getActivity()).showBackBtn();
        ((HomeActivity) getActivity()).setToolbarTitle(getResources().getString(R.string.widget_view_title));
    }

    private class EditTextListener implements TextWatcher {
        TextInputEditText textInput;

        public EditTextListener(TextInputEditText edtText) {
            this.textInput = edtText;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            switch (textInput.getId()) {
                case R.id.edt_email:
                    ValidationHelper.validateEmail(mBinding.edtEmail, mBinding.wrpperEmail);
                    break;
                case R.id.edt_pwd:
                    ValidationHelper.validatePwd(mBinding.edtPwd, mBinding.wrapperPwd);
                    break;
            }
            validate();
        }
    }

    private boolean validate() {
        if (!ValidationHelper.validateEmail(mBinding.edtEmail, mBinding.wrpperEmail)) return false;
        else if (!ValidationHelper.validatePwd(mBinding.edtPwd, mBinding.wrapperPwd)) return false;
        return true;
    }

    private Runnable progressBarThread = new Runnable() {
        @Override
        public void run() {
            while (mProgress < 100) {
                try {
                    Thread.sleep(1000);
                    mBinding.progress.setProgress(mProgress);
                    getActivity().runOnUiThread(() -> mBinding.txtProgress.setText(mProgress+"/"+mBinding.progress.getMax()));
                    mProgress++;
                } catch (Throwable t) {
                    Log.d(TAG, "run: " + t.toString());
                }
            }
        }
    };

    private void showPopupMenu(View view) {
        PopupMenu popup = new PopupMenu(getActivity(), view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.option_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(new OptionItemMenu(getActivity()));
        popup.show();
    }

}
