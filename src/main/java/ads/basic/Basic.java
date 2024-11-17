package ads.basic;

import java.sql.*;
import java.util.*;

public interface Basic {
    boolean add(PreparedStatement preStmt);
    boolean edit(PreparedStatement preStmt);
    boolean del(PreparedStatement preStmt);

    ArrayList<ResultSet> gets(String multiSelect);
    ResultSet get(String sql, int value);
    ResultSet get(String sql, String name, String pass);

    void releaseConnection();
}
