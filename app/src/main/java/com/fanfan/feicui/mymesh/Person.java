package com.fanfan.feicui.mymesh;

/**
 * Created by Administrator on 2016/5/25.
 */
public class Person {
    String name;
    int age;
    String rank;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge(int age) {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRank(String rank) {
        return this.rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "姓名："+name+"年龄"+age+"班级"+rank;
    }

}
