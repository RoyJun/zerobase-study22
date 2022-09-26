package publicwifi;

import java.sql.*;

public class Main {
    private static Connection conn = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;

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

    public static void main(String[] args) {


        String URL = "jdbc:mysql://localhost/publicwifiservice";
        String id = "root";
        String pass = "0000";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, id, pass);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        String sql = "select count(*) hi from wifi";

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println(rs.getString("hi"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
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

    }


}
