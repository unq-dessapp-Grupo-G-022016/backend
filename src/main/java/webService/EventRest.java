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
     public Response addEvent(){
 		
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
        	e.setImageUrl("http://earthobservatory.nasa.gov/blogs/earthmatters/files/2011/08/smptebars-468x351.png");
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
        e1.setImageUrl("https://media-cdn.tripadvisor.com/media/photo-s/04/9f/09/51/pizzeria-guerrin.jpg");
        eventService.save(e1);
    	}
    	
    	{

            Event e2 = new Event();
            e2.setName("L.A. Guns en Glamnation");
            e2.setAddress("calchaqui 3100");
            e2.setDetails("L.A. Guns es un grupo de hard rock formado en la ciudad estadounidense de Los Ángeles, " );//+
            //        "California en 1983. Su carrera discográfica comenzó en 1988 y su pico de popularidad fue a fines " +
              //      "de los años 80´ con sus dos primeros discos “L.A. Guns” (1988), “Cocked & Loaded” (1989) y " +
                //    "Hollywood Vampires (1991), ambos con un gran éxito comercial y considerados como álbumes clásicos" +
                  //  " del Hard Rock de Los Ángeles.");
            e2.setPrice(new Price(150));
            e2.setStartTime(LocalDateTime.now());
            e2.setEndTime(LocalDateTime.now());
            Set<User> users2 = new HashSet<User>();
            Attenders attenders2 = new Attenders();
            attenders2.setUsers(users2);
            attenders2.setMaxCapacity(1000);
            attenders2.setRecommendedMaxGroup(100);
            attenders2.setRecommendedMinGroup(1);
            e2.setAttenders(attenders2);
            Profile p2 = new Profile();
            p2.addCategory(at);
            //p.addCategory(new Category(""));
            e2.setProfile(p2);
            //e.setCategory(new Category("warm places"));
            //e.setHour(LocalTime.of(12, 50));
            e2.setImageUrl("http://earthobservatory.nasa.gov/blogs/earthmatters/files/2011/08/smptebars-468x351.png");
            eventService.save(e2);

    	}
    	
    	{
    		Event e3 = new Event();
            e3.setName("Los Violadores - Luna Park");
            e3.setAddress("corrientes 85 ciudad autonoma buenos aires");
            e3.setDetails("Los Violadores, la banda más importante de punk rock latinoamericano, regresan para celebrar ") ;//+
            //        "los 30 años de su disco más exitoso ¿Y ahora qué pasa, eh? (editado en 1985) con la formación " +
              //      "original que grabó aquel emblemático álbum: Pil Trafa (voz), Stuka (guitarra), Robert Wojtehk " +
                //    "Polaco Zelazek (bajo) y Sergio Gramática (batería). Uno, dos, ultraviolento " +
                  //  "(tema incluido en el disco) fué la primera canción en lograr difusión radial masiva y fue uno " +
                    //"de los primeros éxitos del punk rock en español de Latinoamérica. ");
            e3.setPrice(new Price(200));
            e3.setStartTime(LocalDateTime.now());
            e3.setEndTime(LocalDateTime.now());
            Set<User> users3 = new HashSet<User>();
            Attenders attenders3 = new Attenders();
            attenders3.setUsers(users3);
            attenders3.setMaxCapacity(1000);
            attenders3.setRecommendedMaxGroup(100);
            attenders3.setRecommendedMinGroup(1);
            e3.setAttenders(attenders3);
            Profile p3 = new Profile();
            p3.addCategory(te);
            //p.addCategory(new Category(""));
            e3.setProfile(p3);
            //e.setCategory(new Category("warm places"));
            //e.setHour(LocalTime.of(12, 50));
            e3.setImageUrl("http://earthobservatory.nasa.gov/blogs/earthmatters/files/2011/08/smptebars-468x351.png");
            eventService.save(e3);
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

}
