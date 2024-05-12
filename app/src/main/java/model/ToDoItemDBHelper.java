package model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class ToDoItemDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME ="todo.db" ;
    private static final int DATABASE_VERSION = 1;

    public ToDoItemDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE todoItem(_id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,year INTEGER , month INTEGER," +
                "day INTEGER, urgency TEXT)");
        db.execSQL("INSERT INTO  todoItem(name,year,month,day,urgency) VALUES ('Kamal Amal', '1991','02','23','0')");
        db.execSQL("INSERT INTO  todoItem(name,year,month,day,urgency) VALUES ('Amal Lamal', '2991','11','10','2')");
        db.execSQL("INSERT INTO  todoItem(name,year,month,day,urgency) VALUES ('Bimal Sumal', '2023','06','30','1')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.e("TODOAPP", "DB upgrade");
        db.execSQL("DROP TABLE todoItem");
        onCreate(db);

    }
}
