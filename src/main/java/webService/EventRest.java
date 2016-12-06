package webService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.persistents.*;
import model.tools.Bundle;
import model.tools.TripManager;
import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;

import dtos.EventDTO;
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
@Path("/event")
public class EventRest {

    public static final int NUMBER_OF_POST = 10;

    /**
     * IMPORTANTE!! - AcÃ¡ deben inyectar el servicio de la aplicaciÃ³n y este
     * servicio REST solo debe hacer un delegate simple.
     */
    //private PostRepository postRepository;
    private EventService eventService;
    private CategoryService categoryService;
	private UserService userService;
	
    /*
     *  C.R.U.D.
     */
    @POST
    @Path("/create")
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response Create(Event newEvent){
    	if (duplicatedEvent(newEvent)){
    		return Response.status(409).build();
    	}
    	eventService.save(newEvent);
    	return Response.ok(newEvent.getId()).build();
    }
    
    private boolean duplicatedEvent(Event event){
    	Event exampleObject = new Event();
    	exampleObject.setDay(event.getDay());
    	exampleObject.setName(event.getName());
    	exampleObject.setAddress(event.getAddress());
    	
    	List<Event> events = eventService.findByExample(exampleObject);
    	return events.size()>0;
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
    @GET
    @Path("/readdto/{idE}")
    @Produces("application/json")
    public Response Readdto(@PathParam ("idE") int idE){

        Event e = eventService.findById(idE);
        if (e == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(new EventDTO(e)).build();
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

    //////////////////////////////
    /*
    // bundles...
     */
    @GET
    @Path("/cheap/{day}/{user}")
    @Produces("application/json")
    public Response cheap(@PathParam("day") int day, @PathParam("user") String user){
        List<List<Event>> bundles = new ArrayList<List<Event>>();

        List<Bundle> cheaptrips = new TripManager().cheapTrip(this.getbydate(day),userService.findById(user));
        cheaptrips.forEach(bundle -> bundles.add(bundle.getBundle()));

        return Response.ok(bundles).build();
    }
    @GET
    @Path("/surprise/{day}/{user}")
    @Produces("application/json")
    public Response surprise(@PathParam("day") int day, @PathParam("user") String user){
        List<List<Event>> bundles = new ArrayList<List<Event>>();

        List<Bundle> cheaptrips = new TripManager().surpriseTrip(this.getbydate(day),userService.findById(user));
        cheaptrips.forEach(bundle -> bundles.add(bundle.getBundle()));

        return Response.ok(bundles).build();
    }
    @GET
    @Path("/friendly/{day}/{user}")
    @Produces("application/json")
    public Response friendly(@PathParam("day") int day, @PathParam("user") String user){
        List<List<Event>> bundles = new ArrayList<List<Event>>();

        List<Bundle> cheaptrips = new TripManager().friendlyTrip(this.getbydate(day),userService.findById(user));
        cheaptrips.forEach(bundle -> bundles.add(bundle.getBundle()));

        return Response.ok(bundles).build();
    }





    public List<Event> getbydate(int date){
        Event exampleObject = new Event();
        exampleObject.setDay(date);
        //exampleObject.setName("goingToHell");
        return  eventService.findByExample(exampleObject);
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
    @Path("/eventsdto")
    @Produces("application/json")
    public Response alleventsdto() {
        List<Event> events = eventService.retriveAll();
        List<EventDTO> eventsDto = new ArrayList<EventDTO>();
        events.forEach(event -> eventsDto.add(new EventDTO(event)));
        return Response.ok(eventsDto).build();
    }

    @Transactional
    @PUT
    @Path("/attend/{eventId}/{userName}")
    @Produces("application/json")
    public Response attend(@PathParam ("eventId") int id,@PathParam ("userName") String userName){
    	int i = 0;
    	Event e = this.eventService.findById(id);
    	User u = this.userService.findById(userName);
    	e.attend(u);
    	u.attend(e);
    	eventService.update(e);
    	userService.update(u);
    	return Response.ok().build();
    }
    @Transactional
    @PUT
    @Path("/personal/{eventId}/{userName}")
    @Produces("application/json")
    public Response personal(@PathParam ("eventId") int id,@PathParam ("userName") String userName){
        int i = 0;
        Event e = this.eventService.findById(id);
        User u = this.userService.findById(userName);
        //e.attend(u);
        //u.attend(e);
        u.createPersonalEvent(e);
        eventService.update(e);
        userService.update(u);
        return Response.ok().build();
    }
    
    @GET
    @Path("/getbydate/{date}")
    @Produces("application/json")
    public List<Event> getby(@PathParam ("date") int date){
    	Event exampleObject = new Event();
    	exampleObject.setDay(date);
    	//exampleObject.setName("goingToHell");
    	List<Event> events = eventService.findByExample(exampleObject);
    	return events;
    }
    @GET
    @Path("/getbyq/")
    @Produces("application/json")
    public List<Event> getbyq(@PathParam ("date") int date){
    	Event exampleObject = new Event();
    	exampleObject.setDay(date);
    	//exampleObject.setName("goingToHell");
    	@SuppressWarnings("unchecked")
		List<Event> events = (List<Event>) eventService.find("select name from Event");
    	return events;
    }
    

     
 	 @GET
     @Path("/addEvents")
     @Produces("application/json")
     public String addEvent(){
 		
 		Category food = this.categoryService.findById("Food");
 		
 		Category at = this.categoryService.findById("Atomico");
    	Category te = this.categoryService.findById("Termonuclear");
    	Category fa = this.categoryService.findById("Fantabulosico");
    	Category tr = this.categoryService.findById("Tranca");
    	Category to =this.categoryService.findById("Tornado");
    	
    	{
    	Event e = new Event();
    	e.setName("goingToHell");
    	e.setAddress("666");
    	e.setDetails("Devils house");
    	e.setPrice(new Price(20));
    	e.setStartTime(LocalDateTime.now());
    	e.setEndTime(LocalDateTime.now());
    	Set<User> uset = new HashSet<User>();
    	Attenders attenders = new Attenders();
    	attenders.setUsers(uset);
    	attenders.setMaxCapacity(0);
    	attenders.setRecommendedMaxGroup(0);
    	attenders.setRecommendedMinGroup(0);
    	e.setAttenders(attenders);
    	Profile p = new Profile();
    	p.addCategory(fa);
    	e.setProfile(p);
    	//e.setCategory(new Category("warm places"));
    	//e.setHour(LocalTime.of(12, 50));
    	}
    	
    	
    	for(int i=1;i<20;i++){
    		Event e = new Event();
    		String newname = "goingToHell"+i;
    		e.setName(newname);
    		
    		e.setAddress("666");
        	e.setDetails("Devils house");
        	e.setPrice(new Price(20));
        	e.setStartTime(LocalDateTime.now());
        	e.setEndTime(LocalDateTime.now());
        	Set<User> uset = new HashSet<User>();
        	Attenders attenders = new Attenders();
        	attenders.setUsers(uset);
        	attenders.setMaxCapacity(0);
        	attenders.setRecommendedMaxGroup(0);
        	attenders.setRecommendedMinGroup(0);
        	e.setAttenders(attenders);
        	Profile p = new Profile();
        	p.addCategory(fa);
        	e.setProfile(p);
        	
        	if (i%2==0){
        	    e.setPrice(new Price(0));
        	    e.getProfile().addCategory(at);
            }
            else{
        	    e.setPrice(new Price(20));
                e.getProfile().addCategory(te);
            }

        	eventService.save(e);
    	}

    	{
        Event e1 = new Event();
        e1.setName("Guerrin");
        e1.setAddress("Av. Corrientes 1368, C1043ABN CABA");
        e1.setDetails("La pizzería Guerrin está entre las más antiguas y tradicionales de la calle corrientes. " +
                "Fué fundada hacia el año 1932 por el Sr. Arturo Malvezzi y el Sr. Guido Grondona, emigrantes " +
                "Genoveses que habían llegado de Italia en el año 1927.");

        e1.setPrice(new Price(20));
        e1.setStartTime(LocalDateTime.now());
        e1.setEndTime(LocalDateTime.now());
        Set<User> users1 = new HashSet<User>();
        Attenders attenders1 = new Attenders();
        attenders1.setUsers(users1);
        attenders1.setMaxCapacity(1000);
        attenders1.setRecommendedMaxGroup(100);
        attenders1.setRecommendedMinGroup(1);
        e1.setAttenders(attenders1);
        Profile p1 = new Profile();
        p1.addCategory(food);
        p1.addCategory(at);
        p1.addCategory(te);
        e1.setProfile(p1);
        //e.setCategory(new Category("warm places"));
        //e.setHour(LocalTime.of(12, 50));
        eventService.save(e1);
    	}
    	
    	{
    		
    	}
    	
    	{
    		
    	}

     	return "OK";
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

}
