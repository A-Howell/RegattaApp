package classes;

import enums.Gender;

import java.time.LocalDate;
import java.util.ArrayList;

public class Official extends Person {
    private ArrayList<Race> racesOfficiating;
    private int officialID;

    private static int officialCount = 0;

    public Official(String fName, String lName, String phoneNum, LocalDate birthday, Gender gender) {
        super(fName, lName, phoneNum, birthday, gender);
        this.racesOfficiating = new ArrayList<Race>();

        officialCount++;
        this.officialID = officialCount;
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
        return "Official | [" + this.officialID + "] " + super.toString();
    }
}
