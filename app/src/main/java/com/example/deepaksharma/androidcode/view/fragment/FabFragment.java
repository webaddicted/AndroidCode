package com.example.deepaksharma.androidcode.view.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.Window;
import android.view.animation.OvershootInterpolator;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.databinding.ViewDataBinding;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.DialogCustomBinding;
import com.example.deepaksharma.androidcode.databinding.FragmentFabBinding;
import com.example.deepaksharma.androidcode.global.DialogUtil;
import com.example.deepaksharma.androidcode.utils.GlobalUtilities;
import com.example.deepaksharma.androidcode.view.base.BaseFragment;
import com.example.deepaksharma.androidcode.view.dialog.LoginDialog;
import com.example.deepaksharma.androidcode.view.home.HomeActivity;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FabFragment extends BaseFragment {
    public static final String TAG = FabFragment.class.getSimpleName();
    private FragmentFabBinding mBinding;
    private List<FloatingActionMenu> menus = new ArrayList<>();
    private Handler mUiHandler = new Handler();
    private int mMaxProgress = 100;
    private LinkedList<ProgressType> mProgressTypes;

    public static FabFragment getInstance(Bundle bundle) {
        FabFragment fragment = new FabFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_fab;
    }

    @Override
    protected void onViewsInitialized(ViewDataBinding binding, View view) {
        mBinding = (FragmentFabBinding) binding;
        mProgressTypes = new LinkedList<>();
        mBinding.fabProgress.setMax(mMaxProgress);
        for (ProgressType type : ProgressType.values()) {
            mProgressTypes.offer(type);
        }
//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                if (Math.abs(dy) > 4) {
//                    if (dy > 0) {
//                        fab_Progress.hide(true);
//                    } else {
//                        fab_Progress.show(true);
//                    }
//                }
//            }
//        });
        mBinding.fabProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressType type = mProgressTypes.poll();
                Log.d(TAG, "onClick: "+type);
                switch (type) {
                    case INDETERMINATE:
                        mBinding.fabProgress.setShowProgressBackground(true);
                        mBinding.fabProgress.setIndeterminate(true);
                        mProgressTypes.offer(ProgressType.INDETERMINATE);
                        break;
                    case PROGRESS_POSITIVE:
                        mBinding.fabProgress.setIndeterminate(false);
                        mBinding.fabProgress.setProgress(70, true);
                        mProgressTypes.offer(ProgressType.PROGRESS_POSITIVE);
                        break;
                    case PROGRESS_NEGATIVE:
                        mBinding.fabProgress.setProgress(30, true);
                        mProgressTypes.offer(ProgressType.PROGRESS_NEGATIVE);
                        break;
                    case HIDDEN:
                        mBinding.fabProgress.hideProgress();
                        mProgressTypes.offer(ProgressType.HIDDEN);
                        break;
                    case PROGRESS_NO_ANIMATION:
                        increaseProgress(mBinding.fabProgress, 0);
                        break;
                    case PROGRESS_NO_BACKGROUND:
                        mBinding.fabProgress.setShowProgressBackground(false);
                        mBinding.fabProgress.setIndeterminate(true);
                        mProgressTypes.offer(ProgressType.PROGRESS_NO_BACKGROUND);
                        break;
                }
            }
        });


        final com.github.clans.fab.FloatingActionButton programFab1 = new com.github.clans.fab.FloatingActionButton(getActivity());
        programFab1.setButtonSize(com.github.clans.fab.FloatingActionButton.SIZE_MINI);
        programFab1.setLabelText(getString(R.string.REFERAL_TITLE));
        programFab1.setImageResource(R.drawable.ic_edit);
        mBinding.menuRed.addMenuButton(programFab1);
        programFab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                programFab1.setLabelColors(ContextCompat.getColor(getActivity(), R.color.grey),
                        ContextCompat.getColor(getActivity(), R.color.light_grey),
                        ContextCompat.getColor(getActivity(), R.color.white_transparent));
                programFab1.setLabelTextColor(ContextCompat.getColor(getActivity(), R.color.black));
            }
        });
        fffff();
        ContextThemeWrapper context = new ContextThemeWrapper(getActivity(), R.style.MenuButtonsStyle);
        com.github.clans.fab.FloatingActionButton programFab2 = new com.github.clans.fab.FloatingActionButton(context);
        programFab2.setLabelText("Programmatically added button");
        programFab2.setImageResource(R.drawable.ic_edit);
        mBinding.menuYellow.addMenuButton(programFab2);

        mBinding.fab1.setEnabled(false);
        mBinding.menuRed.setClosedOnTouchOutside(true);
        mBinding.menuBlue.setIconAnimated(false);

        mBinding.menuDown.hideMenuButton(false);
        mBinding.menuRed.hideMenuButton(false);
        mBinding.menuYellow.hideMenuButton(false);
        mBinding.menuGreen.hideMenuButton(false);
        mBinding.menuBlue.hideMenuButton(false);
        mBinding.menuLabelsRight.hideMenuButton(false);

