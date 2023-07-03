package sp_example.repositories;

import java.util.ArrayList;
import java.util.List;

public class Event extends sp_example.models.Event {
    private String id;
    private String name;
    private List<User> members;

    public Event(String id, String name) {
        this.id = id;
        this.name = name;
        this.members = new ArrayList<>();
    }

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }
}
