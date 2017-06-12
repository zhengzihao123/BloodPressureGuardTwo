package test.jiyun.com.bloodpressureguard.sq;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;


/**
 * Created by 韩志军 on 2017/6/11.
 */

public class MyManager {

    private Context mContexta;
    private SQLite mSq;
    private SQLiteDatabase mDB;
    private final String DB_NAME="hzj.db";
    private final int DB_VALUE=1;


    public MyManager(Context mContext) {
        this.mContexta = mContext;
        mSq=new SQLite(mContexta,DB_NAME,null,DB_VALUE);
        mDB=mSq.getWritableDatabase();
        mDB=mSq.getReadableDatabase();
    }

    //封装四个方法
    //添加一个用户
    public boolean addStudent(Student student){
        boolean boo=false;
        ContentValues values = new ContentValues();
        values.put("id",student.getId());
        values.put("day",student.getDay());
        values.put("time",student.getTime());
        values.put("gaoya",student.getGaoya());
        values.put("diya",student.getDiya());
        values.put("name",student.getName());
        long insert = mDB.insert("hzj", null, values);
        if (insert>0){
            boo=true;
        }
        return boo;

    }

    //查询所有用户
    public ArrayList<Student> getAllStudent(){
        ArrayList<Student> list = new ArrayList<Student>();
        String sql = "select * from hzj";
        Cursor cursor = mDB.rawQuery(sql, null);
        //为什么不要Cursor判空？
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            int day = cursor.getInt(cursor.getColumnIndex("day"));
            int gaoya = cursor.getInt(cursor.getColumnIndex("gaoya"));
            int diya = cursor.getInt(cursor.getColumnIndex("diya"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String time = cursor.getString(cursor.getColumnIndex("time"));
            Student student = new Student(id,day,time,gaoya,diya,name);
            list.add(student);
        }
        return list;
    }

    //查询所有用户
    public ArrayList<Student> getRi(int yue,int ri){
        ArrayList<Student> list = new ArrayList<>();
        String sq="select * from hzj where id="+yue+" and day"+"="+ri;
        Cursor cursor = mDB.rawQuery(sq, null);

        while (cursor.moveToNext()) {
            int gaoya = cursor.getInt(cursor.getColumnIndex("gaoya"));
            int diya = cursor.getInt(cursor.getColumnIndex("diya"));
            String time = cursor.getString(cursor.getColumnIndex("time"));
            Student student = new Student();
            student.setGaoya(gaoya);
            student.setDiya(diya);
            student.setTime(time);
            list.add(student);
        }
        return list;
    }

    //查询所有用户
    public ArrayList<Student> getYue(int yue){
        ArrayList<Student> list = new ArrayList<>();
        String sq="select * from hzj where id="+yue;
        Cursor cursor = mDB.rawQuery(sq, null);
        while (cursor.moveToNext()) {
            int gaoya = cursor.getInt(cursor.getColumnIndex("gaoya"));
            int diya = cursor.getInt(cursor.getColumnIndex("diya"));
            int time = cursor.getInt(cursor.getColumnIndex("day"));
            Student student = new Student();
            student.setGaoya(gaoya);
            student.setDiya(diya);
            student.setDay(time);
            list.add(student);
        }
        return list;
    }

    //查询所有用户
    public ArrayList<Student> getRiA(int ri){
        ArrayList<Student> list = new ArrayList<>();
        String sq="select * from hzj where day"+"="+ri;
        Cursor cursor = mDB.rawQuery(sq, null);

        while (cursor.moveToNext()) {
            int gaoya = cursor.getInt(cursor.getColumnIndex("gaoya"));
            int diya = cursor.getInt(cursor.getColumnIndex("diya"));
            int time = cursor.getInt(cursor.getColumnIndex("day"));
            Student student = new Student();
            student.setGaoya(gaoya);
            student.setDiya(diya);
            student.setDay(time);
            list.add(student);
        }
        return list;
    }

    //查询所有用户
    public ArrayList<Student> getYueA(int yue){
        ArrayList<Student> list = new ArrayList<>();
        String sq="select * from hzj where id"+"="+yue;
        Cursor cursor = mDB.rawQuery(sq, null);

        while (cursor.moveToNext()) {
            int gaoya = cursor.getInt(cursor.getColumnIndex("gaoya"));
            int diya = cursor.getInt(cursor.getColumnIndex("diya"));
            int time = cursor.getInt(cursor.getColumnIndex("id"));
            Student student = new Student();
            student.setGaoya(gaoya);
            student.setDiya(diya);
            student.setId(time);
            list.add(student);
        }
        return list;
    }

}
