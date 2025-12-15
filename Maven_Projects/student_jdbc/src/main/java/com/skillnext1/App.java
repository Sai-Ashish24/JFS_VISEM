package com.skillnext1;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentDAO sd = new StudentDAO();

        while (true) {
            System.out.println("\nWelcome to Student Management System");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Delete Student");
            System.out.println("4. Update Student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine().trim()); 
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1: {
                    System.out.print("Enter name: ");
                    String name = sc.nextLine().trim();
                    System.out.print("Enter semester: ");
                    int sem = Integer.parseInt(sc.nextLine().trim());
                    System.out.print("Enter dept: ");
                    String dept = sc.nextLine().trim();

                    Student stu = new Student(0, name, sem, dept);
                    try {
                        sd.insert(stu);
                        System.out.println("Student added successfully");
                    } catch (Exception e) {
                        System.out.println("Error adding student: " + e.getMessage());
                        e.printStackTrace();
                    }
                    break;
                }

                case 2: {
                    try {
                        List<Student> all = sd.selectAll(); 
                        if (all.isEmpty()) {
                            System.out.println("No students found.");
                        } else {
                            for (Student s : all) {
                                System.out.println(s.getId() + "  " + s.getName() + "  " + s.getSem() + "  " + s.getDept());
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Error fetching students: " + e.getMessage());
                        e.printStackTrace();
                    }
                    break;
                }

                case 3: {
                    System.out.print("Enter student id to delete: ");
                    int id;
                    try {
                        id = Integer.parseInt(sc.nextLine().trim());
                    } catch (NumberFormatException nfe) {
                        System.out.println("Invalid id.");
                        break;
                    }

                    try {
                        if (!sd.exists(id)) {               
                            System.out.println("Student with id " + id + " does not exist");
                            break;
                        }
                        sd.delete(id);
                        System.out.println("Student deleted successfully");
                    } catch (Exception e) {
                        System.out.println("Error deleting student: " + e.getMessage());
                        e.printStackTrace();
                    }
                    break;
                }

                case 4: {
                    System.out.print("Enter id to update: ");
                    int id;
                    try {
                        id = Integer.parseInt(sc.nextLine().trim());
                    } catch (NumberFormatException nfe) {
                        System.out.println("Invalid id.");
                        break;
                    }

                    try {
                        if (!sd.exists(id)) {
                            System.out.println("Student with id " + id + " does not exist");
                            break;
                        }

                        System.out.print("Enter new name: ");
                        String name = sc.nextLine().trim();
                        System.out.print("Enter new semester: ");
                        int sem = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("Enter new dept: ");
                        String dept = sc.nextLine().trim();

                        Student stu = new Student(0, name, sem, dept); 
                        sd.update(stu); 
                        System.out.println("Student updated successfully");
                    } catch (Exception e) {
                        System.out.println("Error updating student: " + e.getMessage());
                        e.printStackTrace();
                    }
                    break;
                }

                case 5: {
                    System.out.println("Exiting. Bye!");
                    sc.close();
                    return;
                }

                default: {
                    System.out.println("Invalid choice, please try again.");
                }
            } 
        } 
    }
}