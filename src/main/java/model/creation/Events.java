package model.creation;

import model.persistents.*;

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

        Event e1 = new Event();
        e1.setName("Guerrin");
        e1.setAddress("Av. Corrientes 1368, C1043ABN CABA");
        e1.setDetails("La pizzería Guerrin está entre las más antiguas y tradicionales de la calle corrientes. " +
                "Fué fundada hacia el año 1932 por el Sr. Arturo Malvezzi y el Sr. Guido Grondona, emigrantes " +
                "Genoveses que habían llegado de Italia en el año 1927.");

        e1.setPrice(new Price(20));
        e1.setStartTime(LocalDateTime.now());
        e1.setEndTime(LocalDateTime.now());
        Set<User> users1 = new HashSet<User>();
        Attenders attenders1 = new Attenders();
        attenders1.setUsers(users1);
        attenders1.setMaxCapacity(1000);
        attenders1.setRecommendedMaxGroup(100);
        attenders1.setRecommendedMinGroup(1);
        e1.setAttenders(attenders1);
        Profile p = new Profile();
        p.addCategory(new Category("Food"));
        //p.addCategory(new Category(""));
        e1.setProfile(p);
        //e.setCategory(new Category("warm places"));
        //e.setHour(LocalTime.of(12, 50));
        someEvents.add(e1);


        Event e2 = new Event();
        e2.setName("L.A. Guns en Glamnation");
        e2.setAddress("calchaqui 3100");
        e2.setDetails("L.A. Guns es un grupo de hard rock formado en la ciudad estadounidense de Los Ángeles, " +
                "California en 1983. Su carrera discográfica comenzó en 1988 y su pico de popularidad fue a fines " +
                "de los años 80´ con sus dos primeros discos “L.A. Guns” (1988), “Cocked & Loaded” (1989) y " +
                "Hollywood Vampires (1991), ambos con un gran éxito comercial y considerados como álbumes clásicos" +
                " del Hard Rock de Los Ángeles.");
        e2.setPrice(new Price(150));
        e2.setStartTime(LocalDateTime.now());
        e2.setEndTime(LocalDateTime.now());
        Set<User> users2 = new HashSet<User>();
        Attenders attenders2 = new Attenders();
        attenders2.setUsers(users2);
        attenders2.setMaxCapacity(1000);
        attenders2.setRecommendedMaxGroup(100);
        attenders2.setRecommendedMinGroup(1);
        e2.setAttenders(attenders2);
        Profile p2 = new Profile();
        p2.addCategory(new Category("Recital"));
        //p.addCategory(new Category(""));
        e2.setProfile(p2);
        //e.setCategory(new Category("warm places"));
        //e.setHour(LocalTime.of(12, 50));
        someEvents.add(e2);

        Event e3 = new Event();
        e3.setName("Los Violadores - Luna Park");
        e3.setAddress("corrientes 85 ciudad autonoma buenos aires");
        e3.setDetails("Los Violadores, la banda más importante de punk rock latinoamericano, regresan para celebrar " +
                "los 30 años de su disco más exitoso \"¿Y ahora qué pasa, eh?\" (editado en 1985) con la formación " +
                "original que grabó aquel emblemático álbum: Pil Trafa (voz), Stuka (guitarra), Robert Wojtehk " +
                "\"Polaco\" Zelazek (bajo) y Sergio Gramática (batería). \"Uno, dos, ultraviolento\" " +
                "(tema incluido en el disco) fué la primera canción en lograr difusión radial masiva y fue uno " +
                "de los primeros éxitos del punk rock en español de Latinoamérica. ");
        e3.setPrice(new Price(200));
        e3.setStartTime(LocalDateTime.now());
        e3.setEndTime(LocalDateTime.now());
        Set<User> users3 = new HashSet<User>();
        Attenders attenders3 = new Attenders();
        attenders3.setUsers(users3);
        attenders3.setMaxCapacity(1000);
        attenders3.setRecommendedMaxGroup(100);
        attenders3.setRecommendedMinGroup(1);
        e3.setAttenders(attenders3);
        Profile p3 = new Profile();
        p3.addCategory(new Category("Recital"));
        //p.addCategory(new Category(""));
        e3.setProfile(p3);
        //e.setCategory(new Category("warm places"));
        //e.setHour(LocalTime.of(12, 50));
        someEvents.add(e3);


        return someEvents;
    }

}
