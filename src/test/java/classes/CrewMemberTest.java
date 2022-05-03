package classes;

import enums.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CrewMemberTest {

    CrewMember cm1;

    @BeforeEach
    void setUp() {
        CrewMember.setCrewMemberCount(0);
        cm1 = new CrewMember("Aydan", "Howell", "07521108304", LocalDate.now(),
                Gender.MALE, "T1");
    }

    @Test
    void testToString() {
        assertEquals("[T1] | [R1] Aydan Howell", cm1.toString());
    }
}