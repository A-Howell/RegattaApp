package classes;

import enums.BoatType;
import enums.Division;
import enums.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class OfficialTest {
    Official o;
    Race r1;

    @BeforeEach
    void setUp() {
        Official.setOfficialCount(0);
        o = new Official("Aydan", "Howell","07521108304", LocalDate.now(), Gender.MALE);
        r1 = new Race(LocalTime.now(), Gender.MALE, Division.MASTER, BoatType.SINGLE_SCULL);
    }

    @Test
    void addRaceTest() {
        o.addRace(r1);
        assertEquals(Arrays.asList(r1), o.getRacesOfficiating());
    }

    @Test
    void testToString() {
        assertEquals("Official | [O1] Aydan Howell", o.toString());
    }
}