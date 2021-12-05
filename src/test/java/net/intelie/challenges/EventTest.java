package net.intelie.challenges;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EventTest {
    /*
        verifies Event class constructor and methods
     */

    @Test
    public void thisIsAWarning() throws Exception {
        Event event = new Event("some_type", 123L);

        //THIS IS A WARNING:
        //Some of us (not everyone) are coverage freaks.
        assertEquals(123L, event.timestamp());
        assertEquals("some_type", event.type());
    }

    @Test
    public void EventTest() {
        String type = "a";
        long timestamp =  System.currentTimeMillis() / 1000;
        Event event = new Event(type, timestamp);

        assertEquals(event.type(), type);
        assertEquals(event.timestamp(), timestamp);
    }
}