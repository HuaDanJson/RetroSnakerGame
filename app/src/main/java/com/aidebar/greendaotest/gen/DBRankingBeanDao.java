package com.aidebar.greendaotest.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.zhangjinming.androidgame8.bean.DBRankingBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "DBRANKING_BEAN".
*/
public class DBRankingBeanDao extends AbstractDao<DBRankingBean, Long> {

    public static final String TABLENAME = "DBRANKING_BEAN";

    /**
     * Properties of entity DBRankingBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property CurrentTimeAsId = new Property(0, long.class, "currentTimeAsId", true, "_id");
        public final static Property Score = new Property(1, int.class, "score", false, "DBRankingBean");
        public final static Property Type = new Property(2, int.class, "type", false, "TYPE");
    }


    public DBRankingBeanDao(DaoConfig config) {
        super(config);
    }
    
    public DBRankingBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"DBRANKING_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY NOT NULL ," + // 0: currentTimeAsId
                "\"DBRankingBean\" INTEGER NOT NULL ," + // 1: score
                "\"TYPE\" INTEGER NOT NULL );"); // 2: type
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"DBRANKING_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, DBRankingBean entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getCurrentTimeAsId());
        stmt.bindLong(2, entity.getScore());
        stmt.bindLong(3, entity.getType());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, DBRankingBean entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getCurrentTimeAsId());
        stmt.bindLong(2, entity.getScore());
        stmt.bindLong(3, entity.getType());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }    

    @Override
    public DBRankingBean readEntity(Cursor cursor, int offset) {
        DBRankingBean entity = new DBRankingBean( //
            cursor.getLong(offset + 0), // currentTimeAsId
            cursor.getInt(offset + 1), // score
            cursor.getInt(offset + 2) // type
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, DBRankingBean entity, int offset) {
        entity.setCurrentTimeAsId(cursor.getLong(offset + 0));
        entity.setScore(cursor.getInt(offset + 1));
        entity.setType(cursor.getInt(offset + 2));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(DBRankingBean entity, long rowId) {
        entity.setCurrentTimeAsId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(DBRankingBean entity) {
        if(entity != null) {
            return entity.getCurrentTimeAsId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(DBRankingBean entity) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
