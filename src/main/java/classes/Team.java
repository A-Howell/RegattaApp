package classes;

import java.util.ArrayList;

public class Team {
    private String teamID;
    private String teamName;
    private ArrayList<CrewMember> teamMembers;
    private ArrayList<Crew> teamCrews;



    private static int teamCount = 0;

    public Team(String teamName) {
        teamCount++;
        this.teamID = "T" + teamCount;

        this.teamName = teamName;
        this.teamCrews = new ArrayList<Crew>();
        this.teamMembers = new ArrayList<CrewMember>();
    }

    // Setters

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setTeamCrews(ArrayList<Crew> teamCrews) {
        this.teamCrews = teamCrews;
    }

    public void setTeamMembers(ArrayList<CrewMember> teamMembers) {
        this.teamMembers = teamMembers;
    }

    public void setTeamID(String teamID) {
        this.teamID = teamID;
    }

    public static void setTeamCount(int teamCount) {
        Team.teamCount = teamCount;
    }

    // Getters

    public String getTeamName() {
        return teamName;
    }

    public ArrayList<Crew> getTeamCrews() {
        return teamCrews;
    }

    public ArrayList<CrewMember> getTeamMembers() {
        return teamMembers;
    }

    public String getTeamID() {
        return teamID;
    }

    public static int getTeamCount() {
        return teamCount;
    }

    // Other

    public void addTeamMember(CrewMember teamMember) {
        this.teamMembers.add(teamMember);
    }

    public void addCrew(Crew crew) {
        this.teamCrews.add(crew);
    }

    @Override
    public String toString() {
        return "[" + this.teamID + "] " + this.teamName;
    }
}
