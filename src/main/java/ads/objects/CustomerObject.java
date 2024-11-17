package ads.objects;

import java.util.Date;

//import lombok.Data;
//
//@Data
public class CustomerObject {
	private String customerId;
	private String customerName;
	private String customerAddress;
	private String customerPhoneNumber;
	private String customerEmail;
	private String customerPassword;
	private Date customerBirthday;
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public String getCustomerPhoneNumber() {
		return customerPhoneNumber;
	}
	public void setCustomerPhoneNumber(String customerPhoneNumber) {
		this.customerPhoneNumber = customerPhoneNumber;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getCustomerPassword() {
		return customerPassword;
	}
	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}
	public Date getCustomerBirthday() {
		return customerBirthday;
	}
	public void setCustomerBirthday(Date customerBirthday) {
		this.customerBirthday = customerBirthday;
	}
	
	
}
