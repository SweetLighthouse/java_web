package ads.basic;

import java.sql.*;
import java.util.*;
import ads.*;

public class BasicImpl implements Basic {

    // object to work with Basic
    private String objectName;

    // bộ quản lý kết nối dc chia sẻ
    private ConnectionPool cp = ConnectionPoolImpl.getInstance();

    // kết nối của riêng Basic sử dụng
    protected Connection con;

    public BasicImpl(String objectName) {
        // xác định đối tượng làm việc
        this.objectName = objectName;

        // xin kết nối
        try {
            this.con = this.cp.getConnection(this.objectName);
            
            // kiểm tra chế độ thực thi của kết nối
            if(this.con.getAutoCommit()) {
                // huỷ chế độ 
                this.con.setAutoCommit(false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean execute(PreparedStatement preStmt) {
        if(preStmt != null) {
            try {
                int recordAffectedCount = preStmt.executeUpdate();
                if (recordAffectedCount == 0) {
                    this.con.rollback();
                    return false;
                }

                // xác định thực thi sau cùng
                this.con.commit();
                return true;
            }
            catch(SQLException e) {
                e.printStackTrace();
                
                // trở lại trạng thái an toàn của kết nối
                try {
                    this.con.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public boolean add(PreparedStatement preStmt) {
        return this.execute(preStmt);
    }

    @Override
    public boolean edit(PreparedStatement preStmt) {
        return this.execute(preStmt);
    }

    @Override
    public boolean del(PreparedStatement preStmt) {
        return this.execute(preStmt);
    }

    @Override
    public ResultSet get(String sql, int value) {
        try (PreparedStatement preStmt = this.con.prepareStatement(sql)) {
            if(value > 0) preStmt.setInt(1, value);
            return preStmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResultSet get(String sql, String name, String pass) {
        try (PreparedStatement preStmt = this.con.prepareStatement(sql)) {
            preStmt.setString(1, name);
            preStmt.setString(2, pass);
            return preStmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<ResultSet> gets(String multiSelect) {
        ArrayList<ResultSet> result = new ArrayList<>();
        try {
            PreparedStatement stmt = this.con.prepareStatement(multiSelect);
            boolean results = stmt.execute();
            do {
                if(results) {
                    result.add(stmt.getResultSet());
                }
                results = stmt.getMoreResults(Statement.KEEP_CURRENT_RESULT);
            } while(results);
            // TODO results return?
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public void releaseConnection() {
        try {
            this.cp.releaseConnection(this.con, this.objectName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void finalize() throws Throwable {
        // this.releaseConnection();
    }
    
}
