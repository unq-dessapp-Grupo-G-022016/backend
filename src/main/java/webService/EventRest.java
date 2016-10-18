package webService;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.StringUtils;

import model.Category;
import model.Event;
import model.Price;
import model.User;
import service.EventService;

/**
 * 
 * 
 * @author cristian
 */

@Path("/event")
public class EventRest {

    public static final int NUMBER_OF_POST = 10;

    /**
     * IMPORTANTE!! - AcÃ¡ deben inyectar el servicio de la aplicaciÃ³n y este
     * servicio REST solo debe hacer un delegate simple.
     */
    //private PostRepository postRepository;
    private EventService eventService;
    
    /*
     *  C.R.U.D.
     */
    @POST
    @Path("/create")
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public String Create(Event newUser){
    	eventService.save(newUser);
    	return "OK";
    }
    /*
    @GET
    @Path("/read/{userName}")
    @Produces("application/json")
    public User Read(@PathParam ("userName") String userName){
    	List<User> users = userService.retriveAll();
    	User u = null;
    	for(User each : users){
    		if (each.getUserName().equals(userName)){
    			u = each;
    		}
    	}
    	return u;
    }  
    
    @POST
    @Path("/update")
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public String Update(User updatedUser){
    	String retValue = "NotFound";
    	List<User> users = userService.retriveAll();
    	for(User each : users){
    		if (each.getUserName().equals(updatedUser.getUserName())){
    			userService.update(updatedUser);
    			retValue = "OK";
    		}
    	}
    	return retValue;
    }
    
    @GET
    @Path("/delete/{userName}")
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public String Delete(@PathParam ("userName") String userName){
    	List<User> users = userService.retriveAll();
    	users.forEach(each->{
    		if(userName.equals(each.getUserName())){
    			userService.delete(each);
    		}
    	});
    	return "OK";
    }
    
    /////////////////////////*/
    @GET
    @Path("/events")
    @Produces("application/json")
    public List<Event> getAllPlayers() {
        List<Event> events = eventService.retriveAll();
        return events;
    }
    
 	@GET
     @Path("/addEvent")
     @Produces("application/json")
     public String addEvent(){
    	Event e = new Event();
    	e.setName("goingToHell");
    	e.setAddress("666");
    	e.setDetails("Devils house");
    	e.setPrice(new Price(20));
    	e.setStartTime(LocalDateTime.now());
    	e.setEndTime(LocalDateTime.now());
    	Set<User> uset = new HashSet<User>();
    	e.setAttenders(uset);
    	e.setCategory(new Category("warm places"));
    	eventService.save(e);
     	return "OK";
     }
    /*
    @GET
    @Path("/event")
    @Produces("application/json")
    public List<Event> getAllPlayers() {
        List<Event> events = EventService.retriveAll();
        return events;
    }
    */
    /*
    @GET
    @Path("/events")
    @Produces("application/json")
    public List<Event> getAllPlayers() {
        List<Event> events = EventService.retriveAll();
        return event;
    }
     */
    
    /*
    @GET
    @Path("/addPlayerDummy")
    @Produces("application/json")
    public String addPlayer(){
    	Player newPlayer = new Player("looser");
    	newPlayer.setScore(9);
    	playerService.save(newPlayer);
    	return "OK";
    }
    
    @POST
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addPlayer/")
    public String addPlayer(Player newPlayer){
    	playerService.save(newPlayer);
    	return "OK";
    }
    */
    
    /*

    @GET
    @Path("/{from}")
    @Produces("application/json")
    public List<Post> findPostsPublishedByBlogId(@PathParam("from") final Integer from) {
        List<Post> posts = postRepository.getPosts(from, NUMBER_OF_POST, "");
        return posts;
    }

    @GET
    @Path("/byAuthor/{id}")
    @Produces("application/json")
    public List<Post> findPostsPublishedByAuthorId(@PathParam("id") final String id) {
        List<Post> posts = postRepository.getPosts(id);
        return posts;
    }

    @GET
    @Path("/count")
    @Produces("application/json")
    public Integer countPostsPublishedByBlogId(@DefaultValue(StringUtils.EMPTY) @QueryParam("tag") final String tag) {
        return postRepository.getcount(tag);
    }

    @GET
    @Path("/tags")
    @Produces("application/json")
    public Set<String> getTagsByBlogId() {
        return postRepository.getTags();
    }
*/
    public void setEventService(final EventService eventDAO) {
        eventService = eventDAO;
    }

}
