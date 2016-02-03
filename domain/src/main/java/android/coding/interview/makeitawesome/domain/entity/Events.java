package android.coding.interview.makeitawesome.domain.entity;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by Liang on 2016/1/30.
 */
@Root
public class Events {
    @ElementList(inline=true)
    private List<Event> events;

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
