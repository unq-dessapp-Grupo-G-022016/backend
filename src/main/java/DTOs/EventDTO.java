package DTOs;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import model.Event;

public class EventDTO {
	
	String address;
	Set<String> attenders;
	String details;
	int startTimeDate;
	int startTimeHour;
	int endTimeDate;
	int entTimeHour;
	String name;
	int price;
	Set<String> categories;
	boolean strictSchedule;
	
	public EventDTO(){
		
	}
	public EventDTO(Event event){
		this.address = event.getAddress();
		setAttenders(event);
		this.details = event.getDetails();
		setTime(event);
		this.name = event.getName();
		this.price = event.getPrice().getAmmount();
		setCategories(event);
		this.strictSchedule = event.isStrictSchedule();
	}

	private void setTime(Event event) {
		// TODO Auto-generated method stub
		this.startTimeDate = dateTimeToDateInt(event.getStartTime());
		this.endTimeDate = dateTimeToDateInt(event.getEndTime());
		this.startTimeHour = dateTimeToHourInt(event.getStartTime());
		this.endTimeDate = dateTimeToHourInt(event.getEndTime());				
	}
	
	private int dateTimeToHourInt(LocalDateTime dateTime) {
		// TODO Auto-generated method stub
		int hour = dateTime.getHour() * 100 + dateTime.getMinute();
		return hour;
	}
	private int dateTimeToDateInt(LocalDateTime dateTime){
		int day = dateTime.getYear() * 10000
        		+dateTime.getMonthValue() * 100
        		+dateTime.getDayOfMonth();
		return day;
	}
	
	private void setAttenders(Event event) {
		// TODO Auto-generated method stub
		Set<String> setOfString = new HashSet<String>();
		event.getAttenders().getUsers().forEach(user-> setOfString.add(user.userName));
		this.attenders = setOfString;
	}
	private void setCategories(Event event) {
		// TODO Auto-generated method stub
		Set<String> setOfString = new HashSet<String>();
		event.getProfile().allCategories().forEach(category-> setOfString.add(category.getName()));
		this.categories = setOfString;
	}
	

}
