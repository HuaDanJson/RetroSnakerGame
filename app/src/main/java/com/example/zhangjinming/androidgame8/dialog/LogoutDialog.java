package com.example.zhangjinming.androidgame8.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zhangjinming.androidgame8.R;
import com.example.zhangjinming.androidgame8.base.BaseFragmentDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class LogoutDialog extends BaseFragmentDialog {

    @BindView(R.id.tv_yes_logout_dialog) TextView mGoOn;
    @BindView(R.id.tv_cancel_logout_dialog) TextView mLogout;
    @BindView(R.id.rl_log_out_dialog) RelativeLayout mLogoutDialog;
    Unbinder unbinder;

    private boolean mHasSavedInstanceState;

    private LogoutDialogListener mLogoutDialogListener;

    public void setLogoutDialogListener(LogoutDialogListener listener) {
        this.mLogoutDialogListener = listener;
    }

    public interface LogoutDialogListener {

        void onLogoutClicked();

        void onGoOnGameClicked();
    }


    @Override
    protected boolean hasSavedInstanceState() {
        return mHasSavedInstanceState;
    }

    public void setSavedInstanceState(boolean hasSavedInstanceState) {
        this.mHasSavedInstanceState = hasSavedInstanceState;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.dialog_log_out;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        disableBackPress(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.tv_yes_logout_dialog)
    public void onGoClicked(View view) {
        if (mLogoutDialogListener != null) {
            mLogoutDialogListener.onGoOnGameClicked();
        }
        tryHide();
    }

    @OnClick(R.id.tv_cancel_logout_dialog)
    public void onLogoutClicked(View view) {
        if (mLogoutDialogListener != null) {
            mLogoutDialogListener.onLogoutClicked();
        }
        tryHide();
    }

}
