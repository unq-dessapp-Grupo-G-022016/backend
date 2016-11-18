package service;

import dtos.EventDTO;

public class GeneralService {

	private EventService eventService;
	private UserService userService;
	private CategoryService categoryService;
	


	public EventService getEventService() {
		return eventService;
	}

	public void setEventService(final EventService eventService) {
		this.eventService = eventService;
	}

	public UserService getUserService() {
		return userService;
	}
	public CategoryService getCategoryService(){
		return categoryService;
	}

	public void setUserService(final UserService userService) {
		this.userService = userService;
	}
	
	public void setCategoryService(final CategoryService categoryService) {
		this.categoryService = categoryService;
	}

}
