package classes;

import enums.BoatType;
import enums.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CrewTest {

    CrewMember cm1;
    CrewMember cm2;
    Crew c1;

    @BeforeEach
    void setUp() {
        CrewMember.setCrewMemberCount(0);
        Crew.setCrewCounter(0);
        cm1 = new CrewMember("Aydan", "Howell", "07521108304", LocalDate.now(),
                Gender.MALE, "T1");
        cm2 = new CrewMember("Max", "Browning", "07521108302", LocalDate.now(),
                Gender.MALE, "T1");

        c1 = new Crew(BoatType.DOUBLE_SCULL);
        c1.setTeamID("T1");

    }

    @Test
    void addCrewMemberTest() {
        c1.addCrewMember(cm1.getCrewMemberID());
        c1.addCrewMember(cm2.getCrewMemberID());

        assertEquals(Arrays.asList("R1", "R2"), c1.getCrewMembersID());
    }

    @Test
    void removeCrewMemberTest() {
        c1.addCrewMember(cm1.getCrewMemberID());
        c1.addCrewMember(cm2.getCrewMemberID());

        assertEquals(Arrays.asList("R1", "R2"), c1.getCrewMembersID());

        c1.removeCrewMember(cm1.getCrewMemberID());
        assertEquals(Arrays.asList("R2"), c1.getCrewMembersID());

    }

    @Test
    void testToString() {
        assertEquals("[T1] | [C1] 2x", c1.toString());
    }
}