//        fabEdit.setShowAnimation(AnimationUtils.loadAnimation( FloatingButton.this, R.anim.scale_up));
//        fabEdit.setHideAnimation(AnimationUtils.loadAnimation( FloatingButton.this, R.anim.scale_down));
    }

    //    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//
    public void fffff() {
        menus.add(mBinding.menuDown);
        menus.add(mBinding.menuRed);
        menus.add(mBinding.menuYellow);
        menus.add(mBinding.menuGreen);
        menus.add(mBinding.menuBlue);
        menus.add(mBinding.menuLabelsRight);

        mBinding.menuYellow.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener() {
            @Override
            public void onMenuToggle(boolean opened) {
                String text;
                if (opened) {
                    text = "Menu opened";
                } else {
                    text = "Menu closed";
                }
                Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
            }
        });

        mBinding.fab1.setOnClickListener(clickListener);
        mBinding.fab2.setOnClickListener(clickListener);
        mBinding.fab3.setOnClickListener(clickListener);

        int delay = 400;
        for (final FloatingActionMenu menu : menus) {
            mUiHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    menu.showMenuButton(true);
                }
            }, delay);
            delay += 150;
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mBinding.fabEdit.show(true);
            }
        }, delay + 150);

        mBinding.menuRed.setOnMenuButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBinding.menuRed.isOpened()) {
                    Toast.makeText(getActivity(), mBinding.menuRed.getMenuButtonLabelText(), Toast.LENGTH_SHORT).show();
                }

                mBinding.menuRed.toggle(true);
            }
        });

        createCustomAnimation();
    }

    private void createCustomAnimation() {
        AnimatorSet set = new AnimatorSet();

        ObjectAnimator scaleOutX = ObjectAnimator.ofFloat(mBinding.menuGreen.getMenuIconView(), "scaleX", 1.0f, 0.2f);
        ObjectAnimator scaleOutY = ObjectAnimator.ofFloat(mBinding.menuGreen.getMenuIconView(), "scaleY", 1.0f, 0.2f);

        ObjectAnimator scaleInX = ObjectAnimator.ofFloat(mBinding.menuGreen.getMenuIconView(), "scaleX", 0.2f, 1.0f);
        ObjectAnimator scaleInY = ObjectAnimator.ofFloat(mBinding.menuGreen.getMenuIconView(), "scaleY", 0.2f, 1.0f);

        scaleOutX.setDuration(50);
        scaleOutY.setDuration(50);

        scaleInX.setDuration(150);
        scaleInY.setDuration(150);

        scaleInX.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                mBinding.menuGreen.getMenuIconView().setImageResource(mBinding.menuGreen.isOpened()
                        ? R.drawable.ic_close : R.drawable.ic_star);
            }
        });

        set.play(scaleOutX).with(scaleOutY);
        set.play(scaleInX).with(scaleInY).after(scaleOutX);
        set.setInterpolator(new OvershootInterpolator(2));

        mBinding.menuGreen.setIconToggleAnimatorSet(set);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.fab1:
                    break;
                case R.id.fab2:
                    mBinding.fab2.setVisibility(View.GONE);
                    break;
                case R.id.fab3:
                    mBinding.fab2.setVisibility(View.VISIBLE);
                    break;
            }
        }
    };
    private enum ProgressType {
        INDETERMINATE, PROGRESS_POSITIVE, PROGRESS_NEGATIVE, HIDDEN, PROGRESS_NO_ANIMATION, PROGRESS_NO_BACKGROUND
    }
    private void increaseProgress(final FloatingActionButton fab, int i) {
        if (i <= mMaxProgress) {
            fab.setProgress(i, false);
            final int progress = ++i;
            mUiHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    increaseProgress(fab, progress);
                }
            }, 30);
        } else {
            mUiHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    fab.hideProgress();
                }
            }, 200);
