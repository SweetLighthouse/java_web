package ads;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class asdf {
	public void main(String[] args) {
		ConnectionPool cp = ConnectionPoolImpl.getInstance();

		Connection conn = null;
		try {
			conn = cp.getConnection("idk man");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String sql = "update tblStudent set fullNName=? where id=?";

		try {
			PreparedStatement preStmt = conn.prepareStatement(sql);
			preStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("Hello world!");
	}
}