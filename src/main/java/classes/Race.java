package classes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import enums.BoatType;
import enums.Division;
import enums.Gender;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Race {
    private LocalTime startTime;
    private Gender gender;
    private Division division;
    private BoatType boatType;
    private String raceID;

    private static int raceCounter = 0;

    private List<Crew> crewList;

    public Race() {

    }

    public Race(LocalTime startTime, Gender gender, Division division, BoatType boatType) {
        this.startTime = startTime;
        this.gender = gender;
        this.division = division;
        this.boatType = boatType;

        this.crewList = new ArrayList<>();

        raceCounter++;
        this.raceID = "R" + raceCounter;
    }

    // Setters

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setBoatType(BoatType boatType) {
        this.boatType = boatType;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setCrewList(List<Crew> crewList) {
        this.crewList = crewList;
    }

    public void setRaceID(String raceID) {
        this.raceID = raceID;
    }

    public static void setRaceCounter(int raceCounter) {
        Race.raceCounter = raceCounter;
    }

    // Getters

    public LocalTime getStartTime() {
        return startTime;
    }

    public BoatType getBoatType() {
        return boatType;
    }

    public Division getDivision() {
        return division;
    }

    public Gender getGender() {
        return gender;
    }

    public List<Crew> getCrewList() {
        return crewList;
    }

    public String getRaceID() {
        return raceID;
    }

    public static int getRaceCounter() {
        return raceCounter;
    }

    // Others

    public void addCrew(Crew crew) {
        this.crewList.add(crew);
    }

    public void removeCrew(Crew crew) {
            this.crewList.remove(crew);
    }

    @JsonIgnore
    public String getStartTimeString() {
        // TODO useless, remove
        return this.startTime.toString().substring(0, this.startTime.toString().length());
    }

    @Override
    public String toString() { // 10:40 | Nov M 4x
        return "[" + this.raceID + "] "
                + getStartTimeString() + " | "
                + this.division.toStringShortened() + " "
                + this.gender.toStringShortened() + " " + this.boatType.toString() + " | "
                + this.crewList.size() + " boats";
    }
}
