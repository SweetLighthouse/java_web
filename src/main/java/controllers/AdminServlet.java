package controllers;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;
import ads.admin.AdminImpl;
import ads.objects.AdminObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/admin")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminServlet() {
		super();
	}

	private AdminImpl adminImpl = new AdminImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String action = request.getParameter("action"); // For identifying the action
        String adminIdParam = request.getParameter("adminId"); 
        String adminNameParam = request.getParameter("adminName"); 
        String adminPassParam = request.getParameter("adminPass"); 
        String multiSelectParam = request.getParameter("multiSelect"); 
        String pageParam = request.getParameter("page"); // For pagination
        String totalParam = request.getParameter("total"); // For pagination

        
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        try {
            // Handle different actions based on the parameters
            if ("getAdmins".equals(action)) {
            	ArrayList<ResultSet> admins = adminImpl.getAdmins(multiSelectParam);
                out.print(convertResultSetToJson(admins));
            }
            else if ("getAdminById".equals(action) && adminIdParam != null) {
                ResultSet admin = adminImpl.getAdmin(Integer.parseInt(adminIdParam));
                out.print(convertResultSetToJson(admin));
            }
            else if ("getAdminByCredentials".equals(action) && adminNameParam != null && adminPassParam != null) {
                ResultSet admin = adminImpl.getAdmin(adminNameParam, adminPassParam);
                out.print(convertResultSetToJson(admin));
            }
            else if ("getAdminsPaginated".equals(action) && pageParam != null && totalParam != null) {
                int page = Integer.parseInt(pageParam);
                byte total = Byte.parseByte(totalParam);
                AdminObject similar = new AdminObject(); // You can adjust this based on requirements
                ArrayList<ResultSet> admins = adminImpl.getAdmins(similar, page, total);
                out.print(convertResultSetToJson(admins));
            } else {
                out.print("{\"message\": \"Invalid action or missing parameters\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.print("{\"message\": \"Error processing request\"}");
        }
    }
	
	// Helper method to convert ResultSet to JSON using Gson
    private String convertResultSetToJson(ArrayList<ResultSet> resultSet) {
        Gson gson = new Gson();
        ArrayList<AdminObject> adminList = new ArrayList<>();

        // Iterate through ResultSet and map to AdminObject
        for (ResultSet rs : resultSet) {
            try {
                while (rs.next()) {
                    AdminObject admin = new AdminObject();
                    admin.setAdminId(rs.getString("admin_id"));
                    admin.setAdminName(rs.getString("admin_name"));
                    admin.setAdminEmail(rs.getString("admin_email"));
                    adminList.add(admin);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return gson.toJson(adminList);
    }

    private String convertResultSetToJson(ResultSet resultSet) {
        Gson gson = new Gson();
        ArrayList<AdminObject> adminList = new ArrayList<>();

        try {
            while (resultSet.next()) {
                AdminObject admin = new AdminObject();
                admin.setAdminId(resultSet.getString("admin_id"));
                admin.setAdminName(resultSet.getString("admin_name"));
                admin.setAdminEmail(resultSet.getString("admin_email"));
                adminList.add(admin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return gson.toJson(adminList);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("application/json");
    	
    	Gson gson = new Gson();
        AdminObject admin = gson.fromJson(request.getReader(), AdminObject.class);
        boolean result = adminImpl.addAdmin(admin);
        if (result) {
            response.setStatus(HttpServletResponse.SC_CREATED); // 201 Created
            response.getWriter().write("{\"message\": \"Successfully added admin.\"}");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 500 Internal Server Error
            response.getWriter().write("{\"message\": \"Failed to add admin.\"}");
        }
    }
    
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("application/json");
    	
    	Gson gson = new Gson();
        AdminObject admin = gson.fromJson(request.getReader(), AdminObject.class);
        boolean result = adminImpl.editAdmin(admin);
        if (result) {
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("{\"message\": \"Successfully updated admin.\"}");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 500
            response.getWriter().write("{\"message\": \"Failed to update admin.\"}");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        
    	String adminId = request.getParameter("admin_id");
    	
        if (adminId == null || adminId.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400
            response.getWriter().write("{\"message\": \"Admin ID is required.\"}");
            return;
        }

        boolean result = adminImpl.delAdmin(adminId);
        if (result) {
            response.setStatus(HttpServletResponse.SC_OK); // 200 OK
            response.getWriter().write("{\"message\": \"Admin deleted successfully.\"}");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 500 Internal Server Error
            response.getWriter().write("{\"message\": \"Failed to delete admin.\"}");
        }
    }
}