package webService;

import java.util.*;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;

import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;

import dtos.UserDTO;
import model.persistents.Category;
import model.persistents.Event;
import model.persistents.Friends;
import model.persistents.Price;
import model.persistents.Profile;
import model.persistents.User;
import service.CategoryService;
import service.EventService;
import service.UserService;

/**
 * 
 * 
 * @author cristian
 */
@CrossOriginResourceSharing(
        allowAllOrigins = true, 
        allowCredentials = true, 
        maxAge = 1, 
        allowHeaders = {
           "X-custom-1", "X-custom-2"
        }, 
        exposeHeaders = {
           "X-custom-3", "X-custom-4"
        }
)
@Path("/user")
public class UserRest {

    //public static final int NUMBER_OF_POST = 10;

    /**
     * IMPORTANTE!! - AcÃ¡ deben inyectar el servicio de la aplicaciÃ³n y este
     * servicio REST solo debe hacer un delegate simple.
     */
    //private PostRepository postRepository;
	@Context
    private HttpHeaders headers;
    private UserService userService;
    private EventService eventService;
    private CategoryService categoryService;
    /*
     *  C.R.U.D.
     */
    @POST
    @Path("/create")
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public String Create(User newUser){
    	userService.save(newUser);
    	return "OK";
    }
    @Transactional
    @POST
    @Path("/createdto/{userName}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response Createdto(@PathParam ("userName") String userName){
    	User u = new User();
		u.setUserName(userName);
		u.setAttendedEvents(new HashSet<Event>());
    	u.setFriends(new Friends());
    	u.setLowCostTrip(new Price(0));
    	u.setPersonalEvent(new HashSet<Event>());
    	u.setProfile(new Profile());
    	userService.save(u);
    	return Response.ok().build();
    }

    @Transactional
    @PUT
    @Path("/updatepricedto/{userName}/{price}")
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response Createdto(@PathParam ("userName") String userName,@PathParam ("price") int price){
        User u = userService.findById(userName);
        u.getLowCostTrip().setAmmount(price);
        // todo : try/catch
        userService.save(u);
        return Response.ok(u).build();
    }
    
