package com.example.zhangjinming.androidgame8.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class DBScoreBean {

    @Id(autoincrement = false)
    public long userId;

    @Property(nameInDb = "DBScoreBean")

    private int score;//自己分数

    private int type;//自己的等级

    @Generated(hash = 112911899)
    public DBScoreBean(long userId, int score, int type) {
        this.userId = userId;
        this.score = score;
        this.type = type;
    }

    @Generated(hash = 1323068593)
    public DBScoreBean() {
    }

    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "DBScoreBean{" +
                "userId=" + userId +
                ", score=" + score +
                ", type=" + type +
                '}';
    }
}
