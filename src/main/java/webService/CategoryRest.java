package webService;


import java.util.List;

import javax.persistence.Query;
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

import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;

import model.Category;
import model.Event;
import model.User;
import service.CategoryService;
import service.EventService;
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
    @Path("/create/{cat}")
    public Response Create(@PathParam ("cat") String cat){

		return categoryService.create(cat);
        
    }
	/*
    private boolean duplicatedCategory(Category cat){
    	Category exampleObject = new Category(cat.getName());
    	List<Category> events = categoryService.findByExample(exampleObject);
    	return events.size()>0;
    }
    */
    @GET
    @Path("/read/{cat}")
    @Produces("application/json")
    public Response Read(@PathParam ("cat") String cat){
    	//Category catcat = new Category();
		//catcat.setName(cat);
    	
		Category e = categoryService.findById(cat);
    	if (e == null) {
    		return Response.status(Response.Status.NOT_FOUND).build();
    	}
    	
        return Response.ok(e).build();
    }
    
    
    @PUT
    @Path("/update")
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response Update(Category updatedCategory){
    	
        return categoryService.updateByName(updatedCategory.getName());

    }
    
    @DELETE
    @Path("/delete/{id}")
    @Produces("application/json")
    public Response Delete(@PathParam ("id") String id){
    	
    	return categoryService.deleteByName(id);
    }
    /*
     * 
     */
    @GET
    @Path("/categories")
    @Produces("application/json")
    public Response getAllUsers() {
        List<Category> users = categoryService.retriveAll();
        if (users.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(users).build();
    }
    
    @Transactional
    @GET
    @Path("/addCategories")
    public Response addCategories(){
    	Category cat = new Category("Vegano");
    	categoryService.save(cat);
    	cat = new Category("Rabioles");
    	categoryService.save(cat);
    	cat = new Category("Aburrido");
    	categoryService.save(cat);
    	cat = new Category("Disco");
    	categoryService.save(cat);
    	cat = new Category("Escabios");
    	categoryService.save(cat);
    	cat = new Category("Gay-Pride");
    	categoryService.save(cat);
    	cat = new Category("Pro-Lesbo");
    	categoryService.save(cat);
		return Response.ok().build();
    }

    public void setCategoryService(final CategoryService eventDAO) {
        categoryService = eventDAO;
    }
}
