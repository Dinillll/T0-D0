package model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DBModel implements IModel {
    private ToDoItemDBHelper helper;

    public DBModel(Context context) {
        this.helper = new ToDoItemDBHelper(context);
    }


    @Override
    public List<ToDoItem> getAllToDoItem() {
        List<ToDoItem> items = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM  todoItem", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt((int) cursor.getColumnIndex("_id"));
            String name = cursor.getString((int) cursor.getColumnIndex("name"));
            int year = cursor.getInt((int) cursor.getColumnIndex("year"));
            int month = cursor.getInt((int) cursor.getColumnIndex("month"));
            int day = cursor.getInt((int) cursor.getColumnIndex("day"));
            String urgency = cursor.getString((int) cursor.getColumnIndex("urgency"));

            ToDoItem tdi = new ToDoItem(id, name, year, month, day, urgency);
            items.add(tdi);
            cursor.moveToNext();
        }
        db.close();
        return items;
    }

    @Override
    public void removeToDoItem(ToDoItem tdi) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("DELETE FROM todoItem WHERE _id=?", new Integer []{tdi.getId()});
        db.close();
    }

    @Override
    public void removeAllToDOItem() {

    }


    @Override
    public void saveOrUdpate(ToDoItem tdi) {

        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        if (tdi.getId()==-1){
            cv.put("name",tdi.getName());
            cv.put("year",tdi.getYear());
            cv.put("month",tdi.getMonth());
            cv.put("day",tdi.getDay());
            cv.put("urgency",tdi.getUrgency());
            int id = (int) db.insert("todoItem",null,cv);
            tdi.setId(id);
        }
        else {
            db.execSQL("UPDATE todoItem SET name=?, year=?,month=?,day=?,urgency=? WHERE _id=?",new Object[]{tdi.getName(),tdi.getYear()
            ,tdi.getMonth(),tdi.getDay(),tdi.getUrgency(),tdi.getId()});

    }
        db.close();
    }
}
