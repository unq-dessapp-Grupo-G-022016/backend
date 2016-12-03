package service;

import javax.transaction.Transactional;
import javax.ws.rs.core.Response;

import model.persistents.Category;

public class CategoryService extends GenericService<Category>{
	
	@Transactional
	public Response create(String cat){
		Category e = this.getRepository().findById(cat);
    	if (e != null) {
    		return Response.status(409).build();
    	}
    	e = new Category(cat);
    	this.getRepository().save(new Category(cat));
    	return Response.ok().build();
	}
/*
	@Transactional
	public Response updateByName(String name){
		Category e = this.getRepository().findById(name);
    	if (e == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    	else{
    		this.getRepository().update(e);
    	}
        return Response.ok().build();
	}
*/
	@Transactional
	public Response deleteByName(String name){
		Category e = this.getRepository().findById(name);
    	if (e == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    	this.getRepository().delete(e);
    	return Response.ok().build();
	}

}
