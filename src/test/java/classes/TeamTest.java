package classes;

import enums.BoatType;
import enums.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TeamTest {
    Team t1;
    CrewMember cm1;
    Crew c1;

    @BeforeEach
    void setUp() {
        Team.setTeamCount(0);
        CrewMember.setCrewMemberCount(0);
        Crew.setCrewCounter(0);
        t1 = new Team("Worthing Rowing Club");
        cm1 = new CrewMember("Aydan", "Howell", "07521108304", LocalDate.now(),
                Gender.MALE, "T1");
        c1 = new Crew(BoatType.DOUBLE_SCULL);
        c1.setTeamID("T1");
        c1.addCrewMember(cm1.getCrewMemberID());
    }

    @Test
    void addTeamMemberTest() {
        t1.addTeamMember(cm1);
        assertEquals(Arrays.asList(cm1), t1.getTeamMembers());
    }

    @Test
    void addCrewTest() {
        t1.addCrew(c1);
        assertEquals(Arrays.asList(c1), t1.getTeamCrews());
    }

    @Test
    void testToString() {
        assertEquals("[T1] Worthing Rowing Club", t1.toString());
    }
}