package com.skillnext1;

import javax.persistence.*;

@Entity
@Table(name = "Student")   // EXACT table name
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "sem")
    private int sem;

    @Column(name = "dept")
    private String dept;

    public Student() {}

    public Student(String name, int sem, String dept) {
        this.name = name;
        this.sem = sem;
        this.dept = dept;
    }

    // getters & setters
    public int getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getSem() { return sem; }
    public void setSem(int sem) { this.sem = sem; }

    public String getDept() { return dept; }
    public void setDept(String dept) { this.dept = dept; }
}