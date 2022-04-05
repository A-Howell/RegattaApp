package classes;

import enums.Gender;

import java.time.LocalDate;

public class CrewMember extends Person {
    private String crewMemberID;
    private String teamID;


    private static int crewMemberCount = 0;

    public CrewMember(String fName, String lName, String phoneNum, LocalDate birthday,
                      Gender gender, String teamID) {
        super(fName, lName, phoneNum, birthday, gender);
        this.teamID = teamID;

        crewMemberCount++;
        this.crewMemberID = "R" + crewMemberCount;
    }

    // Setters

    public void setTeamID(String teamID) {
        this.teamID = teamID;
    }

    public void setCrewMemberID(String crewMemberID) {
        this.crewMemberID = crewMemberID;
    }

    public static void setCrewMemberCount(int crewMemberCount) {
        CrewMember.crewMemberCount = crewMemberCount;
    }

    // Getters

    public String getTeamID() {
        return teamID;
    }

    public String getCrewMemberID() {
        return crewMemberID;
    }

    public static int getCrewMemberCount() {
        return crewMemberCount;
    }

    // Other

    @Override
    public String toString() {
            return "[" + this.teamID + "] | [" + this.crewMemberID + "] " + super.toString();
    }

}
