package net.intelie.challenges;

import org.junit.Before;
import org.junit.Test;


import java.util.Arrays;

import static org.junit.Assert.*;

public class EventStoreTest {
    /*
        verifies EventStoreImpl class constructor and methods
     */

    private EventStore store;
    private Event event1;
    private Event event2;
    private Event event3;

    @Before
    public void setupEventStore() {
        store = new EventStoreImpl();
        event1 = new Event("a", System.currentTimeMillis() / 1000);
        event2 = new Event("a", System.currentTimeMillis() / 1000);
        event3 = new Event("b", System.currentTimeMillis() / 1000);

        store.insert(event1);
        store.insert(event2);
        store.insert(event3);
    }

    @Test
    public void testInsert() {
        EventStore expectedStore = new EventStoreImpl(Arrays.asList(event1, event2, event3));
        assertEquals(expectedStore, store);
    }

    @Test
    public void testRemoveAll() {
        store.removeAll("a");
        assertEquals(store, new EventStoreImpl(Arrays.asList(event3)));
    }

    @Test
    public void testQuery() {
        EventIterator expected = new EventIteratorImpl(Arrays.asList(event2));
        EventIterator result = store.query("a", event1.timestamp(), event3.timestamp());
        assertEquals(expected, result);
    }

}