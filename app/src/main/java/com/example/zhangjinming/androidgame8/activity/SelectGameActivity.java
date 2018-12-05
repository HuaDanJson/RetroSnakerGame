package com.example.zhangjinming.androidgame8.activity;

import android.content.Intent;
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
        toPlayGameActivity(1);
    }

    @OnClick(R.id.btn_middle_select_game_activity)
    public void selectMiddleGame() {
        toPlayGameActivity(2);
    }

    @OnClick(R.id.btn_hard_select_game_activity)
    public void selectHardGame() {
        toPlayGameActivity(3);
    }

    public void toPlayGameActivity(int type) {
        Intent intent = new Intent(SelectGameActivity.this, PlayGameActivity.class);
        intent.putExtra("TYPE", type);
        startActivity(intent);
    }
}
