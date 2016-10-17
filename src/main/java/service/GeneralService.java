package service;

public class GeneralService {

	private CategoryService categoryService;
	private EventService eventService;
	private FriendsService friendsService;
	private PriceService priceService;
	private ProfileService profileService;
	private UserService userService;
	

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(final CategoryService categoryService) {
		this.categoryService = categoryService;
	}
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
	public PriceService getPriceService() {
		return priceService;
	}

	public void setPriceService(final PriceService priceService) {
		this.priceService = priceService;
	}
	public ProfileService getProfileService() {
		return profileService;
	}

	public void setProfileService(final ProfileService profileService) {
		this.profileService = profileService;
	}
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(final UserService userService) {
		this.userService = userService;
	}

}
