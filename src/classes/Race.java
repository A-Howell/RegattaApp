package classes;

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

    private int numOfCrewsEntered;
    private List<Crew> crewList;

    public Race(LocalTime startTime, Gender gender, Division division, BoatType boatType) {
        this.startTime = startTime;
        this.gender = gender;
        this.division = division;
        this.boatType = boatType;

        this.numOfCrewsEntered = 0;
        this.crewList = new ArrayList<>();
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

    public void setNumOfCrewsEntered(int numOfCrewsEntered) {
        this.numOfCrewsEntered = numOfCrewsEntered;
    }

    public void setCrewList(List<Crew> crewList) {
        this.crewList = crewList;
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

    public int getNumOfCrewsEntered() {
        return numOfCrewsEntered;
    }

    public List<Crew> getCrewList() {
        return crewList;
    }

    // Others

    public void addCrew(Crew crew) {
        this.crewList.add(crew);
    }

    public void removeCrew(Crew crew) {
        try {
            this.crewList.remove(crew);
        } catch (Exception e) {
            System.out.println("Error: crew not in race.");
        }
    }
}
