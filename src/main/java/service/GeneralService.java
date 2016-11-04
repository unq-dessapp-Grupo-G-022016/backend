package service;

public class GeneralService {

	private EventService eventService;
	private FriendsService friendsService;
	private UserService userService;
	private AttendersService attendersService;
	


	public EventService getEventService() {
		return eventService;
	}

	public void setEventService(final EventService eventService) {
		this.eventService = eventService;
	}
	
	public FriendsService getFriendsService() {
		return friendsService;
	}

	public void setFriendsService(final FriendsService friendsService) {
		this.friendsService = friendsService;
	}
	public AttendersService getAttendersService() {
		return attendersService;
	}

	public void setAttendersService(final AttendersService attendersService) {
		this.attendersService = attendersService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(final UserService userService) {
		this.userService = userService;
	}

}
