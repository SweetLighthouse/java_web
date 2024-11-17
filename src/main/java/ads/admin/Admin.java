package ads.admin;

import java.sql.ResultSet;
import java.util.ArrayList;

import ads.objects.*;

public interface Admin {
    boolean addAdmin(AdminObject item);
    boolean editAdmin(AdminObject item);
    boolean delAdmin(String id);

    ArrayList<ResultSet> getAdmins(String multiSelect);
    ArrayList<ResultSet> getAdmins(AdminObject similar, int at, byte total);
    ResultSet getAdmin(int id);
    ResultSet getAdmin(String Adminname, String Adminpass);
}