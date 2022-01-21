import java.util.ArrayList;

public class Team {
    private String teamName;
    private ArrayList<Crew> teamCrews;
    private ArrayList<CrewMember> teamMembers;

    public Team(String teamName) {
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

    // Other

    public void addTeamMember(CrewMember teamMember) {
        this.teamMembers.add(teamMember);
    }

    @Override
    public String toString() {
        return this.teamName;
    }
}
