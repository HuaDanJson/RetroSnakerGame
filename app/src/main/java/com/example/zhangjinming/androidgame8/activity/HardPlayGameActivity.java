package com.example.zhangjinming.androidgame8.activity;

import android.os.Bundle;

import com.example.zhangjinming.androidgame8.R;
import com.example.zhangjinming.androidgame8.base.BaseActivity;
import com.example.zhangjinming.androidgame8.utils.PlayMusicManager;

public class HardPlayGameActivity extends BaseActivity {

    private HardGameView mHardGameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHardGameView = new HardGameView(this);
        setContentView(mHardGameView);
        PlayMusicManager.getInstance().playMusic(this, R.raw.background_music);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PlayMusicManager.getInstance().stopMusic();
    }
}
