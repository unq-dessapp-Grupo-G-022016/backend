package webService;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang.StringUtils;
import org.apache.cxf.rs.security.cors.CorsHeaderConstants;
import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;
import org.apache.cxf.rs.security.cors.LocalPreflight;

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
    		userService.delete(u);
    	}
        return Response.ok().build();
    }
    
    /////////////////////////
    
    @GET
    @Path("/addFriend/{userName}/{friendName}")
    @Produces("application/json")
    public String AddFriend(@PathParam ("userName") String userName,@PathParam ("friendName") String friendName){
    	List<User> users = userService.retriveAll();
    	User u = null;
    	User f = null;
    	for(User each : users){
    		if (each.getUserName().equals(userName)){
    			u = each;
    		}
    	}
    	for(User each : users){
    		if (each.getUserName().equals(friendName)){
    			f = each;
    		}
    	}
    	u.addFriend(f);
    	userService.update(u);
    	return u.getUserName()+f.getUserName();
    } 
    
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
 // This method will do a preflight check itself
    /*
    @OPTIONS
    @Path("/")
    @LocalPreflight
    public Response options() {
        //String origin = headers.getRequestHeader("Origin").get(0);
        //if ("http://localhost:8080".equals(origin)) {
            return Response.ok()
                           .header(CorsHeaderConstants.HEADER_AC_ALLOW_METHODS, "DELETE PUT")
                           .header(CorsHeaderConstants.HEADER_AC_ALLOW_CREDENTIALS, "false")
                           .header(CorsHeaderConstants.HEADER_AC_ALLOW_ORIGIN, "http://frulanga")
                           .build();
        //} else {
        //    return Response.ok().build();
        //}
    }*/
 /*
    @GET
    @CrossOriginResourceSharing(
         //allowOrigins = { "http://localhost:8080" },
    	allowAllOrigins = true,
         allowCredentials = true, 
         exposeHeaders = { "X-custom-3", "X-custom-4" }
    )
    @Produces("text/plain")
    @Path("/annotatedGet/{echo}")
    public String annotatedGet(@PathParam("echo") String echo) {
        return echo;
    }
 */
}
