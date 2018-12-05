package com.example.zhangjinming.androidgame8.activity;

import android.os.Bundle;
import android.widget.Button;

import com.example.zhangjinming.androidgame8.R;
import com.example.zhangjinming.androidgame8.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.btn_start_game_main_activity) Button mStartGame;
    @BindView(R.id.btn_ranking_main_activity) Button mRanking;
    @BindView(R.id.btn_store_main_activity) Button mStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_start_game_main_activity)
    public void startGame() {
        toActivity(SelectGameActivity.class);
    }

    @OnClick(R.id.btn_ranking_main_activity)
    public void toRankingActivity() {
        toActivity(RankingActivity.class);
    }

    @OnClick(R.id.btn_store_main_activity)
    public void toStoreGame() {
        toActivity(StoreActivity.class);
    }
}
