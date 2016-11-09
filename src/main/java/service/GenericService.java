package service;

import java.io.Serializable;
//import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import repository.GenericRepository;

public class GenericService<T> //implements Serializable {
{
    //private static final long serialVersionUID = -6540963495078524186L;

    private GenericRepository<T> repository;

    public GenericRepository<T> getRepository() {
        return this.repository;
    }

    public void setRepository(final GenericRepository<T> repository) {
        this.repository = repository;
    }

    @Transactional
    public void delete(final T object) {
        this.getRepository().delete(object);
    }

    @Transactional(readOnly = true)
    public List<T> retriveAll() {
        return this.getRepository().findAll();
    }

    @Transactional
    public void save(final T object) {
        this.getRepository().save(object);
    }

    //@Transactional
    public void update(final T object) {
        this.getRepository().update(object);
    }
    //@Transactional
    public T findById(final Serializable id) {
        return this.getRepository().findById(id);
    }
    @Transactional
    public List<T> findByExample(T exampleObject){
    	return this.getRepository().findByExample(exampleObject);
    }
    
    @Transactional
    public List<?> find(String query){
    	return this.getRepository().find(query);
    }
    

}
