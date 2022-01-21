import java.time.LocalDate;
import java.util.ArrayList;

public class CrewMember extends Person {
    private ArrayList<Crew> crewsJoined;
    private Team team;

    public CrewMember(String fName, String lName, String phoneNum, LocalDate birthday, Team team) {
        super(fName, lName, phoneNum, birthday);
        this.team = team;
        this.crewsJoined = new ArrayList<Crew>();
    }

    // Setters

    public void setCrews(ArrayList<Crew> crews) {
        this.crewsJoined = crews;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    // Getters

    public ArrayList<Crew> getCrews() {
        return crewsJoined;
    }

    public Team getTeam() {
        return team;
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
        return team.getTeamName() + " | " + super.toString();
    }

}