//            mProgressTypes.offer(ProgressType.PROGRESS_NO_ANIMATION);
        }
    }


    private void clickListener() {
    }

    @Override
    public void onResume() {
        super.onResume();
        ((HomeActivity) getActivity()).showBackBtn();
        ((HomeActivity) getActivity()).setToolbarTitle(getResources().getString(R.string.dialog_title));
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_single_click:
                DialogUtil.showOkDialog(getActivity(), "Title", "msg", "ok", (dialog, which) -> {
                    GlobalUtilities.showToast("ok click" + which);
                    dialog.dismiss();
                });
                break;
            case R.id.btn_two_event_dialog:
                DialogUtil.showOkCancelDialog(getActivity(), "Title", "msg", (dialog, which) -> {
                    GlobalUtilities.showToast("ok click");
                    dialog.dismiss();
                }, (dialog, which) -> {
                    GlobalUtilities.showToast("cancel click");
                    dialog.dismiss();
                });
                break;
            case R.id.btn_custom_dialog:
                customDialog();
                break;
            case R.id.btn_dialog_fragment:
                LoginDialog loginDialog = new LoginDialog();
                loginDialog.show(getFragmentManager(), LoginDialog.TAG);
                break;
            case R.id.btn_selection_list:
                DialogUtil.getSingleChoiceDialog(getActivity(), getResources().getString(R.string.select_country), getCountryList(), (dialog, position) -> {
                    if (position > 0)
                        GlobalUtilities.showToast(getCountryList().get(position - 1).toString());
                    dialog.dismiss();
                }, (dialog, position) -> dialog.dismiss());
                break;
            case R.id.btn_list_dialog:
                DialogUtil.showListDialog(getActivity(), getResources().getString(R.string.select_country), getCountryList(), (dialog, which) -> {
                    GlobalUtilities.showToast(getCountryList().get(which).toString());
                    dialog.dismiss();
                });
                break;
        }
    }

    public List<String> getCountryList() {
        List<String> list = new ArrayList<>();
        list.add("India");
        list.add("Japan");
        list.add("Armenia");
        list.add("Australia");
        list.add("Bangladesh");
        return list;
    }

    private void customDialog() {
        Dialog dialog = new Dialog(getActivity(), R.style.AlertDialogStyle);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogSlideUpAnimation;
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        DialogCustomBinding dialogBinding = (DialogCustomBinding) GlobalUtilities.getLayoutBinding(getActivity(), R.layout.dialog_custom);
        dialog.setContentView(dialogBinding.getRoot());
        dialogBinding.txtTitle.setText("this is demo");
        dialogBinding.txtMessage.setText(getResources().getString(R.string.marquee_txt));
        dialogBinding.btnOk.setOnClickListener(v -> {
            GlobalUtilities.showToast(getResources().getString(R.string.ok));
            dialog.dismiss();
        });
        dialogBinding.txtCancel.setOnClickListener(v -> {
            GlobalUtilities.showToast(getResources().getString(R.string.cancel));
            dialog.dismiss();
        });
        dialog.show();
    }

}
