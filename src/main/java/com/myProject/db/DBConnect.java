package com.myProject.db;

import java.sql.*;

public class DBConnect {
    private final String url = "jdbc:mariadb://localhost:33062/pWifi_db";
    private final String dbUserId = "pWifi_db";
    private final String dbUserPassword = "s5554549";
    public Connection getConnection() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            return DriverManager.getConnection(url, dbUserId, dbUserPassword);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("DB연결 실패");
            throw new RuntimeException(e);
        }
    }
    public void close(Connection conn, ResultSet rs, PreparedStatement pstmt) {
        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if (pstmt != null && !pstmt.isClosed()) {
                pstmt.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
