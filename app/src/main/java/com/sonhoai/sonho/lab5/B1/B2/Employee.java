package com.sonhoai.sonho.lab5.B1.B2;

/**
 * Created by sonho on 3/13/2018.
 */

public class Employee {
    private int ID;
    private String fistName;
    private String lastName;
    private String gender;
    private String hireDate;
    private String dept;

    public Employee(int ID, String fistName, String lastName, String gender, String hireDate, String dept) {
        this.ID = ID;
        this.fistName = fistName;
        this.lastName = lastName;
        this.gender = gender;
        this.hireDate = hireDate;
        this.dept = dept;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFistName() {
        return fistName;
    }

    public void setFistName(String fistName) {
        this.fistName = fistName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }
}