    @GET
    @Path("/read/{userName}")
    @Produces("application/json")
    public Response Read(@PathParam ("userName") String userName){
    	
    	User u = userService.findById(userName);
    	if (u == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(u).build();
    }
    @GET
    @Path("/readdto/{userName}")
    @Produces("application/json")
    public Response Readdto(@PathParam ("userName") String userName){
    	
    	User u = userService.findById(userName);
    	if (u == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(new UserDTO(u)).build();
    }
    
    @PUT
    @Path("/update")
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response Update(User updatedUser){
    	User u = userService.findById(updatedUser.userName);
    	if (u == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    	else{
    		userService.update(updatedUser);
    	}
        return Response.ok().build();

    }

    @Transactional()
    @DELETE
    @Path("/delete/{userName}")
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response Delete(@PathParam ("userName") String userName){
    	User u = userService.findById(userName);
    	if (u == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    	else{
    		//u.removeAllFriend();
    		//userService.find("delete from Friends where friendName='"+u.getUserName()+"'");
    		//u.removemeFromAllFriendsAndRemoveThemFromMe();
    		u.getFriends().getFriends().forEach(f -> {
    			User databaseF = userService.findById(f.userName);
    			databaseF.removeFriend(u);
    			userService.update(databaseF);
    			u.removeFriend(databaseF);
    			userService.update(u);
    		});
    		userService.delete(u);
    	}
        return Response.ok().build();
    }
    
    /////////////////////////
    @Transactional
    @PUT
    @Path("/addFriend/{userName}/{friendName}")
    @Produces("application/json")
    public Response AddFriend(@PathParam ("userName") String userName,@PathParam ("friendName") String friendName){
    	User u = userService.findById(userName);
    	User f = userService.findById(friendName);
    	if (u == null || f == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    	u.addFriend(f);
    	f.addFriend(u);
    	userService.update(u);
    	return Response.ok().build();
    } 
    
    /*
    @GET
    @Path("/attend/{userName}")
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response Attend(@RequestBody Event event, @PathParam ("userName") String userName){
    	User u = userService.findById(userName);
    	if (u == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    	//u.attend(event);
    	//userService.update(u);
    	return Response.ok(event).build();
    } 
    */
    
    @GET
    @Path("/users")
    @Produces("application/json")
    public Response getAllUsers() {
        List<User> users = userService.retriveAll();
        if (users.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(users).build();
    }
    
    @GET
    @Path("/usersdto")
    @Produces("application/json")
    public Response alleventsdto() {
        List<User> events = userService.retriveAll();
        List<UserDTO> eventsDto = new ArrayList<UserDTO>();
        events.forEach(event -> eventsDto.add(new UserDTO(event)));
        return Response.ok(eventsDto).build();
    }
    
    @Transactional
    @GET
    @Path("/addUsers")
    @Produces("application/json")
    public Response addUser(){
    	this.categoryService.save(new Category("Food"));
    	this.categoryService.save(new Category("Atomico"));
    	this.categoryService.save(new Category("Termonuclear"));
    	this.categoryService.save(new Category("Fantabulosico"));
    	this.categoryService.save(new Category("Tranca"));
    	this.categoryService.save(new Category("Triplex"));
    	
    	Category at = this.categoryService.findById("Atomico");
    	Category te = this.categoryService.findById("Termonuclear");
    	Category fa = this.categoryService.findById("Fantabulosico");
    	Category tr = this.categoryService.findById("Tranca");
    	Category to =this.categoryService.findById("Tornado");
    	
    	for(int i=1;i<20;i++){
    		User u = new User();
    		String newname = "momo"+i;
    		u.setUserName(newname);
    		u.setAttendedEvents(new HashSet<Event>());
        	u.setFriends(new Friends());
        	u.setLowCostTrip(new Price(100));
        	u.setPersonalEvent(new HashSet<Event>());
        	u.setProfile(new Profile());
        	userService.save(u);
    	}
        {
            User u = new User();
            String newname = "leog91@gmail.com";
            u.setUserName(newname);
            u.setAttendedEvents(new HashSet<Event>());
            u.setFriends(new Friends());
            u.setLowCostTrip(new Price(30));
            u.setPersonalEvent(new HashSet<Event>());
            u.setProfile(new Profile());
            u.getProfile().addCategory(at);
            u.getProfile().addCategory(te);
            userService.save(u);

        }

        {
            User u = new User();
            String newname = "k11alejandro@gmail.com";
            u.setUserName(newname);
            u.setAttendedEvents(new HashSet<Event>());
            u.setFriends(new Friends());
            u.setLowCostTrip(new Price(100));
            u.setPersonalEvent(new HashSet<Event>());
            u.setProfile(new Profile());
            u.getProfile().addCategory(at);
            u.getProfile().addCategory(fa);
            userService.save(u);
        }


    	return Response.ok().build();
    }
    
    public void setUserService(final UserService userDAO) {
        userService = userDAO;
    }
    public void setCategoryService(final CategoryService userDAO) {
        categoryService = userDAO;
    }
    public void setEventService(final EventService userDAO) {
        eventService = userDAO;
    }
    

    /*
     *  the dark side
     */
  

    @GET
    @Path("/addCategory/{userName}/{c}")
    @Produces("application/json")
    public Response getbyq(@PathParam ("userName") String userName,@PathParam ("c") String c){
        Category cat = null;
    	User u = userService.findById(userName);
    	cat = categoryService.findById(c);
    	if (cat == null){
            categoryService.create(c);
            cat = categoryService.findById(c);
        }
        //if user has category error

        return userService.addCategory(u,cat);

    }



}
