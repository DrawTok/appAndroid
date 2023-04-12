package com.drawtok.handinguiland;

import java.io.Serializable;

public class Student implements Serializable {
    private String idStudent;
    private String name;
    private int birthYear;
    private String address;

    public Student(String idStudent, String name, int birthYear, String address) {
        this.idStudent = idStudent;
        this.name = name;
        this.birthYear = birthYear;
        this.address = address;
    }

    public String getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(String idStudent) {
        this.idStudent = idStudent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
