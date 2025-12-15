package com.skillnext1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/Student_DB";
    private static final String USER = "root";
    private static final String PASSWORD = "Ashish@24";

    // Optional: explicitly load driver if your environment needs it
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // ignore or log — modern JDBC typically doesn't require this
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public boolean exists(int id) throws SQLException {
        String sql = "SELECT id FROM Student WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    public void insert(Student s) throws SQLException {
        String sql = "INSERT INTO Student (name, sem, dept) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getName());
            ps.setInt(2, s.getSem());
            ps.setString(3, s.getDept());
            ps.executeUpdate();
        }
    }

    public void update(Student s) throws SQLException {
        String sql = "UPDATE Student SET name = ?, sem = ?, dept = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getName());
            ps.setInt(2, s.getSem());
            ps.setString(3, s.getDept());
            ps.setInt(4, s.getId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Student WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
    public void countStudentsBranchWise() throws SQLException {
    String sql = "SELECT dept, COUNT(*) AS total FROM Student GROUP BY dept";

    try (Connection conn = getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        while (rs.next()) {
            String dept = rs.getString("dept");
            int count = rs.getInt("total");
            System.out.println(dept + " : " + count);
        }
    }
}

    public List<Student> selectAll() throws SQLException {
        String sql = "SELECT * FROM Student";
        List<Student> list = new ArrayList<>();
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setSem(rs.getInt("sem"));
                s.setDept(rs.getString("dept"));
                list.add(s);
            }
        }
        return list;
    }
}