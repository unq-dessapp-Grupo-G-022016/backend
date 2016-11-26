package service;

import model.Category;
import model.User;

import javax.transaction.Transactional;
import javax.ws.rs.core.Response;

public class UserService extends GenericService<User>{

    @Transactional
    public Response addCategory(User user, Category cat){

        user.getProfile().addCategory(cat);

        this.getRepository().update(user);
        return Response.ok().build();
    }

}
