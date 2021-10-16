import org.junit.Test;

import static org.junit.Assert.*;

public class FlikTest {
    @Test
    public void testIsSameNumber() {
        assertTrue("128 is not same", Flik.isSameNumber(128, 128));
        assertTrue("200 is not same", Flik.isSameNumber(200, 200));
    }
}
