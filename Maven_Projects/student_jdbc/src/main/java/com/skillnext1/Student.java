package com.skillnext1;

public class Student {
    public int id;
    public String name;
    public int sem;
    public String dept;
    public Student() {}
    public Student(int id, String name, int sem, String dept) {
        this.id = id;
        this.name = name;
        this.sem = sem;
        this.dept = dept;
    }
    //getters and setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getSem() {
        return sem;
    }
    public void setSem(int sem) {
        this.sem = sem;
    }
    public String getDept() {
        return dept;
    }
    public void setDept(String dept) {
        this.dept = dept;
    }
    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", sem=" + sem + ", dept=" + dept + "]";
    }   
}