package in.co.one.angry.acrservice.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import in.co.one.angry.acrservice.sBase;

public class DataBaseClass  extends SQLiteOpenHelper{
    public DataBaseClass(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sBase.TABLE_QUERY);
        Log.w(DataBaseClass.class.getSimpleName(),"Database Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+sBase.TABLE_ACR);
        onCreate(db);
        Log.w("DATABASE","Re created");
    }
}
