package com.example.deepaksharma.androidcode.utils;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.deepaksharma.androidcode.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationHelper {
    private static float mAlpha = 0.4f;


    public static boolean isChecked(@Nullable CheckBox checkbox, @Nullable String msg) {
        if (!checkbox.isChecked()) {
            showToast(checkbox.getContext(), msg);
            return false;
        }
        return true;
    }

    public enum ALERT_TYPE {TOAST, SNACK_BAR}

    /**
     * This method returns true if a edit text is blank ,false otherwise
     *
     * @param targetEditText source edit text
     * @param msg            message to be shown in snackbar
     * @return
     */
    public static boolean isBlank(@NonNull TextView targetEditText, String msg) {
        String source = targetEditText.getText().toString().trim();
        if (source.isEmpty()) {
            showAlert(targetEditText, ALERT_TYPE.SNACK_BAR, msg);
            return true;
        }
        return false;
    }

    public static boolean isBlank(@NonNull TextView targetEditText) {
        return targetEditText.getText().toString().trim().isEmpty();
    }

    public static boolean validatePasswordSameFields(EditText password, EditText confPassword, String message, boolean showToast) {
        if (password.getText().toString().equals(confPassword.getText().toString())) {
            return true;
        } else {
            if (showToast)
                showToast(password.getContext(), message);
            else
                showAlert(password, ALERT_TYPE.SNACK_BAR, message);
            return false;
        }
    }

    public static boolean isBlank(@NonNull TextView textView, String msg, boolean showToast) {
        String source = textView.getText().toString().trim();
        if (source.isEmpty() && showToast) {
            showToast(textView.getContext(), msg);
            return true;
        }
        return false;
    }

    public static boolean hasMinimumLength(EditText editText, int length, String message, boolean showToast) {
        if (!hasMinimumLength(editText.getText().toString().trim(), length)) {
            if (showToast)
                showToast(editText.getContext(), message);
            else
                showAlert(editText, ALERT_TYPE.SNACK_BAR, message);
            return false;
        }
        return true;

    }


    /**
     * @param v view for check mAlpha
     * @return mAlpha status
     */
    public static boolean isAlphaEnable(View v) {
        if (v != null) {
            if (v.getAlpha() < 1) return true;
            else return false;
        }
        return false;
    }

    /**
     * @param v        view for set mAlpha
     * @param setAlpha
     */
    public static void setAlpha(View v, boolean setAlpha) {
        if (v != null) {
            if (setAlpha) v.setAlpha(mAlpha);
            else v.setAlpha(1);
        }
    }

    /**
     * This method returns true if a edit text contains valid email ,false otherwise
     *
     * @param targetEditText source edit text
     * @param msg            message to be shown in snackbar
     * @return
     */
    public static boolean isEmailValid(@NonNull EditText targetEditText, String msg) {
        String source = targetEditText.getText().toString().trim();
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern p = Pattern.compile(expression, Pattern.CASE_INSENSITIVE); // pattern=/^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\.([a-zA-Z])+([a-zA-Z])+/;
        Matcher m = p.matcher(source);
        if (m.matches() && source.trim().length() > 0) {
            return true;
        }
        showAlert(targetEditText, ALERT_TYPE.SNACK_BAR, msg);
        return false;
    }

    public static boolean isEmailValid(@NonNull EditText targetEditText, String msg, boolean showToast) {
        String source = targetEditText.getText().toString().trim();
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern p = Pattern.compile(expression, Pattern.CASE_INSENSITIVE); // pattern=/^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\.([a-zA-Z])+([a-zA-Z])+/;
        Matcher m = p.matcher(source);
        if (m.matches() && source.trim().length() > 0) {
            return true;
        }
        if (showToast)
            showAlert(targetEditText, ALERT_TYPE.TOAST, msg);
        return false;
    }

    public static boolean isEmailValid(@NonNull EditText targetEditText) {
        String source = targetEditText.getText().toString().trim();
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern p = Pattern.compile(expression, Pattern.CASE_INSENSITIVE); // pattern=/^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\.([a-zA-Z])+([a-zA-Z])+/;
        Matcher m = p.matcher(source);
        if (m.matches() && source.trim().length() > 0) {
            return true;
        }

        return false;
    }

    /**
     * This method returns true if a edit text contains any digit in it ,false otherwise
     *
     * @param targetEditText source edit text
     * @param msg            message to be shown in snackbar
     * @return
     */
    public static boolean isContainDigit(@NonNull EditText targetEditText, ALERT_TYPE alertType, String msg, boolean msgType) {
        String pattern = ".*\\d.*";
        String source = targetEditText.getText().toString().trim();
        if (source.matches(pattern)) {
            if (msgType) {
                showAlert(targetEditText, alertType, msg);
            }
            return true;
        } else {
            if (!msgType) {
                showAlert(targetEditText, alertType, msg);
            }
            return false;
        }
    }

    public static boolean passwordCheck(String string) {
        String pattern = ".*\\d.*";
        boolean atleastOneAlpha = string.matches(".*[a-zA-Z]+.*");
        String source = string.trim();
        if (!source.matches(pattern) || string.length() < 8 || !atleastOneAlpha) {
        } else return true;
        return false;
    }

    public static boolean containOnlyDigit(@NonNull EditText targetEditText, String msg) {
        String pattern = "\\d+";
        String source = targetEditText.getText().toString().trim();
        if (source.matches(pattern)) {
            return true;
        } else {
            showAlert(targetEditText, ALERT_TYPE.SNACK_BAR, msg);
            return false;
        }
    }


    public static boolean isEqual(@NonNull EditText sourceEditText, @NonNull EditText destinationEditText, String msg, boolean msgType) {
        return isEqual(sourceEditText, destinationEditText, ALERT_TYPE.SNACK_BAR, msg, msgType);

    }

    public static boolean isEqual(@NonNull EditText sourceEditText, @NonNull EditText destinationEditText, ALERT_TYPE alertType, String msg, boolean msgType) {

        String source = sourceEditText.getText().toString().trim();
        String destination = destinationEditText.getText().toString().trim();
        if (source.equalsIgnoreCase(destination)) {
            if (msgType) {
                showAlert(destinationEditText, alertType, msg);
            }
            return true;
        } else {
            if (!msgType) {
                showAlert(destinationEditText, alertType, msg);
            }
            return false;
        }

    }

    public static boolean isEqual(@NonNull EditText sourceEditText, @NonNull EditText destinationEditText, String msg) {

        String source = sourceEditText.getText().toString().trim();
        String destination = destinationEditText.getText().toString().trim();
        if (source.equalsIgnoreCase(destination)) {
            return true;
        } else {
            showToast(destinationEditText.getContext(), msg);
            return false;
        }

    }

    public static boolean isSame(@NonNull TextView sourceEditText, String destinationString, String msg) {

        String source = sourceEditText.getText().toString().trim();
        if (source.equalsIgnoreCase(destinationString)) {
            showToast(sourceEditText.getContext(), msg);
            return true;
        }
        return false;

    }

    public static void showToast(Context context, String msg) {
        Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

    private static void showAlert(Context context, String msg) {

    }

    private static void showAlert(TextView targetEditText, ALERT_TYPE alertType, String msg) {
        //View v = parentLayout == null ? targetEditText.getRootView() : parentLayout;
        targetEditText.requestFocus();
        if (alertType == ALERT_TYPE.TOAST) {
            showToast(targetEditText.getContext(), msg);
        } else if (alertType == ALERT_TYPE.SNACK_BAR) {
            showSnackBar(targetEditText, msg);
        }

    }


    public static void showSnackBar(View parentLayout, String msg) {
        final Snackbar snackBar = Snackbar.make(parentLayout, msg, Snackbar.LENGTH_SHORT);
        snackBar.setActionTextColor(Color.WHITE);
        View view = snackBar.getView();
        TextView tv = (TextView)
                view.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(Color.WHITE);
        snackBar.show();

    }

    public static boolean hasMinimumLength(String source, int length) {
        if (source.trim().length() >= length)
            return true;
        return false;
    }

    public static boolean hasMinimumLength(EditText editText, int length, String message) {
        if (!hasMinimumLength(editText.getText().toString().trim(), length)) {
            showAlert(editText, ALERT_TYPE.SNACK_BAR, message);
            return false;
        }
        return true;

    }

    public static InputFilter getBlockedSpecialCharacterFilter() {
        final String blockCharacterSet = "~#^|$%&*!@+_-1234567890";
        return new InputFilter() {

            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if (source != null && blockCharacterSet.contains(("" + source))) {
                    return "";
                }
                return null;
            }
        };

    }

    public static boolean isValidName(TextView textView, String message) {
        String targetString = textView.getText().toString().trim();
        String regx = "^[\\p{L} .'-]+$";
        if (Pattern.matches(regx, targetString)) {
            return true;
        }
        showAlert(textView, ALERT_TYPE.SNACK_BAR, message);
        return false;
    }

    public static boolean hasMinimumwords(EditText editText, ALERT_TYPE alertType, int length, String message) {
        if (editText.getText().toString().trim().length() >= length) {
            showAlert(editText, alertType, message);
            return false;
        } else {
            return true;
        }
    }

    public static boolean isValidURL(EditText mFeedEditText, ALERT_TYPE alertType, String msg) {

        String url = mFeedEditText.getText().toString().toLowerCase();
        if (Patterns.WEB_URL.matcher(url).matches()) {
            return true;
        } else {
            showAlert(mFeedEditText, alertType, msg);
            return false;
        }
    }

    public static boolean isValidExpDate(String expDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/yy", Locale.getDefault());
        Date strDate = null;
        try {
            strDate = sdf.parse(expDate);
        } catch (ParseException e) {
            return false;
        }
        Calendar validDate = new GregorianCalendar();
        validDate.setTime(strDate);
        return Calendar.getInstance().before(validDate);
    }

    public static boolean isValidExpDate(EditText expDateEt, String msg) {
        String expDate = expDateEt.getText().toString();
        if (!isValidExpDate(expDate)) {
            showSnackBar(expDateEt, msg);
            return false;
        }
        return true;
    }

    public static boolean isValidPassword(TextInputLayout layoutPassword, String password, String errorMsg) {
        Pattern UpperCasePatten = Pattern.compile("[A-Z ]");
        Pattern lowerCasePatten = Pattern.compile("[a-z ]");
        Pattern digitCasePatten = Pattern.compile("[0-9 ]");
        if (!UpperCasePatten.matcher(password).find()) {
            layoutPassword.setError(layoutPassword.getContext().getResources().getString(R.string.error_uppercase_pattern));
            return false;
        } else if (!lowerCasePatten.matcher(password).find()) {
            layoutPassword.setError(layoutPassword.getContext().getResources().getString(R.string.error_lowercase_pattern));
            return false;
        } else if (!digitCasePatten.matcher(password).find()) {
            layoutPassword.setError(layoutPassword.getContext().getResources().getString(R.string.error_digit_pattern));
            return false;
        }else if (password.length()<8){
            layoutPassword.setError(errorMsg);
            return false;
        }
        layoutPassword.setError(null);
        return true;

    }


    /**
     * check username validation
     *
     * @param edtName     first name widget
     * @param wrapperName
     * @return name validation status
     */
    public static boolean validateName(TextInputEditText edtName, TextInputLayout wrapperName) {
        if (ValidationHelper.isBlank(edtName)) {
            wrapperName.setError(edtName.getContext().getResources().getString(R.string.enter_first_name));
        } else {
            wrapperName.setError(null);
            return true;
        }
        return false;
    }

    /**
     * check username validation
     *
     * @param edtLastName     last name widget
     * @param wrapperLastname
     * @return name validation status
     */
    public static boolean validateLastName(TextInputEditText edtLastName, TextInputLayout wrapperLastname) {
        if (ValidationHelper.isBlank(edtLastName)) {
            wrapperLastname.setError(edtLastName.getContext().getResources().getString(R.string.enter_last_name));
        } else {
            wrapperLastname.setError(null);
            return true;
        }
        return false;
    }

    /**
     * check email id validation
     *
     * @param wrapperEmailId
     * @param edtEmailId     email id widget
     * @return email id validation status
     */
    public static boolean validateEmail(TextInputEditText edtEmailId, TextInputLayout wrapperEmailId) {
        if (ValidationHelper.isBlank(edtEmailId)) {
            wrapperEmailId.setError(edtEmailId.getContext().getResources().getString(R.string.enter_your_email));
        } else if (!Patterns.EMAIL_ADDRESS.matcher(edtEmailId.getText().toString()).matches()) {
            wrapperEmailId.setError(edtEmailId.getContext().getResources().getString(R.string.error_enter_valid_email));
        } else {
            wrapperEmailId.setError(null);
            return true;
        }
        return false;
    }

    /**
     * check email id validation
     *
     * @param edtEmailId email id widget
     * @return email id validation status
     */
    public static boolean validateFatherEmail(TextInputEditText edtEmailId, TextInputLayout inputLayout, String childEmail) {
        if (ValidationHelper.isBlank(edtEmailId)) {
            inputLayout.setError(edtEmailId.getContext().getResources().getString(R.string.enter_your_email));
        } else if (!Patterns.EMAIL_ADDRESS.matcher(edtEmailId.getText().toString()).matches()) {
            inputLayout.setError(edtEmailId.getContext().getResources().getString(R.string.error_enter_valid_email));
        } else if (edtEmailId.getText().toString().trim().equals(childEmail.trim())) {
            inputLayout.setError(edtEmailId.getContext().getResources().getString(R.string.error_email_must_be_different));
        } else {
            inputLayout.setError(null);
            return true;
        }
        return false;
    }

    /**
     * check dob validation
     *
     * @param edtDob     dob widget
     * @param wrapperDob
     * @return dob validation status
     */
    public static boolean validateDob(TextInputEditText edtDob, TextInputLayout wrapperDob) {
        if (ValidationHelper.isBlank(edtDob)) {
            wrapperDob.setError(edtDob.getContext().getResources().getString(R.string.enter_dob));
        } else {
            wrapperDob.setError(null);
            return true;
        }
        return false;
    }

    /**
     * check country validation
     *
     * @param edtCountry     country widget
     * @param wrapperCountry
     * @return country validation status
     */
    public static boolean validateCountry(TextInputEditText edtCountry, TextInputLayout wrapperCountry) {
        if (ValidationHelper.isBlank(edtCountry)) {
            wrapperCountry.setError(edtCountry.getContext().getResources().getString(R.string.enter_country));
        } else {
            wrapperCountry.setError(null);
            return true;
        }
        return false;
    }

    /**
     * check password validation
     *
     * @param wrapperPsw
     * @param edtPassword password widget
     * @return password validation status
     */
    public static boolean validatePwd(TextInputEditText edtPassword, TextInputLayout wrapperPsw) {
        if (isBlank(edtPassword)) {
            wrapperPsw.setError(edtPassword.getContext().getResources().getString(R.string.enter_passowrd));
        } else if (!isValidPassword(wrapperPsw,edtPassword.getText().toString(), edtPassword.getContext().getResources().getString(R.string.password_length))) {
        } else {
            wrapperPsw.setError(null);
            return true;
        }
        return false;
    }

    /**
     * check confirm password validation
     *
     * @param confirmPassword password widget
     * @param wrapperConPsw
     * @param password
     * @return confirm password validation status
     */
    public static boolean validateConfirmPwd(TextInputEditText confirmPassword, TextInputLayout wrapperConPsw, String password) {
        if (isBlank(confirmPassword)) {
            wrapperConPsw.setError(confirmPassword.getContext().getResources().getString(R.string.enter_passowrd));
        } else if (!isValidPassword(wrapperConPsw, confirmPassword.getText().toString(),confirmPassword.getContext().getResources().getString(R.string.confirm_pwd_length)) ) {
        } else if (!confirmPassword.getText().toString().equals(password)) {
            wrapperConPsw.setError(confirmPassword.getContext().getResources().getString(R.string.error_passowrd_not_match));
        } else {
            wrapperConPsw.setError(null);
            return true;
        }
        return false;
    }

    /**
     * check edit profile validation
     *
     * @param textInput     widget
     * @param wrapperNewPwd
     * @return change password validation status
     */
    public static boolean validateConfirmChangePwd(TextInputEditText textInput, TextInputLayout wrapperNewPwd, String oldPassword) {
        if (isBlank(textInput)) {
            wrapperNewPwd.setError(textInput.getContext().getResources().getString(R.string.enter_passowrd));
        } else if (!isValidPassword(wrapperNewPwd, textInput.getText().toString(), textInput.getContext().getResources().getString(R.string.confirm_pwd_length))) {
        } else if (textInput.getText().toString().trim().equals(oldPassword.trim())) {
            wrapperNewPwd.setError(textInput.getContext().getResources().getString(R.string.error_password_different));
        } else {
            wrapperNewPwd.setError(null);
            return true;
        }
        return false;
    }

    public static boolean validateReferral(TextInputEditText edtReferralCode, TextInputLayout wrapperReferralCode) {
        if (ValidationHelper.isBlank(edtReferralCode)) {
            wrapperReferralCode.setError(edtReferralCode.getContext().getResources().getString(R.string.error_referral_code));
        } else {
            wrapperReferralCode.setError(null);
            return true;
        }
        return false;
    }

    public static boolean validateBlank(TextInputEditText textInput, TextInputLayout wrapper, String enter_subject) {
        if (ValidationHelper.isBlank(textInput)) {
            wrapper.setError(enter_subject);
        } else {
            wrapper.setError(null);
            return true;
        }
        return false;
    }

    public static boolean validateCode(TextInputEditText textInput, TextInputLayout wrapperCode) {
        if (ValidationHelper.isBlank(textInput)) {
            wrapperCode.setError(textInput.getContext().getResources().getString(R.string.error_valid_code));
        } else if (!(textInput.getText().toString().length() == 6)) {
            wrapperCode.setError(textInput.getContext().getResources().getString(R.string.error_six_digit_code));
        } else {
            wrapperCode.setError(null);
            return true;
        }
        return false;
    }
}
