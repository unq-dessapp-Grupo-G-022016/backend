package model.creation;

import model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by alejandrok on 29/11/16.
 */
public class Events {


    public List<Event> someEvents(){
        List<Event> someEvents = new ArrayList<Event>();

        Event e = new Event();
        e.setName("Guerrin");
        e.setAddress("Av. Corrientes 1368, C1043ABN CABA");
        e.setDetails("La pizzería Guerrin está entre las más antiguas y tradicionales de la calle corrientes. " +
                "Fué fundada hacia el año 1932 por el Sr. Arturo Malvezzi y el Sr. Guido Grondona, emigrantes " +
                "Genoveses que habían llegado de Italia en el año 1927.");
        e.setPrice(new Price(20));
        e.setStartTime(LocalDateTime.now());
        e.setEndTime(LocalDateTime.now());
        Set<User> uset = new HashSet<User>();
        Attenders attenders = new Attenders();
        attenders.setUsers(uset);
        attenders.setMaxCapacity(1000);
        attenders.setRecommendedMaxGroup(100);
        attenders.setRecommendedMinGroup(1);
        e.setAttenders(attenders);
        Profile p = new Profile();
        p.addCategory(new Category("Food"));
        //p.addCategory(new Category(""));
        e.setProfile(p);
        //e.setCategory(new Category("warm places"));
        //e.setHour(LocalTime.of(12, 50));
        someEvents.add(e);


        e.setName("L.A. Guns en Glamnation");
        e.setAddress("calchaqui 3100");
        e.setDetails("L.A. Guns es un grupo de hard rock formado en la ciudad estadounidense de Los Ángeles, " +
                "California en 1983. Su carrera discográfica comenzó en 1988 y su pico de popularidad fue a fines " +
                "de los años 80´ con sus dos primeros discos “L.A. Guns” (1988), “Cocked & Loaded” (1989) y " +
                "Hollywood Vampires (1991), ambos con un gran éxito comercial y considerados como álbumes clásicos" +
                " del Hard Rock de Los Ángeles.");
        e.setPrice(new Price(150));
        e.setStartTime(LocalDateTime.now());
        e.setEndTime(LocalDateTime.now());
         uset = new HashSet<User>();
         attenders = new Attenders();
        attenders.setUsers(uset);
        attenders.setMaxCapacity(1000);
        attenders.setRecommendedMaxGroup(100);
        attenders.setRecommendedMinGroup(1);
        e.setAttenders(attenders);
         p = new Profile();
        p.addCategory(new Category("Recital"));
        //p.addCategory(new Category(""));
        e.setProfile(p);
        //e.setCategory(new Category("warm places"));
        //e.setHour(LocalTime.of(12, 50));
        someEvents.add(e);


        e.setName("Los Violadores - Luna Park");
        e.setAddress("corrientes 85 ciudad autonoma buenos aires");
        e.setDetails("Los Violadores, la banda más importante de punk rock latinoamericano, regresan para celebrar " +
                "los 30 años de su disco más exitoso \"¿Y ahora qué pasa, eh?\" (editado en 1985) con la formación " +
                "original que grabó aquel emblemático álbum: Pil Trafa (voz), Stuka (guitarra), Robert Wojtehk " +
                "\"Polaco\" Zelazek (bajo) y Sergio Gramática (batería). \"Uno, dos, ultraviolento\" " +
                "(tema incluido en el disco) fué la primera canción en lograr difusión radial masiva y fue uno " +
                "de los primeros éxitos del punk rock en español de Latinoamérica. ");
        e.setPrice(new Price(200));
        e.setStartTime(LocalDateTime.now());
        e.setEndTime(LocalDateTime.now());
         uset = new HashSet<User>();
         attenders = new Attenders();
        attenders.setUsers(uset);
        attenders.setMaxCapacity(1000);
        attenders.setRecommendedMaxGroup(100);
        attenders.setRecommendedMinGroup(1);
        e.setAttenders(attenders);
         p = new Profile();
        p.addCategory(new Category("Recital"));
        //p.addCategory(new Category(""));
        e.setProfile(p);
        //e.setCategory(new Category("warm places"));
        //e.setHour(LocalTime.of(12, 50));
        someEvents.add(e);


        return someEvents;
    }

}
