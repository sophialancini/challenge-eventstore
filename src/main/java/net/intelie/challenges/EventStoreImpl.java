package net.intelie.challenges;

import java.util.ArrayList;
import java.util.List;

public class EventStoreImpl implements EventStore {
    /*
        this class implements the EventStore interface,
        storing a list of Event objects to persist inserted
        data and remove the deleted ones.
     */

    private List<Event> events;

    public EventStoreImpl() {
        this.events = new ArrayList<>();
    }

    public EventStoreImpl(List<Event> events) {
        this.events = events;
    }

    public List<Event> getEvents() {
        return events;
    }

    @Override
    public void insert(Event event) {
        events.add(event);
    }

    @Override
    public void removeAll(String type) {
        events.removeIf(e -> e.type() == type);
    }

    @Override
    public EventIterator query(String type, long startTime, long endTime) {
        EventIterator iterator = new EventIteratorImpl(events);
        while (iterator.moveNext()) {
            if (iterator.current().type() != type
                    || iterator.current().timestamp() < startTime
                    || iterator.current().timestamp() > endTime)
                iterator.remove();
        }
        return iterator;
    }

    public boolean equals(Object o) {
        if (o instanceof EventStore) {
            EventIterator thisIterator = new EventIteratorImpl(events);
            EventStoreImpl oEventStore = (EventStoreImpl) o;
            EventIterator oIterator = new EventIteratorImpl(oEventStore.getEvents());
            while (thisIterator.moveNext() && oIterator.moveNext()) {
                if (thisIterator.current().type() != oIterator.current().type() ||
                    thisIterator.current().timestamp() != oIterator.current().timestamp())
                    return false;
            }
            return true;
        }
        return false;
    }
}
