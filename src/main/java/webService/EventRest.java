package webService;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang.StringUtils;
import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;

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
    
      
    @GET
    @Path("/read/{idE}")
    @Produces("application/json")
    public Response Read(@PathParam ("idE") int idE){
    	
    	Event e = eventService.findById(idE);
    	if (e == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(e).build();
    }
    @PUT
    @Path("/update")
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response Update(Event updatedEvent){
    	Event e = eventService.findById(updatedEvent.getId());
    	if (e == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    	else{
    		eventService.update(updatedEvent);
    	}
        return Response.ok().build();

    }
    
    @DELETE
    @Path("/delete/{idE}")
    @Produces("application/json")
    public Response Delete(@PathParam ("idE") int idE){
    	Event e = eventService.findById(idE);
    	if (e == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    	eventService.delete(e);
    	return Response.ok().build();
    }
    
    /////////////////////////*/
    @GET
    @Path("/events")
    @Produces("application/json")
    public List<Event> getAllPlayers() {
        List<Event> events = eventService.retriveAll();
        return events;
    }
    
    /*
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
     */
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
    
    public void setEventService(final EventService eventDAO) {
        eventService = eventDAO;
    }

}
