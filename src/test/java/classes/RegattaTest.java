package classes;

import enums.BoatType;
import enums.Division;
import enums.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RegattaTest {
    Regatta r;
    Team t1;
    Team t2;
    Team t3;
    CrewMember cm1;
    CrewMember cm2;
    Official o1;
    Crew c1;
    Crew c2;
    Race r1;

    @BeforeEach
    void setUp() {
        String name = "Worthing Regatta";
        LocalDate date = LocalDate.now();
        String location = "Worthing, West Sussex";
        r = new Regatta(name, date, location);
        Team.setTeamCount(0);
        CrewMember.setCrewMemberCount(0);
        Crew.setCrewCounter(0);
        Race.setRaceCounter(0);
        t1 = new Team("Worthing Rowing Club");
        t2 = new Team("Shoreham Rowing Club");
        t3 = new Team("Shoreham Rowing Club");

        cm1 = new CrewMember("Aydan", "Howell", "07521108304", LocalDate.now(),
                Gender.MALE, "T1");
        cm2 = new CrewMember("Max", "Browning", "07521108302", LocalDate.now(),
                Gender.MALE, "T2");

        o1 = new Official("Offical", "Person","07521108304", LocalDate.now(), Gender.MALE);

        c1 = new Crew(BoatType.SINGLE_SCULL);
        c2 = new Crew(BoatType.SINGLE_SCULL);

        r1 = new Race(LocalTime.now(), Gender.MALE, Division.MASTER, BoatType.SINGLE_SCULL);

    }

    @Test
    void addTeamTest() {
        r.addTeam(t1);
        r.addTeam(t2);
        assertEquals(2, r.getTeams().size());
    }

    @Test
    void addDuplicateTeamTest() {
        r.addTeam(t1);
        r.addTeam(t2);

        r.addTeam(t3);
        assertEquals(2, r.getTeams().size());
        System.out.println(r.getTeams().size());
    }

    @Test
    void addCrewMemberToTeamTest() {
        r.addTeam(t1);
        r.addTeam(t2);
        r.addTeam(t3);

        r.addCrewMemberToTeam(cm1);
        assertEquals(Arrays.asList(t1), r.getTeamsWithMembers());
        r.addCrewMemberToTeam(cm2);
        assertEquals(Arrays.asList(t1, t2), r.getTeamsWithMembers());
    }

    @Test
    void addOfficialTest() {
        r.addOfficial(o1);
        assertEquals(Arrays.asList(o1), r.getOfficials());
    }

    @Test
    void addRaceTest() {
        r.addTeam(t1);
        r.addTeam(t2);
        r.addTeam(t3);
        r.addCrewMemberToTeam(cm1);
        r.addCrewMemberToTeam(cm2);

        c1.addCrewMember(cm1.getCrewMemberID());
        c2.addCrewMember(cm2.getCrewMemberID());

        t1.addCrew(c1);
        t2.addCrew(c2);

        r1.addCrew(c1);
        r1.addCrew(c2);

        r.addRace(r1);

        assertEquals(Arrays.asList(r1), r.getRaces());
    }

    @Test
    void saveCountersTest() {
        r.addTeam(t1);
        r.addTeam(t2);
        r.addTeam(t3);
        r.addCrewMemberToTeam(cm1);
        r.addCrewMemberToTeam(cm2);

        c1.addCrewMember(cm1.getCrewMemberID());
        c2.addCrewMember(cm2.getCrewMemberID());

        t1.addCrew(c1);
        t2.addCrew(c2);

        r1.addCrew(c1);
        r1.addCrew(c2);

        r.addRace(r1);

        r.addOfficial(o1);

        r.saveCounters();

        assertEquals(2,r.getTeamCount());
        assertEquals(2,r.getCrewCount());
        assertEquals(2,r.getCrewMemberCount());
        assertEquals(1,r.getRaceCount());
        assertEquals(1,r.getOfficialCount());
    }

    @Test
    void loadCountersTest() {
        r.setTeamCount(5);
        r.setCrewCount(10);
        r.setCrewMemberCount(11);
        r.setRaceCount(59);
        r.setOfficialCount(0);

        r.loadCounters();

        Team t = new Team("Worthing Rowing Club");
        Crew c = new Crew(BoatType.SINGLE_SCULL);
        CrewMember cm = new CrewMember("Aydan", "Howell", "07521108304", LocalDate.now(),
                Gender.MALE, "T1");
        Race race = new Race(LocalTime.now(), Gender.MALE, Division.MASTER, BoatType.SINGLE_SCULL);
        Official o = new Official("Offical", "Person","07521108304", LocalDate.now(), Gender.MALE);

        assertEquals("T6", t.getTeamID());
        assertEquals("C11", c.getCrewID());
        assertEquals("R12", cm.getCrewMemberID());
        assertEquals("R60", race.getRaceID());
        assertEquals("O1", o.getOfficialID());
    }

    @Test
    void testToString() {
        r.addTeam(t1);
        r.addTeam(t2);
        r.addTeam(t3);
        r.addCrewMemberToTeam(cm1);
        r.addCrewMemberToTeam(cm2);

        c1.addCrewMember(cm1.getCrewMemberID());
        c2.addCrewMember(cm2.getCrewMemberID());

        t1.addCrew(c1);
        t2.addCrew(c2);

        r1.addCrew(c1);
        r1.addCrew(c2);

        r.addRace(r1);

        r.addOfficial(o1);

        assertEquals("[" + t1.toString() + ", " + t2.toString() + "]\n"
                + "[" + r1.toString() + "]\n", r.toString());
    }
}