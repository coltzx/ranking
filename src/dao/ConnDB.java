package dao;

import java.sql.*;

public class ConnDB {
    public static Connection openConn(){
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql:///ranking";
            String user = "root";
            String password = "wang2001";
            conn = DriverManager.getConnection(url,user,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void closeDB(ResultSet rs, PreparedStatement ps,Connection conn){
        try{
            if(rs!=null){
                rs.close();
            }
            if(ps!=null){
                ps.close();
            }
            if(conn!=null){
                conn.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

