package classes;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Regatta {
    private String name;
    private LocalDate date;
    private String location;
    private List<Official> officials;
    private List<Team> teams;
    private List<Race> races;

    public Regatta(String name, LocalDate date, String location) {
        this.name = name;
        this.date = date;
        this.location = location;
        this.races = new ArrayList<Race>();
        this.officials = new ArrayList<Official>();
        this.teams = new ArrayList<Team>();
    }

    // Setters

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setRaces(List<Race> races) {
        this.races = races;
    }

    public void setOfficials(List<Official> officials) {
        this.officials = officials;
    }


    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    // Getters

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public List<Race> getRaces() {
        return races;
    }

    public List<Official> getOfficials() {
        return officials;
    }

    @JsonIgnore
    public List<CrewMember> getAllCrewMembers() {
        List<CrewMember> allCrewMembersList = new ArrayList<>();
        for (Team t : this.getTeams()) {
            allCrewMembersList.addAll(t.getTeamMembers());
        }
        return allCrewMembersList;
    }

    public List<Team> getTeams() {
        return teams;
    }

    // Other
    public void addTeam(Team team) {
        this.teams.add(team);
    }

    @JsonIgnore
    public List<Team> getTeamsWithMembers() {
        List<Team> teamsWithMembers = new ArrayList<Team>();
        for (Team team : this.teams) {
            if (!team.getTeamMembers().isEmpty()) {
                teamsWithMembers.add(team);
            }
        }

        return teamsWithMembers;
    }

    public void addCrewMemberToTeam(CrewMember crewMember) {
//        this.crewMembers.add(crewMember);
//        crewMember.getTeam().addTeamMember(crewMember);
        for (Team team : this.getTeams()) {
            if (team.getTeamID().equals(crewMember.getTeamID())) {
                team.addTeamMember(crewMember);
                break;
            }
        }
    }

    public void addOfficial(Official official) {
        this.officials.add(official);
    }

    public void addRace(Race race) {
        this.races.add(race);
    }

    @Override
    public String toString() {
        return this.teams + "\n"
                + this.races + "\n";
    }

}
