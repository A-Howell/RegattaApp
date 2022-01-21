package classes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Regatta {
    private String name;
    private LocalDate date;
    private String location;
    private List<Race> races;
    private List<Official> officials;
    private List<CrewMember> crewMembers;
    private List<Team> teams;

    public Regatta(String name, LocalDate date, String location) {
        this.name = name;
        this.date = date;
        this.location = location;
        this.races = new ArrayList<Race>();
        this.officials = new ArrayList<Official>();
        this.crewMembers = new ArrayList<CrewMember>();
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

    public void setCrewMembers(List<CrewMember> crewMembers) {
        this.crewMembers = crewMembers;
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

    public List<CrewMember> getCrewMembers() {
        return crewMembers;
    }

    public List<Team> getTeams() {
        return teams;
    }

    // Other
    public void addTeam(Team team) {
        this.teams.add(team);
    }

    public List<Team> getTeamsWithMembers() {
        List<Team> teamsWithMembers = new ArrayList<Team>();
        for (Team team : this.teams) {
            if (!team.getTeamMembers().isEmpty()) {
                teamsWithMembers.add(team);
            }
        }

        return teamsWithMembers;
    }

    public void addCrewMember(CrewMember crewMember) {
        this.crewMembers.add(crewMember);
        crewMember.getTeam().addTeamMember(crewMember);
    }

    public void addOfficial(Official official) {
        this.officials.add(official);
    }

}
