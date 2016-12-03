package dtos;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import model.persistents.Category;
import model.persistents.Event;
import model.persistents.User;

public class UserDTO {

	String userName;
	List<String> attendedEvents;
	List<String> personalEvents;
	List<String> profileCategories;
	List<String> friendsFriends;
	int lowCostTripAmount;
	
	public UserDTO(){
		
	}
	public UserDTO(User user){
		this.userName = user.userName;
		this.lowCostTripAmount = user.getLowCostTrip().getAmmount();
		this.attendedEvents = eventListToListOfStrings(user.getAttendedEvents());
		this.personalEvents = eventListToListOfStrings(user.getPersonalEvent());
		this.profileCategories = categoryListToListOfStrings(user.getProfile().allCategories());
		this.friendsFriends = userListToListOfStrings(user.getFriends().getFriends());
	}
	
	private List<String> eventListToListOfStrings(Set<Event> aSetOf){
		List<String> newList = new ArrayList<String>();
		aSetOf.forEach(elem -> newList.add(elem.getName()));
		return newList;
	}
	private List<String> userListToListOfStrings(Set<User> aSetOf){
		List<String> newList = new ArrayList<String>();
		aSetOf.forEach(elem -> newList.add(elem.getUserName()));
		return newList;
	}
	private List<String> categoryListToListOfStrings(Set<Category> aSetOf){
		List<String> newList = new ArrayList<String>();
		aSetOf.forEach(elem -> newList.add(elem.getName()));
		return newList;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List<String> getAttendedEvents() {
		return attendedEvents;
	}
	public void setAttendedEvents(List<String> attendedEvents) {
		this.attendedEvents = attendedEvents;
	}
	public List<String> getPersonalEvents() {
		return personalEvents;
	}
	public void setPersonalEvents(List<String> personalEvents) {
		this.personalEvents = personalEvents;
	}
	public List<String> getProfileCategories() {
		return profileCategories;
	}
	public void setProfileCategories(List<String> profileCategories) {
		this.profileCategories = profileCategories;
	}
	public List<String> getFriendsFriends() {
		return friendsFriends;
	}
	public void setFriendsFriends(List<String> friendsFriends) {
		this.friendsFriends = friendsFriends;
	}
	public int getLowCostTripAmount() {
		return lowCostTripAmount;
	}
	public void setLowCostTripAmount(int lowCostTripAmount) {
		this.lowCostTripAmount = lowCostTripAmount;
	}
	
}
