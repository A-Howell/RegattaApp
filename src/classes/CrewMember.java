package classes;

import java.time.LocalDate;
import java.util.ArrayList;

public class CrewMember extends Person {
    private ArrayList<Crew> crewsJoined;
    private Team team;
    private Boolean isCox;

    public CrewMember(String fName, String lName, String phoneNum, LocalDate birthday, Team team) {
        super(fName, lName, phoneNum, birthday);
        this.team = team;
        this.isCox = false;
    }

    // Setters

    public void setCrews(ArrayList<Crew> crews) {
        this.crewsJoined = crews;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setCox(Boolean cox) {
        isCox = cox;
    }

    // Getters

    public ArrayList<Crew> getCrews() {
        return crewsJoined;
    }

    public Team getTeam() {
        return team;
    }

    public Boolean getCox() {
        return isCox;
    }

    // Other

    public void joinCrew(Crew crew) {
        this.crewsJoined.add(crew);
    }

    public void removeCrew(Crew crew) {
        this.crewsJoined.remove(crew);
    }

    @Override
    public String toString() {
//        if (this.isCox) {
//            return team.getTeamName() + " | " + super.toString() + " | Cox";
//        } else {
            return team.getTeamName() + " | " + super.toString();
//        }
    }

}
