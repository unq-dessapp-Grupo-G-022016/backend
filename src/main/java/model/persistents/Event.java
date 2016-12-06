package model.persistents;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.Type;

/**
 * Created by leog on 30/08/16.
 */
@Entity
//public abstract class Event {
public class Event {
	
	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	@Column
	private boolean strictSchedule;
	

	public boolean isStrictSchedule() {
		return strictSchedule;
	}

	public void setStrictSchedule(boolean strictSchedule) {
		this.strictSchedule = strictSchedule;
	}

	@Column
	private int day;
	
	/*
	@Column
    @JsonSerialize(using = type.LocalTimeSerializer.class)
    @JsonDeserialize(using = type.LocalTimeDeserializer.class)
	private LocalTime hour;
	
	public LocalTime getHour() {
		return hour;
	}

	public void setHour(LocalTime hour) {
		this.hour = hour;
	}
	*/


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

	public void setAttenders(Attenders attenders) {
		this.attenders = attenders;
	}

	@Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
    private String name;
	@Column
    private String address;
	@Column
    private String details;
	@JsonIgnore
	@ManyToOne (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Price price;
	
    @Type(type="type.LocalDateTimeUserType")
    @Column
    @JsonSerialize(using = type.LocalDateTimeSerializer.class)
    @JsonDeserialize(using = type.LocalDateTimeDeserializer.class)
    private LocalDateTime startTime;
    
    @Type(type="type.LocalDateTimeUserType")
    @Column
    @JsonSerialize(using = type.LocalDateTimeSerializer.class)
    @JsonDeserialize(using = type.LocalDateTimeDeserializer.class)
    private LocalDateTime endTime;
    /*
    @JsonIgnore
    @ManyToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<User>attenders = new HashSet<>();
    @JsonIgnore
    */
    /*
    @ManyToOne (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Category category;
	*/
	@OneToOne (cascade = CascadeType.ALL)
    private Profile profile;

    @JsonSerialize(using = type.LocalDateTimeSerializer.class)
    @JsonDeserialize(using = type.LocalDateTimeDeserializer.class)
    public LocalDateTime getStartTime() {
        return startTime;
    }
    @JsonSerialize(using = type.LocalDateTimeSerializer.class)
    @JsonDeserialize(using = type.LocalDateTimeDeserializer.class)
    public LocalDateTime getEndTime() {
        return endTime;
    }
    
    public Event(){
    	
    }
/*
    public Event(String name, String address, String details, Price price, LocalDateTime date, LocalDateTime duration, Attenders attenders, Profile profile) {
        this.name = name;
        this.address = address;
        this.details = details;
        this.price = price;
        setStartTime(date);
        this.endTime = duration;
        this.attenders = attenders;
        //this.category = category;
        this.profile = profile;
    }
*/    
    public Event(String name, String address, String details, Price price, LocalDateTime date, LocalDateTime duration, Attenders attenders, Profile profile, boolean strictSchedule) {
        this.name = name;
        this.address = address;
        this.details = details;
        this.price = price;
        setStartTime(date);
        this.endTime = duration;
        this.attenders = attenders;
        //this.category = category;
        this.profile = profile;
        this.strictSchedule = strictSchedule;
    }

    public Price getPrice(){
        return price;
    }

    public boolean isFoodEvent(){return getProfile().hasCategory("Food");}

    public boolean isMusicEvent(){return false;}

    public boolean isMovieEvent(){return false;}

    public boolean timeCompatible(Event event){
        return this.getEndTime().isBefore(event.getStartTime());
    }

    /*
     *  !!!!!!!!!!!!
     */
    public boolean hasCategory(){
        //return !(this.category.getName().equals("undefined"));
    	return true;
    }
    
    @JsonSerialize(using = type.LocalDateTimeSerializer.class)
    @JsonDeserialize(using = type.LocalDateTimeDeserializer.class)
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
    @JsonSerialize(using = type.LocalDateTimeSerializer.class)
    @JsonDeserialize(using = type.LocalDateTimeDeserializer.class)
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
        this.day = startTime.getYear() * 10000
        		+startTime.getMonthValue() * 100
        		+startTime.getDayOfMonth();
    }

    public Profile getProfile(){
    	return this.profile;
    }
    public void setProfile(Profile profile){
    	this.profile = profile;
    }
    /*
    public Category getCategory(){
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
*/
    
    /*
    public boolean hasTheSameCategory(Event anotherEvent) {
        return this.getCategory().getName().equals(anotherEvent.getCategory().getName());

*/
    public boolean hasTheSameCategory(Set<Category> categorySet){
        Stream<Category> categories = categorySet.stream().filter(cat ->
            this.getProfile().hasCategory(cat.getName())
        );
        return ! categories.collect(Collectors.toList()).isEmpty();

    }


    /**
     * Return suggestions based on event attenders.
     */
    public List<Event> eventSuggestions(Event event){
        List<Event> suggetions = new ArrayList<>();
        event.getAttenders().getUsers().forEach(user -> suggetions.addAll(user.getAttendedEvents()));
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

    public Attenders getAttenders() {
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
    
    @OneToOne (cascade = CascadeType.ALL)
    private Attenders attenders;
    
    @Column
    private String image;
    
    public void setImageUrl(String url){
    	this.image = url;
    }
    public String getImageUrl(){
    	return this.image;
    }
}



