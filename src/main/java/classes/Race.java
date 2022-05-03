package classes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import enums.BoatType;
import enums.Division;
import enums.Gender;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Race {
    private String raceID;
    private LocalTime startTime;
    private boolean isFinished;
    private Division division;
    private BoatType boatType;
    private Gender gender;
    private List<String> crewList;
    private LocalTime actualRaceStartTime;
    private String winnerCrewID;
    private TreeMap<String, LocalTime> crewFinishTimes;


    private static int raceCounter = 0;


    public Race() {
    }

    public Race(LocalTime startTime, Gender gender, Division division, BoatType boatType) {
        this.startTime = startTime;
        this.gender = gender;
        this.division = division;
        this.boatType = boatType;

        this.isFinished = false;

        this.actualRaceStartTime = null;
        this.crewFinishTimes = new TreeMap<>();

        this.crewList = new ArrayList<>();
        this.winnerCrewID = "";
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

    public void setCrewList(List<String> crewList) {
        this.crewList = crewList;
    }

    public void setRaceID(String raceID) {
        this.raceID = raceID;
    }

    public static void setRaceCounter(int raceCounter) {
        Race.raceCounter = raceCounter;
    }

    public void setActualRaceStartTime(LocalTime actualRaceStartTime) {
        this.actualRaceStartTime = actualRaceStartTime;
    }

    public void setCrewFinishTimes(TreeMap<String, LocalTime> crewFinishTimes) {
        this.crewFinishTimes = crewFinishTimes;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public void setWinnerCrewID(String winnerCrewID) {
        this.winnerCrewID = winnerCrewID;
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

    public List<String> getCrewList() {
        return crewList;
    }

    public String getRaceID() {
        return raceID;
    }

    public static int getRaceCounter() {
        return raceCounter;
    }

    public LocalTime getActualRaceStartTime() {
        return actualRaceStartTime;
    }

    public TreeMap<String, LocalTime> getCrewFinishTimes() {
        return crewFinishTimes;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public String getWinnerCrewID() {
        return winnerCrewID;
    }

    // Others

    public void addCrew(Crew crew) {
        this.crewList.add(crew.getCrewID());
    }

    public void removeCrew(Crew crew) {
            this.crewList.remove(crew.getCrewID());
    }

    public void addFinishTime(String crewID, LocalTime finishTime) {
        this.crewFinishTimes.put(crewID, finishTime);
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

    public String toStringForPDF() {
        return getStartTimeString() + " | "
                + this.division.toString() + " "
                + this.gender.toString() + " " + this.boatType.toString() + " | "
                + this.crewList.size() + " boats";
    }
}
