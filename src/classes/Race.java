package classes;

import enums.BoatType;
import enums.Division;
import enums.Gender;

import java.util.ArrayList;
import java.util.List;

public class Race {
    private int startTime;
    private Gender gender;
    private Division division;
    private BoatType boatType;

    private int maxNumOfCrews;
    private int numOfCrewsEntered;
    private List<Crew> crewList;

    public Race(int startTime, Gender gender, Division division, BoatType boatType) {
        this.startTime = startTime;
        this.gender = gender;
        this.division = division;
        this.boatType = boatType;

        this.maxNumOfCrews = boatType.getMaxCrewSize();
        this.numOfCrewsEntered = 0;
        this.crewList = new ArrayList<Crew>();
    }

    // Setters

    public void setStartTime(int startTime) {
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

    public void setMaxNumOfCrews(int maxNumOfCrews) {
        this.maxNumOfCrews = maxNumOfCrews;
    }

    public void setNumOfCrewsEntered(int numOfCrewsEntered) {
        this.numOfCrewsEntered = numOfCrewsEntered;
    }

    public void setCrewList(List<Crew> crewList) {
        this.crewList = crewList;
    }

    // Getters

    public int getStartTime() {
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

    public int getMaxNumOfCrews() {
        return maxNumOfCrews;
    }

    public int getNumOfCrewsEntered() {
        return numOfCrewsEntered;
    }

    public List<Crew> getCrewList() {
        return crewList;
    }

    // Others

    public void enterCrew(Crew crew) {
        if (numOfCrewsEntered + 1 > maxNumOfCrews) {
            // Throw error
        } else {
            this.crewList.add(crew);
        }
    }

    public void removeCrew(Crew crew) {
        if (numOfCrewsEntered - 1 < 0) {
            // Throw error
        } else {
            this.crewList.remove(crew);
        }
    }
}