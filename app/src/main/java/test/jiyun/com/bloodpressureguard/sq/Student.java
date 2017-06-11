package test.jiyun.com.bloodpressureguard.sq;

/**
 * Created by 韩志军 on 2017/6/11.
 */

public class Student {
    private int id;
    private int day;
    private int time;
    private int gaoya;
    private int diya;

    public Student(int id, int day, int time, int gaoya, int diya) {
        this.id = id;
        this.day = day;
        this.time = time;
        this.gaoya = gaoya;
        this.diya = diya;
    }

    public Student() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getGaoya() {
        return gaoya;
    }

    public void setGaoya(int gaoya) {
        this.gaoya = gaoya;
    }

    public int getDiya() {
        return diya;
    }

    public void setDiya(int diya) {
        this.diya = diya;
    }
}
