package com.example.deepaksharma.androidcode.global;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.example.deepaksharma.androidcode.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class DialogUtil {
    private static final String TAG = DialogUtil.class.getSimpleName();

    public static ProgressDialog showProgressDialog(@NonNull Context mContext) {
        try {
            if (mContext != null && !((Activity) mContext).isFinishing()) {
                ProgressDialog mProgressDialog = ProgressDialog.show(mContext,
                        "", mContext.getString(R.string.please_wait));
                return mProgressDialog;
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return new ProgressDialog(mContext);
    }

    public static void showProgressDialog(@NonNull ProgressDialog progressDialog) {
        try {
            if (progressDialog != null) {
                Activity activity = (Activity) progressDialog.getContext();
                if (activity != null && !activity.isFinishing()) {
                    if (!progressDialog.isShowing()) progressDialog.show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * close open progress dialog
     *
     * @param progressDialog progress dialog referance
     */
    public static void hideProgressDialog(@NonNull ProgressDialog progressDialog) {
        if (progressDialog != null && progressDialog.isShowing()) progressDialog.dismiss();
    }

    /**
     * show snackbar action button
     *
     * @param view     snackbar show view
     * @param listener action button click listener
     * @return snackbar
     */
    public static Snackbar showNoNetworkSnackBar(@NonNull View view, @NonNull View.OnClickListener listener) {
        String msg = view.getContext().getResources().getString(R.string.no_network_msg);
        return showSnackBar(view, msg, R.string.retry, listener);
    }

    /**
     * @param view          snackbar show view
     * @param msg           sanckbar message
     * @param strBtn        action button anme
     * @param retryListener action button click listener
     * @return snackbar
     */
    public static Snackbar showSnackBar(@NonNull View view, @NonNull String msg, @NonNull int strBtn, @NonNull View.OnClickListener retryListener) {
        try {
            Snackbar snackBar = Snackbar.make(view, msg, Snackbar.LENGTH_INDEFINITE);
            snackBar.setActionTextColor(ContextCompat.getColor(view.getContext(), R.color.app_color));
            View snackBarView = snackBar.getView();
            TextView tv = snackBarView.findViewById(R.id.snackbar_text);
            snackBarView.setBackgroundColor(ContextCompat.getColor(snackBarView.getContext(), R.color.app_color));
            tv.setTextColor(ContextCompat.getColor(snackBarView.getContext(), R.color.white));
            snackBar.setAction(strBtn, v -> {
                snackBar.dismiss();
                retryListener.onClick(v);
            });
            snackBar.show();
            return snackBar;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param context    referance of activity
     * @param title      title of dialog
     * @param msg        message of dialog
     * @param okBtn      ok button text
     * @param okListener button click listener
     * @return dialog
     */
    public static AlertDialog showOkDialog(@NonNull Context context, @NonNull String title, @NonNull String msg, @NonNull String okBtn, @NonNull AlertDialog.OnClickListener okListener) {
        return showOkDialog(context, R.style.AlertDialogStyle, R.style.DialogLeftRightAnimation, title, msg, 0, true, okBtn, okListener);
    }

    /**
     * @param context         referance of activity
     * @param style           set dialog style
     * @param dialogAnimation set enter exit animation on dilaog
     * @param title           title of dialog
     * @param msg             message of dialog
     * @param icon            set icon
     * @param isCancelable    set is dialog cancelable
     * @param okBtn           ok button text
     * @param okListener      button click listener
     * @return dialog
     */
    public static AlertDialog showOkDialog(@NonNull Context context, @NonNull int style, @NonNull int dialogAnimation, @NonNull String title, @NonNull String msg, @NonNull int icon, @NonNull boolean isCancelable, @NonNull String okBtn, @NonNull AlertDialog.OnClickListener okListener) {
        AlertDialog alertDialog = new AlertDialog.Builder(context, style).create();
        alertDialog.getWindow().getAttributes().windowAnimations = dialogAnimation;
        if (title != null) alertDialog.setTitle(title);
        if (msg != null) alertDialog.setMessage(msg);
        if (icon > 0) alertDialog.setIcon(icon);
        alertDialog.setCancelable(isCancelable);
        alertDialog.setButton(okBtn, okListener);
        alertDialog.show();
        return alertDialog;
    }

    /**
     * @param context        referance of activity
     * @param title          title of dialog
     * @param msg            message of dialog
     * @param okListener     ok button click listener
     * @param cancelListener cancel button click listener
     * @return dialog
     */
    public static AlertDialog.Builder showOkCancelDialog(@NonNull Context context, @NonNull String title, @NonNull String msg, @NonNull AlertDialog.OnClickListener okListener, @NonNull AlertDialog.OnClickListener cancelListener) {
        return showOkCancelDialog(context, R.style.AlertDialogStyle, R.style.DialogSlideUpAnimation, title, msg, 0, false, context.getString(R.string.ok), context.getString(R.string.cancel), okListener, cancelListener);
    }

    /**
     * @param context         referance of activity
     * @param style           set dialog style
     * @param dialogAnimation set enter exit animation on dilaog
     * @param title           title of dialog
     * @param msg             message of dialog
     * @param icon            set icon
     * @param isCancelable    set is dialog cancelable
     * @param okBtn           ok button text
     * @param cancelBtn       cancel button text
     * @param okListener      ok button click listener
     * @param cancelListener  cancel button click listener
     * @return dialog
     */
    public static AlertDialog.Builder showOkCancelDialog(@NonNull Context context, @NonNull int style, @NonNull int dialogAnimation, @NonNull String title, @NonNull String msg, @NonNull int icon, @NonNull boolean isCancelable, @NonNull String okBtn, @NonNull String cancelBtn, @NonNull AlertDialog.OnClickListener okListener, @NonNull AlertDialog.OnClickListener cancelListener) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context, style);
        if (title != null) alertDialog.setTitle(title);
        if (msg != null) alertDialog.setMessage(msg);
        if (icon > 0) alertDialog.setIcon(icon);
        alertDialog.setCancelable(isCancelable);
        alertDialog.setPositiveButton(okBtn, okListener);
        alertDialog.setNegativeButton(cancelBtn, cancelListener);
        AlertDialog alertDialogs = alertDialog.create();
        alertDialogs.getWindow().getAttributes().windowAnimations = dialogAnimation;
        alertDialog.show();
        return alertDialog;
    }

    /**
     * @param context        referance of activity
     * @param title          title of single selection dilaog
     * @param items          item list in dilog
     * @param okListener     ok click listener
     * @param cancelListener cancel click listener
     * @param <T>
     * @return dialog
     */
    public static <T> AlertDialog getSingleChoiceDialog(Context context, String title, List<T> items, DialogInterface.OnClickListener okListener, DialogInterface.OnClickListener cancelListener) {
        return showSingleChoiceDialog(context, R.style.AlertDialogStyle, R.style.DialogSlideUpAnimation, title, items, 0, context.getResources().getString(R.string.ok), context.getResources().getString(R.string.cancel), okListener, cancelListener);
    }

    /**
     * @param context         referance of activity
     * @param style           dialog style
     * @param dialogAnimation animation on dialog
     * @param title           title of dialog
     * @param items           list of dialog
     * @param checkedItem     initial selected item
     * @param okBtn           ok button text
     * @param cancelBtn       cancel button text
     * @param okListener      ok click listener
     * @param cancelListener  cancel click listener
     * @param <T>
     * @return dialog
     */
    public static <T> AlertDialog showSingleChoiceDialog(Context context, @NonNull int style, @NonNull int dialogAnimation, String title, List<T> items, int checkedItem, String okBtn, String cancelBtn, DialogInterface.OnClickListener okListener, DialogInterface.OnClickListener cancelListener) {
        int size = items.size();
        String[] itemArray = new String[size];
        for (int i = 0; i < size; i++) {
            itemArray[i] = items.get(i).toString();
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context, style);
        if (title != null) builder.setTitle(title);
        builder.setSingleChoiceItems(itemArray, checkedItem, (dialog, which) -> {
        });
        builder.setPositiveButton(okBtn, okListener);
        builder.setNegativeButton(cancelBtn, cancelListener);
        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().getAttributes().windowAnimations = dialogAnimation;
        alertDialog.show();
        return alertDialog;
    }

    /**
     * @param context    referance of activity
     * @param title      title of dialog
     * @param items      list show in diloag
     * @param okListener ok click listener
     * @param <T>
     * @return dialog
     */
    public static <T> AlertDialog showListDialog(Context context, String title, List<T> items, DialogInterface.OnClickListener okListener) {
        return showListDialog(context, R.style.AlertDialogStyle, R.style.DialogSlideUpAnimation, title, items, okListener);
    }

    /**
     * @param context         referance of activity
     * @param style           dialog style
     * @param dialogAnimation dialog animation
     * @param title           dialog title
     * @param items           list show in dilaog
     * @param listener        ok click listener
     * @param <T>
     * @return dialog
     */
    public static <T> AlertDialog showListDialog(Context context, @NonNull int style, @NonNull int dialogAnimation, String title, List<T> items, DialogInterface.OnClickListener listener) {
        int size = items.size();
        String[] itemArray = new String[size];
        for (int i = 0; i < size; i++) {
            itemArray[i] = items.get(i).toString();
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context, style);
        if (title != null) builder.setTitle(title);
        builder.setItems(itemArray, listener);
        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().getAttributes().windowAnimations = dialogAnimation;
        alertDialog.show();
        return alertDialog;
    }

//    {START SHOW DIALOG STYLE}
//    apply on resume method

    /**
     * show dialog with transprant background
     *
     * @param activity reference of activity
     * @param dialog   reference of dialog
     */
    public static void modifyDialogBounds(Activity activity, Dialog dialog) {
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(activity, android.R.color.transparent)));
        dialog.getWindow().getDecorView().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = dialog.getWindow();
        lp.copyFrom(window.getAttributes());
        //This makes the dialog take up the full width
        //lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.width = (int) (dialog.getContext().getResources().getDisplayMetrics().widthPixels * 0.83);
        //  lp.height = (int) (dialog.getContext().getResources().getDisplayMetrics().heightPixels * 0.55);
        window.setAttributes(lp);
    }

    /**
     * show dialog in full screen
     *
     * @param activity reference of activity
     * @param dialog   reference of dialog
     */
    public static void fullScreenDialogBounds(Activity activity, Dialog dialog) {
        if (dialog != null && dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(activity, android.R.color.transparent)));
            dialog.getWindow().getDecorView().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            if (dialog != null) {
                int width = ViewGroup.LayoutParams.MATCH_PARENT;
                int height = ViewGroup.LayoutParams.MATCH_PARENT;
                dialog.getWindow().setLayout(width, height);
            }
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            Window window = dialog.getWindow();
            lp.copyFrom(window.getAttributes());
            //This makes the dialog take up the full width
            //lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.width = (int) (dialog.getContext().getResources().getDisplayMetrics().widthPixels * 0.83);
            //  lp.height = (int) (dialog.getContext().getResources().getDisplayMetrics().heightPixels * 0.55);
            window.setAttributes(lp);
        }
    }

    /**
     * show dialog in full screen
     *
     * @param activity reference of activity
     * @param dialog   reference of dialog
     */
    public static void fullScreenTransDialogBounds(Activity activity, Dialog dialog) {
        if (dialog != null && dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(activity, android.R.color.transparent)));
            dialog.getWindow().getDecorView().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            if (dialog != null) {
                int width = ViewGroup.LayoutParams.MATCH_PARENT;
                int height = ViewGroup.LayoutParams.MATCH_PARENT;
                dialog.getWindow().setLayout(width, height);
            }
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            Window window = dialog.getWindow();
            lp.copyFrom(window.getAttributes());
            //This makes the dialog take up the full width
            //lp.width = WindowManager.LayoutParams.MATCH_PARENT;
//            lp.width = (int) (dialog.getContext().getResources().getDisplayMetrics().widthPixels * 0.83);
            //  lp.height = (int) (dialog.getContext().getResources().getDisplayMetrics().heightPixels * 0.55);
            window.setAttributes(lp);
        }
    }
    //    {STOP SHOW DIALOG STYLE}
}
