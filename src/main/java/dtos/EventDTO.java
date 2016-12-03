package dtos;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import model.persistents.Event;

public class EventDTO {
	
	String address;
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Set<String> getAttenders() {
		return attenders;
	}

	public void setAttenders(Set<String> attenders) {
		this.attenders = attenders;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public int getStartTimeDate() {
		return startTimeDate;
	}

	public void setStartTimeDate(int startTimeDate) {
		this.startTimeDate = startTimeDate;
	}

	public int getStartTimeHour() {
		return startTimeHour;
	}

	public void setStartTimeHour(int startTimeHour) {
		this.startTimeHour = startTimeHour;
	}

	public int getEndTimeDate() {
		return endTimeDate;
	}

	public void setEndTimeDate(int endTimeDate) {
		this.endTimeDate = endTimeDate;
	}

	public int getEntTimeHour() {
		return endTimeHour;
	}

	public void setEntTimeHour(int entTimeHour) {
		this.endTimeHour = entTimeHour;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Set<String> getCategories() {
		return categories;
	}

	public void setCategories(Set<String> categories) {
		this.categories = categories;
	}

	public int getId(){ return this.id ;}

	public boolean isStrictSchedule() {
		return strictSchedule;
	}

	public void setStrictSchedule(boolean strictSchedule) {
		this.strictSchedule = strictSchedule;
	}
	Set<String> attenders;
	String details;

	int startTimeDate;
	int startTimeHour;
	int endTimeDate;
	int endTimeHour;

	String name;
	int price;
	Set<String> categories;
	boolean strictSchedule;
	int id;
	
	/*
	public String toString(){
		Gson gson = new Gson();
		String jsonInString = gson.toJson(this);
		return jsonInString;
	}
	*/
	
	public EventDTO(){
		
	}
	
	public EventDTO(Event event){
		this.id = event.getId();
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
		this.endTimeHour = dateTimeToHourInt(event.getEndTime());				
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
