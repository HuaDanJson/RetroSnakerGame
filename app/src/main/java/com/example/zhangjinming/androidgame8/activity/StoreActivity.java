package com.example.zhangjinming.androidgame8.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.zhangjinming.androidgame8.R;
import com.example.zhangjinming.androidgame8.base.BaseActivity;
import com.example.zhangjinming.androidgame8.bean.DBScoreBean;
import com.example.zhangjinming.androidgame8.utils.DBScoreBeanDaoUtils;
import com.example.zhangjinming.androidgame8.utils.ToastHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StoreActivity extends BaseActivity {

    @BindView(R.id.tv_score_store_activity) TextView mScoreStore;
    @BindView(R.id.tv_store1_store_activity) TextView mSelect1;
    @BindView(R.id.tv_store2_store_activity) TextView mSelect2;
    @BindView(R.id.tv_store3_store_activity) TextView mSelect3;
    @BindView(R.id.tv_store4_store_activity) TextView mSelect4;
    @BindView(R.id.tv_store5_store_activity) TextView mSelect5;
    @BindView(R.id.tv_buy_store_store_activity) TextView mBuyStore;
    private DBScoreBean mDBScoreBean;
    private int mAllStoreCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        ButterKnife.bind(this);
        mDBScoreBean = DBScoreBeanDaoUtils.getInstance().queryOneData(1);
        mAllStoreCount = mDBScoreBean.getScore();
        mScoreStore.setText("总积分：" + String.valueOf(mAllStoreCount));
    }

    @OnClick(R.id.tv_store1_store_activity)
    public void select01() {
        if (mAllStoreCount >= 2) {
            mDBScoreBean.setScore(mDBScoreBean.getScore() - 2);
            mAllStoreCount = mDBScoreBean.getScore();
            mScoreStore.setText("总积分：" + String.valueOf(mAllStoreCount));
            mDBScoreBean.setType(1);
            DBScoreBeanDaoUtils.getInstance().updateData(mDBScoreBean);
            mBuyStore.setText("您购买了：1号商品");
        } else {
            ToastHelper.showShortMessage("余额不足");
        }
    }

    @OnClick(R.id.tv_store2_store_activity)
    public void select02() {
        if (mAllStoreCount >= 4) {
            mDBScoreBean.setScore(mDBScoreBean.getScore() - 4);
            mAllStoreCount = mDBScoreBean.getScore();
            mScoreStore.setText("总积分：" + String.valueOf(mAllStoreCount));
            mDBScoreBean.setType(2);
            DBScoreBeanDaoUtils.getInstance().updateData(mDBScoreBean);
            mBuyStore.setText("您购买了：2号商品");
        } else {
            ToastHelper.showShortMessage("余额不足");
        }
    }

    @OnClick(R.id.tv_store3_store_activity)
    public void select03() {
        if (mAllStoreCount >= 6) {
            mDBScoreBean.setScore(mDBScoreBean.getScore() - 6);
            mAllStoreCount = mDBScoreBean.getScore();
            mScoreStore.setText("总积分：" + String.valueOf(mAllStoreCount));
            mDBScoreBean.setType(3);
            DBScoreBeanDaoUtils.getInstance().updateData(mDBScoreBean);
            mBuyStore.setText("您购买了：3号商品");
        } else {
            ToastHelper.showShortMessage("余额不足");
        }
    }

    @OnClick(R.id.tv_store4_store_activity)
    public void select04() {
        if (mAllStoreCount >= 8) {
            mDBScoreBean.setScore(mDBScoreBean.getScore() - 8);
            mAllStoreCount = mDBScoreBean.getScore();
            mScoreStore.setText("总积分：" + String.valueOf(mAllStoreCount));
            mDBScoreBean.setType(4);
            DBScoreBeanDaoUtils.getInstance().updateData(mDBScoreBean);
            mBuyStore.setText("您购买了：4号商品");
        } else {
            ToastHelper.showShortMessage("余额不足");
        }
    }

    @OnClick(R.id.tv_store5_store_activity)
    public void select05() {
        if (mAllStoreCount >= 10) {
            mDBScoreBean.setScore(mDBScoreBean.getScore() - 10);
            mAllStoreCount = mDBScoreBean.getScore();
            mScoreStore.setText("总积分：" + String.valueOf(mAllStoreCount));
            mDBScoreBean.setType(5);
            DBScoreBeanDaoUtils.getInstance().updateData(mDBScoreBean);
            mBuyStore.setText("您购买了：5号商品");
        } else {
            ToastHelper.showShortMessage("余额不足");
        }
    }
}
