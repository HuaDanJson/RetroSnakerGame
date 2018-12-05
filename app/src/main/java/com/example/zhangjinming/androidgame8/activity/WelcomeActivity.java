package com.example.zhangjinming.androidgame8.activity;

import android.os.Bundle;

import com.example.zhangjinming.androidgame8.R;
import com.example.zhangjinming.androidgame8.base.BaseActivity;
import com.example.zhangjinming.androidgame8.bean.DBScoreBean;
import com.example.zhangjinming.androidgame8.utils.DBScoreBeanDaoUtils;

import java.util.List;

public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        doInUI(new Runnable() {
            @Override
            public void run() {
                //创建用户 放到本地数据库
                List<DBScoreBean> dbScoreBeanList = DBScoreBeanDaoUtils.getInstance().queryAllData();
                if (dbScoreBeanList == null || dbScoreBeanList.isEmpty()) {
                    DBScoreBean dbScoreBean = new DBScoreBean();
                    dbScoreBean.setUserId(1);
                    dbScoreBean.setScore(0);
                    dbScoreBean.setType(0);
                    DBScoreBeanDaoUtils.getInstance().insertOneData(dbScoreBean);
                }
                // 允许用户使用应用
                toActivity(MainActivity.class);
                WelcomeActivity.this.finish();
            }
        }, 1500);
    }
}
