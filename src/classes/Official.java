package classes;

import java.time.LocalDate;
import java.util.ArrayList;

public class Official extends Person {
    private ArrayList<Race> racesOfficiating;

    public Official(String fName, String lName, String phoneNum, LocalDate birthday) {
        super(fName, lName, phoneNum, birthday);
        this.racesOfficiating = new ArrayList<Race>();
    }

    // Setters

    public void setRacesOfficiating(ArrayList<Race> racesOfficiating) {
        this.racesOfficiating = racesOfficiating;
    }

    // Getters

    public ArrayList<Race> getRacesOfficiating() {
        return racesOfficiating;
    }

    // Other

    public void addRace(Race race) {
        this.racesOfficiating.add(race);
    }

    @Override
    public String toString() {
        return "classes.Official | " + super.toString();
    }
}
