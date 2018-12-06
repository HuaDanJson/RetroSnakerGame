package com.example.zhangjinming.androidgame8.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.example.zhangjinming.androidgame8.R;
import com.example.zhangjinming.androidgame8.base.BaseActivity;
import com.example.zhangjinming.androidgame8.bean.DBRankingBean;
import com.example.zhangjinming.androidgame8.bean.DBScoreBean;
import com.example.zhangjinming.androidgame8.dialog.LogoutDialog;
import com.example.zhangjinming.androidgame8.utils.DBRankingBeanDaoUtils;
import com.example.zhangjinming.androidgame8.utils.DBScoreBeanDaoUtils;
import com.example.zhangjinming.androidgame8.utils.PlayMusicManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlayGameActivity extends BaseActivity implements GameView.EasyGameViewListener, LogoutDialog.LogoutDialogListener {


    @BindView(R.id.rl_easy_game_activity)
    @Nullable GameView mGameView;
    @BindView(R.id.tv_score_easy_game_activity)
    TextView mScoreTextView;
    @BindView(R.id.tv_type_play_game_activity)
    TextView mTypeTextView;
    private DBScoreBean mDBScoreBean;
    private int mScoreCount;
    private int mType;
    private LogoutDialog mLogoutDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_play_game);
        ButterKnife.bind(this);
        initType();
        PlayMusicManager.getInstance().playMusic(this, R.raw.background_music);
    }

    public void initType() {
        mDBScoreBean = DBScoreBeanDaoUtils.getInstance().queryOneData(1);
        mType = getIntent().getIntExtra("TYPE", 0);
        mGameView.setEasyGameViewListener(this);
        mGameView.setType(mType, mDBScoreBean.getType());
        if (mType == 1) {
            mTypeTextView.setText("简单模式");
        } else if (mType == 2) {
            mTypeTextView.setText("中级模式");
        } else if (mType == 3) {
            mTypeTextView.setText("高级模式");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PlayMusicManager.getInstance().stopMusic();
    }

    @Override
    public void easyGameViewEatFood() {
        mScoreCount = mScoreCount + 1;
        mDBScoreBean.setScore(mDBScoreBean.getScore() + 1);
        DBScoreBeanDaoUtils.getInstance().updateData(mDBScoreBean);
        doInUI(new Runnable() {
            @Override
            public void run() {
                mScoreTextView.setText("得分：" + String.valueOf(mScoreCount));
            }
        });
    }

    @Override
    public void gameOver() {
        showLogoutDialog();
        if (mScoreCount > 0) {
            DBRankingBean dbRankingBean = new DBRankingBean();
            dbRankingBean.setCurrentTimeAsId(System.currentTimeMillis());
            dbRankingBean.setScore(mScoreCount);
            dbRankingBean.setType(mType);
            DBRankingBeanDaoUtils.getInstance().insertOneData(dbRankingBean);
        }
        mScoreCount = 0;
        doInUI(new Runnable() {
            @Override
            public void run() {
                mScoreTextView.setText("得分：" + String.valueOf(0));
            }
        });
    }

    @Override
    public void onLogoutClicked() {
        PlayGameActivity.this.finish();
    }

    @Override
    public void onGoOnGameClicked() {
        if (mTypeTextView == null) {return;}
        mTypeTextView.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mGameView == null) {return;}
                mGameView.reStartGame();
            }
        }, 1000);
    }

    public void showLogoutDialog() {
        if (mLogoutDialog == null) {
            mLogoutDialog = new LogoutDialog();
        }
        mLogoutDialog.setLogoutDialogListener(this);
        mLogoutDialog.tryShow(getSupportFragmentManager());
    }

    public void hideLogoutDialog() {
        if (mLogoutDialog != null) {
            mLogoutDialog.tryHide();
        }
    }
}
