package me.jessyan.mvparms.demo.mvp.model.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by gaohui.you on 2018/5/21 0021
 * Email:839939978@qq.com
 */
@Entity
public class Test {
    private String name;
    private int age;
    private int sex;
    @Generated(hash = 618614228)
    public Test(String name, int age, int sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }
    @Generated(hash = 372557997)
    public Test() {
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public int getSex() {
        return this.sex;
    }
    public void setSex(int sex) {
        this.sex = sex;
    }
}
