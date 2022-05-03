package classes;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Regatta {
    private String name;
    private LocalDate date;
    private String location;

    private int officialCount = 0;
    private List<Official> officials;
    private int teamCount = 0;
    private int crewMemberCount = 0;
    private int crewCount = 0;
    private List<Team> teams;
    private int raceCount = 0;

    private List<Race> races;
    private boolean saved;

    public Regatta() {
    }

    public Regatta(String name, LocalDate date, String location) {
        this.name = name;
        this.date = date;
        this.location = location;
        this.races = new ArrayList<>();
        this.officials = new ArrayList<>();
        this.teams = new ArrayList<>();
        this.saved = false;
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

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    public void setOfficialCount(int officialCount) {
        this.officialCount = officialCount;
    }

    public void setCrewMemberCount(int crewMemberCount) {
        this.crewMemberCount = crewMemberCount;
    }

    public void setTeamCount(int teamCount) {
        this.teamCount = teamCount;
    }

    public void setCrewCount(int crewCount) {
        this.crewCount = crewCount;
    }

    public void setRaceCount(int raceCount) {
        this.raceCount = raceCount;
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

    public int getOfficialCount() {
        return officialCount;
    }

    public int getCrewMemberCount() {
        return crewMemberCount;
    }

    public int getTeamCount() {
        return teamCount;
    }

    public int getCrewCount() {
        return crewCount;
    }

    public int getRaceCount() {
        return raceCount;
    }

    public Crew getCrewByID(String crewID) {
        for (Team team : this.teams) {
            for (Crew crew : team.getTeamCrews()) {
                if (crew.getCrewID().equals(crewID)) {
                    return crew;
                }
            }
        }
        return null;
    }

    public Team getTeamByID(String teamID) {
        for (Team team : this.teams) {
            if (team.getTeamID().equals(teamID)) {
                return team;
            }
        }
        return null;
    }

    public CrewMember getCrewMemberByID(String crewMemberID) {
        for (Team team : this.teams) {
            for (CrewMember crewMember : team.getTeamMembers()) {
                if (crewMember.getCrewMemberID().equals(crewMemberID)) {
                    return crewMember;
                }
            }
        }
        return null;
    }

    // Bool check

    public boolean isSaved() {
        return saved;
    }

    // Other
    public void addTeam(Team team) {
        for (Team tmpTeam : this.teams) {
            if (team.getTeamName().equals(tmpTeam.getTeamName())) {
                return;
            }
        }

        if(!this.teams.contains(team)) {
            this.teams.add(team);
        }
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

    public void saveCounters() {
        this.officialCount = this.officials.size();
        this.teamCount = this.teams.size();
        for (Team team : this.teams) {
            this.crewMemberCount += team.getTeamMembers().size();
            this.crewCount += team.getTeamCrews().size();
        }
        this.raceCount = this.races.size();
    }

    public void loadCounters() {
        Official.setOfficialCount(this.officialCount);
        Team.setTeamCount(this.teamCount);
        CrewMember.setCrewMemberCount(this.crewMemberCount);
        Crew.setCrewCounter(this.crewCount);
        Race.setRaceCounter(this.raceCount);
    }

    @Override
    public String toString() {
        return this.teams + "\n"
                + this.races + "\n";
    }

}
