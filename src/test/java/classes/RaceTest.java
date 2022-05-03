package classes;

import enums.BoatType;
import enums.Division;
import enums.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class RaceTest {
    Team t1;
    Team t2;
    Team t3;
    CrewMember cm1;
    CrewMember cm2;
    Crew c1;
    Crew c2;
    Race r1;
    LocalTime time;

    @BeforeEach
    void setUp() {
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

        c1 = new Crew(BoatType.SINGLE_SCULL);
        c2 = new Crew(BoatType.SINGLE_SCULL);
        time = LocalTime.now();
        r1 = new Race(time, Gender.MALE, Division.MASTER, BoatType.SINGLE_SCULL);
    }

    @Test
    void addCrewTest() {
        r1.addCrew(c1);
        r1.addCrew(c2);

        assertEquals(Arrays.asList("C1", "C2"), r1.getCrewList());
    }

    @Test
    void removeCrewTest() {
        r1.addCrew(c1);
        r1.addCrew(c2);
        assertEquals(Arrays.asList("C1", "C2"), r1.getCrewList());
        r1.removeCrew(c1);
        assertEquals(Arrays.asList("C2"), r1.getCrewList());

    }

    @Test
    void addFinishTimeTest() {
        r1.addCrew(c1);
        r1.addCrew(c2);
        r1.addFinishTime("C1", time);
        r1.addFinishTime("C2", time);

        TreeMap<String, LocalTime> treeMap = new TreeMap<>();
        treeMap.put("C1", time);
        treeMap.put("C2", time);

        assertEquals(treeMap, r1.getCrewFinishTimes());

    }

    @Test
    void testToString() {
        r1.addCrew(c1);
        r1.addCrew(c2);
        assertEquals("[R1] " + time + " | Mst M 1x | 2 boats", r1.toString());
    }

    @Test
    void toStringForPDFTest() {
        r1.addCrew(c1);
        r1.addCrew(c2);
        assertEquals(time + " | Master Male 1x | 2 boats", r1.toStringForPDF());
    }
}