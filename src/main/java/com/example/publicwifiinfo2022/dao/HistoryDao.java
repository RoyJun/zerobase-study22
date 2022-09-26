package com.example.publicwifiinfo2022.dao;

import com.example.publicwifiinfo2022.vo.HistoryVo;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoryDao {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    void getConnection() {
        String URL = "jdbc:mysql://localhost/wifidb";
        String id = "wifiuser";
        String pass = "1234";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, id, pass);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void DbClose() {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public int insertHistory(Double lat, Double lnt) throws SQLException {
        String sql = "insert into history(lat, lnt, date) values(?, ?, now())";

        getConnection();

        try {
            ps = conn.prepareStatement(sql);

            conn.setAutoCommit(false);

            ps.setDouble(1, lat);
            ps.setDouble(2, lnt);

            int count = ps.executeUpdate();

            conn.commit();

            return count;
        } catch (Exception e) {
            conn.rollback();
            conn.setAutoCommit(true);
            throw new RuntimeException(e);
        } finally {
            DbClose();
        }
    }

    public int deleteHistory(int historyId) throws SQLException {
        String sql = "delete from history where id = ?";

        getConnection();
        try {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);

            ps.setInt(1, historyId);

            int count = ps.executeUpdate();

            conn.commit();

            return count;
        } catch (Exception e) {
            conn.rollback();
            conn.setAutoCommit(true);
            throw new RuntimeException(e);
        } finally {
            DbClose();
        }
    }

    public List<HistoryVo> selectHistoryList() {
        String sql = "select * from history order by id desc";

        List<HistoryVo> list = new ArrayList<>();

        getConnection();

        try{
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new HistoryVo(
                        rs.getString("id"),
                        rs.getDouble("lat"),
                        rs.getDouble("lnt"),
                        rs.getString("date")
                ));
            }

            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DbClose();
        }
    }
}
