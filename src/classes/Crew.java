package classes;

import java.util.ArrayList;

public class Crew {
    private String crewName;
    private ArrayList<Race> racesEntered;
    private ArrayList<CrewMember> crewMembers;
    private int maxCrewMembers;
    private CrewMember cox;

    public Crew(String crewName, int maxCrewMembers) {
        this.crewName = crewName;
        this.maxCrewMembers = maxCrewMembers;
        this.racesEntered = new ArrayList<Race>();
        this.crewMembers = new ArrayList<CrewMember>();
        this.cox = null;
    }

    // Setters

    public void setCrewName(String crewName) {
        this.crewName = crewName;
    }

    public void setRacesEntered(ArrayList<Race> racesEntered) {
        this.racesEntered = racesEntered;
    }

    public void setCrewMembers(ArrayList<CrewMember> crewMembers) {
        this.crewMembers = crewMembers;
    }

    public void setMaxCrewMembers(int maxCrewMembers) {
        this.maxCrewMembers = maxCrewMembers;
    }

    public void setCox(CrewMember cox) {
        this.cox = cox;
    }

    // Getters

    public String getCrewName() {
        return crewName;
    }

    public ArrayList<Race> getRacesEntered() {
        return racesEntered;
    }

    public ArrayList<CrewMember> getCrewMembers() {
        return crewMembers;
    }

    public int getMaxCrewMembers() {
        return maxCrewMembers;
    }

    public CrewMember getCox() {
        return cox;
    }

    // Other

    public void enterRace(Race race) {
        this.racesEntered.add(race);
    }

    public void removeRace(Race race) {
        this.racesEntered.remove(race);
    }

    public void addCrewMember(CrewMember crewMember) {
        if (crewMembers.size() + 1 > maxCrewMembers) {
            // Throw error
        } else {
            this.crewMembers.add(crewMember);
        }

    }

    public void removeCrewMember(CrewMember crewMember) {
        if (crewMembers.size() - 1 < 0) {
            // Throw error
        } else {
            this.crewMembers.remove(crewMember);
        }
    }

}
