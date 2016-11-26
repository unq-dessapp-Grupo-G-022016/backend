package service;

import model.Event;

import javax.transaction.Transactional;

public class EventService extends GenericService<Event>{

    @Transactional
    public void save(final Event object) {
        this.getRepository().save(object);
    }

}
