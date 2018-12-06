package com.example.zhangjinming.androidgame8.base;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.example.zhangjinming.androidgame8.R;
import com.example.zhangjinming.androidgame8.utils.DensityUtil;
import com.example.zhangjinming.androidgame8.utils.WindowUtil;

import butterknife.ButterKnife;

public abstract class BaseFragmentDialog extends DialogFragment {

    protected boolean mDisableBackPress;
    private boolean mCancelOnTouchOutside;
    private boolean mIsShowing;
    private boolean mIsAdded;
    protected Handler mHandler;
    private OnDismissListener mDismissListener;
    private Dialog mDialog;
    private Window mWindow;

    public void setOnDismissListener(OnDismissListener listener) {
        mDismissListener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResId(), container, false);
        ButterKnife.bind(this, view);
        mIsAdded = true;
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (view != null) {
            view.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (mCancelOnTouchOutside) {
                        dismiss();
                        return true;
                    } else {
                        return false;
                    }
                }
            });
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        statsPause();
    }

    @Override
    public void onPause() {
        super.onPause();
        statsResume();
    }

    protected void statsPause() {
        Context context = getContext();
    }

    protected void statsResume() {
        Context context = getContext();
    }

    @Override
    public void onDestroyView() {
        mIsAdded = false;
        super.onDestroyView();
    }

    protected abstract int getLayoutResId();

    public void disableBackPress(boolean disableBackPress) {
        this.mDisableBackPress = disableBackPress;
    }

    public void setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
        this.mCancelOnTouchOutside = canceledOnTouchOutside;
    }

    public void setDialogFullScreen(boolean fullScreen) {
        if (fullScreen && mDialog != null) {
            mDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            fullScreenStatusBar();
        } else if (mDialog != null) {
            mDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
            notFullScreenStatusBar();
        }
    }

    public void fullScreenStatusBar() {
        if (mWindow == null) {
            return;
        }
        mWindow.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    public void notFullScreenStatusBar() {
        if (mWindow == null) {
            return;
        }
        int screenHeight = WindowUtil.getScreenHeight();
        int statusBarHeight = DensityUtil.getStatusBarHeight(getContext());
        int dialogHeight = screenHeight - statusBarHeight;
        mWindow.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, dialogHeight == 0 ? ViewGroup.LayoutParams.MATCH_PARENT : dialogHeight);
        WindowManager.LayoutParams layoutParams = mWindow.getAttributes();
        layoutParams.dimAmount = 0.0f;
        mWindow.setAttributes(layoutParams);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // the content
        final RelativeLayout root = new RelativeLayout(getActivity());
        root.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        // creating the fullscreen dialog
        mDialog = new Dialog(getActivity());
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(root);
        mWindow = mDialog.getWindow();
        if (mWindow != null) {
            mWindow.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            mWindow.setBackgroundDrawableResource(android.R.color.transparent);
            int screenHeight = WindowUtil.getScreenHeight();
            int statusBarHeight = DensityUtil.getStatusBarHeight(getContext());
            int dialogHeight = screenHeight - statusBarHeight;
            mWindow.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, dialogHeight == 0 ? ViewGroup.LayoutParams.MATCH_PARENT : dialogHeight);
            WindowManager.LayoutParams layoutParams = mWindow.getAttributes();
            layoutParams.dimAmount = 0.0f;
            layoutParams.windowAnimations = R.style.DialogScaleAnimation;
            mWindow.setAttributes(layoutParams);
        }
        // listener for back press
        mDialog.setOnKeyListener(new Dialog.OnKeyListener() {

            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (mDisableBackPress && keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
                    // disable back presse
                    onBackPressed();
                    return true;
                } else {
                    return false;
                }
            }
        });
        return mDialog;
    }

    protected void onBackPressed() {
    }

    public void tryRecover(FragmentManager fm) {
        if (mIsShowing) {
            tryShow(fm);
        } else {
            tryHide();
        }
    }

    public void tryShow(FragmentManager fm) {
        if (!hasSavedInstanceState() && !mIsAdded && (!isAdded())) {
            try {
                show(fm, getPrivateTag());
                mIsAdded = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        mIsShowing = true;
    }

    public void tryHide() {
        if (!hasSavedInstanceState() && mIsAdded) {
            try {
                mIsAdded = false;
                super.dismiss();
            } catch (Exception e) {

            }
        }
        mIsShowing = false;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        mIsAdded = false;
        mIsShowing = false;
        super.onDismiss(dialog);
        if (mDismissListener != null) {
            mDismissListener.onDismiss(this);
        }
    }

    @Override
    public void dismiss() {
        tryHide();
    }

    protected boolean hasSavedInstanceState() {
        Context context = getContext();
        return false;
    }

    private String getPrivateTag() {
        return getClass().toString();
    }


    public Handler getHandler() {
        if (mHandler == null) {
            synchronized (this) {
                if (mHandler == null) {
                    mHandler = new Handler(Looper.getMainLooper());
                }
            }
        }
        return mHandler;
    }

    public void doInUI(Runnable runnable) {
        doInUI(runnable, 0);
    }

    public void doInUI(Runnable runnable, long delayMillis) {
        getHandler().postDelayed(runnable, delayMillis);
    }

    public interface OnDismissListener {
        void onDismiss(BaseFragmentDialog dialog);
    }

//    //获取屏幕高度
//    private static int getScreenHeight(Activity activity) {
//        DisplayMetrics displaymetrics = new DisplayMetrics();
//        activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
//        return displaymetrics.heightPixels;
//    }
//
//    //获取状态栏高度
//    private static int getStatusBarHeight(Context context) {
//        int statusBarHeight = 0;
//        Resources res = context.getResources();
//        int resourceId = res.getIdentifier("status_bar_height", "dimen", "android");
//        if (resourceId > 0) {
//            statusBarHeight = res.getDimensionPixelSize(resourceId);
//        }
//        return statusBarHeight;
//    }


}
