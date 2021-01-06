package com.example.myapplication;

import ohos.data.orm.OrmObject;
import ohos.data.orm.annotation.Entity;
import ohos.data.orm.annotation.Index;
import ohos.data.orm.annotation.PrimaryKey;


@Entity(tableName = "user", ignoredColumns = {"ignoreColumn1", "ignoreColumn2"},
        indices = {@Index(value = {"firstName", "lastName"}, name = "name_index", unique = true)})
public class User extends OrmObject {
    // 此处将userId设为了自增的主键。注意只有在数据类型为包装类型时，自增主键才能生效。
    @PrimaryKey(autoGenerate = true)
    private Integer userId;
    private String firstName;
    private String lastName;
    private int age;
    private double balance;
    private int ignoreColumn1;
    private int ignoreColumn2;

    // 开发者自行添加字段的getter和setter 方法。

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getIgnoreColumn1() {
        return ignoreColumn1;
    }

    public void setIgnoreColumn1(int ignoreColumn1) {
        this.ignoreColumn1 = ignoreColumn1;
    }

    public int getIgnoreColumn2() {
        return ignoreColumn2;
    }

    public void setIgnoreColumn2(int ignoreColumn2) {
        this.ignoreColumn2 = ignoreColumn2;
    }
}