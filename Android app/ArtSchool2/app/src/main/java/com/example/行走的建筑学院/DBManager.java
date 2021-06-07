package com.example.行走的建筑学院;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

/**
 * 项目名称：SQLiteLearn
 * 创建人：later
 * 创建时间：2018/10/10
 * 修改备注：
 */
public class DBManager  {
    private DBHelper helper;
    private SQLiteDatabase db;    //SQLite数据库

    public DBManager(Context context){
        helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

    public void add(List<Person> persons){
        db.beginTransaction();
        try{
            for (Person p:persons){
                db.execSQL("INSERT INTO person VALUES(null,?,?,?)",
                        new Object[]{p.getName(),p.getAge(),p.getInfo()});
            }
            db.setTransactionSuccessful();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
        }
    }

    public void update(Person p){
        ContentValues cv = new ContentValues();
        cv.put("age",p.getAge());
        cv.put("info",p.getInfo());
        db.update("person",cv,"name=?",new String[]{p.getName()});
    }

    public void deleteOldPerson(Person p){
        db.delete("person","name=?",new String[]{p.getName()});

    }

    public List<Person> findAllPerson(String pp){
        ArrayList<Person> persons = new ArrayList<Person>();
        String q ="SELECT * FROM person WHERE name = "+"'"+pp+"'";
        Cursor c = db.rawQuery(q, null);
        while(c.moveToNext()){
            Person p = new Person();
            p.set_id(c.getInt(c.getColumnIndex("_id")));
            p.setName(c.getString(c.getColumnIndex("name")));
            p.setAge(c.getInt(c.getColumnIndex("age")));
            p.setInfo(c.getString(c.getColumnIndex("info")));
            persons.add(p);
        }
        c.close();
        return persons;
    }
    public List<Person> findAll(){
        ArrayList<Person> persons = new ArrayList<Person>();
        Cursor c = db.rawQuery("SELECT * FROM person", null);
        while(c.moveToNext()){
            Person p = new Person();
            p.set_id(c.getInt(c.getColumnIndex("_id")));
            p.setName(c.getString(c.getColumnIndex("name")));
            p.setAge(c.getInt(c.getColumnIndex("age")));
            p.setInfo(c.getString(c.getColumnIndex("info")));
            persons.add(p);
        }
        c.close();
        return persons;
    }
//    public void dropTable(){
//        db.execSQL("drop table person");
//    }

    public void closeDB(){
        db.close();
    }

}