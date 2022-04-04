package classes;

import enums.Gender;

import java.time.LocalDate;
import java.util.ArrayList;

public class CrewMember extends Person {
    private Team team;
    private int crewMemberID;

    private static int crewMemberCount = 0;

    public CrewMember(String fName, String lName, String phoneNum, LocalDate birthday,
                      Gender gender, Team team) {
        super(fName, lName, phoneNum, birthday, gender);
        this.team = team;

        crewMemberCount++;
        this.crewMemberID = crewMemberCount;
    }

    // Setters

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setCrewMemberID(int crewMemberID) {
        this.crewMemberID = crewMemberID;
    }

    public static void setCrewMemberCount(int crewMemberCount) {
        CrewMember.crewMemberCount = crewMemberCount;
    }

    // Getters

    public Team getTeam() {
        return team;
    }

    public int getCrewMemberID() {
        return crewMemberID;
    }

    public static int getCrewMemberCount() {
        return crewMemberCount;
    }

    // Other

    @Override
    public String toString() {
            return team.getTeamName() + " | [" + this.crewMemberID + "] " + super.toString();
    }

}
