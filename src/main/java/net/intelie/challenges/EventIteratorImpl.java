package net.intelie.challenges;
import java.util.List;

public class EventIteratorImpl implements EventIterator {
    /*
        this class implements the EventIterator interface
     */

    private List<Event> eventIterator;
    private Integer index;

    public EventIteratorImpl(List<Event> eventIterator) {
        this.eventIterator = eventIterator;
        this.index = -1;
    }


    @Override
    public boolean moveNext() {
        if (index < eventIterator.size()-1) {
            index+=1;
            return true;
        }
        return false;
    }

    @Override
    public Event current() {
        return eventIterator.get(index);
    }

    @Override
    public void remove() {
        eventIterator.remove(index);
    }

    @Override
    public void close() throws Exception {
    }

    public boolean equals(Object o) {
        if (o instanceof EventIteratorImpl) {
            EventIteratorImpl oIterator = (EventIteratorImpl) o;
            while (this.moveNext() && oIterator.moveNext()) {
                if (this.current().type() != oIterator.current().type() ||
                        this.current().timestamp() != oIterator.current().timestamp())
                    return false;
            }
            return true;
        }
        return false;
    }
}
