package com.example.zhangjinming.androidgame8.activity;

import android.os.Bundle;
import android.widget.Button;

import com.example.zhangjinming.androidgame8.R;
import com.example.zhangjinming.androidgame8.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectGameActivity extends BaseActivity {

    @BindView(R.id.btn_easy_select_game_activity) Button mSelectEasyGame;
    @BindView(R.id.btn_middle_select_game_activity) Button mSelectMiddleGame;
    @BindView(R.id.btn_hard_select_game_activity) Button mSelectHardGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_game);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_easy_select_game_activity)
    public void selectEasyGame() {
        toActivity(EasyPlayGameActivity.class);
    }

    @OnClick(R.id.btn_middle_select_game_activity)
    public void selectMiddleGame() {
        toActivity(MiddlePlayGameActivity.class);
    }

    @OnClick(R.id.btn_hard_select_game_activity)
    public void selectHardGame() {
        toActivity(HardPlayGameActivity.class);
    }
}
