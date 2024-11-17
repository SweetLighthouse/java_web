package ads;

import java.sql.*;
import java.util.Stack;

public class ConnectionPoolImpl implements ConnectionPool {
	private String driver; // trình điều khiển làm việc
	private String username; // tài khoản kết nối
	private String password;
	private String url; // đường dẫn thực thi

	// đối tượng lưu trữ kết nối
	private Stack<Connection> pool;

	// singleton design pattern
	private static ConnectionPool cp;

	private ConnectionPoolImpl() {
		// xác định trình điều khiển
		this.driver = "com.mysql.cj.jdbc.Driver";

		// xác định đường dẫn thực thi
		this.url = "jdbc://mysql://localhost:3311/khachsan_database?allowMultiQueries=true";

		// xác định tài khoản làm việc
		this.username = "khachsan_dangbh";
		this.password = "@123$%65";

		// nạp trình điều khiển
		try {
			Class.forName(this.driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// khởi tạo đối tượng lưu trữ
		this.pool = new Stack<>();
	}

	@Override
	public Connection getConnection(String objectName) throws SQLException {
		if (this.pool.isEmpty()) {
			// khởi tạo kết nối mới
			System.out.println(objectName + " have created a new Connection.");
			return DriverManager.getConnection(this.url, this.username, this.password);
		} else {
			// lấy kết nối đã dc lưu trữ
			System.out.println(objectName + " have popped the Connection.");
			return this.pool.pop();
		}
	}

	@Override
	public void releaseConnection(Connection connection, String objectName) throws SQLException {
		System.out.println(objectName + "Đã trả về 1 kết nối.");
		this.pool.push(connection);
	}

	public static ConnectionPool getInstance() {
		if (cp == null) {
			synchronized (ConnectionPoolImpl.class) {
				if(cp == null) {
					cp = new ConnectionPoolImpl();
				}
			}
		}
		return cp;
	}

	protected void finalize() throws Throwable {
		this.pool.clear();
		this.pool = null;
		System.out.println("CPool is closed!");
	}
}