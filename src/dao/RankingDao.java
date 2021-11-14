package dao;

import pojo.Model;

import java.awt.print.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RankingDao {

    Connection conn = null;
    PreparedStatement ps = null;

    ResultSet rs = null;

    public int addRank(Model model) {
        int num = 0;
        conn = ConnDB.openConn();

        try {
            String sql = "insert into main(id,name,a,b,g,h,c,d) values(?,?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, model.getId());
            ps.setString(2, model.getName());
            ps.setDouble(3, model.getA());
            ps.setDouble(4, model.getB());
            ps.setDouble(5, model.getG());
            ps.setDouble(6, model.getH());
            ps.setInt(7, model.getC());
            ps.setInt(8, model.getD());

            num = ps.executeUpdate();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            ConnDB.closeDB(rs, ps, conn);
        }
        return num;
    }

    public int updateRank(Model model) {
        int num = 0;
        conn = ConnDB.openConn();
        try {
            String sql = "update main set c=?,d=? where id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, model.getC());
            ps.setInt(2, model.getD());
            ps.setInt(3, model.getId());
            num = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnDB.closeDB(rs, ps, conn);
        }
        return num;
    }

    public List<Model> doRank() {

        List<Model> list = new ArrayList<Model>();

        conn = ConnDB.openConn();
        try {

            String sql = "select * from main";

            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("id");
                String name = rs.getString("name");
                double a = rs.getDouble("a");
                double b = rs.getDouble("b");
                double g = rs.getDouble("g");
                double h = rs.getDouble("h");
                double i = rs.getDouble("i");
                int c = rs.getInt("c");
                int d = rs.getInt("d");

                Model model = new Model();
                model.setId(id);
                model.setName(name);
                model.setA(a);
                model.setB(b);
                model.setG(g);
                model.setH(h);
                model.setC(c);
                model.setD(d);
                model.setI(i);

                list.add(model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            ConnDB.closeDB(rs, ps, conn);
        }
        return list;
    }

    public int addI(Model model) {
        int num = 0;
        conn = ConnDB.openConn();
        try {
            String sql = "update main set i=? where id=?";
            ps = conn.prepareStatement(sql);
            ps.setDouble(1, model.getI());
            ps.setInt(2, model.getId());
            num = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnDB.closeDB(rs, ps, conn);
        }
        return num;
    }

    public int delRank(int id){
        int num = 0;

        conn = ConnDB.openConn();
        try {

            String sql = "delete from main where id=?";

            ps = conn.prepareStatement(sql);

            ps.setInt(1,id);

            num = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnDB.closeDB(rs,ps,conn);
        }
        return num;
    }
}