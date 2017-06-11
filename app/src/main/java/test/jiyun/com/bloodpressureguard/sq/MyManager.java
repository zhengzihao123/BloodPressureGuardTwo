package test.jiyun.com.bloodpressureguard.sq;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import static android.R.attr.name;

/**
 * Created by 韩志军 on 2017/6/11.
 */

public class MyManager {

    private Context mContext;
    private SQLite mSq;
    private SQLiteDatabase mDB;
    private final String DB_NAME="hzj.db";
    private final int DB_VALUE=1;


    public MyManager(Context mContext) {
        super();
        this.mContext = mContext;
        mSq=new SQLite(mContext,DB_NAME,null,DB_VALUE);
        mDB=mSq.getWritableDatabase();
    }

    //封装四个方法
    //添加一个用户
    private void addStudent(Student student){
        ContentValues values = new ContentValues();
        values.put("id",student.getId());
        values.put("day",student.getDay());
        values.put("time",student.getTime());
        values.put("gaoya",student.getGaoya());
        values.put("diya",student.getDiya());
        mDB.insert("animal",null,values);
    }

    //返回boolean判断添加数据
    public Boolean addStudent(String name,int password){
        boolean boo = false;
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("password",password);
        long shu = mDB.insert("animal",null,values);
        if(shu>0){
            boo = true;
        }else {
            boo = false;
        }
        return boo;
    }
    //查询所有用户
    private ArrayList<Student> getAllStudent(){
        ArrayList<Student> list = new ArrayList<Student>();
        return list;
    }
}
