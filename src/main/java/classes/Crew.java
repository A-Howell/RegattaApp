package classes;

import enums.BoatType;
import enums.Gender;

import java.util.ArrayList;

public class Crew {

    private String crewID;
    private String teamID;
    private BoatType boatType;
    private Gender gender;
    private CrewMember cox;
    private ArrayList<String> crewMembersID;

    private static int crewCounter = 0;

    public Crew() {

    }

    public Crew(BoatType boatType) {
        this.boatType = boatType;
        this.crewMembersID = new ArrayList<>();
        this.cox = null;
        this.teamID = "";
        this.gender = Gender.OTHER;

        crewCounter++;
        this.crewID = "C" + crewCounter;
    }

    // Setters

    public void setCrewMembersID(ArrayList<String> crewMembersID) {
        this.crewMembersID = crewMembersID;
    }

    public void setBoatType(BoatType boatType) {
        this.boatType = boatType;
    }

    public void setCox(CrewMember cox) {
        this.cox = cox;
    }

    public void setTeamID(String teamID) {
        this.teamID = teamID;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setCrewID(String crewID) {
        this.crewID = crewID;
    }

    public static void setCrewCounter(int crewCounter) {
        Crew.crewCounter = crewCounter;
    }

    // Getters

    public ArrayList<String> getCrewMembersID() {
        return crewMembersID;
    }

    public BoatType getBoatType() {
        return boatType;
    }

    public CrewMember getCox() {
        return cox;
    }

    public String getTeamID() {
        return teamID;
    }

    public Gender getGender() {
        return gender;
    }

    public String getCrewID() {
        return crewID;
    }

    public static int getCrewCounter() {
        return crewCounter;
    }

    // Other

    public void addCrewMember(String crewMemberID) {
        this.crewMembersID.add(crewMemberID);
    }

    public void removeCrewMember(String crewMemberID) {
        if (crewMembersID.contains(crewMemberID)) {
            this.crewMembersID.remove(crewMemberID);
        } else {
            System.out.println("Rower don't exist bro");
        }

    }

    @Override
    public String toString() {
        String str = "[" + this.teamID + "] | [" + this.crewID + "] " + this.boatType.toString();
        if (this.cox != null) {
             str += " | Cox: " + this.cox.toString();
        }
        return str;
    }

}
