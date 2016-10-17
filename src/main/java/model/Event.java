package model;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;

/**
 * Created by leog on 30/08/16.
 */
@Entity
public abstract class Event {

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	public void setAttenders(Set<User> attenders) {
		this.attenders = attenders;
	}

	@Id()
    @GeneratedValue()
	private int id;
	
	@Column
    private String name;
	@Column
    private String address;
	@Column
    private String details;
	@ManyToOne
    private Price price;
    @Type(type="type.LocalDateTimeUserType")
    @Column
    private LocalDateTime startTime;
    @Type(type="type.LocalDateTimeUserType")
    @Column
    private LocalDateTime endTime;
    @ManyToMany
    private Set<User>attenders = new HashSet<>();
    @ManyToOne
    private Category category;

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }
    
    public Event(){
    	
    }

    public Event(String name, String address, String details, Price price, LocalDateTime date, LocalDateTime duration, Set<User> attenders, Category category) {
        this.name = name;
        this.address = address;
        this.details = details;
        this.price = price;
        this.startTime = date;
        this.endTime = duration;
        this.attenders = attenders;
        this.category = category;
    }

    public Price getPrice(){
        return price;
    }

    public boolean isFoodEvent(){return false;}

    public boolean isMusicEvent(){return false;}

    public boolean isMovieEvent(){return false;}

    public boolean timeCompatible(Event event){
        return this.getEndTime().isBefore(event.getStartTime());
    }

    public boolean hasCategory(){
        return !(this.category.getName().equals("undefined"));
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public Category getCategory(){
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean hasTheSameCategory(Event anotherEvent) {
        return this.getCategory().getName().equals(anotherEvent.getCategory().getName());
    }

    public boolean hasTheSameCategory(Set<Category> categorySet){
        Stream<Category> events= categorySet.stream().filter(event -> event.isEqual(this.category));
        return ! events.collect(Collectors.toList()).isEmpty();

    }

    /**
     * Return suggestions based on event attenders.
     */
    public List<Event> eventSuggestions(Event event){
        List<Event> suggetions = new ArrayList<>();
        event.getAttenders().forEach(user -> suggetions.addAll(user.getAttendedEvents()));
        return suggetions;
    }


    public boolean isFree() {
        return (this.price.isFree());
    }

    public boolean isCheap(User user){
        return this.getPrice().isCheap(user);
    }

    public void attend(User user){
        this.attenders.add(user);
    }

    public Set<User> getAttenders() {
        return attenders;
    }

    public String getName() {
        return name;
    }

    public String getDetails() {return details;
    }

    public boolean matchForDay(LocalDateTime date) {
        return this.startTime.getDayOfYear()==date.getDayOfYear();
    }
}



