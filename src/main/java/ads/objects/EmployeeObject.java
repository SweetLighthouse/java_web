package ads.objects;

import java.util.Date;
//import lombok.Data;
//
//@Data
public class EmployeeObject {
	private String employeeId;
    private String employeeName;
    private String employeeAddress;
    private String employeePhoneNumber;
    private String employeeEmail;
    private Date employeeBirthday;
    private String employeeCccd;
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeAddress() {
		return employeeAddress;
	}
	public void setEmployeeAddress(String employeeAddress) {
		this.employeeAddress = employeeAddress;
	}
	public String getEmployeePhoneNumber() {
		return employeePhoneNumber;
	}
	public void setEmployeePhoneNumber(String employeePhoneNumber) {
		this.employeePhoneNumber = employeePhoneNumber;
	}
	public String getEmployeeEmail() {
		return employeeEmail;
	}
	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}
	public Date getEmployeeBirthday() {
		return employeeBirthday;
	}
	public void setEmployeeBirthday(Date employeeBirthday) {
		this.employeeBirthday = employeeBirthday;
	}
	public String getEmployeeCccd() {
		return employeeCccd;
	}
	public void setEmployeeCccd(String employeeCccd) {
		this.employeeCccd = employeeCccd;
	}
    
    
}
