package org.cocos2dx.lib;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
/* loaded from: classes.dex */
public class Cocos2dxLocalStorage {
    private static String DATABASE_NAME = "jsb.sqlite";
    private static final int DATABASE_VERSION = 1;
    private static String TABLE_NAME = "data";
    private static final String TAG = "Cocos2dxLocalStorage";
    private static SQLiteDatabase mDatabase;
    private static DBOpenHelper mDatabaseOpenHelper;

    /* loaded from: classes.dex */
    private static class DBOpenHelper extends SQLiteOpenHelper {
        DBOpenHelper(Context context) {
            super(context, Cocos2dxLocalStorage.DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 1);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + Cocos2dxLocalStorage.TABLE_NAME + "(key TEXT PRIMARY KEY,value TEXT);");
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            Log.w(Cocos2dxLocalStorage.TAG, "Upgrading database from version " + i + " to " + i2 + ", which will destroy all old data");
        }
    }

    public static void destory() {
        SQLiteDatabase sQLiteDatabase = mDatabase;
        if (sQLiteDatabase != null) {
            sQLiteDatabase.close();
        }
    }

    public static String getItem(String str) {
        String str2 = null;
        try {
            Cursor rawQuery = mDatabase.rawQuery("select value from " + TABLE_NAME + " where key=?", new String[]{str});
            while (true) {
                if (!rawQuery.moveToNext()) {
                    break;
                } else if (str2 != null) {
                    Log.e(TAG, "The key contains more than one value.");
                    break;
                } else {
                    str2 = rawQuery.getString(rawQuery.getColumnIndex("value"));
                }
            }
            rawQuery.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str2 == null ? "" : str2;
    }

    public static boolean init(String str, String str2) {
        if (Cocos2dxActivity.getContext() != null) {
            DATABASE_NAME = str;
            TABLE_NAME = str2;
            DBOpenHelper dBOpenHelper = new DBOpenHelper(Cocos2dxActivity.getContext());
            mDatabaseOpenHelper = dBOpenHelper;
            mDatabase = dBOpenHelper.getWritableDatabase();
            return true;
        }
        return false;
    }

    public static void removeItem(String str) {
        try {
            mDatabase.execSQL("delete from " + TABLE_NAME + " where key=?", new Object[]{str});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setItem(String str, String str2) {
        try {
            mDatabase.execSQL("replace into " + TABLE_NAME + "(key,value)values(?,?)", new Object[]{str, str2});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
