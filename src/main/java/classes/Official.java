package classes;

import enums.Gender;

import java.time.LocalDate;
import java.util.ArrayList;

public class Official extends Person {
    private ArrayList<Race> racesOfficiating;
    private String officialID;

    private static int officialCount = 0;

    public Official(String fName, String lName, String phoneNum, LocalDate birthday, Gender gender) {
        super(fName, lName, phoneNum, birthday, gender);
        this.racesOfficiating = new ArrayList<Race>();

        officialCount++;
        this.officialID = "O" + officialCount;
    }

    // Setters

    public void setRacesOfficiating(ArrayList<Race> racesOfficiating) {
        this.racesOfficiating = racesOfficiating;
    }

    public void setOfficialID(String officialID) {
        this.officialID = officialID;
    }

    public static void setOfficialCount(int officialCount) {
        Official.officialCount = officialCount;
    }

    // Getters

    public ArrayList<Race> getRacesOfficiating() {
        return racesOfficiating;
    }

    public String getOfficialID() {
        return officialID;
    }

    public static int getOfficialCount() {
        return officialCount;
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
