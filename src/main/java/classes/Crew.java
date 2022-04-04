package classes;

import enums.BoatType;
import enums.Gender;

import java.util.ArrayList;

public class Crew {
    private ArrayList<CrewMember> crewMembers;
    private BoatType boatType;
    private Team team;
    private CrewMember cox;
    private Gender gender;
    private int crewID;

    private static int crewCounter = 0;


    public Crew(BoatType boatType) {
        this.boatType = boatType;
        this.crewMembers = new ArrayList<>();
        this.cox = null;
        this.team = null;
        this.gender = Gender.OTHER;

        crewCounter++;
        this.crewID = crewCounter;
    }

    // Setters

    public void setCrewMembers(ArrayList<CrewMember> crewMembers) {
        this.crewMembers = crewMembers;
    }

    public void setBoatType(BoatType boatType) {
        this.boatType = boatType;
    }

    public void setCox(CrewMember cox) {
        this.cox = cox;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setCrewID(int crewID) {
        this.crewID = crewID;
    }

    public static void setCrewCounter(int crewCounter) {
        Crew.crewCounter = crewCounter;
    }

    // Getters

    public ArrayList<CrewMember> getCrewMembers() {
        return crewMembers;
    }

    public BoatType getBoatType() {
        return boatType;
    }

    public CrewMember getCox() {
        return cox;
    }

    public Team getTeam() {
        return team;
    }

    public Gender getGender() {
        return gender;
    }

    public int getCrewID() {
        return crewID;
    }

    public static int getCrewCounter() {
        return crewCounter;
    }

    // Other

    public void addCrewMember(CrewMember crewMember) {
        this.crewMembers.add(crewMember);
    }

    public void removeCrewMember(CrewMember crewMember) {
        if (crewMembers.contains(crewMember)) {
            this.crewMembers.remove(crewMember);
        } else {
            System.out.println("Rower don't exist bro");
        }

    }

    @Override
    public String toString() {
        String str = this.team.toString() + " | [" + this.crewID + "] " + this.boatType.toString();
        if (this.cox != null) {
             str += " | Cox: " + this.cox.getfName() + " " + this.cox.getlName();
        }
        return str;
    }

}
