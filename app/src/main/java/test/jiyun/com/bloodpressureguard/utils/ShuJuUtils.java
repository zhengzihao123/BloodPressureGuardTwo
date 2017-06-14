package test.jiyun.com.bloodpressureguard.utils;

import java.util.ArrayList;

import test.jiyun.com.bloodpressureguard.App;
import test.jiyun.com.bloodpressureguard.sq.MyManager;
import test.jiyun.com.bloodpressureguard.sq.Student;

/**
 * Created by 韩志军 on 2017/6/12.
 */

public class ShuJuUtils {

    public static ArrayList<Student> getShuJu() {
        MyManager myManager = new MyManager(App.activity);
        ArrayList<Student> student = myManager.getAllStudent();

        for (int i = 0; i < student.size(); i++) {
            if (i < 6) {
                String time = student.get(i).getTime();
                String[] split = time.split(":");
                String substring = split[2].substring(0, 2);
                time = split[0] + ":" + split[1] + ":" + substring;
                student.get(i).setTime(time);
            }
        }

        return student;
    }

}
