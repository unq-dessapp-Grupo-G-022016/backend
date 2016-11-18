package repository;

import model.Event;

@SuppressWarnings("serial")
public class CategoryRepository extends HibernateGenericRepository<Event> implements GenericRepository<Event> {
	
	 @Override
	    protected Class<Event> getDomainClass() {
	        return Event.class;
	    }

}
