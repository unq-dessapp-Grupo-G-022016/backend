package model;


import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by leog on 30/08/16.
 */
public class Event {

    String name;
    String address;
    String details;
    Price price;
    LocalDateTime startTime;
    LocalDateTime endTime;
    Set<User>attenders = new HashSet<>();
    Category category;

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public Event(){} //FOR TEST ONLY

    public Event(String name, String address, String details, Price price, LocalDateTime date, LocalDateTime duration, Set<User> attenders) {
        this.name = name;
        this.address = address;
        this.details = details;
        this.price = price;
        this.startTime = date;
        this.endTime = duration;
        this.attenders = attenders;
        this.category = new Category("undefined");
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

    public boolean isFoodEvent(){
        return false;
    }

    public boolean isMovieEvent(){return false;}

    public boolean isMusicEvent(){return false;}

    public boolean timeCompatible(Event event){
        return this.getEndTime().isBefore(event.getStartTime());
    }

    public boolean hasCategory(){
        return (!(this.category.getName()=="undefined"));
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
        // reminder: undefineds? if this is??
        boolean categoryNameIsInTheSet = false;
        Iterator<Category> it = categorySet.iterator();
        while (it.hasNext() && categoryNameIsInTheSet == false){
            Category categoryFromSet = it.next();
            String s1 = categoryFromSet.getName();
            String s2 = this.getCategory().getName();
            categoryNameIsInTheSet = s1.contentEquals(s2);
            //res = getCategory.isEqual(((SpecificEvent) event).getCategory());
        }
        return categoryNameIsInTheSet;
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

    public void attend(Set<User> users){
        this.attenders.addAll(users);
    }

    public Set<User> getAttenders() {
        return attenders;
    }

    public String getName() {
        return name;
    }

    public String getDetails() {
        return details;
    }
}



