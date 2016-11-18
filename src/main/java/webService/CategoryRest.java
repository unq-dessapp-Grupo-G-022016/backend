package webService;


import java.util.List;

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

import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;

import model.Category;
import model.Event;
import service.CategoryService;
import service.UserService;



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
@Path("/category")
public class CategoryRest {
	private CategoryService categoryService;
    /*
     *  C.R.U.D.
     */
    @POST
    @Path("/create")
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response Create(Category cat){
    	if (duplicatedCategory(cat)){
    		return Response.status(409).build();
    	}
    	categoryService.save(cat);
    	return Response.ok().build();
    }
    private boolean duplicatedCategory(Category cat){
    	Category exampleObject = new Category(cat.getName());
    	List<Category> events = categoryService.findByExample(exampleObject);
    	return events.size()>0;
    }
    @GET
    @Path("/read/{idE}")
    @Produces("application/json")
    public Response Read(@PathParam ("idE") int idE){
    	
    	Category e = categoryService.findById(idE);
    	if (e == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(e).build();
    }
    @PUT
    @Path("/update")
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response Update(Category updatedEvent){
    	Category e = categoryService.findById(updatedEvent.getName());
    	if (e == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    	else{
    		categoryService.update(updatedEvent);
    	}
        return Response.ok().build();

    }
    
    @DELETE
    @Path("/delete/{idE}")
    @Produces("application/json")
    public Response Delete(@PathParam ("idE") int idE){
    	Category e = categoryService.findById(idE);
    	if (e == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    	categoryService.delete(e);
    	return Response.ok().build();
    }

}
