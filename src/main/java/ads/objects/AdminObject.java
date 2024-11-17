package ads.objects;

import java.util.Date;
//import lombok.Data;
//
//@Data
public class AdminObject {
    private String adminId;
    private String adminName;
    private String adminAddress;
    private String adminPhoneNumber;
    private String adminEmail;
    private String adminPassword;
    private Date adminBirthday;
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getAdminAddress() {
		return adminAddress;
	}
	public void setAdminAddress(String adminAddress) {
		this.adminAddress = adminAddress;
	}
	public String getAdminPhoneNumber() {
		return adminPhoneNumber;
	}
	public void setAdminPhoneNumber(String adminPhoneNumber) {
		this.adminPhoneNumber = adminPhoneNumber;
	}
	public String getAdminEmail() {
		return adminEmail;
	}
	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	public Date getAdminBirthday() {
		return adminBirthday;
	}
	public void setAdminBirthday(Date adminBirthday) {
		this.adminBirthday = adminBirthday;
	}
    
    
}
