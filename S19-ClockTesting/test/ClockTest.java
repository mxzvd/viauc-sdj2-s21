import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClockTest {

    private Clock clock;

    @BeforeEach
    void setUp() {
        clock = new Clock(0, 0, 0);
    }

    private String valueOf(Clock clock) {
        return clock.getHour() + ":" + clock.getMinute() + ":" + clock.getSecond();
    }

    @Test
    void setZero() {
        clock.set(0, 0, 0);
        assertEquals("0:0:0", valueOf(clock));
    }

    @Test
    void setOne() {
        clock.set(0, 0, 1);
        assertEquals("0:0:1", valueOf(clock));
        clock.set(0, 1, 0);
        assertEquals("0:1:0", valueOf(clock));
        clock.set(1, 0, 0);
        assertEquals("1:0:0", valueOf(clock));
    }

    @Test
    void setMany() {
        clock.set(12, 15, 20);
        assertEquals("12:15:20", valueOf(clock));
        clock.set(7, 41, 9);
        assertEquals("7:41:9", valueOf(clock));
    }

    @Test
    void setBoundary() {
        assertThrows(IllegalArgumentException.class, () -> clock.set(0, 0, -1));
        assertThrows(IllegalArgumentException.class, () -> clock.set(0, -1, 0));
        assertThrows(IllegalArgumentException.class, () -> clock.set(-1, 0, 0));

        clock.set(0, 0, 59);
        assertEquals("0:0:59", valueOf(clock));
        clock.set(0, 59, 0);
        assertEquals("0:59:0", valueOf(clock));
        clock.set(23, 0, 0);
        assertEquals("23:0:0", valueOf(clock));

        assertThrows(IllegalArgumentException.class, () -> clock.set(0, 0, 60));
        assertThrows(IllegalArgumentException.class, () -> clock.set(0, 60, 0));
        assertThrows(IllegalArgumentException.class, () -> clock.set(24, 0, 0));
    }

    @Test
    void setException() {
        assertThrows(IllegalArgumentException.class, () -> clock.set(0, 0, -5));
        assertThrows(IllegalArgumentException.class, () -> clock.set(0, -5, 0));
        assertThrows(IllegalArgumentException.class, () -> clock.set(-5, 0, 0));

        assertThrows(IllegalArgumentException.class, () -> clock.set(0, 0, 71));
        assertThrows(IllegalArgumentException.class, () -> clock.set(0, 71, 0));
        assertThrows(IllegalArgumentException.class, () -> clock.set(41, 0, 0));
    }

    @Test
    public void setOneArgsZero() {
        clock.set(0);
        assertEquals("0:0:0", valueOf(clock));
    }

    @Test
    public void setOneArgsOne() {
        clock.set(1);
        assertEquals("0:0:1", valueOf(clock));
    }

    @Test
    public void setOneArgsMany() {
        clock.set(185);
        assertEquals("0:3:5", valueOf(clock));
        clock.set(86000);
        assertEquals("23:53:20", valueOf(clock));
        clock.set(86000 + 86400);
        assertEquals("23:53:20", valueOf(clock));
        clock.set(86000 + 7 * 86400);
        assertEquals("23:53:20", valueOf(clock));
    }

    @Test
    public void setOneArgsBoundary() {
        assertThrows(IllegalArgumentException.class, () -> clock.set(-1));

        clock.set(86399);
        assertEquals("23:59:59", valueOf(clock));

        clock.set(86400);
        assertEquals("0:0:0", valueOf(clock));
    }

    @Test
    public void setOneArgsException() {
        assertThrows(IllegalArgumentException.class, () -> clock.set(-26));

        assertThrows(IllegalArgumentException.class, () -> clock.set(-90000));
    }

    @Test
    public void getTimeInSecondsZero() {
        assertEquals(0, clock.getTimeInSeconds());
    }

    @Test
    public void getTimeInSecondsOne() {
        clock.set(0, 0, 1);
        assertEquals(1, clock.getTimeInSeconds());
    }

    @Test
    public void getTimeInSecondsMany() {
        clock.set(0, 3, 5);
        assertEquals(185, clock.getTimeInSeconds());
        clock.set(23, 53, 20);
        assertEquals(86000, clock.getTimeInSeconds());
    }

    @Test
    public void getTimeInSecondsBoundary() {
        clock.set(23, 59, 59);
        assertEquals(86399, clock.getTimeInSeconds());
    }

    @Test
    public void getTimeInSecondsException() {
        // No exceptions to test.
    }

    @Test
    public void ticZero() {
        // No need to check zero.
    }

    @Test
    public void ticOne() {
        clock.tic();
        assertEquals("0:0:1", valueOf(clock));
    }

    @Test
    public void ticMany() {
        for (int i = 0; i < 185; i++) clock.tic();
        assertEquals("0:3:5", valueOf(clock));

        clock.set(0, 0, 0);

        for (int i = 0; i < 86000; i++) clock.tic();
        assertEquals("23:53:20", valueOf(clock));

        clock.set(0, 0, 0);

        for (int i = 0; i < 86000 + 86400 * 7; i++) clock.tic();
        assertEquals("23:53:20", valueOf(clock));
    }

    @Test
    public void ticBoundary() {
        for (int i = 0; i < 59; i++) clock.tic();
        assertEquals("0:0:59", valueOf(clock));
        clock.tic();
        assertEquals("0:1:0", valueOf(clock));

        clock.set(0, 0, 0);

        for (int i = 0; i < 3599; i++) clock.tic();
        assertEquals("0:59:59", valueOf(clock));
        clock.tic();
        assertEquals("1:0:0", valueOf(clock));

        clock.set(0, 0, 0);

        for (int i = 0; i < 86399; i++) clock.tic();
        assertEquals("23:59:59", valueOf(clock));
        clock.tic();
        assertEquals("0:0:0", valueOf(clock));
    }

    @Test
    public void toStringZero() {
        assertEquals("00:00:00", clock.toString());
    }

    @Test
    public void toStringOne() {
        clock.set(0, 0, 1);
        assertEquals("00:00:01", clock.toString());
        clock.set(0, 1, 0);
        assertEquals("00:01:00", clock.toString());
        clock.set(1, 0, 0);
        assertEquals("01:00:00", clock.toString());
    }

    @Test
    public void toStringMany() {
        clock.set(0, 5, 3);
        assertEquals("00:05:03", clock.toString());
        clock.set(23, 52, 7);
        assertEquals("23:52:07", clock.toString());
    }

    @Test
    public void toStringBoundary() {
        clock.set(0, 0, 59);
        assertEquals("00:00:59", clock.toString());
        clock.set(0, 59, 0);
        assertEquals("00:59:00", clock.toString());
        clock.set(23, 0, 0);
        assertEquals("23:00:00", clock.toString());
        clock.set(23, 59, 59);
        assertEquals("23:59:59", clock.toString());

        clock.set(0, 0, 9);
        assertEquals("00:00:09", clock.toString());
        clock.set(0, 9, 0);
        assertEquals("00:09:00", clock.toString());
        clock.set(9, 0, 0);
        assertEquals("09:00:00", clock.toString());

        clock.set(0, 0, 10);
        assertEquals("00:00:10", clock.toString());
        clock.set(0, 10, 0);
        assertEquals("00:10:00", clock.toString());
        clock.set(10, 0, 0);
        assertEquals("10:00:00", clock.toString());
    }

    @Test
    public void toStringException() {
        // No exceptions to test.
    }

    @Test
    public void constructor3Zero() {
        clock = new Clock(0, 0, 0);
        assertEquals("0:0:0", valueOf(clock));
    }

    @Test
    public void constructor3One() {
        clock = new Clock(0, 0, 1);
        assertEquals("0:0:1", valueOf(clock));
        clock = new Clock(0, 1, 0);
        assertEquals("0:1:0", valueOf(clock));
        clock = new Clock(1, 0, 0);
        assertEquals("1:0:0", valueOf(clock));
    }

    @Test
    public void constructor3Many() {
        clock = new Clock(12, 15, 20);
        assertEquals("12:15:20", valueOf(clock));
        clock = new Clock(7, 41, 9);
        assertEquals("7:41:9", valueOf(clock));
    }

    @Test
    public void constructor3Boundary() {
        assertThrows(IllegalArgumentException.class, () -> clock = new Clock(0, 0, -1));
        assertThrows(IllegalArgumentException.class, () -> clock = new Clock(0, -1, 0));
        assertThrows(IllegalArgumentException.class, () -> clock = new Clock(-1, 0, 0));

        clock = new Clock(0, 0, 59);
        assertEquals("0:0:59", valueOf(clock));
        clock = new Clock(0, 59, 0);
        assertEquals("0:59:0", valueOf(clock));
        clock = new Clock(23, 0, 0);
        assertEquals("23:0:0", valueOf(clock));

        assertThrows(IllegalArgumentException.class, () -> clock = new Clock(0, 0, 60));
        assertThrows(IllegalArgumentException.class, () -> clock = new Clock(0, 60, 0));
        assertThrows(IllegalArgumentException.class, () -> clock = new Clock(24, 0, 0));
    }

    @Test
    public void constructor3Exception() {
        assertThrows(IllegalArgumentException.class, () -> clock = new Clock(0, 0, -5));
        assertThrows(IllegalArgumentException.class, () -> clock = new Clock(0, -5, 0));
        assertThrows(IllegalArgumentException.class, () -> clock = new Clock(-5, 0, 0));

        assertThrows(IllegalArgumentException.class, () -> clock = new Clock(0, 0, 71));
        assertThrows(IllegalArgumentException.class, () -> clock = new Clock(0, 71, 0));
        assertThrows(IllegalArgumentException.class, () -> clock = new Clock(41, 0, 0));
    }

    @Test
    public void constructor1Zero() {
        clock = new Clock(0);
        assertEquals("0:0:0", valueOf(clock));
    }

    @Test
    public void constructor1ArgsOne() {
        clock = new Clock(1);
        assertEquals("0:0:1", valueOf(clock));
    }

    @Test
    public void constructor1ArgsMany() {
        clock = new Clock(185);
        assertEquals("0:3:5", valueOf(clock));
        clock = new Clock(86000);
        assertEquals("23:53:20", valueOf(clock));
        clock = new Clock(86000 + 86400);
        assertEquals("23:53:20", valueOf(clock));
        clock = new Clock(86000 + 7 * 86400);
        assertEquals("23:53:20", valueOf(clock));
    }

    @Test
    public void constructor1ArgsBoundary() {
        assertThrows(IllegalArgumentException.class, () -> clock = new Clock(-1));

        clock = new Clock(86399);
        assertEquals("23:59:59", valueOf(clock));

        clock = new Clock(86400);
        assertEquals("0:0:0", valueOf(clock));
    }

    @Test
    public void constructor1ArgsException() {
        assertThrows(IllegalArgumentException.class, () -> clock = new Clock(-26));

        assertThrows(IllegalArgumentException.class, () -> clock = new Clock(-90000));
    }
}
