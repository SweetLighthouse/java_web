package ads.admin;

import java.sql.*;
import java.util.*;
import java.sql.Date;

import ads.objects.*;
import ads.basic.*;


public class AdminImpl extends BasicImpl implements Admin {

    public AdminImpl() {
        super("Admin");
    }
    
    @Override
    public boolean addAdmin(AdminObject item) {
        // TODO Auto-generated method stub
    	String sql = "INSERT INTO tbladmin (admin_id, admin_name, admin_address, admin_phone_number, admin_email, admin_password, admin_birthday) values (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, item.getAdminId());
			stmt.setString(2, item.getAdminName());
			stmt.setString(3, item.getAdminAddress());
			stmt.setString(4, item.getAdminPhoneNumber());
			stmt.setString(5, item.getAdminEmail());
			stmt.setString(6, item.getAdminPassword());
			stmt.setDate(7, new Date(item.getAdminBirthday().getTime()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return this.add(stmt);
    }

    @Override
    public boolean editAdmin(AdminObject item) {
        String sql = "UPDATE tbladmin SET admin_name = ?, admin_address = ?, admin_phone_number = ?, admin_email = ?, admin_password = ?, admin_birthday = ? WHERE admin_id = ?";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, item.getAdminName());
            stmt.setString(2, item.getAdminAddress());
            stmt.setString(3, item.getAdminPhoneNumber());
            stmt.setString(4, item.getAdminEmail());
            stmt.setString(5, item.getAdminPassword());
            stmt.setDate(6, new Date(item.getAdminBirthday().getTime()));
            stmt.setString(7, item.getAdminId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.add(stmt);
    }


    @Override
    public boolean delAdmin(String id) {
        String sql = "DELETE FROM tbladmin WHERE admin_id = ?";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.add(stmt);
    }


    @Override
    public ArrayList<ResultSet> getAdmins(String multiSelect) {
        if(multiSelect != null && !"".equalsIgnoreCase(multiSelect)) {
            return this.gets(multiSelect);
        }
        else {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM tbladmin ");
            sql.append("");
            sql.append("ORDER BY admin_id DESC ");
            sql.append("LIMIT 10;");
            return this.gets(sql.toString());
        }
    }

    @Override
    public ResultSet getAdmin(int id) {
        String sql = "SELECT * FROM tbladmin WHERE admin_id=?";
        return this.get(sql, id);
    }

    @Override
    public ResultSet getAdmin(String Adminname, String Adminpass) {
        String sql = "SELCT * FROM tbladmin WHERE (admin_name=?) AND (admin_pass=?)";
        return this.get(sql, Adminname, Adminpass);
    }

    @Override
    public ArrayList<ResultSet> getAdmins(AdminObject similar, int at, byte total) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM tbladmin ");
        sql.append("");
        sql.append("ORDER BY admin_id DESC ");
        sql.append("LIMIT ").append(at).append(", ").append(total).append(";");

        // dem so luong ng dung tron ghe thong
        sql.append("SELECT COUNT(admin_id) AS total FROM tbladmin");
        return this.gets(sql.toString());
    }

    public static void main(String[] args) {
        Admin u = new AdminImpl();

        // list results
        ArrayList<ResultSet> results = u.getAdmins(null, 0, (byte)15);

        // tra ket noi


        // duyet va hien thi

        
        ResultSet result = results.get(0);
        StringBuilder row = new StringBuilder();
        try {
            while(result.next()) {
                row.append("ID: " + result.getInt("admin_id") + "\t");
                row.append("ID: " + result.getString("admin_name") + "\t");
                row.append("ID: " + result.getString("admin_fullname") + "\t");
                row.append("ID: " + result.getString("admin_pass") + "\t");

                System.out.println(row);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ResultSet result2 = results.get(1);
        if(result2 != null) {
            try {
                while(result2.next()) {
                    System.out.println("Tổng số người sử dụng: " + result2.getInt("total"));
                }
                result2.close();
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        // results.forEach(result -> {
        //     StringBuilder row = new StringBuilder();
        //     try {
        //         while(result.next()) {
        //             row.append("ID: " + result.getInt("admin_id") + "\t");
        //             row.append("ID: " + result.getString("admin_name") + "\t");
        //             row.append("ID: " + result.getString("admin_fullname") + "\t");
        //             row.append("ID: " + result.getString("admin_pass") + "\t");

        //             System.out.println(row);
        //         }
                
        //     } catch (SQLException e) {
        //         e.printStackTrace();
        //     }
        // });
    }
}
