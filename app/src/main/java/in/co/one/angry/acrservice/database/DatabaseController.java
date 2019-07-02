package in.co.one.angry.acrservice.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import in.co.one.angry.acrservice.model.sPojoDataBase;
import in.co.one.angry.acrservice.sBase;

public class DatabaseController {
    //private Context context;
    //private DatabaseController databaseController;
    private DataBaseClass dataBaseClass;

    public DatabaseController(Context context){
        //this.context=context;
        this.dataBaseClass=new DataBaseClass(context, sBase.DB_NAME,null,sBase.VERSION);
    }
//    public DatabaseController getDataBase(Context context){
//        if(databaseController==null){
//            databaseController=new DatabaseController(context);
//        }
//        return databaseController;
//    }
    public boolean insert_file(sPojoDataBase sPojoDataBase){
        ContentValues contentValues=new ContentValues();
        contentValues.put(sBase.FILE_NAME,sPojoDataBase.getFileName());
        contentValues.put(sBase.FILE_NUMBER,sPojoDataBase.getNumber());
        //contentValues.put(sBase.IS_SYNC,sPojoDataBase.isIs_sync());
        SQLiteDatabase sqLiteDatabase=dataBaseClass.getWritableDatabase();
        long i=sqLiteDatabase.insert(sBase.TABLE_ACR,null,contentValues);
        sqLiteDatabase.close();
        return i > 0;
    }
    public Cursor read_file(){
        SQLiteDatabase sqLiteDatabase=dataBaseClass.getReadableDatabase();
        return sqLiteDatabase.rawQuery(sBase.TABLE_READ_DATA_QUERY,null);
    }
}
