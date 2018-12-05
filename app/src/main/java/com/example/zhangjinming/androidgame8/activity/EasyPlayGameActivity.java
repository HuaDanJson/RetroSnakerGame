package com.example.zhangjinming.androidgame8.activity;

import android.os.Bundle;

import com.example.zhangjinming.androidgame8.R;
import com.example.zhangjinming.androidgame8.base.BaseActivity;
import com.example.zhangjinming.androidgame8.bean.DBScoreBean;
import com.example.zhangjinming.androidgame8.utils.PlayMusicManager;

import butterknife.ButterKnife;

public class EasyPlayGameActivity extends BaseActivity {

    private DBScoreBean mDBScoreBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setContentView(R.layout.activity_easy_play_game);
        PlayMusicManager.getInstance().playMusic(this, R.raw.background_music);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PlayMusicManager.getInstance().stopMusic();
    }
}
