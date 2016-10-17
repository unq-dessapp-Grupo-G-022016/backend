package webService;

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

import model.Event;
import model.User;
import model.creation.UserBuilder;
import service.EventService;
import service.UserService;

/**
 * 
 * 
 * @author cristian
 */

@Path("/user")
public class UserRest {

    //public static final int NUMBER_OF_POST = 10;

    /**
     * IMPORTANTE!! - AcÃ¡ deben inyectar el servicio de la aplicaciÃ³n y este
     * servicio REST solo debe hacer un delegate simple.
     */
    //private PostRepository postRepository;
    
    private UserService userService;
    
    
    @GET
    @Path("/users")
    @Produces("application/json")
    public List<User> getAllUsers() {
        List<User> users = userService.retriveAll();
        return users;
    }
    
    /*
    @GET
    @Path("/events")
    @Produces("application/json")
    public List<Event> getAllPlayers() {
        List<Event> events = EventService.retriveAll();
        return event;
    }
     */
    
    
    @GET
    @Path("/addUser")
    @Produces("application/json")
    public String addPlayer(){
    	UserBuilder ub = new UserBuilder();
    	User user = ub.anyUser().build();
    	userService.save(user);
    	return "OK";
    }
    
    /*
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
    public void setUserService(final UserService userDAO) {
        userService = userDAO;
    }

}
