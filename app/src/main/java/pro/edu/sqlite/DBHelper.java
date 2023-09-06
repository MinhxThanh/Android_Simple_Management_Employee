package pro.edu.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "Demo5";
    public static final int DB_VERSION = 2;
    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String slQuery = "CREATE TABLE employee (" +
                "id TEXT PRIMARY KEY," +
                "name TEXT NOT NULL," +
                "salary real NOT NULL)";
        sqLiteDatabase.execSQL(slQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String slQuery = "DROP TABLE IF EXISTS employee";
        sqLiteDatabase.execSQL(slQuery);
        this.onCreate(sqLiteDatabase);
    }
}
