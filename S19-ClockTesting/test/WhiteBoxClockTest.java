import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WhiteBoxClockTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    public void testCase1() {
        Clock clock = new Clock(5, 5, 5);
        clock.tic();
        System.out.println(clock);
        assertEquals("05:05:06", clock.toString());
    }

    @Test
    public void testCase2() {
        Clock clock = new Clock(5, 5, 59);
        clock.tic();
        System.out.println(clock);
        assertEquals("05:06:00", clock.toString());
    }

    @Test
    public void testCase3() {
        Clock clock = new Clock(5, 59, 59);
        clock.tic();
        System.out.println(clock);
        assertEquals("06:00:00", clock.toString());
    }

    @Test
    public void testCase4() {
        Clock clock = new Clock(23, 59, 59);
        clock.tic();
        System.out.println(clock);
        assertEquals("00:00:00", clock.toString());
    }
}
