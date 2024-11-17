package ads.objects;

import java.util.Date;

//import lombok.*;
//
//@Data
public class BookingObject {
	private String bookingId;
    private String customerId;
    private String roomId;
    private String employeeId;
    private String bookingState;
    private String rate;
    private Date bookingStartDay;
    private Date bookingStartTime;
    private int bookingDurationHour;
	public String getBookingId() {
		return bookingId;
	}
	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getBookingState() {
		return bookingState;
	}
	public void setBookingState(String bookingState) {
		this.bookingState = bookingState;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public Date getBookingStartDay() {
		return bookingStartDay;
	}
	public void setBookingStartDay(Date bookingStartDay) {
		this.bookingStartDay = bookingStartDay;
	}
	public Date getBookingStartTime() {
		return bookingStartTime;
	}
	public void setBookingStartTime(Date bookingStartTime) {
		this.bookingStartTime = bookingStartTime;
	}
	public int getBookingDurationHour() {
		return bookingDurationHour;
	}
	public void setBookingDurationHour(int bookingDurationHour) {
		this.bookingDurationHour = bookingDurationHour;
	}
    
    
}
