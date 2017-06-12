package test.jiyun.com.bloodpressureguard.sq;

/**
 * Created by 韩志军 on 2017/6/11.
 */

public class Student {
    private int id;
    private int day;
    private String time;
    private int gaoya;
    private int diya;
    private String name;


    public Student(int id, int day, String time, int gaoya, int diya, String name) {
        this.id = id;
        this.day = day;
        this.time = time;
        this.gaoya = gaoya;
        this.diya = diya;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
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
